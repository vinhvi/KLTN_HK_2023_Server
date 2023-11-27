package com.example.demo.serviceImpl;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;
import com.example.demo.repository.PriceListRepo;
import com.example.demo.service.PriceListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PriceListImpl implements PriceListService {
    private final PriceListRepo priceListRepo;


    @Override
    public PriceList saveOrUpdate(PriceList priceList) {

        return priceListRepo.save(priceList);
    }

    @Override
    public PriceList getById(int id) {
        return priceListRepo.findPriceListById(id);
    }

    @Override
    public List<PriceList> getAll() {
        return priceListRepo.findAll();
    }

    @Override
    public List<PriceList> getByProduct(Product product) {
        return priceListRepo.findPriceListByProduct(product);
    }
}
