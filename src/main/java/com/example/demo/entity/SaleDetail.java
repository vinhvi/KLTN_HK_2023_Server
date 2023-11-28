package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sale_details")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int enable;
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sales;
}
