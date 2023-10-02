package com.example.demo.serviceImpl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderImpl implements OrderService {
    private final OrderRepo orderRepo;

    @Override
    public Order saveOrUpdate(Order order) {
        order.setId(randomOrderId());
        return orderRepo.save(order);
    }

    @Override
    public Order getById(String id) {
        return orderRepo.findOrderById(id);
    }

    @Override
    public List<Order> getOrderByCustomer(Customer customer) {
        return orderRepo.findOrderByCustomer(customer);
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
