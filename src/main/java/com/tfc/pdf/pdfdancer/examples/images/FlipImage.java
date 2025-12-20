package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;
import com.pdfdancer.common.request.ImageTransformRequest.FlipDirection;

import java.io.File;
import java.util.List;

/**
 * Working with Images — Flip an image horizontally, vertically, or both.
 */
public final class FlipImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/flipped_image.pdf";

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
            throw new IllegalStateException("No images found on page 1 to flip.");
        }

        ImageReference image = images.get(0);
        image.flip(FlipDirection.HORIZONTAL);

        pdf.save(outputPath);
        System.out.println("Flipped first image on page 1 horizontally and saved to " + outputPath + ".");
    }
}
