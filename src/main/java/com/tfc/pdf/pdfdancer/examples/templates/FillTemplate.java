package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Templates 01 — Fill placeholders in a template PDF.
 *
 * This example demonstrates basic template filling by replacing
 * placeholders like {{NAME}} with actual values.
 */
public final class FillTemplate {
    private static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    private static final String OUTPUT_PATH = "output/working-with-templates/filled_template.pdf";

    public static void main(String[] args) {
        runExample(new File(TEMPLATE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File templatePath, String outputPath) {
        if (!templatePath.exists()) {
            throw new IllegalArgumentException(
                "Template PDF not found: " + templatePath +
                ". Run CreateTemplatePDF first to generate it."
            );
        }

        PDFDancer pdf = PDFDancer.createSession(templatePath);

        // Fill all placeholders in the document using fluent API
        pdf.replace("{{RECIPIENT_NAME}}", "John Smith")
                .replace("{{COURSE_NAME}}", "Advanced Java Programming")
                .replace("{{DATE}}", "January 7, 2026")
                .replace("{{ISSUER_NAME}}", "PDFDancer Academy")
                .apply();

        pdf.save(outputPath);
        System.out.println("Filled template and saved to " + outputPath);
    }
}
