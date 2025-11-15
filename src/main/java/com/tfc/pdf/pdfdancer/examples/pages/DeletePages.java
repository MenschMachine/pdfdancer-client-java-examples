package com.tfc.pdf.pdfdancer.examples.pages;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Pages 03 — Delete a specific page.
 */
public final class DeletePages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-pages/deleted_page.pdf";
    private static final int PAGE_INDEX = 3;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, PAGE_INDEX);
    }

    public static void runExample(File pdfPath, String outputPath, int pageIndex) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        if (pageIndex >= pdf.getPages().size()) {
            throw new IllegalArgumentException("Page index " + pageIndex + " out of range.");
        }

        pdf.page(pageIndex).delete();

        pdf.save(outputPath);
        System.out.println("Deleted page " + pageIndex + ". Document now has " + pdf.getPages().size() + " pages.");
    }
}
