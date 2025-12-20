package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images — Rotate an image by a specified angle in degrees.
 */
public final class RotateImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/rotated_image.pdf";
    private static final double ROTATION_DEGREES = 90.0;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<ImageReference> images = pdf.page(1).selectImages();
        if (images.isEmpty()) {
            throw new IllegalStateException("No images found on page 1 to rotate.");
        }

        ImageReference image = images.get(0);
        image.rotate(ROTATION_DEGREES);

        pdf.save(outputPath);
        System.out.println("Rotated first image on page 1 by " + ROTATION_DEGREES + " degrees and saved to " + outputPath + ".");
    }
}
