package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.entity.Sale;
import com.example.demo.repository.SaleRepo;
import com.example.demo.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SaleImpl implements SaleService {
    private final SaleRepo saleRepo;

    @Override
    public Sale saveOrUpdate(Sale sale) {
        if (sale.getId() == null) {
            sale.setId(createId());
            sale.setEnable(0);
            return saleRepo.save(sale);
        }
        return saleRepo.save(sale);
    }

    @Override
    public Sale getById(String id) {
        return saleRepo.findSaleById(id);
    }

    @Override
    public String createId() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "KM" + String.format("%04d", number);
            List<Sale> sales = saleRepo.findAll();
            if (sales.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = sales.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }

    @Override
    public List<Sale> getAll() {
        return saleRepo.findAll();
    }

    @Override
    public List<Sale> getByProduct(Product product) {
        return saleRepo.findByProducts(product);
    }
}
