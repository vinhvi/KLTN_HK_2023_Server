package com.example.demo.DataBean;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfor {

    private String idOrder;
    private double price;
    private String status;
    private String url;
}
