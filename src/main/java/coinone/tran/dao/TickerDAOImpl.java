package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.TickerDtlVO;
import coinone.tran.vo.TickerVO;
import coinone.tran.vo.TranConfigVO;

@Repository
public class TickerDAOImpl implements TickerDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "coinone.tran.dao.DbMapper.Ticker";

	public void register(TickerDtlVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".register", vo);

	}

	public TickerDtlVO get(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TickerDtlVO> getList(TickerDtlVO vo) {
		// TODO Auto-generated method stub		
		return sqlSession.selectList(namespace + ".getList", vo);
	}

	@Override
	public TickerDtlVO getLastestTicker(TickerDtlVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getLastestTicker", vo);
	}

	@Override
	public List<TickerDtlVO> getDailyList(TickerDtlVO vo) {
		return sqlSession.selectList(namespace + ".getDailyList", vo);
	}

}
