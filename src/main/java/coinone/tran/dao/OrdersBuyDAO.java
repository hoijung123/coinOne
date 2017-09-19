package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.ConfigVO;
import coinone.tran.vo.OrdersBuyVO;


public interface OrdersBuyDAO extends GenericDAO<OrdersBuyVO, String> {
	public List<OrdersBuyVO> getOrdersBuyList(OrdersBuyVO vo);
	public OrdersBuyVO getOrdersBuy(OrdersBuyVO vo);
	public void updateOrdersBuy(OrdersBuyVO vo);
	public void registerOrdersBuy(OrdersBuyVO vo);
}