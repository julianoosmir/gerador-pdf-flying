package com.generate.service;

import com.lowagie.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import org.thymeleaf.templatemode.TemplateMode;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public PdfService() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("templates-pdf/");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public InputStream gerarInputStreamPdfDoHtml(String nomeTemplate, Context context) {
        return new ByteArrayInputStream(gerarPdfDoHtml(nomeTemplate, context));
    }

    public byte[] gerarPdfDoHtml(String nomeTemplate, Context context) {

        String html = parseThymeleafTemplate(nomeTemplate, context);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private String parseThymeleafTemplate(String template, Context context) {
        return templateEngine.process(template, context);
    }
}
