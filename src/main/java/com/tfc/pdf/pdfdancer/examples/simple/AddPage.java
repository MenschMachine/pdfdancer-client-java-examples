package com.tfc.pdf.pdfdancer.examples.simple;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Simple example: Add a blank page to a PDF.
 *
 * This example demonstrates:
 * - Opening a PDF
 * - Adding a new blank page
 * - Saving the modified PDF
 */
public final class AddPage {
    public static void main(String[] args) {
        String inputPath = args.length > 0 ? args[0] : "src/main/resources/Showcase.pdf";
        String outputPath = args.length > 1 ? args[1] : "output/with_new_page.pdf";

        File inputPdf = new File(inputPath);
        if (!inputPdf.exists()) {
            System.err.println("PDF not found: " + inputPath);
            System.exit(1);
        }

        PDFDancer pdf = PDFDancer.createSession(inputPdf);

        int originalPageCount = pdf.getPages().size();
        pdf.addPage();
        int newPageCount = pdf.getPages().size();

        pdf.save(outputPath);

        System.out.println("Added blank page");
        System.out.println("Original pages: " + originalPageCount);
        System.out.println("New pages: " + newPageCount);
        System.out.println("Saved to: " + outputPath);
    }
}
