package com.tfc.pdf.pdfdancer.examples.workingwithimages;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images 02 — Move the first page image to new coordinates.
 */
public final class MoveImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/moved_image.pdf";
    private static final double NEW_X = 60.0;
    private static final double NEW_Y = 60.0;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<ImageReference> images = pdf.page(0).selectImages();
        if (images.isEmpty()) {
            throw new IllegalStateException("No images found on page 0 to move.");
        }

        ImageReference image = images.get(0);
        image.moveTo(NEW_X, NEW_Y);

        pdf.save(outputPath);
        System.out.println("Moved first image on page 0 to (" + NEW_X + ", " + NEW_Y + ") and saved to " + outputPath + ".");
    }
}
