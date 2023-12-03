package com.example.demo.controller;

import com.example.demo.DataBean.ProductTK;
import com.example.demo.DataBean.ThongKeProduct;
import com.example.demo.entity.*;
import com.example.demo.service.ImportOrderService;
import com.example.demo.service.OrderService;
import com.example.demo.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCarts")
public class StatisticalController {
    private final OrderService orderService;
    private final SaleService saleService;
    private final ImportOrderService importOrderService;

    @GetMapping("/thong_ke_product_sale_by_moth/{date}")
    public ResponseEntity<?> staticalForMonth(@PathVariable("date") String date) {
        try {
            int month = 0;
            int year = 0;
            String[] parts = date.split("-");
            if (parts.length == 2) {
                month = Integer.parseInt(parts[0]);
                year = Integer.parseInt(parts[1]);
            } else {
                return ResponseEntity.badRequest().body("Invalid input format !!");
            }
            List<Order> orderList = orderService.getByDateBetween(month, year);

            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            List<ProductTK> productTKS = new ArrayList<>();
            ThongKeProduct thongKeProduct = new ThongKeProduct();
            int slB = 0;
            for (Order order : orderList) {
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    slB = slB + orderDetail.getQuantity();
                    Product product = orderDetail.getProduct();
                    ProductTK productTK = new ProductTK();
                    productTK.setType(product.getCategory().getCategoryName());
                    productTK.setSl(orderDetail.getQuantity());
                    if (productTKS.isEmpty()) {
                        productTKS.add(productTK);
                    }
                    boolean found = false;
                    for (ProductTK productTK1 : productTKS) {
                        if (productTK.getType().equals(productTK1.getType())) {
                            productTK1.setSl(productTK1.getSl() + orderDetail.getQuantity());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        productTKS.add(productTK);
                    }
                }
            }
            thongKeProduct.setTongSP(slB);
            thongKeProduct.setProductTKS(productTKS);
            return ResponseEntity.ok().body(thongKeProduct);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/thong_ke_tt_date/{date}")
    public ResponseEntity<?> getTTByDate(@PathVariable("date") String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateQuery = dateFormat.parse(date);
            List<Order> orderList = orderService.getByDate(dateQuery);
            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            double tt = 0;
            for (Order order : orderList) {
                if (order.getStatusOrder().equals("3")) {
                    for (OrderDetail orderDetail : order.getOrderDetails()) {
                        if (orderDetail.getSaleId() != null) {
                            Sale sale = saleService.getById(orderDetail.getSaleId());
                            tt = tt + (orderDetail.getProduct().getPrice() - (orderDetail.getProduct().getPrice() * (sale.getDiscount() / 100))) * orderDetail.getQuantity();
                        } else {
                            tt = tt + orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
                        }
                    }
                }
            }
            return ResponseEntity.ok().body(tt);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/thong_ke_TT_month/{date}")
    public ResponseEntity<?> getTTByMonth(@PathVariable("date") String date) {
        try {
            int month = 0;
            int year = 0;
            String[] parts = date.split("-");
            if (parts.length == 2) {
                month = Integer.parseInt(parts[0]);
                year = Integer.parseInt(parts[1]);
            } else {
                return ResponseEntity.badRequest().body("Invalid input format !!");
            }
            List<Order> orderList = orderService.getByDateBetween(month, year);
            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            double tt = 0;
            for (Order order : orderList) {
                if (order.getStatusOrder().equals("3")) {
                    for (OrderDetail orderDetail : order.getOrderDetails()) {
                        if (orderDetail.getSaleId() != null) {
                            Sale sale = saleService.getById(orderDetail.getSaleId());
                            tt = tt + (orderDetail.getProduct().getPrice() - (orderDetail.getProduct().getPrice() * (sale.getDiscount() / 100))) * orderDetail.getQuantity();
                        } else {
                            tt = tt + orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
                        }
                    }
                }
            }
            return ResponseEntity.ok().body(tt);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/thong_ke_product_import_by_month/{date}")
    public ResponseEntity<?> getSPByMonth(@PathVariable("date") String date) {
        try {
            int month = 0;
            int year = 0;
            String[] parts = date.split("-");
            if (parts.length == 2) {
                month = Integer.parseInt(parts[0]);
                year = Integer.parseInt(parts[1]);
            } else {
                return ResponseEntity.badRequest().body("Invalid input format !!");
            }

            List<ImportOrder> importOrderList = importOrderService.getByMonth(month, year);

            if (importOrderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }

            List<ProductTK> productTKS = new ArrayList<>();
            ThongKeProduct thongKeProduct = new ThongKeProduct();
            int slB = 0;

            for (ImportOrder importOrder : importOrderList) {
                for (ImportOrderDetail importOrderDetail : importOrder.getImportOrderDetail()) {
                    slB = slB + importOrderDetail.getQuantity();
                    Product product = importOrderDetail.getLoHang().getProduct();
                    ProductTK productTK = new ProductTK();
                    productTK.setType(product.getCategory().getCategoryName());
                    productTK.setSl(importOrderDetail.getQuantity());

                    // Sử dụng Iterator để tránh ConcurrentModificationException
                    boolean found = false;
                    for (ProductTK productTK1 : productTKS) {
                        if (productTK.getType().equals(productTK1.getType())) {
                            productTK1.setSl(productTK1.getSl() + importOrderDetail.getQuantity());
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        productTKS.add(productTK);
                    }
                }
            }

            thongKeProduct.setTongSP(slB);
            thongKeProduct.setProductTKS(productTKS);
            return ResponseEntity.ok().body(thongKeProduct);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }


}
