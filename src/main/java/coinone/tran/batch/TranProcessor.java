package coinone.tran.batch;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;

public class TranProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranProcessor.class);
	private static final int SLEEP_TIME = 2000;

	@Inject
	private TranConfigDAO tranConfigDAO;

	@Override
	public String process(String item) throws Exception {
		this.tranCoin(Constants.ETH_KRW);
		Thread.sleep(SLEEP_TIME);
		this.tranCoin(Constants.ETC_KRW);
		return item;
	}

	public String tranCoin(String sCurrency) {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		TranConfigVO vo = new TranConfigVO();
		vo.setCurrency(sCurrency);
		vo.setTran_type("B");
		TranConfigVO tranConfigBuyVO = tranConfigDAO.getTranConfig(vo);

		vo = new TranConfigVO();
		vo.setCurrency(sCurrency);
		vo.setTran_type("S");
		TranConfigVO tranConfigSellVO = tranConfigDAO.getTranConfig(vo);

		Float lBuyPrice = tranConfigBuyVO.getTarget_price();
		String sTranBuyYn = tranConfigBuyVO.getTran_yn();
		Float lBuyUnits = tranConfigBuyVO.getUnits();
		String sBuyStatus = tranConfigBuyVO.getStatus();

		Float lSellPrice = tranConfigSellVO.getTarget_price();
		Float lSellUnits = tranConfigSellVO.getUnits();
		String sTranSellYn = tranConfigSellVO.getTran_yn();
		String sSellStatus = tranConfigSellVO.getStatus();

		try {
			TickerVO tickerVo = api.getTickerAll();

			System.out.println(sCurrency + " Ticker Price =============> " + tickerVo.getBch().getLast());

			if ("Y".equals(sTranBuyYn)) {
				if (tickerVo.getBch().getLast() <= lBuyPrice) {
					if ("N".equals(sBuyStatus)) {
						float krw_balance = 0;//api.getBalances().getKrw().getAvailable();
						logger.debug("======> krw_balance : " + krw_balance);
						OrderRetVO ret = null ;//api.ordersBuyLimit(sCurrency, lBuyUnits.toString(), lBuyPrice.toString());
						if (Constants.SUCCESS != ret.getStatus()) {
							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("Y");
							vo.setTran_type("B");
							vo.setTran_yn("Y");
							tranConfigDAO.updateTranConfig(vo);

							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("N");
							vo.setTran_type("S");
							vo.setTran_yn("Y");
							tranConfigDAO.updateTranConfig(vo);
							sendMail.sendMail("Korbit Buy", sCurrency + "/" + " Buy " + "/" + " Unit:" + lBuyUnits + "/"
									+ " Price:" + tickerVo.getBch().getLast());
						} else {
							System.out.println("Buy Fail ==>" + ret.getStatus());
						}
					} else {
						System.out.println("Tran is Not Setting");
					}
				}
			}

			// lSellPrice = lBuyPrice.longValue() + 5;
			System.out.println(sCurrency + " SellTargetPrice ==========================> " + lSellPrice);

			if ("N".equals(sSellStatus)) {
				if (tickerVo.getBch().getLast() > lSellPrice) {
					float currency_balance = 0;
					if (Constants.ETH_KRW.equals(sCurrency)) {
//						currency_balance = api.getBalances().getEth().getAvailable();
					} else if (Constants.ETC_KRW.equals(sCurrency)) {
//						currency_balance = api.getBalances().getEtc().getAvailable();
					}

					System.out.println("======> " + sCurrency + "_balance : " + currency_balance);

					// if (currency_balance > lSellUnits.floatValue()) {
					if ("Y".equals(sTranSellYn)) {
						OrderRetVO ret = null;// api.ordersSellLimit(sCurrency, lSellUnits.toString(), lSellPrice.toString());
						if (Constants.SUCCESS != ret.getStatus()) {
							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("Y");
							vo.setTran_type("S");
							vo.setTran_yn("Y");
							tranConfigDAO.updateTranConfig(vo);

							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("N");
							vo.setTran_type("B");
							vo.setTran_yn("Y");
							tranConfigDAO.updateTranConfig(vo);

							sendMail.sendMail("Korbit Sell", sCurrency + "/" + " Sell " + "/" + " Unit:" + lBuyUnits
									+ "/" + " Price:" + tickerVo.getBch().getLast());
						} else {
							System.out.println("Sell Fail ==>" + ret.getStatus());
						}
					} else {
						System.out.println("Tran is Not Setting");
					}
					// }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("tranCoin --> " + sCurrency);
		return "item";
	}

}