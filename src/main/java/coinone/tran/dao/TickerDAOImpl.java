package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.TickerDtlVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;


@Repository
public class TickerDAOImpl extends GenericDAOImpl<TickerDtlVO, String> implements TickerDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"coin.tran.dao.DbMapper";
	@Override
	public List<TickerDtlVO> getTickerList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getTickerList");
	}
	@Override
	public TickerDtlVO getTicker(TickerDtlVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getTicker", vo);
	}
	@Override
	public void updateTicker(TickerDtlVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateTicker", vo);
	}
	@Override
	public void registerTicker(TickerDtlVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerTicker", vo);
		
	}
}
