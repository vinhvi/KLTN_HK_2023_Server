package com.example.demo.serviceImpl;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepo;
import com.example.demo.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartItemImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;

    @Override
    public CartItem saveOrUpDate(CartItem cartItem) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        CartItem cartItemCheck = cartItemRepo.findShoppingCartDetailByProductAndAndShoppingCart(cartItem.getProduct(), cartItem.getShoppingCart());
        if (cartItemCheck != null) {
            cartItemCheck.setQuantity(cartItem.getQuantity());
            cartItemCheck.setDate(date);
            return cartItemRepo.save(cartItemCheck);
        }
        cartItem.setDate(date);
        return cartItemRepo.save(cartItem);
    }

    @Override
    public Boolean remove(int id) {
        try {
            CartItem cartItem = cartItemRepo.findShoppingCartDetailById(id);
            cartItemRepo.delete(cartItem);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public CartItem getById(int id) {
        return cartItemRepo.findShoppingCartDetailById(id);
    }

    @Override
    public List<CartItem> getByCart(ShoppingCart shoppingCart) {
        return cartItemRepo.findShoppingCartDetailByShoppingCart(shoppingCart);
    }
}

