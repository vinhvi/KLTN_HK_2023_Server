package com.example.demo.DataBean;

import com.example.demo.entity.Account;
import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDataBean {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private int sex;
    private String phone;
    private String address;
    private String image;
    private Account account;
    private Date workdate;
    private String token;
}
