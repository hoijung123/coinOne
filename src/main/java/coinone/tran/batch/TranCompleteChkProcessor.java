package coinone.tran.batch;

import java.util.List;

import javax.inject.Inject;

import coinone.tran.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.OrderDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;

public class TranCompleteChkProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranCompleteChkProcessor.class);
	private static final int SLEEP_TIME = 1000;

	@Inject
	private TranConfigDAO tranConfigDAO;
	@Inject
	private OrderDAO orderDao;

	@Override
	public String process(String item) throws Exception {
		this.tranCoin(Constants.COIN_XRP);
		Thread.sleep(500);
		this.tranCoin(Constants.COIN_BCH);
		return item;
	}

	public String tranCoin(String sCurrency) throws Exception {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		OrderVO orderVO = new OrderVO();
		orderVO.setCurrency(sCurrency);
		orderVO.setType(Constants.TRAN_SELL);
		orderVO.setResult(Constants.SUCCESS);
		List<OrderVO> ordersSellList = orderDao.getOrderList(orderVO);

		TranConfigVO vo = new TranConfigVO();
		vo = new TranConfigVO();
		vo.setCurrency(sCurrency);
		vo.setTran_type(Constants.TRAN_SELL);
		TranConfigVO tranConfigSellVO = tranConfigDAO.getTranConfig(vo);
		String sTranSellYn = tranConfigSellVO.getTran_yn();

		vo.setTran_type(Constants.TRAN_BUY);
		TranConfigVO tranConfigBuyVO = tranConfigDAO.getTranConfig(vo);
		String sTranBuyYn = tranConfigSellVO.getTran_yn();

		OrderVO ordersBuyVO = new OrderVO();
		ordersBuyVO.setType(Constants.TRAN_BUY);
		ordersBuyVO.setCurrency(sCurrency);
		ordersBuyVO.setResult(Constants.SUCCESS);
		List<OrderVO> ordersBuyList = orderDao.getOrderList(ordersBuyVO);
		BalanceVO balanceVO = null;

		List<OrderVO> openOrderList = api.getLimitOrders(sCurrency).getLimitOrders();

		boolean find = false;

		for (OrderVO sub : ordersSellList) {
			if ("N".equals(sTranSellYn)) {
				continue;
			}
			find = false;
			for (OrderVO s : openOrderList) {
				if (s.getOrderId().equals(sub.getOrderId())) {
					find = true;
				}
			}

			if (!find)
			{
				OrderVO buyVO = new OrderVO();
				buyVO.setCurrency(sub.getCurrency());
				buyVO.setOrderId(sub.getOrderId());
				buyVO.setType(Constants.TRAN_BUY);
				buyVO.setSeq(sub.getSeq());
				buyVO.setResult("N");
				orderDao.updateOrder(buyVO);

				OrderVO sellVO = new OrderVO();
				sellVO.setCurrency(sub.getCurrency());
				sellVO.setOrderId(sub.getOrderId());
				sellVO.setType(Constants.TRAN_SELL);
				sellVO.setSeq(sub.getSeq());
				sellVO.setResult("Y");
				orderDao.updateOrder(sellVO);

				sendMail.sendMail("CoinOne Sell Complete", sCurrency + "/" + " Sell " + "/" + " Unit:"
						+ sub.getQty() + "/" + " Price:" + sub.getPrice());
			}
		}
		

		for (OrderVO sub : ordersBuyList) {
			if ("N".equals(sTranBuyYn)) {
				continue;
			}
			find = false;
			for (OrderVO s : openOrderList) {
				if (s.getOrderId().equals(sub.getOrderId())) {
					find = true;
				}
			}

			if (!find)
			{
				OrderVO sellVO = new OrderVO();
				sellVO.setCurrency(sub.getCurrency());
				sellVO.setOrderId(sub.getOrderId());
				sellVO.setType(Constants.TRAN_SELL);
				sellVO.setSeq(sub.getSeq());
				sellVO.setResult("N");
				orderDao.updateOrder(sellVO);

				OrderVO buyVO = new OrderVO();
				buyVO.setCurrency(sub.getCurrency());
				buyVO.setOrderId(sub.getOrderId());
				buyVO.setType(Constants.TRAN_BUY);
				buyVO.setSeq(sub.getSeq());
				buyVO.setResult("Y");
				orderDao.updateOrder(buyVO);
//
				sendMail.sendMail("CoinOne Buy Complete", sCurrency + "/" + " Buy " + "/" + " Unit:"
						+ sub.getQty() + "/" + " Price:" + sub.getPrice());
			}
		}

		// logger.info("tranCoin --> " + sCurrency_pair);
		return "item";
	}
}