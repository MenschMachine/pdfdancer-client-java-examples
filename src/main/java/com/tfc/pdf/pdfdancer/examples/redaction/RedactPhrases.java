package com.tfc.pdf.pdfdancer.examples.redaction;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Redaction 01 — Redact paragraphs containing forbidden phrases.
 */
public final class RedactPhrases {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/redaction/redacted_phrases.pdf";
    private static final String[] TARGET_PHRASES = {"replaced", "pdfdancer.com"};

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, TARGET_PHRASES);
    }

    public static void runExample(File pdfPath, String outputPath, String[] phrases) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        String[] loweredPhrases = new String[phrases.length];
        for (int i = 0; i < phrases.length; i++) {
            loweredPhrases[i] = phrases[i].toLowerCase();
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<TextParagraphReference> matches = new ArrayList<>();
        for (TextParagraphReference paragraph : pdf.selectParagraphs()) {
            String text = paragraph.getText();
            if (text != null) {
                String lowerText = text.toLowerCase();
                for (String phrase : loweredPhrases) {
                    if (lowerText.contains(phrase)) {
                        matches.add(paragraph);
                        break;
                    }
                }
            }
        }

        if (matches.isEmpty()) {
            throw new IllegalStateException("No matching paragraphs found to redact.");
        }

        for (TextParagraphReference paragraph : matches) {
            paragraph.redact().withReplacement("[REDACTED]").apply();
        }

        pdf.save(outputPath);
        System.out.println("Redacted " + matches.size() + " paragraphs. Saved to " + outputPath + ".");
    }
}
