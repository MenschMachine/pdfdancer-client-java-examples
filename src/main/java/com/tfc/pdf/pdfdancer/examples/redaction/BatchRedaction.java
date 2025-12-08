package com.tfc.pdf.pdfdancer.examples.redaction;

import com.pdfdancer.client.rest.BaseReference;
import com.pdfdancer.client.rest.ImageReference;
import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;
import com.pdfdancer.common.model.Color;
import com.pdfdancer.common.response.RedactResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Redaction 06 — Batch redaction of multiple objects at once.
 * Demonstrates collecting multiple objects (text, images) and redacting them in a single operation.
 */
public final class BatchRedaction {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/redaction/batch_redacted.pdf";
    private static final String[] TARGET_PHRASES = {"replaced", "pdfdancer.com"};
    private static final String URL_PATTERN = "\\b[a-zA-Z0-9.-]+\\.(com|org|net|io)\\b";
    private static final int IMAGE_PAGE = 3;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<BaseReference> objectsToRedact = new ArrayList<>();

        // Collect paragraphs containing target phrases
        String[] loweredPhrases = new String[TARGET_PHRASES.length];
        for (int i = 0; i < TARGET_PHRASES.length; i++) {
            loweredPhrases[i] = TARGET_PHRASES[i].toLowerCase();
        }

        for (TextParagraphReference paragraph : pdf.selectParagraphs()) {
            String text = paragraph.getText();
            if (text != null) {
                String lowerText = text.toLowerCase();
                for (String phrase : loweredPhrases) {
                    if (lowerText.contains(phrase)) {
                        objectsToRedact.add(paragraph);
                        break;
                    }
                }
            }
        }

        // Collect paragraphs matching URL pattern
        Pattern urlPattern = Pattern.compile(URL_PATTERN);
        for (TextParagraphReference paragraph : pdf.selectParagraphs()) {
            String text = paragraph.getText();
            if (text != null && urlPattern.matcher(text).find()) {
                if (!objectsToRedact.contains(paragraph)) {
                    objectsToRedact.add(paragraph);
                }
            }
        }

        // Collect images from specified page
        if (IMAGE_PAGE <= pdf.getPages().size()) {
            List<ImageReference> images = pdf.page(IMAGE_PAGE).selectImages();
            objectsToRedact.addAll(images);
        }

        if (objectsToRedact.isEmpty()) {
            System.out.println("No objects found to redact.");
            return;
        }

        // Batch redact all collected objects in a single operation
        RedactResponse result = pdf.redact(objectsToRedact, "[REDACTED]", Color.BLACK);

        if (!result.success()) {
            throw new IllegalStateException("Batch redaction failed.");
        }

        pdf.save(outputPath);
        System.out.println("Batch redacted " + result.count() + " object(s). Saved to " + outputPath + ".");
    }
}
