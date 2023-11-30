package com.example.demo.DataBean;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfor {

    private String idOrder;
    private double price;

}
