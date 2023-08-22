package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "specification_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String specCategoryName;
}
