package com.generate;

import com.generate.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenerateApplication implements CommandLineRunner {

	@Autowired
	private PdfService pdfService;

	public static void main(String[] args) {
		SpringApplication.run(GenerateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pdfService.createPdf();
	}
}
