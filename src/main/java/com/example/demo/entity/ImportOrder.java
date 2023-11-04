package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "import_orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrder implements Serializable {
    @Id
    private String id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "suppliers_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employee employee;

    @OneToMany(mappedBy = "importOrder")
    private List<ImportOrderDetail> importOrderDetail;

}
