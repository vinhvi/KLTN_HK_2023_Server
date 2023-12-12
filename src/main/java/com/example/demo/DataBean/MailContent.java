package com.example.demo.DataBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailContent {

    private String to;
    private String subject;
    private String content;

}
