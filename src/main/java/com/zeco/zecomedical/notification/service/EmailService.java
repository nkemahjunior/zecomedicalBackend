package com.zeco.zecomedical.notification.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
 import jakarta.mail.internet.MimeMessage;
 import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
 import org.springframework.mail.MailException;
 import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
 import org.springframework.retry.annotation.Backoff;
 import org.springframework.retry.annotation.Retryable;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.time.LocalDate;
 import java.util.UUID;
 import java.util.concurrent.CompletableFuture;

 @Service
 @RequiredArgsConstructor
 @Log4j2
 public class EmailService {

     private final JavaMailSender javaMailSender;
     private final TemplateEngine templateEngine;

     @Async
     @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 30000, multiplier = 2))//30000 milliseconds = 30 seconds
     public CompletableFuture<Void> verifyEmailAddress(UUID token, String email)  {

         return  CompletableFuture.runAsync( () -> {

             try{

                 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                 MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

                 mimeMessageHelper.setFrom("nkemahjunior679205967@gmail.com");
                 mimeMessageHelper.setTo(email);
                 mimeMessageHelper.setSubject("Confirm your signup to zecomedical");

                 Context context = new Context();

                 context.setVariable("verificationUrl",  "http://localhost:8080/email/confirm-email?token="+token);
                 String processedString = templateEngine.process("emailVerification", context);

                 mimeMessageHelper.setText(processedString, true);
                 javaMailSender.send(mimeMessage);

             }catch(Exception e){
                 log.error("error sending verification mail :::" + e.getMessage());
             }


             }).exceptionally( (error) -> {
                log.error("error sending verification mail :::" + error.getMessage());

                return null;
             });

     }



     @Async
     @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 30000, multiplier = 2))
     public CompletableFuture<Void> appointmentStatusEmail(String patientName, String doctorName, String reason, LocalDate appointmentDate,String status){

         return  CompletableFuture.runAsync( () -> {
         MimeMessagePreparator preparator= new MimeMessagePreparator() {

             @Override
             public void prepare(MimeMessage mimeMessage) throws Exception {
             mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("nkemahjunior679205967@gmail.com"));
             mimeMessage.setFrom("nkemahjunior679205967@gmail.com");
             mimeMessage.setSubject("update on your appointment status");
             mimeMessage.setText("Hello "+patientName+", your appointment with Doctor "+doctorName +" on "+ appointmentDate +" for "+reason+" has been "+status);

         }};


             //int x = 10 / 0; coz of @Async this method is running on another thread ,not the main thread like all the other methods, so if any error happen in this method you will not see it boy

         javaMailSender.send(preparator);

         }).whenComplete( (result,error) -> {
             if(error != null ){
                    //coz of @Async by the time an exception happens the main thread will have already finish executing, so u will not see no error log from this method boy
                 log.error("error sending appointment status mail :::" + error.getMessage());
             }

         });
     }

 }



 /*MimeMessagePreparator preparator = new MimeMessagePreparator() {

 @Override
 public void prepare(MimeMessage mimeMessage) throws Exception {

 mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
 mimeMessage.setFrom("nkemahjunior679205967@gmail.com");
 mimeMessage.setSubject("Confirm your signup to zecomedical");
 mimeMessage.setText("This link will expire in 5 hours ,follow this  to confirm your email : http://localhost:8080/auth/confirm-email?token="+token);

 }};*/



//javaMailSender.send(preparator);


/**
 USE THIS SYNTAX  , IF YOU ARE SENDING A SIMPLE TEXT EMAIL MESSAGE

     public void sendSimpleMessage(String to, String subject, String text) {
         ...
         SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom("noreply@baeldung.com");
         message.setTo(to);
         message.setSubject(subject);
         message.setText(text);
         javaMailSender.send(message);
         ...
     }
***/







/** SYNTAX TO SEND HTML TEMPLATES
 public void sendMail() throws MessagingException {
 MimeMessage mimeMessage = javaMailSender.createMimeMessage();

 MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

 mimeMessageHelper.setFrom("nkemahjunior679205967@gmail.com");
 mimeMessageHelper.setTo("nkemahjunior679205967@gmail.com");
 mimeMessageHelper.setSubject("testing thymeleaf template");

 Context context = new Context();
 /*
 content is the variable defined in our HTML template within the div tag

 context.setVariable("content", "<h1>lets see</h1>");
 String processedString = templateEngine.process("emailHtml", context);

 mimeMessageHelper.setText(processedString, true);
 /** } else {
 mimeMessageHelper.setText(request.getMessage(), false);
 }

 javaMailSender.send(mimeMessage);
 }
**/













/********
 import jakarta.mail.Message;
 import jakarta.mail.internet.InternetAddress;
 import jakarta.mail.internet.MimeMessage;
 import lombok.RequiredArgsConstructor;
 import org.springframework.mail.MailException;
 import org.springframework.mail.javamail.JavaMailSender;
 import org.springframework.mail.javamail.MimeMessagePreparator;
 import org.springframework.retry.annotation.Backoff;
 import org.springframework.retry.annotation.Retryable;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Service;


 import java.time.LocalDate;
 import java.util.UUID;
 import java.util.concurrent.CompletableFuture;

 @Service
 @RequiredArgsConstructor
 public class EmailService {

 private final JavaMailSender javaMailSender;
 //private final TemplateEngine templateEngine;

 @Async
 @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 30000, multiplier = 2))//30000 milliseconds = 30 seconds
 public CompletableFuture<Void> verifyEmailAddress(UUID token, String email){


 MimeMessagePreparator preparator= new MimeMessagePreparator() {

 @Override
 public void prepare(MimeMessage mimeMessage) throws Exception {

 mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
 mimeMessage.setFrom("nkemahjunior679205967@gmail.com");
 mimeMessage.setSubject("Confirm your signup to zecomedical");
 mimeMessage.setText("This link will expire in 5 hours ,follow this  to confirm your email : http://localhost:8080/auth/confirm-email?token="+token);

 }
 };

 try{
 //throw new RuntimeException();
 javaMailSender.send(preparator);
 }catch (MailException ex) {
 System.err.println(ex.getMessage());
 }

 return null;
 }


 @Async
 @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 30000, multiplier = 2))
 public CompletableFuture<Void> appointmentStatusEmail(String patientName, String doctorName, String reason, LocalDate appointmentDate,String status){

 CompletableFuture<Boolean> future = new CompletableFuture<>();

 MimeMessagePreparator preparator= new MimeMessagePreparator() {

 @Override
 public void prepare(MimeMessage mimeMessage) throws Exception {
 mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("nkemahjunior679205967@gmail.com"));
 mimeMessage.setFrom("nkemahjunior679205967@gmail.com");
 mimeMessage.setSubject("update on your appointment status");
 mimeMessage.setText("Hello "+patientName+", your appointment with Doctor "+doctorName +" on "+ appointmentDate +" for "+reason+" has been "+status);

 }
 };

 try{
 javaMailSender.send(preparator);


 }catch (MailException ex) {

 System.err.println(ex.getMessage());
 }


 return null;
 }
 }
*/
