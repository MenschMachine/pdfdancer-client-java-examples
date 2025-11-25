package com.tfc.pdf.pdfdancer.examples.pages;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Pages 03 — Delete a specific page.
 */
public final class DeletePages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-pages/deleted_page.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, 4);
    }

    public static void runExample(File pdfPath, String outputPath, int pageNumber) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        if (pageNumber > pdf.getPages().size()) {
            throw new IllegalArgumentException("Page number " + pageNumber + " out of range.");
        }

        pdf.page(pageNumber + 1).delete();

        pdf.save(outputPath);
        System.out.println("Deleted page " + pageNumber + ". Document now has " + pdf.getPages().size() + " pages.");
    }
}
