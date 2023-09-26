package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "import_orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrder {
    @Id
    private String id;
    private Date date;
    private int quantity;
    private double totalMoney;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employee employee;

    @OneToMany(mappedBy = "importOrder")
    private List<ImportOrderDetail> importOrderDetail;

}
