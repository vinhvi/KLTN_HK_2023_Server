package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avatars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avatar extends Image {
    private String name;
}
