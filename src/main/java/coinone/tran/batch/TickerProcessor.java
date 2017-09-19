package coinone.tran.batch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.TickerDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.TickerDtlVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;

public class TickerProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TickerProcessor.class);
	private static final int SLEEP_TIME = 2000;

	@Inject
	private TickerDAO tickerDAO;

	@Override
	public String process(String item) throws Exception {
		this.getTickerAll();
		return item;
	}

	public String getTickerAll() throws Exception {
		SendMail sendMail = new SendMail();
		CallAPIService api = new CallAPIService();

		TickerVO tickerVO = api.getTickerAll();

		Class c = Class.forName("coinone.tran.vo.TickerVO");

		List<String> methodList = new ArrayList<String>(
				Arrays.asList("getBtc", "getEth", "getEtc", "getXrp", "getBch", "getQtum"));

		for (String s : methodList) {
			Method m = c.getDeclaredMethod(s);
			TickerDtlVO dtlVO = (TickerDtlVO) m.invoke(tickerVO);
			dtlVO.setTimestamp(tickerVO.getTimestamp());
			tickerDAO.register(dtlVO);
		}

		logger.info("getTickerAll --> ");
		return "item";
	}

}