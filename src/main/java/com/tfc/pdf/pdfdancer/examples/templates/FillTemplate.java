package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TemplateReplacement;
import com.pdfdancer.common.request.TemplateReplaceRequest;

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

        // Fill all placeholders in the document
        pdf.applyReplacements(TemplateReplaceRequest.builder()
                .addReplacement(new TemplateReplacement("{{RECIPIENT_NAME}}", "John Smith", null, null))
                .addReplacement(new TemplateReplacement("{{COURSE_NAME}}", "Advanced Java Programming", null, null))
                .addReplacement(new TemplateReplacement("{{DATE}}", "January 7, 2026", null, null))
                .addReplacement(new TemplateReplacement("{{ISSUER_NAME}}", "PDFDancer Academy", null, null))
                .build());

        pdf.save(outputPath);
        System.out.println("Filled template and saved to " + outputPath);
    }
}
