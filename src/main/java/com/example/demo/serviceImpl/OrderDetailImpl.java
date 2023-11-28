package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderDetailRepo;
import com.example.demo.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderDetailImpl implements OrderDetailService {
    private final OrderDetailRepo orderDetailRepo;
    private final CartItemService cartItemService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final LoHangService loHangService;
    private final SaleDetailService saleDetailService;

    @Override
    public OrderDetail saveOrUpdate(int idCart, OrderDetail orderDetail) {
        ShoppingCart shoppingCart = shoppingCartService.getByIdCart(idCart);
        Product product = productService.getById(orderDetail.getProduct().getId());
        int sl = 0;
        LoHang loHangUpdate = new LoHang();
        for (LoHang loHang : loHangService.getByProduct(product)) {
            if (loHang.getStatus() == 1) {
                loHangUpdate = loHang;
                sl = loHang.getQuantity() - orderDetail.getQuantity();
                break;
            }
        }
        if (product.getQuantity() < 0) {
            return null;
        }
        int slUpdate = product.getQuantity() - orderDetail.getQuantity();
        if (slUpdate < 0 || sl < 0) {
            return null;
        }
        SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
        if (saleDetail != null) {
            orderDetail.setSaleId(saleDetail.getSales().getId());
        }
        loHangUpdate.setQuantity(sl);
        loHangService.saveOrUpdate(loHangUpdate);
        orderDetail.setLoHangId(loHangUpdate.getId());
        orderDetail.setPrice(product.getPrice());
        product.setQuantity(slUpdate);
        productService.saveOrUpdate(product);
        CartItem cartItem = cartItemService.getByProductAndCart(product, shoppingCart);
        cartItemService.remove(cartItem.getId());
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getByOrder(Order order) {
        return orderDetailRepo.findOrderDetailByOrder(order);
    }

    @Override
    public OrderDetail createNow(OrderDetail orderDetail) {
        Product product = productService.getById(orderDetail.getProduct().getId());
        System.out.println("aaaaa: " + product);
        int sl = 0;
        LoHang loHangUpdate = new LoHang();
        for (LoHang loHang : loHangService.getByProduct(product)) {
            if (loHang.getStatus() == 1) {
                loHangUpdate = loHang;
                sl = loHang.getQuantity() - orderDetail.getQuantity();
                break;
            }
        }
        if (product.getQuantity() < 0) {
            return null;
        }
        int slUpdate = product.getQuantity() - orderDetail.getQuantity();
        if (slUpdate < 0 || sl < 0) {
            return null;
        }
        SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
        if (saleDetail != null) {
            orderDetail.setSaleId(saleDetail.getSales().getId());
        }
        loHangUpdate.setQuantity(sl);
        loHangService.saveOrUpdate(loHangUpdate);
        orderDetail.setLoHangId(loHangUpdate.getId());
        orderDetail.setPrice(product.getPrice());
        product.setQuantity(slUpdate);
        productService.saveOrUpdate(product);
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetailRepo.findOrderDetailById(id);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepo.delete(orderDetail);
    }
}
