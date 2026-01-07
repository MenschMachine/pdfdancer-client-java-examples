package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.ReflowPreset;
import com.pdfdancer.common.request.TemplateReplacement;
import com.pdfdancer.common.request.TemplateReplaceRequest;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Working with Templates 05 — Mail merge: generate multiple documents from a template.
 *
 * This example demonstrates batch document generation by filling the same
 * template with different data for each recipient. Useful for certificates,
 * invoices, letters, and other personalized documents at scale.
 */
public final class MailMerge {
    private static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    private static final String OUTPUT_DIR = "output/working-with-templates/mail-merge";

    public static void main(String[] args) {
        runExample(new File(TEMPLATE_PATH), OUTPUT_DIR);
    }

    public static void runExample(File templatePath, String outputDir) {
        if (!templatePath.exists()) {
            throw new IllegalArgumentException(
                "Template PDF not found: " + templatePath +
                ". Run CreateTemplatePDF first to generate it."
            );
        }

        // Create output directory
        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        // Sample recipient data for mail merge
        List<Map<String, String>> recipients = List.of(
                Map.of(
                        "name", "Alice Johnson",
                        "course", "Java Programming Fundamentals",
                        "date", "January 5, 2026"
                ),
                Map.of(
                        "name", "Bob Smith",
                        "course", "Advanced Python Development",
                        "date", "January 6, 2026"
                ),
                Map.of(
                        "name", "Carol Williams",
                        "course", "Cloud Architecture Essentials",
                        "date", "January 7, 2026"
                )
        );

        System.out.println("Generating " + recipients.size() + " certificates...");

        for (int i = 0; i < recipients.size(); i++) {
            Map<String, String> recipient = recipients.get(i);
            String outputPath = outputDir + "/certificate_" + (i + 1) + ".pdf";

            // Create a new session for each document
            PDFDancer pdf = PDFDancer.createSession(templatePath);

            pdf.applyReplacements(TemplateReplaceRequest.builder()
                    .addReplacement(new TemplateReplacement("{{RECIPIENT_NAME}}", recipient.get("name"), null, null))
                    .addReplacement(new TemplateReplacement("{{COURSE_NAME}}", recipient.get("course"), null, null))
                    .addReplacement(new TemplateReplacement("{{DATE}}", recipient.get("date"), null, null))
                    .addReplacement(new TemplateReplacement("{{ISSUER_NAME}}", "PDFDancer Academy", null, null))
                    .reflowPreset(ReflowPreset.BEST_EFFORT)
                    .build());

            pdf.save(outputPath);
            System.out.println("  Generated certificate for " + recipient.get("name"));
        }

        System.out.println("Mail merge complete. Generated " + recipients.size() + " certificates in " + outputDir);
    }
}
