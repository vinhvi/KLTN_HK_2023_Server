package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Sale;
import com.example.demo.entity.SaleDetail;
import com.example.demo.service.ProductService;
import com.example.demo.service.SaleDetailService;
import com.example.demo.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {
    private final SaleService service;
    private final ProductService productService;
    private final SaleDetailService saleDetailService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Sale sale) {
        try {
            return ResponseEntity.ok().body(service.saveOrUpdate(sale));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{idKM}")
    public ResponseEntity<?> getById(@PathVariable("idKM") String idKM) {
        try {
            Sale sale = service.getById(idKM);
            if (sale == null) {
                return ResponseEntity.badRequest().body(idKM + " not found!");
            }
            return ResponseEntity.ok().body(sale);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Sale> saleList = service.getAll();
            if (saleList.isEmpty()) {
                return ResponseEntity.badRequest().body(" not found!");
            }
            return ResponseEntity.ok().body(saleList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getSaleByProduct/{idProduct}")
    public ResponseEntity<?> getByProduct(@PathVariable("idProduct") String idProduct) {
        try {
            Product product = productService.getById(idProduct);
            List<Sale> sales = service.getByProduct(product);
            if (sales.isEmpty()) {
                return ResponseEntity.badRequest().body("not found sale for " + product.getId());
            }
            return ResponseEntity.ok().body(sales);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/addSaleFoProduct/{idKM}")
    public ResponseEntity<?> addSaleFoProduct(@PathVariable("idKM") String idKM, List<Product> products) {
        try {
            Sale sale = service.getById(idKM);
            List<SaleDetail> saleDetails = new ArrayList<>();
            for (Product product : products) {
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setSales(sale);
                saleDetail.setProduct(product);
                saleDetails.add(saleDetailService.saveOrUpdate(saleDetail));
            }
            sale.setSaleDetails(saleDetails);
            return ResponseEntity.ok().body(service.saveOrUpdate(sale));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
