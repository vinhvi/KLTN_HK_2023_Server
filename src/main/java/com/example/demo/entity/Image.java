package com.example.demo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
@MappedSuperclass
@Data
@Getter
@Setter
public abstract class Image {
    @Id
    private String id;
    private String imageLink;
    private String date;
    private String type;
    private String size;
}
