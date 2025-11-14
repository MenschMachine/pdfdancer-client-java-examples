package com.tfc.pdf.pdfdancer.examples.workingwithtext;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.util.List;

/**
 * Working with Text 04 — Move a paragraph to new coordinates.
 */
public final class MoveText {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/moved_text.pdf";
    private static final String PARAGRAPH_PREFIX = "This is regular";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, PARAGRAPH_PREFIX);
    }

    public static void runExample(File pdfPath, String outputPath, String paragraphPrefix) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<TextParagraphReference> matches = pdf.page(0).selectParagraphsStartingWith(paragraphPrefix);
        if (matches.isEmpty()) {
            throw new IllegalStateException("No paragraph found starting with '" + paragraphPrefix + "'.");
        }

        System.out.println(matches.get(0).getText());
        matches.get(0).edit().moveTo(50, 750).apply();

        pdf.save(outputPath);
        System.out.println("Moved paragraph to (50,750) and saved to " + outputPath + ".");
    }
}
