package coinone.tran.controller;

import java.text.DateFormat;
import java.util.*;

import javax.inject.Inject;

import coinone.tran.dao.OrderDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.vo.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;
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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Inject
    private TickerDAO tickerDAO;

    @Inject
    private OrderDAO orderDAO;

    @Inject
    private TranConfigDAO tranConfigDAO;

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
    public @ResponseBody
    List<TickerDtlVO> jsonTickerList(@RequestParam Map<String, String> params) {
        String currency = params.get("currency");
        if (StringUtils.isEmpty(currency)) {
            currency = Constants.ETH_KRW;
        }
        TickerDtlVO vo = new TickerDtlVO();
        vo.setCurrency(currency);
        List<TickerDtlVO> list = tickerDAO.getDailyList(vo);

        return list;
    }

    @RequestMapping(value = "/jsonTicker", method = RequestMethod.GET)
    public @ResponseBody
    TickerDtlVO jsonTicker(@RequestParam Map<String, String> params) {
        String currency = params.get("currency");
        if (StringUtils.isEmpty(currency)) {
            currency = Constants.ETH_KRW;
        }
        TickerDtlVO vo = new TickerDtlVO();
        vo.setCurrency(currency);
        vo = tickerDAO.getLastestTicker(vo);
        return vo;
    }

    @RequestMapping(value = "/lineChart", method = RequestMethod.GET)
    public String lineChart(@RequestParam Map<String, String> params, Model model) throws ParseException {
        String currency = params.get("currency");
        if (StringUtils.isEmpty(currency)) {
            currency = Constants.ETH_KRW;
        }
        TickerDtlVO vo = new TickerDtlVO();
        vo.setCurrency(currency);
        model.addAttribute("currency", currency);
        vo = tickerDAO.getLastestTicker(vo);
        Long last = null;
        if (null != vo) {
            last = vo.getLast();
        }
        model.addAttribute("last", last);
        return "lineChart";
    }

    /**
     * Simply selects the home view to render by returning its name.
     *
     * @throws ParseException
     */
    @RequestMapping(value = "/tran/listLimitOrders", method = RequestMethod.GET)
    public String listLimitOrders(@RequestParam Map<String, String> params, Model model) throws Exception {
        String currency = params.get("currency");

        if (StringUtils.isEmpty(currency)) currency = Constants.COIN_XRP;

        CallAPIService comm = new CallAPIService();
        LimitOrderVO vo = comm.getLimitOrders(currency);
        model.addAttribute("ordersOpenList", vo.getLimitOrders());

        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        LimitOrderVO vo2 = null;
        json = mapper.writeValueAsString(vo.getLimitOrders());
        model.addAttribute("json", json);

        model.addAttribute("currency", currency);

        OrderVO orderVO = new OrderVO();
        orderDAO.getOrderList(orderVO);

        return "tran/listOrdersOpen";
    }

    /**
     * Simply selects the home view to render by returning its name.
     *
     * @throws ParseException
     */
    @RequestMapping(value = "/tran/cancelOrder", method = RequestMethod.GET)
    public String cancelOrder(@RequestParam Map<String, String> params, Model model) throws Exception {
        String sCurrency = "";
        sCurrency = params.get("currency");
        String orderId = params.get("orderId");
        String qty = params.get("qty");
        String price = params.get("price");
        String type = params.get("type");

        if (StringUtils.isEmpty(sCurrency)) sCurrency = Constants.COIN_XRP;

        CallAPIService api = new CallAPIService();

        OrderVO orderVO = new OrderVO();
        orderVO.setCurrency(sCurrency);
        orderVO.setOrderId(orderId);
        orderVO.setQty(Double.valueOf(qty));
        orderVO.setPrice(new Long(price));
        orderVO.setType(type);
        api.cancelOrder(orderVO);

        return "redirect:listLimitOrders?currency=" + sCurrency;
    }

    /**
     * Simply selects the home view to render by returning its name.
     *
     * @throws ParseException
     */
    @RequestMapping(value = "/tran/registerOrderReq", method = RequestMethod.GET)
    public String registerOrderReq(@RequestParam Map<String, String> params, Model model) throws Exception {
        String sCurrency = "";
        sCurrency = params.get("currency");
        String qty = params.get("qty");
        String reqCnt = params.get("reqCnt");

        if (StringUtils.isEmpty(sCurrency)) sCurrency = Constants.COIN_XRP;

        CallAPIService api = new CallAPIService();

        OrderVO vo = new OrderVO();
        vo.setCurrency(sCurrency);
        vo.setPrice((long) api.getTicker(sCurrency).getLast());
        vo.setQty(new Double(qty));
        vo.setReqCnt(new Integer(reqCnt));
        Integer base = 0;
        if (Constants.COIN_XRP.equals(sCurrency)) {
            base = 1;
        } else if (Constants.COIN_BCH.equals(sCurrency)) {
            base = 1000;
        } else if (Constants.COIN_ETH.equals(sCurrency)) {
            base = 1000;
        } else if (Constants.COIN_QTUM.equals(sCurrency)) {
            base = 30;
        } else if (Constants.COIN_BTC.equals(sCurrency)) {
            base = 1000 * 20;
        } else if (Constants.COIN_ETC.equals(sCurrency)) {
            base = 30;
        }
        vo.setBase(base);

        orderDAO.deleteOrder(vo);
        orderDAO.registerBuyReq(vo);
        orderDAO.registerSellReq(vo);

        return "redirect:listLimitOrders?currency=" + sCurrency;
    }

    @RequestMapping(value = "/tran/listTranConfig", method = RequestMethod.GET)
    public String listTranConfig(@RequestParam Map<String, String> params, Model model) throws Exception {
        List<TranConfigVO> tranConfigVOList = tranConfigDAO.getTranConfigList();
        model.addAttribute("tranConfigVOList", tranConfigVOList);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tranConfigVOList);
        model.addAttribute("json", json);

        return "tran/listTranConfig";
    }

    @RequestMapping(value = "/tran/saveTranConfig", method = RequestMethod.POST)
    public String saveTranConfig(@RequestParam Map<String, String> params, Model model) throws Exception {
        String mode = params.get("mode");
        String currency = params.get("currency");
        String tran_type = params.get("tran_type");
        String tran_yn = params.get("tran_yn");

        TranConfigVO vo = new TranConfigVO();
        vo.setCurrency(currency);
        vo.setTran_type(tran_type);
        vo.setTran_yn(tran_yn);
        vo.setUnits((float) 0);
        if ("N".equals(mode))
        {
            tranConfigDAO.registerTranConfig(vo);
        } else if ("E".equals(mode))
        {
            tranConfigDAO.updateTranConfig(vo);
        }

        return "redirect:listTranConfig";
    }


    @RequestMapping(value = "/tran/getBalances", method = RequestMethod.GET)
    public String getBalances(@RequestParam Map<String, String> params, Model model) throws Exception {
        CallAPIService comm = new CallAPIService();
        BalanceVO vo = comm.getBalance(Constants.COIN_BCH);
        TickerVO tickerVO = comm.getTickerAll();

        List<BalanceDtlVO> balList = new ArrayList<>();
        BalanceDtlVO bchVo = vo.getBch();
        bchVo.setCurrency(Constants.COIN_BCH);
        bchVo.setLast(tickerVO.getBch().getLast());
        bchVo.setKrwBalance((long) (bchVo.getBalance() * bchVo.getLast()));

        balList.add(bchVo);
        BalanceDtlVO krwVo = vo.getKrw();
        krwVo.setCurrency(Constants.COIN_KRW);
        krwVo.setLast((long) 1);
        krwVo.setKrwBalance(krwVo.getBalance().longValue());
        balList.add(krwVo);

        BalanceDtlVO btcVo = vo.getBtc();
        btcVo.setCurrency(Constants.COIN_BTC);
        btcVo.setLast(tickerVO.getBtc().getLast());
        btcVo.setKrwBalance((long) (btcVo.getBalance() * btcVo.getLast()));
        balList.add(btcVo);

        BalanceDtlVO ethVo = vo.getEth();
        ethVo.setCurrency(Constants.COIN_ETH);
        ethVo.setLast(tickerVO.getEth().getLast());
        ethVo.setKrwBalance((long) (ethVo.getBalance() * ethVo.getLast()));
        balList.add(ethVo);

        BalanceDtlVO etcVo = vo.getEtc();
        etcVo.setCurrency(Constants.COIN_ETC);
        etcVo.setLast(tickerVO.getEtc().getLast());
        etcVo.setKrwBalance((long) (etcVo.getBalance() * etcVo.getLast()));
        balList.add(etcVo);

        BalanceDtlVO xrpVo = vo.getXrp();
        xrpVo.setCurrency(Constants.COIN_XRP);
        xrpVo.setLast(tickerVO.getXrp().getLast());
        xrpVo.setKrwBalance((long) (xrpVo.getBalance() * xrpVo.getLast()));
        balList.add(xrpVo);

        BalanceDtlVO qtumVo = vo.getQtum();
        qtumVo.setCurrency(Constants.COIN_QTUM);
        qtumVo.setLast(tickerVO.getQtum().getLast());
        qtumVo.setKrwBalance((long) (qtumVo.getBalance() * qtumVo.getLast()));
        balList.add(qtumVo);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(balList);
        model.addAttribute("balList", json);

        return "tran/getBalances";
    }
}
