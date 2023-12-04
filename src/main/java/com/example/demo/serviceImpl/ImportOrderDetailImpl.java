package com.example.demo.serviceImpl;

import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.LoHang;
import com.example.demo.entity.Product;
import com.example.demo.repository.ImportOrderDetailRepo;
import com.example.demo.service.ImportOrderDetailService;
import com.example.demo.service.LoHangService;
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
    private final LoHangService loHangService;
    @Override
    public ImportOrderDetail saveOrUpdate(ImportOrderDetail importOrderDetail) {
        if(importOrderDetail.getId() == 0){
            LoHang loHang = importOrderDetail.getLoHang();
            loHang.setQuantity(importOrderDetail.getQuantity());
            importOrderDetail.setLoHang(loHangService.saveOrUpdate(loHang));
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

    @Override
    public ImportOrderDetail getByLH(LoHang loHang) {
        return repo.findImportOrderDetailByLoHang(loHang);
    }
}
