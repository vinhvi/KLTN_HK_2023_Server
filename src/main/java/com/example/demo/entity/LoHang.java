package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "lo_hangs")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoHang {
    @Id
    private String id;
    private Date date;
    private int quantity;
    private int status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id")
    private Product product;
}
