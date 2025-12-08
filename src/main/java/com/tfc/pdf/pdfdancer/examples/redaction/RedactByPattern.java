package com.tfc.pdf.pdfdancer.examples.redaction;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Redaction 04 — Redact paragraphs matching a regex pattern (e.g., SSN, email, phone).
 */
public final class RedactByPattern {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/redaction/redacted_by_pattern.pdf";
    // Pattern to match URLs (e.g., pdfdancer.com)
    private static final String URL_PATTERN = "\\b[a-zA-Z0-9.-]+\\.(com|org|net|io)\\b";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, URL_PATTERN);
    }

    public static void runExample(File pdfPath, String outputPath, String regexPattern) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        Pattern pattern = Pattern.compile(regexPattern);

        List<TextParagraphReference> paragraphs = pdf.selectParagraphs();
        int redactedCount = 0;

        for (TextParagraphReference paragraph : paragraphs) {
            String text = paragraph.getText();
            if (text != null && pattern.matcher(text).find()) {
                paragraph.redact().withReplacement("[REDACTED]").apply();
                redactedCount++;
            }
        }

        if (redactedCount == 0) {
            System.out.println("No paragraphs matched the pattern: " + regexPattern);
            return;
        }

        pdf.save(outputPath);
        System.out.println("Redacted " + redactedCount + " paragraph(s) matching pattern. Saved to " + outputPath + ".");
    }
}
