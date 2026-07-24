package com.tfc.pdf.pdfdancer.examples.forms;

import com.pdfdancer.client.rest.FormFieldReference;
import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;
import java.util.List;

/**
 * Working with Forms 04 — Reset all fields to blank values.
 */
public final class ClearFields {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-forms/cleared.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<FormFieldReference> fields = pdf.selectFormFields();
        for (FormFieldReference field : fields) {
            if (field.isButton()) {
                continue;
            }
            // Clear all fields to empty string (or "Off" for checkboxes)
            if (field.isCheckBox()) {
                field.setValue("Off");
            } else {
                field.setValue("");
            }
        }

        pdf.save(outputPath);
        System.out.println("Cleared " + fields.size() + " fields and saved to " + outputPath + ".");
    }
}

