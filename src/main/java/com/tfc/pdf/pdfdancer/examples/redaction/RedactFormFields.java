package com.tfc.pdf.pdfdancer.examples.redaction;

import com.pdfdancer.client.rest.FormFieldReference;
import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;
import java.util.List;

/**
 * Redaction 05 — Redact form fields by name.
 */
public final class RedactFormFields {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/redaction/redacted_form_fields.pdf";
    private static final String[] FIELDS_TO_REDACT = {"Email", "Name"};

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, FIELDS_TO_REDACT);
    }

    public static void runExample(File pdfPath, String outputPath, String[] fieldNames) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        int redactedCount = 0;

        for (String fieldName : fieldNames) {
            List<FormFieldReference> fields = pdf.selectFormFieldsByName(fieldName);
            for (FormFieldReference field : fields) {
                field.redact("[REMOVED]");
                redactedCount++;
            }
        }

        if (redactedCount == 0) {
            System.out.println("No form fields found to redact.");
            return;
        }

        pdf.save(outputPath);
        System.out.println("Redacted " + redactedCount + " form field(s). Saved to " + outputPath + ".");
    }
}
