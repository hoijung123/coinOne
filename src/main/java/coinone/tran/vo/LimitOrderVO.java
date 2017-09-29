package coinone.tran.vo;

import java.util.List;

public class LimitOrderVO extends BaseVO {
    public List<OrderVO> getLimitOrders() {
        return limitOrders;
    }

    public void setLimitOrders(List<OrderVO> limitOrders) {
        this.limitOrders = limitOrders;
    }

    private List<OrderVO> limitOrders = null;
}
