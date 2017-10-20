package coinone.tran.dao;

import coinone.tran.vo.OrderVO;

import java.util.List;


public interface OrderLogDAO {

	void updateOrderLog(OrderLogVO buyVOUpt);

	void deleteOrderLog(OrderLogVO buyVOUpt);

	public OrderVO getOrderLog(OrderLogVO vo);

	List<OrderLogVO> getOrderLogList(OrderLogVO ordersBuyVO);

	public void registerOrderLog(OrderLogVO vo);

}