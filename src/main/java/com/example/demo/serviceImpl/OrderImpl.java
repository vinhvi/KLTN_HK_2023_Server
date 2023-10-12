package com.example.demo.serviceImpl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepo;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;

    @Override
    public Order saveOrUpdate(Order order) {
        if (order.getId() != null) {
            Order orderUpdate = orderRepo.findOrderById(order.getId());
            if (order.getCustomer() != null) {
                orderUpdate.setCustomer(order.getCustomer());
            }
            if (order.getEmployee() != null) {
                orderUpdate.setEmployee(order.getEmployee());
            }
            if (order.getStatusOrder() != null) {
                orderUpdate.setStatusOrder(order.getStatusOrder());
            }
            if (order.getOrderDetails() != null) {
                orderUpdate.setOrderDetails(order.getOrderDetails());
            }
            if (order.getDate() != null) {
                orderUpdate.setDate(order.getDate());
            }
            if (order.getNote() != null) {
                orderUpdate.setNote(order.getNote());
            }
            return orderRepo.save(orderUpdate);
        }
        order.setId(randomOrderId());
        Date currentDate = new Date();
        order.setDate(currentDate);
        order.setStatusOrder("Đang xử lý");
        Order orderSaved = orderRepo.save(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(orderSaved);
            orderDetail.setDate(currentDate);
            orderDetails.add(orderDetailRepo.save(orderDetail));
        }
        orderSaved.setOrderDetails(orderDetails);
        return orderSaved;
    }

    @Override
    public List<Order> getOrderByCustomer(Customer customer) {
        return orderRepo.findOrderByCustomer(customer);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepo.findOrderById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getByDate(Date date) {
        return orderRepo.findOrderByDate(date);
    }

    @Override
    public String randomOrderId() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "HDBH" + String.format("%04d", number);
            List<Order> orders = orderRepo.findAll();
            if (orders.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = orders.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }

}
