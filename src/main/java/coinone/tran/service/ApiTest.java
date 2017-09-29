package coinone.tran.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import coinone.tran.util.Constants;
import coinone.tran.vo.*;

public class ApiTest {

	public static void main(String[] args) throws Exception {

		// Set your API Key
		Map<String, String> apikey = new HashMap<>();
		apikey.put("access_token", "d611b917-f27f-4ef2-b5e7-5c64317fa05a");
		apikey.put("secret", "5abef40a-3c4d-4d07-ae60-7c13fb692f66");
		apikey.put("nonce", String.valueOf(new Date().getTime())); // if you get Exception, you should increase this
																	// value.

		CallAPIService comm = new CallAPIService(apikey);


			BalanceVO ethBal = comm.getBalance(Constants.COIN_ETH);
			BalanceVO krwBal  = comm.getBalance(Constants.COIN_KRW);
			TickerVO etcPrice = comm.getTickerAll();
//			for (int i=0; i<10; i++) {
//				//Thread.sleep(500);
//				//OrderRetVO orderBuyRetVO = comm.ordersLimitBuy(Constants.COIN_XRP, "1", "100");
//			}

			LimitOrderVO limitOrders = comm.getLimitOrders("xrp");
			limitOrders.getLimitOrders().forEach((k)->
					subFun(k)
			);
			//OrderRetVO orderSellRetVO = comm.ordersLimitSell(Constants.COIN_ETH, "100", "100");

//			System.out.println("" + "I have " + ethBal.getEth().getBalance() + " ETH.\n" + "Result "
//					+ orderBuyRetVO.getErrorMessage() + " \n" + "Result " + orderSellRetVO.getErrorMessage() + " \n"
//					// + "I have " + totBal + " won value in Coinone.\n"
//					+ "1 ETC price is " + etcPrice.getEth().getLast() + " won.\n");

	}

	public static void subFun(OrderVO k)
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(k.getIndex() + " / " +k.getDateTime()+ " / " +k.getPrice() + " / " +k.getQty() + " / " +k.getOrderId() + " / " +k.getType() + " / " +k.getFeeRate());

		Map<String, String> apikey = new HashMap<>();
		apikey.put("access_token", "d611b917-f27f-4ef2-b5e7-5c64317fa05a");
		apikey.put("secret", "5abef40a-3c4d-4d07-ae60-7c13fb692f66");
		apikey.put("nonce", String.valueOf(new Date().getTime())); // if you get Exception, you should increase this
		// value.

		CallAPIService comm = new CallAPIService(apikey);
		OrderVO orderVO = new OrderVO();
		orderVO.setCurrency(Constants.COIN_XRP);
		orderVO.setOrderId(k.getOrderId());
		orderVO.setQty(k.getQty());
		orderVO.setPrice(k.getPrice());
		orderVO.setType(k.getType());
		try {
			comm.cancelOrder(orderVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
