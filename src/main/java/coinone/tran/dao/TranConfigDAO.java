package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.TranConfigVO;


public interface TranConfigDAO {
	public List<TranConfigVO> getTranConfigList();
	public TranConfigVO getTranConfig(TranConfigVO vo);
	public void updateTranConfig(TranConfigVO vo);
	public void registerTranConfig(TranConfigVO vo);
}