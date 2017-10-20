package coinone.tran.batch;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.OrderDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;
import coinone.tran.vo.BalanceVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.OrderVO;
import coinone.tran.vo.TranConfigVO;

public class TranLimitSellProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranLimitSellProcessor.class);

	@Inject
	private TranConfigDAO tranConfigDAO;
	@Inject
	private OrderDAO orderDao;

	@Override
	public String process(String item) throws Exception {
		this.tranCoin(Constants.COIN_XRP);
		Thread.sleep(1000);
		this.tranCoin(Constants.COIN_BCH);
		return item;
	}

	public String tranCoin(String sCurrency) throws Exception {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		OrderVO ordersBuyVO = new OrderVO();
		ordersBuyVO.setCurrency(sCurrency);
		ordersBuyVO.setType(Constants.TRAN_SELL);
		ordersBuyVO.setResult("N");
		List<OrderVO> listOrdersSellVo = orderDao.getOrderList(ordersBuyVO);

        TranConfigVO vo = new TranConfigVO();
        vo.setCurrency(sCurrency);
        vo.setTran_type(Constants.TRAN_SELL);
        TranConfigVO tranConfigBuyVO = tranConfigDAO.getTranConfig(vo);
        String sTranSellYn = "";
        if (null!= tranConfigBuyVO) {
			sTranSellYn = tranConfigBuyVO.getTran_yn();
		}
		BalanceVO balanceVO = null;

		balanceVO = api.getBalance(sCurrency);
		Double avail = null;
		if(Constants.COIN_XRP.equals(sCurrency))
		{
			avail = balanceVO.getXrp().getAvail();
		} else if(Constants.COIN_BCH.equals(sCurrency))
		{
			avail = balanceVO.getBch().getAvail();
		}

		boolean isSell = false;

		try {
			for (OrderVO sub : listOrdersSellVo) {
				if ("N".equals(sTranSellYn)) {
					continue;
				}

				if (isSell) {
					continue;
				}

				if (avail < (sub.getQty())) {
					sub.setQty(avail);
				}

					if ("N".equals(sub.getResult())) {
						OrderVO sellVO = new OrderVO();
						sellVO.setCurrency(sCurrency);
						sellVO.setQty(sub.getQty());
						sellVO.setPrice(sub.getPrice());
						OrderRetVO ret = api.ordersLimitSell(sellVO);
						if (null != ret) {
							OrderVO sellVOUpt = new OrderVO();
							sellVOUpt.setCurrency(sCurrency);
							sellVOUpt.setType(Constants.TRAN_SELL);
							sellVOUpt.setSeq(sub.getSeq());
							sellVOUpt.setOrderId(ret.getOrderId());
							sellVOUpt.setResult(ret.getResult());
							sellVOUpt.setErrorCode(ret.getErrorCode());
							orderDao.updateOrdersSell(sellVOUpt);
							sendMail.sendMail("CoinOne Sell Fail", sCurrency + "/" + " Sell " + "/" + " Result:"
									+ ret.getResult() + "/" + " ErrorCode:" + ret.getErrorCode());
						}

						if (Constants.SUCCESS.equals(ret.getResult())) {
							isSell = true;
							OrderVO buyVO = new OrderVO();
							buyVO.setCurrency(sCurrency);
							buyVO.setType(Constants.TRAN_BUY);
							buyVO.setSeq(sub.getSeq());
							buyVO.setResult("N");
							//orderDao.updateOrdersBuy(buyVO);
							sendMail.sendMail("CoinOne Sell", sCurrency + "/" + " Sell " + "/" + " Unit:"
									+ sub.getQty() + "/" + " Price:" + sub.getPrice());
						} else {
							sendMail.sendMail("CoinOne Sell Fail", sCurrency + "/" + " Sell " + "/" + " Result:"
									+ ret.getResult() + "/" + " ErrorCode:" + ret.getErrorCode());
							System.out.println("Buy Fail ==>" + ret.getStatus());
						}

					} else {
						logger.info("Tran is Not Setting");
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("tranCoin --> " + sCurrency);
		return "item";
	}
}