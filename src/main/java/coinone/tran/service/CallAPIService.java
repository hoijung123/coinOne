package coinone.tran.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coinone.tran.util.Constants;
import coinone.tran.vo.BalanceVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.TickerDtlVO;
import coinone.tran.vo.TickerVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CallAPIService {

	private static final Logger logger = LoggerFactory.getLogger(CallAPIService.class);
	
	private Map<String, String> apikey;

	public CallAPIService() {
	}
	
	public CallAPIService(Map<String, String> apikey) {
		this.apikey = apikey;
	}

	public TickerVO getTickerAll() throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = HTTPUtil.getJSONfromGet(Constants.API_URL + Constants.TICKER_URL);

		TickerVO tickerVO = null;
		try {
			tickerVO = mapper.readValue(json, TickerVO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tickerVO;
	}
	/* 최종 체결 가격 */
	public TickerDtlVO getTicker(String coin) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String json = HTTPUtil.getJSONfromGet(Constants.API_URL + Constants.TICKER_URL + coin);

		TickerDtlVO tickerDtlVO = null;
		try {
			tickerDtlVO = mapper.readValue(json, TickerDtlVO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tickerDtlVO;
	}

	/* 지갑조회 */
	/*
	 * HTTP/1.1 200 OK { "errorCode":"0", "normalWallets": [ { "balance":"6.1151",
	 * "label":"First Wallet" }, { "balance":"6.9448", "label":"Second Wallet" } ],
	 * "result":"success", "btc": { "avail":"344.33703699", "balance":"344.33703699"
	 * }, "bch": { "avail":"1.00001234", "balance":"1.00001234" }, "eth": {
	 * "avail":"1.00001234", "balance":"1.00001234" }, "etc": {
	 * "avail":"300.32123699", "balance":"300.32123699" }, "xrp": {
	 * "avail":"434.50890000" "balance":"434.85450000" }, "qtum": {
	 * "avail":"434.50890000" "balance":"434.85450000" }, "krw": {
	 * "avail":"6901425", "balance":"6901430" } }
	 */
	public BalanceVO getBalance(String coin) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		long nonce = Long.valueOf(apikey.get("nonce")) + 1;
		apikey.put("nonce", String.valueOf(nonce));

		String url = Constants.API_URL + "v2/account/balance/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);

		String payload = Base64.encodeBase64String(params.toString().getBytes());
		String signature = Encryptor.getHmacSha512(secret.toUpperCase(), payload).toLowerCase();

		Map<String, String> map = new HashMap<>();
		map.put("content-type", "application/json");
		map.put("accept", "application/json");
		map.put("X-COINONE-PAYLOAD", payload);
		map.put("X-COINONE-SIGNATURE", signature);

		String json = HTTPUtil.getJSONfromPost(url, map, payload);
		// System.out.println(result);
		// String strBalance = (String) ((JSONObject) result.get(coin)).get("avail");

		ObjectMapper mapper = new ObjectMapper();
		BalanceVO balanceVO = null;
		try {
			balanceVO = mapper.readValue(json, BalanceVO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return balanceVO;
	}
	//
	// public long getCompleteBalance() throws Exception {
	// return (long) (getTicker(Constants.COIN_BTC) *
	// getBalance(Constants.COIN_BTC))
	// + (long) (getTicker(Constants.COIN_ETH) * getBalance(Constants.COIN_ETH))
	// + (long) (getTicker(Constants.COIN_ETC) * getBalance(Constants.COIN_ETC))
	// + (long) getBalance(Constants.COIN_KRW);
	// }
/*
	"access_token": ACCESS_TOKEN,
	  "price": 500000,
	  "qty": 0.1,
	  "currency": "btc",
	  "nonce": Date.now()
*/	  
	/*주문-매수*/
	public OrderRetVO ordersLimitBuy(String currency, String qty, String price) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		long nonce = Long.valueOf(apikey.get("nonce")) + 1;
		apikey.put("nonce", String.valueOf(nonce));

		String url = Constants.API_URL + "v2/order/limit_buy/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("currency", currency);
		params.put("price", price);
		params.put("qty", qty);

		String payload = Base64.encodeBase64String(params.toString().getBytes());
		String signature = Encryptor.getHmacSha512(secret.toUpperCase(), payload).toLowerCase();

		Map<String, String> map = new HashMap<>();
		map.put("content-type", "application/json");
		map.put("accept", "application/json");
		map.put("X-COINONE-PAYLOAD", payload);
		map.put("X-COINONE-SIGNATURE", signature);

		String json = HTTPUtil.getJSONfromPost(url, map, payload);
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		OrderRetVO orderRetVO = null;
		try {
			orderRetVO = mapper.readValue(json, OrderRetVO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderRetVO;
	}
	
	/*주문-매수*/
	public OrderRetVO ordersLimitSell(String currency, String qty, String price) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		long nonce = Long.valueOf(apikey.get("nonce")) + 1;
		apikey.put("nonce", String.valueOf(nonce));

		String url = Constants.API_URL + "v2/order/limit_sell/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("currency", currency);
		params.put("price", price);
		params.put("qty", qty);

		String payload = Base64.encodeBase64String(params.toString().getBytes());
		String signature = Encryptor.getHmacSha512(secret.toUpperCase(), payload).toLowerCase();

		Map<String, String> map = new HashMap<>();
		map.put("content-type", "application/json");
		map.put("accept", "application/json");
		map.put("X-COINONE-PAYLOAD", payload);
		map.put("X-COINONE-SIGNATURE", signature);

		String json = HTTPUtil.getJSONfromPost(url, map, payload);
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		OrderRetVO orderRetVO = null;
		try {
			orderRetVO = mapper.readValue(json, OrderRetVO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderRetVO;
	}	
}
