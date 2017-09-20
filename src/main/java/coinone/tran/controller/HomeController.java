package coinone.tran.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import coinone.tran.dao.TickerDAO;
import coinone.tran.util.Constants;
import coinone.tran.vo.TickerDtlVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private TickerDAO tickerDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/jsonTickerList", method = RequestMethod.GET)
	public @ResponseBody List<TickerDtlVO> jsonTickerList(@RequestParam Map<String, String> params) {
		// VO°´Ã¼¿¡ SETÇÑÈÄ vo°´Ã¼ÀÚÃ¼¸¦ return
		String currency = params.get("currency");
		if (StringUtils.isEmpty(currency)) {
			currency = Constants.ETH_KRW;
		}
		TickerDtlVO vo = new TickerDtlVO();
		vo.setCurrency(currency);
		List<TickerDtlVO> list = tickerDAO.getList(vo);
		return list;
	}
	
	@RequestMapping(value = "/jsonTicker", method = RequestMethod.GET)
	public @ResponseBody TickerDtlVO jsonTicker(@RequestParam Map<String, String> params) {
		// VO°´Ã¼¿¡ SETÇÑÈÄ vo°´Ã¼ÀÚÃ¼¸¦ return
		String currency = params.get("currency");
		if (StringUtils.isEmpty(currency)) {
			currency = Constants.ETH_KRW;
		}
		TickerDtlVO vo = new TickerDtlVO();
		vo.setCurrency(currency);
		return tickerDAO.getLastestTicker(vo);
	}	

	@RequestMapping(value = "/lineChart", method = RequestMethod.GET)
	public String lineChart(@RequestParam Map<String, String> params, Model model) {
		String currency = params.get("currency");
		if (StringUtils.isEmpty(currency)) {
			currency = Constants.ETH_KRW;
		}
		TickerDtlVO vo = new TickerDtlVO();
		vo.setCurrency(currency);
		model.addAttribute("currency", currency);
		model.addAttribute("last", tickerDAO.getLastestTicker(vo).getLast());

		return "lineChart";
	}

}
