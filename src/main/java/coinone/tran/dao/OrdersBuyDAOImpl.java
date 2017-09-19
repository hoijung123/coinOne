package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.ConfigVO;
import coinone.tran.vo.OrdersBuyVO;
import coinone.tran.vo.TranConfigVO;


@Repository
public class OrdersBuyDAOImpl extends GenericDAOImpl<OrdersBuyVO, String> implements OrdersBuyDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	@Override
	public List<OrdersBuyVO> getOrdersBuyList(OrdersBuyVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getOrdersBuyList", vo);
	}
	@Override
	public OrdersBuyVO getOrdersBuy(OrdersBuyVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getOrdersBuy", vo);
	}
	@Override
	public void updateOrdersBuy(OrdersBuyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateOrdersBuy", vo);
	}
	@Override
	public void registerOrdersBuy(OrdersBuyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerOrdersBuy", vo);
		
	}
}
