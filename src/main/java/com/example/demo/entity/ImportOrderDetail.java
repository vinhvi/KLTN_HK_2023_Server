package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "import_order_details")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double importPrice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "importOrder_id")
    private ImportOrder importOrder;
}
