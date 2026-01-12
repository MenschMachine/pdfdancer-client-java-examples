package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Templates 04 — Apply custom font and color to replacements.
 *
 * This example demonstrates how to apply custom formatting (font and color)
 * to specific placeholder replacements, allowing different visual styles
 * for different pieces of content.
 */
public final class TemplateWithFormatting {
    private static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    private static final String OUTPUT_PATH = "output/working-with-templates/template_with_formatting.pdf";

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

        // Recipient name with custom bold font in dark blue
        pdf.replace("{{RECIPIENT_NAME}}", "Emily Watson")
                .withFont("Helvetica-Bold", 24)
                .withColor(0, 51, 102)  // Dark blue
                .apply();

        // Course name with custom font in teal
        pdf.replace("{{COURSE_NAME}}", "Data Science Fundamentals")
                .withFont("Helvetica-Bold", 18)
                .withColor(0, 128, 128)  // Teal
                .apply();

        // Simple replacements without custom formatting
        pdf.replace("{{DATE}}", "January 7, 2026")
                .replace("{{ISSUER_NAME}}", "PDFDancer Academy")
                .apply();

        pdf.save(outputPath);
        System.out.println("Filled template with custom formatting and saved to " + outputPath);
    }
}
