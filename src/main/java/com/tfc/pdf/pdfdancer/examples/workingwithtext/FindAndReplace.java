package com.tfc.pdf.pdfdancer.examples.workingwithtext;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.util.List;

/**
 * Working with Text 01 — Replace the first matching paragraph.
 */
public final class FindAndReplace {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/find_and_replace.pdf";
    private static final String PARAGRAPH_PREFIX = "This line will be replaced";

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
            throw new IllegalStateException("No paragraphs found starting with '" + paragraphPrefix + "'.");
        }

        matches.get(0).edit()
            .replace("This line was replaced!\nUpdated with PDFDancer")
            .font("Helvetica", 12.0)
            .lineSpacing(1.1)
            .apply();

        pdf.save(outputPath);
        System.out.println("Saved updated PDF to " + outputPath);
    }
}
