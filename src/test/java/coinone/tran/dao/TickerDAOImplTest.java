package coinone.tran.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import coinone.tran.AbstractTest;
import coinone.tran.service.CallAPIService;
import coinone.tran.vo.TickerDtlVO;
import coinone.tran.vo.TickerVO;

public class TickerDAOImplTest extends AbstractTest {
	@Inject
	private TickerDAOImpl dao;

	@Test
	public final void testGetTime() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRegister() throws Exception {
		CallAPIService api = new CallAPIService();
		TickerVO tickerVO = api.getTickerAll();

		Class c = Class.forName("coinone.tran.vo.TickerVO");

		List<String> methodList = new ArrayList<String>(
				Arrays.asList("getBtc", "getEth", "getEtc", "getXrp", "getBch", "getQtum"));

		for (String s : methodList) {
			Method m = c.getDeclaredMethod(s);
			TickerDtlVO dtlVO = (TickerDtlVO) m.invoke(tickerVO);
			dtlVO.setTimestamp(tickerVO.getTimestamp());
			dao.register(dtlVO);
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGet() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetList() {
		//dao.getList();
		// fail("Not yet implemented"); // TODO
	}

}
