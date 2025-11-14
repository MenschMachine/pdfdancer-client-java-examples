package com.tfc.pdf.pdfdancer.examples.workingwithpages;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Pages 02 — Extract the first N pages.
 */
public final class ExtractPages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-pages/first_three_pages.pdf";
    private static final int PAGES_TO_KEEP = 3;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, PAGES_TO_KEEP);
    }

    public static void runExample(File pdfPath, String outputPath, int pagesToKeep) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        if (pagesToKeep <= 0) {
            throw new IllegalArgumentException("pages_to_keep must be positive");
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        int totalPages = pdf.getPages().size();
        if (pagesToKeep > totalPages) {
            throw new IllegalArgumentException(
                "Document only has " + totalPages + " pages; cannot keep " + pagesToKeep + "."
            );
        }

        for (int index = totalPages - 1; index >= pagesToKeep; index--) {
            pdf.page(index).delete();
        }

        pdf.save(outputPath);
        System.out.println("Extracted first " + pagesToKeep + " pages into " + outputPath + ".");
    }
}
