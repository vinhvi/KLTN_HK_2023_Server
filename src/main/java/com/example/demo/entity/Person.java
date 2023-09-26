package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Data
@Getter
@Setter
public abstract class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private int sex;
    private String phone;
    private String address;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;

}
