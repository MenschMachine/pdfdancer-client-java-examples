package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;
import com.pdfdancer.common.model.Color;

import java.io.File;
import java.util.List;

/**
 * Working with Text 03 — Highlight matching paragraphs in Showcase.pdf.
 */
public final class HighlightMatches {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/highlighted.pdf";
    private static final String TARGET_PATTERN = "alignment";
    private static final Color HIGHLIGHT_COLOR = new Color(255, 0, 0);

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, TARGET_PATTERN, HIGHLIGHT_COLOR);
    }

    public static void runExample(File pdfPath, String outputPath, String pattern, Color color) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<TextParagraphReference> matches = pdf.page(1).selectParagraphsMatching(pattern);
        if (matches.isEmpty()) {
            System.out.println("No paragraphs matched pattern: " + pattern + ". Skipping highlighting.");
            pdf.save(outputPath);
            return;
        }

        for (TextParagraphReference paragraph : matches) {
            paragraph.edit()
                .color(color)
                .font("Helvetica", 12.0)
                .apply();
        }

        pdf.save(outputPath);
        System.out.println("Highlighted " + matches.size() + " paragraphs and saved to " + outputPath + ".");
    }
}
