package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.ImageReference;
import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;
import com.pdfdancer.common.model.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Working with Text 03 — Redact text containing specific phrases and images on a page.
 */
public final class RedactTextAndImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/redacted_text_and_image.pdf";
    private static final String[] TARGET_PHRASES = {"Monospace", "Underlined"};
    private static final int IMAGE_PAGE_NUMBER = 3;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, TARGET_PHRASES, IMAGE_PAGE_NUMBER);
    }

    public static void runExample(File pdfPath, String outputPath, String[] phrases, int imagePageNumber) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        String[] loweredPhrases = new String[phrases.length];
        for (int i = 0; i < phrases.length; i++) {
            loweredPhrases[i] = phrases[i].toLowerCase();
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);

        // Find paragraphs containing target phrases
        List<TextParagraphReference> matchingParagraphs = new ArrayList<>();
        for (TextParagraphReference paragraph : pdf.selectParagraphs()) {
            String text = paragraph.getText();
            if (text != null) {
                String lowerText = text.toLowerCase();
                for (String phrase : loweredPhrases) {
                    if (lowerText.contains(phrase)) {
                        matchingParagraphs.add(paragraph);
                        break;
                    }
                }
            }
        }

        // Redact matching paragraphs
        int redactedTextCount = 0;
        for (TextParagraphReference paragraph : matchingParagraphs) {
            paragraph.redact().apply();
            redactedTextCount++;
        }

        // Redact first image on specified page
        int redactedImageCount = 0;
        if (imagePageNumber <= pdf.getPages().size()) {
            List<ImageReference> images = pdf.page(imagePageNumber).selectImages();
            if (!images.isEmpty()) {
                images.get(0).redact().withColor(Color.BLACK).apply();
                redactedImageCount++;
                System.out.println("Images redacted on page " + imagePageNumber);
            }
        }

        if (redactedTextCount == 0) {
            throw new IllegalStateException("No text found to redact.");
        }
        if (redactedImageCount == 0) {
            throw new IllegalStateException("No image found to redact.");
        }

        pdf.save(outputPath);
        System.out.println("Redacted " + redactedTextCount + " paragraphs and " + redactedImageCount + " image(s). Saved to " + outputPath + ".");
    }
}
