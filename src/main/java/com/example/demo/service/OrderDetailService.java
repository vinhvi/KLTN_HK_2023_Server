package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail saveOrUpdate(int idCart,OrderDetail orderDetail);

    List<OrderDetail> getByOrder(Order order);

    OrderDetail getById(int id);

    void deleteOrderDetail(OrderDetail orderDetail);
}
