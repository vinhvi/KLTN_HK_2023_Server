package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "product_specifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String specificationName;
    private String specificationValue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
