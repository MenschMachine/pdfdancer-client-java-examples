package com.tfc.pdf.pdfdancer.examples.redaction;

import com.pdfdancer.client.rest.ImageReference;
import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;

import java.io.File;
import java.util.List;

/**
 * Redaction 03 — Redact all images on a specific page with a colored placeholder.
 */
public final class RedactImages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/redaction/redacted_images.pdf";
    private static final int TARGET_PAGE = 3;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, TARGET_PAGE);
    }

    public static void runExample(File pdfPath, String outputPath, int pageNumber) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);

        if (pageNumber > pdf.getPages().size()) {
            throw new IllegalArgumentException("Page " + pageNumber + " does not exist. Document has " + pdf.getPages().size() + " pages.");
        }

        List<ImageReference> images = pdf.page(pageNumber).selectImages();

        if (images.isEmpty()) {
            throw new IllegalStateException("No images found on page " + pageNumber);
        }

        int redactedCount = 0;
        for (ImageReference image : images) {
            image.redact(Color.BLACK);
            redactedCount++;
        }

        pdf.save(outputPath);
        System.out.println("Redacted " + redactedCount + " image(s) on page " + pageNumber + ". Saved to " + outputPath + ".");
    }
}
