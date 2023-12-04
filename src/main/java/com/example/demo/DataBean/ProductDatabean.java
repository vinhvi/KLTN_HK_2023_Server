package com.example.demo.DataBean;

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDatabean {

    private String id;
    private String productName;

    private Brand brand;

    private String description;
    private Date importDate;

    private int quantity;
    private double price;

    private Category category;

    private List<ProductSpecification> specifications;

    private List<ImageProduct> imageProducts;

    private SaleDatabean sale;

    private LoHang loHang;
}
