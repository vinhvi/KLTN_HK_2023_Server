package com.example.demo.controller;

import com.example.demo.DataBean.MailContent;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sendMail")
public class SendMailController {
    private final JavaMailSender javaMailSender;
    private final AccountService accountService;
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody MailContent mailContent) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mailContent.getTo());
            mailMessage.setSubject("MiVin-Shop Đổi mật khẩu");
            mailMessage.setText(mailContent.getContent());
            javaMailSender.send(mailMessage);
            return ResponseEntity.ok().body("Đã gửi email thành công!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<?> sendForgotPass(@PathVariable("email") String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("MiVin-Shop đặt lại mật khẩu");
            mailMessage.setText("http://localhost:8521/api/v1/resetPassword/" + email);
            javaMailSender.send(mailMessage);
            return ResponseEntity.ok().body("Đã gửi email thành công!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("MiVin-Shop mật khẩu mới");
            String newPassword = accountService.forgotPassword(email);
            if (newPassword.equals("")){
                mailMessage.setText("email không tồn tại!!");
                javaMailSender.send(mailMessage);
                return ResponseEntity.badRequest().body("thất bại!!");
            }
            mailMessage.setText(newPassword);
            javaMailSender.send(mailMessage);
            return ResponseEntity.ok().body("Đã gửi email thành công!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
