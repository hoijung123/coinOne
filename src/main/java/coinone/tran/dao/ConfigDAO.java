package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.ConfigVO;


public interface ConfigDAO extends GenericDAO<ConfigVO, String> {
	public List<ConfigVO> getConfigList();
	public ConfigVO getConfig(ConfigVO vo);
	public void updateConfig(ConfigVO vo);
	public void registerConfig(ConfigVO vo);
}