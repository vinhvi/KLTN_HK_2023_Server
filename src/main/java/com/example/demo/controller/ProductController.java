package com.example.demo.controller;

import com.example.demo.DataBean.ProductDatabean;
import com.example.demo.DataBean.SaleDatabean;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final SaleService saleService;
    private final SaleDetailService saleDetailService;
    private final ImportOrderDetailService importOrderDetailService;
    private final ImportOrderService importOrderService;
    private final LoHangService loHangService;
    private final ProductSpecificationService productSpecificationService;

    @GetMapping("/randomId")
    public ResponseEntity<?> randomId() {
        try {
            return ResponseEntity.ok().body(productService.randomId());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute!!" + exception);
        }
    }

    @GetMapping("/getSPNB")
    public ResponseEntity<?> getSPNoiBat() {
        try {
            List<ProductDatabean> productDatabeans = new ArrayList<>();
            List<Product> products = productService.getAll();
            for (Product product : products) {
                ProductDatabean productDatabean = new ProductDatabean();
                SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
                SaleDatabean saleDatabean = new SaleDatabean();
                List<LoHang> loHangs = loHangService.getByProduct(product);
                for (LoHang loHang : loHangs) {
                    if (loHang.getStatus() == 1) {
                        productDatabean.setLoHang(loHang);
                        break;
                    }
                }
                if (saleDetail != null) {
                    Sale sale = saleService.getById(saleDetail.getSales().getId());
                    saleDatabean.setId(sale.getId());
                    saleDatabean.setType(sale.getType());
                    saleDatabean.setStart(sale.getStart());
                    saleDatabean.setEnd(sale.getEnd());
                    saleDatabean.setDescription(sale.getDescription());
                    saleDatabean.setDiscount(sale.getDiscount());
                    productDatabean.setSale(saleDatabean);
                    productDatabean.setImageProducts(product.getImageProducts());
                    productDatabean.setPrice(product.getPrice());
                    productDatabean.setDescription(product.getDescription());
                    productDatabean.setProductName(product.getProductName());
                    productDatabean.setQuantity(product.getQuantity());
                    productDatabean.setBrand(product.getBrand());
                    productDatabean.setId(product.getId());
                    productDatabean.setCategory(product.getCategory());
                    productDatabean.setSpecifications(product.getSpecifications());
                    productDatabeans.add(productDatabean);
                }
            }
            return ResponseEntity.ok().body(productDatabeans);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Product product) {
        try {
            Product check = productService.saveOrUpdate(product);
            if (check == null) {
                return ResponseEntity.badRequest().body("failed !!");
            }
            return ResponseEntity.ok().body(check);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Product> products = productService.getProducts();
            List<ProductDatabean> productDatabeans = new ArrayList<>();
            if (products.isEmpty()) {
                return ResponseEntity.ok().body("There are no products in the database yet!!");
            }
            for (Product product : products) {
                ProductDatabean productDatabean = new ProductDatabean();
                SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
                SaleDatabean saleDatabean = new SaleDatabean();
                List<LoHang> loHangs = loHangService.getByProduct(product);
                for (LoHang loHang : loHangs) {
                    if (loHang.getStatus() == 1) {
                        productDatabean.setLoHang(loHang);
                        break;
                    }
                }
                if (saleDetail != null) {
                    Sale sale = saleService.getById(saleDetail.getSales().getId());
                    saleDatabean.setId(sale.getId());
                    saleDatabean.setType(sale.getType());
                    saleDatabean.setStart(sale.getStart());
                    saleDatabean.setEnd(sale.getEnd());
                    saleDatabean.setDescription(sale.getDescription());
                    saleDatabean.setDiscount(sale.getDiscount());
                    productDatabean.setSale(saleDatabean);
                }
                productDatabean.setSpecifications(product.getSpecifications());
                productDatabean.setPrice(product.getPrice());
                productDatabean.setImageProducts(product.getImageProducts());
                productDatabean.setDescription(product.getDescription());
                productDatabean.setProductName(product.getProductName());
                productDatabean.setQuantity(product.getQuantity());
                productDatabean.setBrand(product.getBrand());
                productDatabean.setId(product.getId());
                productDatabean.setCategory(product.getCategory());
                productDatabeans.add(productDatabean);
            }
            return ResponseEntity.ok().body(productDatabeans);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        try {
            List<Product> products = productService.getByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.badRequest().body(name + " not found !!");
            }
            List<ProductDatabean> productDataBeans = new ArrayList<>();
            for (Product product : products) {
                ProductDatabean productDatabean = getProductDataBean(product);
                productDataBeans.add(productDatabean);
            }
            return ResponseEntity.ok().body(productDataBeans);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            Product product = productService.getById(id);
            if (product == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(getProductDataBean(product));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @GetMapping("/getListUpdate")
    public ResponseEntity<?> getListUpdate(){
        try {
            List<Product> productList = productService.listNeedUpdate();
            if (productList == null) {
                return ResponseEntity.badRequest().body("null !!");
            }
            return ResponseEntity.ok().body(productList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }


    private ProductDatabean getProductDataBean(Product product) {
        ProductDatabean productDatabean = new ProductDatabean();
        SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
        SaleDatabean saleDatabean = new SaleDatabean();
        List<LoHang> loHangs = loHangService.getByProduct(product);
        for (LoHang loHang : loHangs) {
            if (loHang.getStatus() == 1) {
                productDatabean.setLoHang(loHang);
                break;
            }
        }
        if (saleDetail != null) {
            Sale sale = saleService.getById(saleDetail.getSales().getId());
            saleDatabean.setId(sale.getId());
            saleDatabean.setType(sale.getType());
            saleDatabean.setStart(sale.getStart());
            saleDatabean.setEnd(sale.getEnd());
            saleDatabean.setDescription(sale.getDescription());
            saleDatabean.setDiscount(sale.getDiscount());
            productDatabean.setSale(saleDatabean);
        }
        productDatabean.setPrice(product.getPrice());
        productDatabean.setSpecifications(product.getSpecifications());
        productDatabean.setImageProducts(product.getImageProducts());
        productDatabean.setDescription(product.getDescription());
        productDatabean.setProductName(product.getProductName());
        productDatabean.setQuantity(product.getQuantity());
        productDatabean.setBrand(product.getBrand());
        productDatabean.setId(product.getId());
        productDatabean.setCategory(product.getCategory());

        return productDatabean;
    }

    @DeleteMapping("/delete_product/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            Product product = productService.getById(id);
            List<LoHang> loHangs = loHangService.getByProduct(product);
            for (LoHang loHang : loHangs) {
                ImportOrderDetail importOrderDetail = importOrderDetailService.getByLH(loHang);
                ImportOrder importOrder = importOrderService.getById(importOrderDetail.getImportOrder().getId());
                System.out.println(importOrder);
                for (ImportOrderDetail importOrderDetailDelete : importOrder.getImportOrderDetail()) {
                    importOrderDetailService.deleteById(importOrderDetailDelete);
                    loHangService.delete(importOrderDetailDelete.getLoHang());
                }
                importOrderService.delete(importOrder);

            }
            for (ProductSpecification productSpecification : product.getSpecifications()) {
                productSpecificationService.delete(productSpecification);
            }
            productService.delete(product);
            return ResponseEntity.ok().body("Success!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
