package coinone.tran.dao;

import java.util.List;

import coinone.tran.vo.TickerDtlVO;


public interface TickerDAO  {

	void register(TickerDtlVO vo);

	public TickerDtlVO get(String userid);

	public List<TickerDtlVO> getList();

}