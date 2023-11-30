package com.example.demo.DataBean;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeProduct {
    private int tongSP;
    List<ProductTK> productTKS;
}
