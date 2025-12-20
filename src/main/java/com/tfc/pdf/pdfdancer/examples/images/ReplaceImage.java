package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Working with Images — Replace an existing image with a new one.
 */
public final class ReplaceImage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String REPLACEMENT_IMAGE_PATH = "src/main/resources/experiment.png";
    private static final String OUTPUT_PATH = "output/working-with-images/replaced_image.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), new File(REPLACEMENT_IMAGE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, File replacementImagePath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }
        if (!replacementImagePath.exists()) {
            throw new IllegalArgumentException("Replacement image not found: " + replacementImagePath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<ImageReference> images = pdf.page(1).selectImages();
        if (images.isEmpty()) {
            throw new IllegalStateException("No images found on page 1 to replace.");
        }

        ImageReference image = images.get(0);
        try {
            image.replace(replacementImagePath);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to replace image", e);
        }

        pdf.save(outputPath);
        System.out.println("Replaced first image on page 1 with " + replacementImagePath.getName() + " and saved to " + outputPath + ".");
    }
}
