package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;

import java.io.File;

/**
 * Helper — Creates a sample template PDF with placeholders for testing.
 *
 * This generates a simple certificate-style template with placeholders:
 * - {{RECIPIENT_NAME}} - Name of the recipient
 * - {{COURSE_NAME}} - Course or achievement name
 * - {{DATE}} - Date of completion
 * - {{ISSUER_NAME}} - Name of the issuer
 */
public final class CreateTemplatePDF {
    private static final String BASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "src/main/resources/Template.pdf";

    public static void main(String[] args) {
        createTemplate();
    }

    public static void createTemplate() {
        File baseFile = new File(BASE_PATH);
        if (!baseFile.exists()) {
            throw new IllegalArgumentException("Base PDF file not found: " + BASE_PATH);
        }

        PDFDancer pdf = PDFDancer.createSession(baseFile);

        // Add a new blank page for our template
        pdf.addPage();
        int templatePage = pdf.getPages().size();

        // Add certificate title
        pdf.newParagraph()
                .text("CERTIFICATE OF COMPLETION")
                .font("Helvetica-Bold", 28.0)
                .color(new Color(0, 51, 102))
                .at(templatePage, 120.0, 700.0)
                .add();

        // Add recipient label and placeholder
        pdf.newParagraph()
                .text("This certifies that")
                .font("Helvetica", 14.0)
                .color(new Color(80, 80, 80))
                .at(templatePage, 220.0, 620.0)
                .add();

        pdf.newParagraph()
                .text("{{RECIPIENT_NAME}}")
                .font("Helvetica-Bold", 24.0)
                .color(new Color(0, 0, 0))
                .at(templatePage, 180.0, 570.0)
                .add();

        // Add course description
        pdf.newParagraph()
                .text("has successfully completed the course")
                .font("Helvetica", 14.0)
                .color(new Color(80, 80, 80))
                .at(templatePage, 180.0, 510.0)
                .add();

        pdf.newParagraph()
                .text("{{COURSE_NAME}}")
                .font("Helvetica-Bold", 20.0)
                .color(new Color(0, 102, 153))
                .at(templatePage, 180.0, 460.0)
                .add();

        // Add date
        pdf.newParagraph()
                .text("Date: {{DATE}}")
                .font("Helvetica", 12.0)
                .color(new Color(80, 80, 80))
                .at(templatePage, 100.0, 350.0)
                .add();

        // Add issuer
        pdf.newParagraph()
                .text("Issued by: {{ISSUER_NAME}}")
                .font("Helvetica", 12.0)
                .color(new Color(80, 80, 80))
                .at(templatePage, 350.0, 350.0)
                .add();

        // Delete all pages except the template (keep only the last page)
        int totalPages = pdf.getPages().size();
        for (int i = totalPages - 1; i > 0; i--) {
            pdf.page(i).delete();
        }

        pdf.save(OUTPUT_PATH);
        System.out.println("Created template PDF at " + OUTPUT_PATH);
    }
}
