package com.example.demo.controller;

import com.example.demo.DataBean.ProductTK;
import com.example.demo.DataBean.ThongKeProduct;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCarts")
public class StatisticalController {
    private final OrderService orderService;

    @PostMapping("/thong_ke_thang/{date}")
    public ResponseEntity<?> staticalForMonth(@PathVariable("date") String date){
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
            List<Order> orderList = orderService.getByDate(month, year);

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
                    for (ProductTK productTK1 : productTKS) {
                        if (productTK.getType().equals(productTK1.getType())) {
                            productTK1.setSl(productTK1.getSl() + orderDetail.getQuantity());
                            break;
                        } else {
                            productTKS.add(productTK);
                        }
                    }
                }
            }
            thongKeProduct.setTongSP(slB);
            thongKeProduct.setProductTKS(productTKS);
            return ResponseEntity.badRequest().body(thongKeProduct);

        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
