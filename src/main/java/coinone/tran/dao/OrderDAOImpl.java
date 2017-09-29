package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.OrderVO;


@Repository
public class OrderDAOImpl implements OrderDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"coinone.tran.dao.DbMapper.Order";
	@Override
	public List<OrderVO> getOrdersSellList(OrderVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getOrderList", vo);
	}
	@Override
	public OrderVO getOrdersSell(OrderVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getOrder", vo);
	}
	@Override
	public void updateOrdersSell(OrderVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateOrder", vo);
	}
	@Override
	public void registerOrdersSell(OrderVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerOrder", vo);
		
	}

	@Override
	public List<OrderVO> getOrdersBuyList(OrderVO ordersBuyVO) {
		return null;
	}

	@Override
	public void updateOrdersBuy(OrderVO buyVOUpt) {

	}

	@Override
	public void updateOrder(OrderVO vo) {
		sqlSession.update(namespace + ".updateOrder", vo);
	}

	@Override
	public void deleteOrder(OrderVO vo) {
		sqlSession.update(namespace + ".deleteOrder", vo);
	}

	@Override
	public OrderVO getOrder(OrderVO vo) {
		return null;
	}

	@Override
	public List<OrderVO> getOrderList(OrderVO vo) {
		List<OrderVO> list = sqlSession.selectList(namespace + ".getOrderList", vo);
		return list;
	}

	@Override
	public List<OrderVO> getList(OrderVO vo) {
		return null;
	}


	@Override
	public void registerOrder(OrderVO vo) {
		sqlSession.update(namespace + ".registerOrder", vo);
	}

	@Override
	public void registerSellReq(OrderVO vo) {
		sqlSession.update(namespace + ".registerSellReq", vo);

	}

	@Override
	public void registerBuyReq(OrderVO vo) {
		sqlSession.update(namespace + ".registerBuyReq", vo);
	}


}
