package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TemplateReplacement;
import com.pdfdancer.common.request.TemplateReplaceRequest;

import java.io.File;

/**
 * Working with Templates 02 — Fill placeholders on specific pages.
 *
 * This example demonstrates page-level template filling, useful when
 * the same placeholder appears on multiple pages but needs different
 * values per page (e.g., different headers per section).
 */
public final class FillTemplateByPage {
    private static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    private static final String OUTPUT_PATH = "output/working-with-templates/filled_by_page.pdf";

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

        // Fill placeholders on page 1 only (pageIndex is 0-based)
        pdf.applyReplacements(TemplateReplaceRequest.builder()
                .addReplacement(new TemplateReplacement("{{RECIPIENT_NAME}}", "Alice Johnson", null, null))
                .addReplacement(new TemplateReplacement("{{COURSE_NAME}}", "Web Development Fundamentals", null, null))
                .addReplacement(new TemplateReplacement("{{DATE}}", "January 7, 2026", null, null))
                .addReplacement(new TemplateReplacement("{{ISSUER_NAME}}", "PDFDancer Academy", null, null))
                .pageIndex(0)
                .build());

        pdf.save(outputPath);
        System.out.println("Filled page-specific placeholders and saved to " + outputPath);
    }
}
