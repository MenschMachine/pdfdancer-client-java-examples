package com.tfc.pdf.pdfdancer.examples.pages;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Pages 01 — Move a page to a new index.
 */
public final class ReorderPages {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-pages/reordered.pdf";
    private static final int SOURCE_INDEX = 0;
    private static final int DEST_INDEX = 2;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, SOURCE_INDEX, DEST_INDEX);
    }

    public static void runExample(File pdfPath, String outputPath, int sourceIndex, int destIndex) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        if (sourceIndex >= pdf.getPages().size()) {
            throw new IllegalArgumentException("Source index " + sourceIndex + " out of range.");
        }

        pdf.movePage(sourceIndex, destIndex);

        pdf.save(outputPath);
        System.out.println("Moved page " + sourceIndex + " to position " + destIndex + ". Saved PDF to " + outputPath + ".");
    }
}
