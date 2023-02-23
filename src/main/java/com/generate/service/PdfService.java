package com.generate.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

@Service
public class PdfService {
    private  static final String OUTPUT_FILE = "test.pdf";
    private static final String UTF_8 = "UTF-8";
    @Autowired
    private TemplateEngine templateEngine;
    public ClassLoaderTemplateResolver templeteResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding(UTF_8);
        return templateResolver;
    }

    public void createPdf() throws IOException, DocumentException {
        Context context = new Context();
        context.setVariable("name", "hellowoed");
        templateEngine.setTemplateResolver(templeteResolver());
        String processHtml = templateEngine.process("helloword", context);

        OutputStream outputStream = new FileOutputStream("message.pdf");
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources")
                .toUri()
                .toURL()
                .toString();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processHtml,baseUrl);
        renderer.layout();
        renderer.createPDF(outputStream);
        renderer.finishPDF();
        outputStream.close();
    }
}
