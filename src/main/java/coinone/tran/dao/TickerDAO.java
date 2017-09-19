package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.TickerDtlVO;


public interface TickerDAO extends GenericDAO<TickerDtlVO, String> {
	public List<TickerDtlVO> getTickerList();
	public TickerDtlVO getTicker(TickerDtlVO vo);
	public void updateTicker(TickerDtlVO vo);
	public void registerTicker(TickerDtlVO vo);
}