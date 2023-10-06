package com.example.demo.serviceImpl;

import com.example.demo.DataBean.ShoppingCartDataBean;
import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.ShoppingCartDetail;
import com.example.demo.repository.ShoppingCartDetailRepo;
import com.example.demo.repository.ShoppingCartRepo;
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
public class ShoppingCartImpl implements ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;
    private final ShoppingCartDetailRepo shoppingCartDetailRepo;

    @Override
    public ShoppingCart saveOrUpdate(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCartDataBean getById(int id) {
        return checkAndUpdate(shoppingCartRepo.findShoppingCartById(id));
    }

    @Override
    public ShoppingCartDataBean getByCustomer(Customer customer) {
        return checkAndUpdate(shoppingCartRepo.findShoppingCartByCustomer(customer));
    }

    @Override
    public ShoppingCart getByIdCart(int id) {
        return shoppingCartRepo.findShoppingCartById(id);
    }

    private ShoppingCartDataBean checkAndUpdate(ShoppingCart shoppingCart) {
        ShoppingCartDataBean shoppingCartDataBean = new ShoppingCartDataBean();
        List<ShoppingCartDetail> shoppingCartDetails = shoppingCartDetailRepo.findShoppingCartDetailByShoppingCart(shoppingCart);
        if (shoppingCartDetails.isEmpty()) {
            shoppingCart.setQuantity(0);
        }
        int sl = shoppingCartDetails.size();
        if (sl != shoppingCart.getQuantity()) {
            shoppingCart.setQuantity(sl);
        }
        shoppingCartRepo.save(shoppingCart);
        shoppingCartDataBean.setShoppingCartDetails(shoppingCartDetails);
        shoppingCartDataBean.setId(shoppingCart.getId());
        shoppingCartDataBean.setDate(shoppingCart.getDate());
        shoppingCartDataBean.setCustomer(shoppingCart.getCustomer());
        return shoppingCartDataBean;
    }
}
