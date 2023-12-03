package com.example.demo.DataBean;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeProduct {
    private int tongSP;
    List<ProductTK> productTKS;
}
