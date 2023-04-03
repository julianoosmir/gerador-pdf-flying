package com.generate.controller;

import java.io.InputStream;
import java.time.LocalDate;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.xml.sax.SAXException;


import com.generate.models.DadoDeclaracao;
import com.generate.service.PdfService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/pdf")
public class PdfController {

    @GetMapping
    public ResponseEntity<?> getPdf() throws SAXException, ParserConfigurationException {
        Context context = new Context();
        PdfService componente = new PdfService();
        DadoDeclaracao dadoDeclaracao = new DadoDeclaracao("joão", "03490113101", LocalDate.now());
        context.setVariable("dado", dadoDeclaracao);
        InputStream arq = componente.gerarInputStreamPdfDoHtml("declaracao-nada-consta", context);
        InputStreamResource inputStreamResource = new InputStreamResource(arq);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" +inputStreamResource.getFilename());

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }
}
