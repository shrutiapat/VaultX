package com.vaultx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaultxApplication {
    public static void main(String[] args) {
        SpringApplication.run(VaultxApplication.class, args);
    }
}
