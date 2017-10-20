package coinone.tran.batch;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import coinone.tran.dao.OrderDAO;
import coinone.tran.dao.TranConfigDAO;
import coinone.tran.service.CallAPIService;
import coinone.tran.util.Constants;
import coinone.tran.util.SendMail;
import coinone.tran.vo.BalanceVO;
import coinone.tran.vo.OrderRetVO;
import coinone.tran.vo.OrderVO;
import coinone.tran.vo.TranConfigVO;

public class TranLimitBuyProcessor implements ItemProcessor<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(TranLimitBuyProcessor.class);
    @Inject
    private TranConfigDAO tranConfigDAO;
    @Inject
    private OrderDAO orderDao;

    @Override
    public String process(String item) throws Exception {
        this.tranCoin(Constants.COIN_XRP);
        Thread.sleep(1000);
        this.tranCoin(Constants.COIN_BCH);
        Thread.sleep(1000);
        this.tranCoin(Constants.COIN_ETH);
        Thread.sleep(1000);
        this.tranCoin(Constants.COIN_QTUM);
        Thread.sleep(1000);
        this.tranCoin(Constants.COIN_BTC);
        Thread.sleep(1000);
        this.tranCoin(Constants.COIN_ETC);
        return item;
    }

    public String tranCoin(String sCurrency) throws Exception {
        SendMail sendMail = new SendMail();
        CallAPIService api = new CallAPIService();

        OrderVO ordersBuyVO = new OrderVO();
        ordersBuyVO.setCurrency(sCurrency);
        ordersBuyVO.setType(Constants.TRAN_BUY);
        ordersBuyVO.setResult("N");
        List<OrderVO> listOrdersBuyVo = orderDao.getOrderList(ordersBuyVO);

        TranConfigVO vo = new TranConfigVO();
        vo.setCurrency(sCurrency);
        vo.setTran_type(Constants.TRAN_BUY);
        TranConfigVO tranConfigBuyVO = tranConfigDAO.getTranConfig(vo);
        String sTranBuyYn = "N";
        if (null != tranConfigBuyVO) {
            sTranBuyYn = tranConfigBuyVO.getTran_yn();
        }

        BalanceVO balanceVO = null;

        balanceVO = api.getBalance(Constants.COIN_KRW);
        Double krwAvail = balanceVO.getKrw().getAvail();

        boolean isBuy = false;
        try {

            for (OrderVO sub : listOrdersBuyVo) {
                //코인거래여부 체크
                if ("N".equals(sTranBuyYn)) {
                    continue;
                }

                //현재목표가 매수 했으면 Pass
                if (isBuy) {
                    continue;
                }

                if (krwAvail < (sub.getQty() * sub.getPrice())) {
                    continue;
                }

                if ("N".equals(sub.getResult())) {
                    OrderVO buyVO = new OrderVO();
                    buyVO.setCurrency(sCurrency);
                    buyVO.setQty(sub.getQty());
                    buyVO.setPrice(sub.getPrice());
                    OrderRetVO ret = api.ordersLimitBuy(buyVO);
                    if (null != ret) {
                        OrderVO buyVOUpt = new OrderVO();
                        buyVOUpt.setCurrency(sub.getCurrency());
                        buyVOUpt.setType(Constants.TRAN_BUY);
                        buyVOUpt.setSeq(sub.getSeq());
                        buyVOUpt.setOrderId(ret.getOrderId());
                        buyVOUpt.setResult(ret.getResult());
                        buyVOUpt.setErrorCode(ret.getErrorCode());
                        orderDao.updateOrder(buyVOUpt);
                    }
                    if (Constants.SUCCESS.equals(ret.getResult())) {
                        isBuy = true;
                        OrderVO sellVO = new OrderVO();
                        sellVO.setCurrency(sCurrency);
                        sellVO.setType(Constants.TRAN_SELL);
                        sellVO.setSeq(sub.getSeq());
                        sellVO.setResult("N");
                        // orderDao.updateOrderLog(sellVO);
                        sendMail.sendMail("CoinOne Buy", sCurrency + "/" + " Buy " + "/" + " Unit:"
                                + sub.getQty() + "/" + " Price:" + sub.getPrice());
                    } else {
                        sendMail.sendMail("CoinOne Buy Fail", sCurrency + "/" + " Sell " + "/" + " Result:"
                                + ret.getResult() + "/" + " ErrorCode:" + ret.getErrorCode());
                    }
                } else {
                    System.out.println("Tran is Not Setting");
                }
            }

        } catch (

                Exception e) {
            e.printStackTrace();
        }

        logger.info("tranCoin --> " + sCurrency);
        return "item";
    }

}