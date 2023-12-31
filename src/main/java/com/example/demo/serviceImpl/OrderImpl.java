package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;

    @Override
    public Order saveOrUpdate(int idCart,Order order) {
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
        order.setStatusOrder("1");
        Order orderSaved = orderRepo.save(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(orderSaved);
            orderDetail.setDate(currentDate);
            orderDetails.add(orderDetailService.saveOrUpdate(idCart,orderDetail));
        }
        orderSaved.setOrderDetails(orderDetails);
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Product product = productService.getById(orderDetail.getProduct().getId());
            int slUpdate = product.getQuantity() - orderDetail.getQuantity();
            if (slUpdate < 0) {
                return null;
            }
            product.setQuantity(slUpdate);
            productService.saveOrUpdate(product);
        }
        return orderSaved;
    }

    @Override
    public Order update(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Order> listConfirm() {
        List<Order> orders = new ArrayList<>();
        for (Order order:orderRepo.findAll()) {
            if (order.getEmployee() == null||order.getStatusOrder()==null||order.getStatusOrder().equals("1")||order.getStatusOrder().equals("2")){
                orders.add(order);
            }
        }
        return orders;
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
    public List<Order> getByDateBetween(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        Date startOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date endOfMonth = calendar.getTime();
        return orderRepo.findOrderByDateBetween(startOfMonth, endOfMonth);

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

    @Override
    public Order createNow(Order order) {
        order.setId(randomOrderId());
        Date currentDate = new Date();
        order.setDate(currentDate);
        Order orderSaved = orderRepo.save(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(orderSaved);
            orderDetail.setDate(currentDate);
            orderDetails.add(orderDetailService.createNow(orderDetail));
        }
        orderSaved.setOrderDetails(orderDetails);
        return orderSaved;
    }

    @Override
    public List<Order> update(Employee employee, List<Order> orders) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : orders) {
            Order orderUpdate = orderRepo.findOrderById(order.getId());
            if (orderUpdate.getStatusOrder().equals("1")){
                orderUpdate.setEmployee(employee);
            }
            orderUpdate.setStatusOrder(order.getStatusOrder());
            Order save = orderRepo.save(orderUpdate);
            orderList.add(save);
        }
        return orderList;
    }

    @Override
    public List<Order> getByDate(Date date) {
        return orderRepo.findOrderByDate(date);
    }

}
