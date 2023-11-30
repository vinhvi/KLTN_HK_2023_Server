package com.example.demo.DataBean;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDatabean {
    private String id;
    private String description;
    private Date start;
    private Date end;
    private String type;
    private double discount;
}
