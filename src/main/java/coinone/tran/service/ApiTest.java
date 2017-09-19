package coinone.tran.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import coinone.tran.util.Constants;
import coinone.tran.vo.BalanceVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.TickerVO;

public class ApiTest {

	public static void main(String[] args) {

		// Set your API Key
		Map<String, String> apikey = new HashMap<>();
		apikey.put("access_token", "d611b917-f27f-4ef2-b5e7-5c64317fa05a");
		apikey.put("secret", "5abef40a-3c4d-4d07-ae60-7c13fb692f66");
		apikey.put("nonce", String.valueOf(new Date().getTime())); // if you get Exception, you should increase this
																	// value.

		CallAPIService comm = new CallAPIService(apikey);

		try {
			BalanceVO ethBal = comm.getBalance(Constants.COIN_ETH);
			// long totBal = comm.getCompleteBalance();
			TickerVO etcPrice = comm.getTickerAll();
			OrderRetVO orderBuyRetVO = comm.ordersLimitBuy(Constants.COIN_ETH, "100", "100");
			OrderRetVO orderSellRetVO = comm.ordersLimitSell(Constants.COIN_ETH, "100", "100");

			System.out.println("" + "I have " + ethBal.getEth().getBalance() + " ETH.\n" + "Result "
					+ orderBuyRetVO.getErrorMessage() + " \n" + "Result " + orderSellRetVO.getErrorMessage() + " \n"
					// + "I have " + totBal + " won value in Coinone.\n"
					+ "1 ETC price is " + etcPrice.getEth().getLast() + " won.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
