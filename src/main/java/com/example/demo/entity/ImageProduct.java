package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imageProduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProduct extends Image {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
