package com.tfc.pdf.pdfdancer.examples.workingwithimages;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.ImageReference;
import com.pdfdancer.common.model.BoundingRect;
import com.pdfdancer.common.model.Position;

import java.io.File;
import java.util.List;

/**
 * Working with Images 01 — List image positions in Showcase.pdf.
 */
public final class ListImages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH));
    }

    public static void runExample(File pdfPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<ImageReference> images = pdf.selectImages();
        if (images.isEmpty()) {
            System.out.println("No images found in this document.");
            return;
        }

        System.out.println("Found " + images.size() + " images:\n");
        for (ImageReference image : images) {
            Position position = image.getPosition();
            String size;
            if (position.getBoundingRect() != null) {
                BoundingRect rect = position.getBoundingRect();
                size = String.format("%.1f×%.1f",
                    rect.getWidth(),
                    rect.getHeight());
            } else {
                size = "unknown size";
            }

            String coords;
            if (position.getX() != null && position.getY() != null) {
                coords = String.format("(%.1f, %.1f)", position.getX(), position.getY());
            } else {
                coords = "(unknown coordinates)";
            }
            System.out.println("- Page " + position.getPageIndex() + ": " + coords + " — " + size);
        }
    }
}
