package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderDetailRepo;
import com.example.demo.service.CartItemService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoppingCartService;
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
    private final CartItemService cartItemService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Override
    public OrderDetail saveOrUpdate(int idCart, OrderDetail orderDetail) {
        ShoppingCart shoppingCart = shoppingCartService.getByIdCart(idCart);
        Product product = productService.getById(orderDetail.getProduct().getId());
        CartItem cartItem = cartItemService.getByProductAndCart(product, shoppingCart);
        cartItemService.remove(cartItem.getId());
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
