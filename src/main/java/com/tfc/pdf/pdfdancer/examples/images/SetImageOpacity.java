package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images — Set an image's opacity (transparency).
 */
public final class SetImageOpacity {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/opacity_image.pdf";
    private static final double OPACITY = 0.5;

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
            throw new IllegalStateException("No images found on page 1 to adjust opacity.");
        }

        ImageReference image = images.get(0);
        image.opacity(OPACITY);

        pdf.save(outputPath);
        System.out.println("Set first image on page 1 to " + (OPACITY * 100) + "% opacity and saved to " + outputPath + ".");
    }
}
