package coinone.tran.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.service.CallAPIService;

public class AccessTokenProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(AccessTokenProcessor.class);

	@Override
	public String process(String item) throws Exception {
		CallAPIService api = new CallAPIService();
		//api.oauth();
		return item;
	}

}