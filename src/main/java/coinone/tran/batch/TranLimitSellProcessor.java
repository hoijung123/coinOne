package coinone.tran.batch;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.OrdersBuyDAO;
import coinone.tran.dao.OrdersSellDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;
import coinone.tran.vo.BalanceVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.OrdersBuyVO;
import coinone.tran.vo.OrdersSellVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;

public class TranLimitSellProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranLimitSellProcessor.class);
	private static final int SLEEP_TIME = 2000;

	@Inject
	private TranConfigDAO tranConfigDAO;
	@Inject
	private OrdersSellDAO ordersSellDao;
	@Inject
	private OrdersBuyDAO ordersBuyDao;

	@Override
	public String process(String item) throws Exception {
		CallAPIService api = new CallAPIService();
//		if (api.getOrdersOpen(Constants.ETH_KRW).size() < 10) {
//			this.tranCoin(Constants.ETH_KRW);
//			Thread.sleep(SLEEP_TIME);
//		}
//		if (api.getOrdersOpen(Constants.ETC_KRW).size() < 10) {
//			this.tranCoin(Constants.ETC_KRW);
//			Thread.sleep(SLEEP_TIME);
//		}
		return item;
	}

	public String tranCoin(String sCurrency_pair) {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		OrdersSellVO ordersSellVO = new OrdersSellVO();
		ordersSellVO.setCurrency_pair(sCurrency_pair);
		List<OrdersSellVO> listOrdersSellVo = ordersSellDao.getOrdersSellList(ordersSellVO);

		TranConfigVO vo = new TranConfigVO();
		vo = new TranConfigVO();
		vo.setCurrency(sCurrency_pair);
		vo.setTran_type("S");
		TranConfigVO tranConfigSellVO = tranConfigDAO.getTranConfig(vo);
		String sTranSellYn = tranConfigSellVO.getTran_yn();

		OrdersBuyVO ordersBuyVO = new OrdersBuyVO();
		ordersBuyVO.setCurrency_pair(sCurrency_pair);
		List<OrdersBuyVO> listOrdersBuyVo = ordersBuyDao.getOrdersBuyList(ordersBuyVO);
		BalanceVO balanceVO = null;

		float currency_balance = 0;
//		try {
//			balanceVO = api.getBalances();
//			if (Constants.ETH_KRW.equals(sCurrency_pair)) {
//				currency_balance = api.getBalances().getEth().getAvailable();
//			} else if (Constants.ETC_KRW.equals(sCurrency_pair)) {
//				currency_balance = api.getBalances().getEtc().getAvailable();
//			}
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			TickerVO tickerVo = api.getTickerAll();
			logger.info(sCurrency_pair + " Ticker Price =============> " + tickerVo.getBch().getLast());

			boolean isSell = false;

			for (OrdersSellVO sub : listOrdersSellVo) {
				if ("N".equals(sTranSellYn)) {
					continue;
				}

				if (isSell) {
					continue;
				}

				if (currency_balance < (sub.getCoin_amount())) {
					sub.setCoin_amount(currency_balance);
				}

				float limit = 0;
				if (Constants.ETH_KRW.equals(sCurrency_pair)) {
					limit = (float) 0.01;
				} else if (Constants.ETC_KRW.equals(sCurrency_pair)) {
					limit = (float) 0.1;
				}
				if (currency_balance >= limit)
					if (!Constants.SUCCESS.equals(sub.getStatus())) {
						OrdersSellVO sellVO = new OrdersSellVO();
						sellVO.setCurrency_pair(sCurrency_pair);
						sellVO.setCoin_amount(sub.getCoin_amount());
						sellVO.setPrice(sub.getPrice());
						OrderRetVO ret = null;//api.ordersSellLimit(sellVO);
						if (null != ret) {
							OrdersSellVO sellVOUpt = new OrdersSellVO();
							sellVOUpt.setCurrency_pair(sCurrency_pair);
							sellVOUpt.setSell_seq(sub.getSell_seq());
							sellVOUpt.setOrder_id(ret.getOrderId());
							sellVOUpt.setStatus(ret.getStatus());
							ordersSellDao.updateOrdersSell(sellVOUpt);
						}

						if (Constants.SUCCESS.equals(ret.getStatus())) {
							isSell = true;
							OrdersBuyVO buyVO = new OrdersBuyVO();
							buyVO.setCurrency_pair(sCurrency_pair);
							buyVO.setBuy_seq(sub.getSell_seq());
							buyVO.setStatus("N");
							ordersBuyDao.updateOrdersBuy(buyVO);
							sendMail.sendMail("Korbit Sell", sCurrency_pair + "/" + " Sell " + "/" + " Unit:"
									+ sub.getCoin_amount() + "/" + " Price:" + sub.getPrice());
						} else {
							System.out.println("Buy Fail ==>" + ret.getStatus());
						}

					} else {
						logger.info("Tran is Not Setting");
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("tranCoin --> " + sCurrency_pair);
		return "item";
	}
}