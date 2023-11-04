package com.example.demo.serviceImpl;

import com.example.demo.entity.ImportOrder;
import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.Order;
import com.example.demo.repository.ImportOrderDetailRepo;
import com.example.demo.repository.ImportOrderRepo;
import com.example.demo.service.ImportOrderDetailService;
import com.example.demo.service.ImportOrderService;
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
public class ImportOrderImpl implements ImportOrderService {

    private final ImportOrderRepo importOrderRepo;
    private final ImportOrderDetailService importOrderDetailService;
    @Override
    public ImportOrder saveOrUpdate(ImportOrder importOrder) {
        if (importOrder.getId() == null){
            importOrder.setId(randomId());
            Date currentDate = new Date();
            importOrder.setDate(currentDate);
            ImportOrder importOrderSaved = importOrderRepo.save(importOrder);
            List<ImportOrderDetail> importOrderDetails = new ArrayList<>();
            for (ImportOrderDetail importOrderDetail:importOrder.getImportOrderDetail()) {
                importOrderDetail.setImportOrder(importOrderSaved);
                importOrderDetails.add(importOrderDetailService.saveOrUpdate(importOrderDetail));
            }
            importOrderSaved.setImportOrderDetail(importOrderDetails);
            return importOrderRepo.save(importOrderSaved);
        }
        return importOrderRepo.save(importOrder);
    }

    @Override
    public List<ImportOrder> getAll() {
        return importOrderRepo.findAll();
    }

    @Override
    public ImportOrder getById(String id) {
        return importOrderRepo.findImportOrderById(id);
    }

    @Override
    public String randomId() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "PNH" + String.format("%04d", number);
            List<ImportOrder> importOrders = importOrderRepo.findAll();
            if (importOrders.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = importOrders.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }
}
