package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    private String id;
    private String description;
    private Date start;
    private Date end;
    private int enable;
    private double discount;
    @OneToMany(mappedBy = "sale")
    private List<Product> products;
}
