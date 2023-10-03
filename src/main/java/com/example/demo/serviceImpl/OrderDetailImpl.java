package com.example.demo.serviceImpl;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepo;
import com.example.demo.service.OrderDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderDetailImpl implements OrderDetailService {
    private final OrderDetailRepo orderDetailRepo;

    @Override
    public OrderDetail saveOrUpdate(OrderDetail orderDetail) {
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getByOrder(Order order) {
        return orderDetailRepo.findOrderDetailByOrder(order);
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetailRepo.findOrderDetailById(id);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepo.delete(orderDetail);
    }
}
