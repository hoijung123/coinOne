package coinone.tran.service;

import coinone.tran.util.Constants;
import coinone.tran.vo.*;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CallAPIService {

	private static final Logger logger = LoggerFactory.getLogger(CallAPIService.class);

	private Map<String, String> apikey;

	public CallAPIService() {
		Map<String, String> apikey = new HashMap<>();
		apikey.put("access_token", Constants.ACCESS_TOKEN);
		apikey.put("secret", Constants.SECRET);
		apikey.put("nonce", String.valueOf(new Date().getTime())); ////
		this.apikey = apikey;
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
	/* ���� ü�� ���� */
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

	/* ������ȸ */
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
		String nonce = String.valueOf(new Date().getTime());
		apikey.put("nonce", nonce);

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
		 System.out.println(json);
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

	public LimitOrderVO getLimitOrders(String coin) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		String nonce = String.valueOf(new Date().getTime());
		apikey.put("nonce", nonce);

		String url = Constants.API_URL + "v2/order/limit_orders/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("currency", coin);

		String payload = Base64.encodeBase64String(params.toString().getBytes());
		String signature = Encryptor.getHmacSha512(secret.toUpperCase(), payload).toLowerCase();

		Map<String, String> map = new HashMap<>();
		map.put("content-type", "application/json");
		map.put("accept", "application/json");
		map.put("X-COINONE-PAYLOAD", payload);
		map.put("X-COINONE-SIGNATURE", signature);

		String json = HTTPUtil.getJSONfromPost(url, map, payload);
		System.out.println(json);
		// String strBalance = (String) ((JSONObject) result.get(coin)).get("avail");

		ObjectMapper mapper = new ObjectMapper();
		LimitOrderVO vo = null;
		try {
			vo = mapper.readValue(json, LimitOrderVO.class);
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

		return vo;
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
	public OrderRetVO ordersLimitBuy(OrderVO vo) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		String nonce = String.valueOf(new Date().getTime());
		apikey.put("nonce", nonce);

		String url = Constants.API_URL + "v2/order/limit_buy/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("currency", vo.getCurrency());
		params.put("price", vo.getPrice());
		params.put("qty", vo.getQty());

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
	
	/*�ֹ�-�ż�*/
	public OrderRetVO ordersLimitSell(OrderVO vo) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		String nonce = String.valueOf(new Date().getTime());
		apikey.put("nonce", nonce);

		String url = Constants.API_URL + "v2/order/limit_sell/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("currency", vo.getCurrency());
		params.put("price", vo.getPrice());
		params.put("qty", vo.getQty());

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

	public BaseVO cancelOrder(OrderVO orderVO) throws Exception {
		String accessToken = apikey.get("access_token");
		String secret = apikey.get("secret");
		String nonce = String.valueOf(new Date().getTime());
		apikey.put("nonce", nonce);

		String url = Constants.API_URL + "v2/order/cancel/";

		JSONObject params = new JSONObject();
		params.put("nonce", nonce);
		params.put("access_token", accessToken);
		params.put("order_id", orderVO.getOrderId());
		params.put("currency", orderVO.getCurrency());
		params.put("price", orderVO.getPrice());
		params.put("qty", orderVO.getQty());
		String isAsk = "1";
		if ("bid".equals(orderVO.getType()))
		{
			isAsk = "0";
		}
		params.put("is_ask", isAsk);

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
		BaseVO orderRetVO = null;
		try {
			orderRetVO = mapper.readValue(json, BaseVO.class);
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
