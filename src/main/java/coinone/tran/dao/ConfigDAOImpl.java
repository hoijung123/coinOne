package coinone.tran.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import coinone.tran.vo.ConfigVO;
import coinone.tran.vo.TranConfigVO;


@Repository
public class ConfigDAOImpl extends GenericDAOImpl<ConfigVO, String> implements ConfigDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	@Override
	public List<ConfigVO> getConfigList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getConfigList");
	}
	@Override
	public ConfigVO getConfig(ConfigVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getConfig", vo);
	}
	@Override
	public void updateConfig(ConfigVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateConfig", vo);
	}
	@Override
	public void registerConfig(ConfigVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerConfig", vo);
		
	}
}
