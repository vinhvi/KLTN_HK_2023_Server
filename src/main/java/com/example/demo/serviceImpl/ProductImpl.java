package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
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
public class ProductImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepo.findProductByCategory(category);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepo.findProductByProductNameContaining(name);
    }

    @Override
    public Product getById(String id) {
        return productRepo.findProductById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product.getId() == null){
            Date currentDate = new Date();
            product.setImportDate(currentDate);
            product.setId(randomId());
        }
        return productRepo.save(product);
    }

    @Override
    public String randomId() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "SP" + String.format("%04d", number);
            List<Product> products = productRepo.findAll();
            if (products.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = products.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }

    @Override
    public List<Product> listNeedUpdate() {
        List<Product> productListNeedUpdate = new ArrayList<>();
        for (Product product:productRepo.findAll()) {
            if (product.getImageProducts() == null || product.getProductName() == null||product.getBrand()==null||product.getDescription()==null
                    ||product.getPrice()==0||product.getSpecifications()==null){
                productListNeedUpdate.add(product);
            }
        }
        return productListNeedUpdate;
    }
}
