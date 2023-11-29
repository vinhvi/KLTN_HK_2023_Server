package com.example.demo.serviceImpl;

import com.example.demo.entity.LoHang;
import com.example.demo.entity.Product;
import com.example.demo.repository.LoHangRepo;
import com.example.demo.service.LoHangService;
import com.example.demo.service.ProductService;
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
public class LoHangImpl implements LoHangService {

    private final LoHangRepo loHangRepo;
    private final ProductService productService;
    @Override
    public LoHang saveOrUpdate(LoHang loHang) {
        if (loHang.getId() == null) {
            Date currentDate = new Date();
            loHang.setDate(currentDate);
            loHang.setStatus(0);
            loHang.setId(randomIDLH());
            String productId = loHang.getProduct().getId();
            if (productId == null) {
                loHang.setProduct(productService.saveOrUpdate(loHang.getProduct()));
            } else {
                Product product = productService.getById(productId);
                product.setQuantity(product.getQuantity() + loHang.getQuantity());
                loHang.setProduct(productService.saveOrUpdate(product));
            }
            return loHangRepo.save(loHang);
        }
        LoHang loHangUpdate = loHangRepo.findLoHangById(loHang.getId());
        loHangUpdate.setStatus(1);
        return loHangRepo.save(loHangUpdate);
    }

    @Override
    public LoHang getById(String iD) {
        return loHangRepo.findLoHangById(iD);
    }

    @Override
    public List<LoHang> getAll() {
        return loHangRepo.findAll();
    }

    @Override
    public List<LoHang> getByProduct(Product product) {
        return loHangRepo.findLoHangByProduct(product);
    }

    @Override
    public String randomIDLH() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "LH" + String.format("%04d", number);
            List<LoHang> loHangs = loHangRepo.findAll();
            if (loHangs.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = loHangs.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }
}
