package com.example.demo.DataBean;

import com.example.demo.entity.Account;
import com.example.demo.entity.Avatar;
import com.example.demo.entity.ShoppingCart;
import lombok.*;

import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataBean {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private int sex;
    private String phone;
    private String address;
    private Avatar avatar;
    private Account account;
    private String customerType;
    private String token;
    private ShoppingCartDataBean shoppingCart;
}
