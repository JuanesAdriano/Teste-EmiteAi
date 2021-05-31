package com.emiteai.prova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class ProvaApplication {

    public static void main(String[] args) {
        System.out.println("Versão Spring:" + SpringVersion.getVersion());
        SpringApplication.run(ProvaApplication.class, args);
    }

}
