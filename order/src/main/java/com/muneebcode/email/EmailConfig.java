package com.muneebcode.email;


import org.springframework.beans.factory.annotation.Value;


public class EmailConfig {

//For Spring
//For Spring boot it picks from application.yml

//    @Value("${email.smtp-server}")
//    private String host;
//    @Value("${email.port}")
//    private int port;
//
//    @Value("${email.username}")
//    private String username;
//
//    @Value("${email.password}")
//    private String password;

//    @Bean
//    public JavaMailSender javaMailSender() {
//        log.info("Configuring java email sender :");
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        log.info("port : {}", port);
//
//        log.info("Host : {}", host);
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//
//    }
}
