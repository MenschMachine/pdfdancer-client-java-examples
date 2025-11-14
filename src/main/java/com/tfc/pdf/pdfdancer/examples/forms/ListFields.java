package com.tfc.pdf.pdfdancer.examples.forms;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.FormFieldReference;

import java.io.File;
import java.util.List;

/**
 * Working with Forms 01 — Inspect field names, types, and values.
 */
public final class ListFields {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH));
    }

    public static void runExample(File pdfPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<FormFieldReference> fields = pdf.selectFormFields();
        System.out.println("Found " + fields.size() + " form fields:\n");
        for (FormFieldReference field : fields) {
            String value = (field.getValue() == null || field.getValue().isEmpty()) ?
                "(empty)" : field.getValue();
            System.out.println("- " + field.getName() + " :: " + value);
        }
    }
}
