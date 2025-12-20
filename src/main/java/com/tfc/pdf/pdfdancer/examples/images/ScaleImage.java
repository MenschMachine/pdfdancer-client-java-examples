package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images — Scale an image by a factor.
 */
public final class ScaleImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/scaled_image.pdf";
    private static final double SCALE_FACTOR = 0.5;

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
            throw new IllegalStateException("No images found on page 1 to scale.");
        }

        ImageReference image = images.get(0);
        image.scale(SCALE_FACTOR);

        pdf.save(outputPath);
        System.out.println("Scaled first image on page 1 by factor " + SCALE_FACTOR + " and saved to " + outputPath + ".");
    }
}
