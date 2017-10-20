package coinone.tran.dao;

import coinone.tran.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;


@Repository
public class OrderLogDAOImpl implements OrderLogDAO {
    @Inject
    private SqlSession sqlSession;

    private static final String namespace =
            "coinone.tran.dao.DbMapper.OrderLog";

    @Override
    public void updateOrderLog(OrderLogVO vo) {
        sqlSession.update(namespace + ".updateOrderLog", vo);
    }

    @Override
    public void deleteOrderLog(OrderLogVO vo) {
        sqlSession.update(namespace + ".deleteOrderLog", vo);
    }

    @Override
    public OrderLogVO getOrderLog(OrderLogVO vo) {
        return  sqlSession.selectOne(namespace + ".getOrderLog", vo);
    }

    @Override
    public List<OrderLogVO> getOrderLogList(OrderLogVO vo) {
        List<OrderLogVO> list = sqlSession.selectList(namespace + ".getOrderLogList", vo);
        return list;
    }

    @Override
    public void registerOrderLog(OrderLogVO vo) {
        sqlSession.update(namespace + ".registerOrderLog", vo);
    }


}
