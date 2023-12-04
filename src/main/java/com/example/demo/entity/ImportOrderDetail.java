package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "import_order_details")
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

    @OneToOne
    @JoinColumn(name = "loHangs_id")
    private LoHang loHang;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "importOrder_id")
    private ImportOrder importOrder;
}
