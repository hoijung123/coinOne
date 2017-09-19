package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.OrdersSellVO;


public interface OrdersSellDAO extends GenericDAO<OrdersSellVO, String> {
	public List<OrdersSellVO> getOrdersSellList(OrdersSellVO vo);
	public OrdersSellVO getOrdersSell(OrdersSellVO vo);
	public void updateOrdersSell(OrdersSellVO vo);
	public void registerOrdersSell(OrdersSellVO vo);
}