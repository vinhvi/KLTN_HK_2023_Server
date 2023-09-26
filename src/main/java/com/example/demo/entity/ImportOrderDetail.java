package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "import_order_details")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double importPrice;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "importOrder_id")
    private ImportOrder importOrder;
}
