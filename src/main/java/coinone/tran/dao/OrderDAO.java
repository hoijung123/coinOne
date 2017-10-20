package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.OrderVO;


public interface OrderDAO  {
	public List<OrderVO> getOrdersSellList(OrderVO vo);
	public OrderVO getOrdersSell(OrderVO vo);
	public void updateOrdersSell(OrderVO vo);
	public void registerOrdersSell(OrderVO vo);

	List<OrderVO> getOrdersBuyList(OrderVO ordersBuyVO);

	void updateOrdersBuy(OrderVO buyVOUpt);

	void updateOrder(OrderVO buyVOUpt);

	void deleteOrder(OrderVO buyVOUpt);

	public OrderVO getOrder(OrderVO vo);

	List<OrderVO> getOrderList(OrderVO ordersBuyVO);

	public List<OrderVO> getList(OrderVO vo);

	public void registerOrder(OrderVO vo);

	public void registerSellReq(OrderVO vo);

	public void registerBuyReq(OrderVO vo);
}