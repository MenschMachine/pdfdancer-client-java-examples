package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.util.List;

/**
 * Working with Text 05 — Apply a new font to the Showcase title.
 */
public final class ChangeFont {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/bold_title.pdf";
    private static final String PARAGRAPH_PREFIX = "PDFDancer";
    private static final String FONT_NAME = "Helvetica-Bold";
    private static final double FONT_SIZE = 24.0;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, PARAGRAPH_PREFIX);
    }

    public static void runExample(File pdfPath, String outputPath, String paragraphPrefix) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<TextParagraphReference> matches = pdf.page(1).selectParagraphsStartingWith(paragraphPrefix);
        if (matches.isEmpty()) {
            throw new IllegalStateException("No paragraph found starting with '" + paragraphPrefix + "'.");
        }

        matches.get(0).edit().font(FONT_NAME, FONT_SIZE).apply();

        pdf.save(outputPath);
        System.out.println("Updated font to " + FONT_NAME + " " + FONT_SIZE + "pt and saved to " + outputPath + ".");
    }
}
