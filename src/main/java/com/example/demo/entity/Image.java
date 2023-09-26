package com.example.demo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@MappedSuperclass
@Data
@Getter
@Setter
public abstract class Image {
    @Id
    private String id;
    private String imageLink;
    private Date date;
    private String type;
    private String size;
}
