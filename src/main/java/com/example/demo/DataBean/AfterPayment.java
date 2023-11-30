package com.example.demo.DataBean;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AfterPayment {

    private String price;
    private String bankCode;
    private String date;
    private String status;
}
