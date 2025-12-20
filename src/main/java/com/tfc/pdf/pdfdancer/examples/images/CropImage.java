package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images — Crop an image by trimming pixels from each edge.
 */
public final class CropImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/cropped_image.pdf";
    private static final int CROP_LEFT = 10;
    private static final int CROP_TOP = 10;
    private static final int CROP_RIGHT = 10;
    private static final int CROP_BOTTOM = 10;

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
            throw new IllegalStateException("No images found on page 1 to crop.");
        }

        ImageReference image = images.get(0);
        image.crop(CROP_LEFT, CROP_TOP, CROP_RIGHT, CROP_BOTTOM);

        pdf.save(outputPath);
        System.out.println("Cropped first image on page 1 by (" + CROP_LEFT + ", " + CROP_TOP + ", " + CROP_RIGHT + ", " + CROP_BOTTOM + ") and saved to " + outputPath + ".");
    }
}
