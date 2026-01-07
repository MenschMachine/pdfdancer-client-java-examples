package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;
import com.pdfdancer.common.model.Font;
import com.pdfdancer.common.request.TemplateReplacement;
import com.pdfdancer.common.request.TemplateReplaceRequest;

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

        // Fill placeholders with custom formatting
        pdf.applyReplacements(TemplateReplaceRequest.builder()
                // Recipient name with custom bold font in dark blue
                .addReplacement(TemplateReplacement.withFormatting(
                        "{{RECIPIENT_NAME}}",
                        "Emily Watson",
                        new Font("Helvetica-Bold", 24),
                        new Color(0, 51, 102)  // Dark blue
                ))
                // Course name with custom font in teal
                .addReplacement(TemplateReplacement.withFormatting(
                        "{{COURSE_NAME}}",
                        "Data Science Fundamentals",
                        new Font("Helvetica-Bold", 18),
                        new Color(0, 128, 128)  // Teal
                ))
                // Simple replacements without custom formatting
                .addReplacement(new TemplateReplacement("{{DATE}}", "January 7, 2026", null, null))
                .addReplacement(new TemplateReplacement("{{ISSUER_NAME}}", "PDFDancer Academy", null, null))
                .build());

        pdf.save(outputPath);
        System.out.println("Filled template with custom formatting and saved to " + outputPath);
    }
}
