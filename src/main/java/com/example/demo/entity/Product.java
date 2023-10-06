package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    private String id;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "brands_id")
    private Brand brand;

    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Date importDate;

    private int quantity;
    private double price;
    private double priceImport;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "suppliers_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<ProductSpecification> specifications;

    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProducts;


}
