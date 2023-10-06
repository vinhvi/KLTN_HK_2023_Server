package com.example.demo.DataBean;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCartDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDataBean {

    private int id;
    private Date date;
    private int quantity;
    private Customer customer;
    private List<ShoppingCartDetail> shoppingCartDetails;
}
