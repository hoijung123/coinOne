package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.OrdersSellVO;
import coinone.tran.vo.TranConfigVO;


@Repository
public class OrdersSellDAOImpl extends GenericDAOImpl<OrdersSellVO, String> implements OrdersSellDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	@Override
	public List<OrdersSellVO> getOrdersSellList(OrdersSellVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getOrdersSellList", vo);
	}
	@Override
	public OrdersSellVO getOrdersSell(OrdersSellVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getOrdersSell", vo);
	}
	@Override
	public void updateOrdersSell(OrdersSellVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateOrdersSell", vo);
	}
	@Override
	public void registerOrdersSell(OrdersSellVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerOrdersSell", vo);
		
	}
}
