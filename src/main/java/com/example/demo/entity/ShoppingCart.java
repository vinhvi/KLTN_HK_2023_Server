package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
