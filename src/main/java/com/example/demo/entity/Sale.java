package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String type;
    private double discount;
    @OneToMany(mappedBy = "sales")
    private List<SaleDetail> saleDetails;
}
