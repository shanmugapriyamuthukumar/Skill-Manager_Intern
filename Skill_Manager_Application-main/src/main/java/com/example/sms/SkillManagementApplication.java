package com.example.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // scans com.example.sms and all subpackages by default
public class SkillManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillManagementApplication.class, args);
    }
}