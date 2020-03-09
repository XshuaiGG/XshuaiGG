package cn.hp.service;

import cn.hp.domain.Order;

import java.util.List;

/**
 * 订单业务层
 */
public interface OrderService {
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
