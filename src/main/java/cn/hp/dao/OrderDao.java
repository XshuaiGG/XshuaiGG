package cn.hp.dao;

import cn.hp.domain.Order;

import java.util.List;

/**
 * 订单持久层
 */
public interface OrderDao {

    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAll();

    /**
     * 保存订单
     * @param order
     */
    void save(Order order);
}
