package coinone.tran.batch;

import java.util.List;

import javax.inject.Inject;

import org.apache.http.NameValuePair;
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
import coinone.tran.vo.OpenOrderVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.OrdersBuyVO;
import coinone.tran.vo.OrdersSellVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;

public class TranCompleteChkProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranCompleteChkProcessor.class);
	private static final int SLEEP_TIME = 1000;

	@Inject
	private TranConfigDAO tranConfigDAO;
	@Inject
	private OrdersSellDAO ordersSellDao;
	@Inject
	private OrdersBuyDAO ordersBuyDao;

	@Override
	public String process(String item) throws Exception {
		this.tranCoin(Constants.ETH_KRW);
		Thread.sleep(SLEEP_TIME);
		this.tranCoin(Constants.ETC_KRW);
		return item;
	}

	public String tranCoin(String sCurrency_pair) {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		OrdersSellVO ordersSellVO = new OrdersSellVO();
		ordersSellVO.setCurrency_pair(sCurrency_pair);
		List<OrdersSellVO> ordersSellList = ordersSellDao.getOrdersSellList(ordersSellVO);

		TranConfigVO vo = new TranConfigVO();
		vo = new TranConfigVO();
		vo.setCurrency(sCurrency_pair);
		vo.setTran_type("S");
		TranConfigVO tranConfigSellVO = tranConfigDAO.getTranConfig(vo);
		String sTranSellYn = tranConfigSellVO.getTran_yn();

		OrdersBuyVO ordersBuyVO = new OrdersBuyVO();
		ordersBuyVO.setCurrency_pair(sCurrency_pair);
		List<OrdersBuyVO> ordersBuyList = ordersBuyDao.getOrdersBuyList(ordersBuyVO);
		BalanceVO balanceVO = null;

		List<OpenOrderVO> openOrderList = null;
//		try {
//			//openOrderList = api.getOrdersOpen(sCurrency_pair);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		boolean find = false;
		//판매완료 Check
		for (OrdersSellVO sub : ordersSellList) {
			find = false;
//			for (String s : openOrderList) {
//				if (s.equals(sub.getOrderId())) {
//					find = true;
//				}
//			}
			//해당판매와 순위가 같은 구매 N 로 update
			if (!find)
			{
				OrdersBuyVO buyVO = new OrdersBuyVO();
				buyVO.setCurrency_pair(sub.getCurrency_pair());
				buyVO.setBuy_seq(sub.getSell_seq());
				buyVO.setStatus("N");
				ordersBuyDao.updateOrdersBuy(buyVO);
				
				sendMail.sendMail("Korbit Sell Complete", sCurrency_pair + "/" + " Sell " + "/" + " Unit:"
						+ sub.getCoin_amount() + "/" + " Price:" + sub.getPrice());
			}
		}
		
		//구매완료 Check
		for (OrdersBuyVO sub : ordersBuyList) {
			find = false;
//			for (String s : openOrderList) {
//				if (s.equals(sub.getOrderId())) {
//					find = true;
//				}
//			}
			//해당구매와 순위가 같은 판매 N 로 update
			if (!find)
			{
				OrdersSellVO sellVO = new OrdersSellVO();
				sellVO.setCurrency_pair(sub.getCurrency_pair());
				sellVO.setSell_seq(sub.getBuy_seq());
				sellVO.setStatus("N");
				ordersSellDao.updateOrdersSell(sellVO);
				
				sendMail.sendMail("Korbit Buy Complete", sCurrency_pair + "/" + " Buy " + "/" + " Unit:"
						+ sub.getCoin_amount() + "/" + " Price:" + sub.getPrice());
			}
		}

		// logger.info("tranCoin --> " + sCurrency_pair);
		return "item";
	}
}