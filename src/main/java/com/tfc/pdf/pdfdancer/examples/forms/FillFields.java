package com.tfc.pdf.pdfdancer.examples.forms;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.FormFieldReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Working with Forms 02 — Populate common fields by name.
 */
public final class FillFields {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-forms/filled.pdf";
    private static final Map<String, String> FIELD_UPDATES = new HashMap<>();

    static {
        FIELD_UPDATES.put("Name", "Ada Lovelace");
        FIELD_UPDATES.put("Email", "ada@example.com");
        FIELD_UPDATES.put("Subscribe", "Yes");
    }

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, FIELD_UPDATES);
    }

    public static void runExample(File pdfPath, String outputPath, Map<String, String> updates) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            List<FormFieldReference> matches = pdf.selectFormFieldsByName(name);
            if (matches.isEmpty()) {
                System.out.println("Skipping '" + name + "' — field not found");
                continue;
            }
            for (FormFieldReference field : matches) {
                field.setValue(value);
            }
        }

        pdf.save(outputPath);
        System.out.println("Filled " + updates.size() + " fields and saved to " + outputPath + ".");
    }
}

