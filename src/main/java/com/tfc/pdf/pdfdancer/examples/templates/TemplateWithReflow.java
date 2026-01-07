package com.tfc.pdf.pdfdancer.examples.templates;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.ReflowPreset;
import com.pdfdancer.common.request.TemplateReplacement;
import com.pdfdancer.common.request.TemplateReplaceRequest;

import java.io.File;

/**
 * Working with Templates 03 — Use text reflow for longer replacements.
 *
 * When replacement text is longer or shorter than the placeholder,
 * PDFDancer can automatically reflow the text to fit. This example
 * demonstrates using the reflow preset to handle varying text lengths.
 *
 * Reflow presets:
 * - BEST_EFFORT: Automatically adjusts text to fit (recommended)
 * - FIT_OR_FAIL: Fails if text doesn't fit in available space
 * - NONE: No reflow; text may overflow or be truncated
 */
public final class TemplateWithReflow {
    private static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    private static final String OUTPUT_PATH = "output/working-with-templates/template_with_reflow.pdf";

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

        // Fill placeholders with longer replacement text
        // Using BEST_EFFORT reflow to handle varying text lengths
        pdf.applyReplacements(TemplateReplaceRequest.builder()
                .addReplacement(new TemplateReplacement("{{RECIPIENT_NAME}}", "Dr. Alexander Christopher Wellington III", null, null))
                .addReplacement(new TemplateReplacement("{{COURSE_NAME}}", "Introduction to Machine Learning and Artificial Intelligence", null, null))
                .addReplacement(new TemplateReplacement("{{DATE}}", "January 7, 2026", null, null))
                .addReplacement(new TemplateReplacement("{{ISSUER_NAME}}", "PDFDancer Academy of Excellence", null, null))
                .reflowPreset(ReflowPreset.BEST_EFFORT)
                .build());

        pdf.save(outputPath);
        System.out.println("Filled template with reflow and saved to " + outputPath);
    }
}
