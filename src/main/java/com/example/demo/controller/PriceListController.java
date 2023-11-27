package com.example.demo.controller;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;
import com.example.demo.service.PriceListService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/priceLists")
public class PriceListController {
    private final PriceListService priceListService;
    private final ProductService productService;

    @PostMapping("/saveOrUpdate/{idProduct}")
    public ResponseEntity<?> saveOrUpdate(@PathVariable("idProduct") String idProduct, @RequestBody PriceList priceList) {
        try {
            Product product = productService.getById(idProduct);
            priceList.setProduct(product);
            return ResponseEntity.ok().body(priceListService.saveOrUpdate(priceList));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            PriceList priceList = priceListService.getById(id);
            if (priceList == null) {
                return ResponseEntity.badRequest().body(id + " not found!!");
            }
            return ResponseEntity.ok().body(priceList);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByProduct/{idProduct}")
    public ResponseEntity<?> getByProduct(@PathVariable("idProduct") String idProduct) {
        try {
            Product product = productService.getById(idProduct);
            List<PriceList> priceLists = priceListService.getByProduct(product);
            if (priceLists.isEmpty()) {
                return ResponseEntity.badRequest().body(idProduct + " not found!!");
            }
            return ResponseEntity.ok().body(priceLists);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
