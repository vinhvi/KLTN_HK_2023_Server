package com.example.demo.serviceImpl;

import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.repository.ImportOrderDetailRepo;
import com.example.demo.service.ImportOrderDetailService;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImportOrderDetailImpl implements ImportOrderDetailService {
    private final ImportOrderDetailRepo repo;
    private final ProductService productService;
    @Override
    public ImportOrderDetail saveOrUpdate(ImportOrderDetail importOrderDetail) {
        String productId = importOrderDetail.getProduct().getId();
        if (productId == null){
            importOrderDetail.setProduct(productService.saveOrUpdate(importOrderDetail.getProduct()));
        }else {
            Product product = productService.getById(productId);
            product.setQuantity(product.getQuantity() + importOrderDetail.getQuantity());
            importOrderDetail.setProduct(productService.saveOrUpdate(product));
        }

        return repo.save(importOrderDetail);
    }

    @Override
    public ImportOrderDetail getById(int id) {
        return repo.findImportOrderDetailById(id);
    }

    @Override
    public void deleteById(ImportOrderDetail importOrderDetail) {
        repo.delete(importOrderDetail);
    }
}
