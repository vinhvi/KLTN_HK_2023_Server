package com.example.demo.serviceImpl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.ShoppingCartRepo;
import com.example.demo.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShoppingCartImpl implements ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;

    @Override
    public ShoppingCart saveOrUpdate(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart getById(int id) {
        return checkAndUpdate(shoppingCartRepo.findShoppingCartById(id));
    }

    @Override
    public ShoppingCart getByCustomer(Customer customer) {
        return checkAndUpdate(shoppingCartRepo.findShoppingCartByCustomer(customer));
    }

    private ShoppingCart checkAndUpdate(ShoppingCart shoppingCart) {
        int sl = shoppingCart.getShoppingCartDetails().size();
        if (sl != shoppingCart.getQuantity()) {
            shoppingCart.setQuantity(sl);
            shoppingCartRepo.save(shoppingCart);
        }
        return shoppingCart;
    }
}
