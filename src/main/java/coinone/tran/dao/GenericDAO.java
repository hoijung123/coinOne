package coinone.tran.dao;

import java.util.List;

public interface GenericDAO<E, K> {

	public K getTime();
	public void register(E vo);
	public E get(K userid);
	public List<E> getList();
	//public void update();
	//public void delete();
	public void setNameSpace(String namespace);
	
}// interface
