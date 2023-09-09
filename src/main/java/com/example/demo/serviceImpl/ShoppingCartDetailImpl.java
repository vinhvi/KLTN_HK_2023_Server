package com.example.demo.serviceImpl;

import com.example.demo.entity.ShoppingCartDetail;
import com.example.demo.repository.ShoppingCartDetailRepo;
import com.example.demo.service.ShoppingCartDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShoppingCartDetailImpl implements ShoppingCartDetailService {
    private final ShoppingCartDetailRepo shoppingCartDetailRepo;

    @Override
    public ShoppingCartDetail saveOrUpDate(ShoppingCartDetail shoppingCartDetail) {
        return shoppingCartDetailRepo.save(shoppingCartDetail);
    }

    @Override
    public Boolean remove(int id) {
        try {
            ShoppingCartDetail shoppingCartDetail = shoppingCartDetailRepo.findShoppingCartDetailById(id);
            shoppingCartDetailRepo.delete(shoppingCartDetail);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

