package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;

import java.io.File;
import java.util.List;

/**
 * Working with Images 03 — Remove all images from a specific page.
 */
public final class DeleteImages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-images/no_images_page.pdf";
    private static final int TARGET_PAGE_INDEX = 2;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, TARGET_PAGE_INDEX);
    }

    public static void runExample(File pdfPath, String outputPath, int pageIndex) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        if (pageIndex >= pdf.getPages().size()) {
            throw new IllegalArgumentException("Page index " + pageIndex + " out of range.");
        }

        List<ImageReference> images = pdf.page(pageIndex).selectImages();
        if (images.isEmpty()) {
            throw new IllegalStateException("No images found on page " + pageIndex + " to delete.");
        }

        for (ImageReference image : images) {
            image.delete();
        }

        pdf.save(outputPath);
        System.out.println("Deleted " + images.size() + " images from page " + pageIndex + " and saved to " + outputPath + ".");
    }
}
