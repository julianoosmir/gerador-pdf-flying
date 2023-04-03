package com.generate;

import com.generate.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenerateApplication {

    @Autowired
    private PdfService pdfService;

    public static void main(String[] args) {
        SpringApplication.run(GenerateApplication.class, args);
    }

}
