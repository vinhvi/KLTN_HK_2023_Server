package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shopping_cart_details")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDetail  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;
    private int quantity;
}
