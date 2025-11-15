package com.tfc.pdf.pdfdancer.examples.pages;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Working with Pages 04 — Append a blank page with custom size.
 */
public final class AddBlankPage {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-pages/extra_page.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        pdf.addPage();

        pdf.save(outputPath);
        System.out.println("Added blank page. Total pages: " + pdf.getPages().size() + ".");
    }
}
