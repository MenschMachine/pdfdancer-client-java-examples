package com.tfc.pdf.pdfdancer.examples.misc;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Misc 01 — Upload and download a large PDF file with timeout handling.
 */
public final class UploadLargeFile {
    private static final String LARGE_PDF_PATH = "src/main/resources/ISO 32000-2 FDIS.pdf";
    private static final String OUTPUT_PATH = "large-file.pdf";
    private static final int TIMEOUT_SECONDS = 120;
    private static final boolean skip = true;

    public static void main(String[] args) {
        runExample();
    }

    public static void runExample() {
        if (skip) {
            return;
        }
        File pdfPath = new File(LARGE_PDF_PATH);
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        System.setProperty("PDFDANCER_TIMEOUT", String.valueOf(TIMEOUT_SECONDS * 1000));

        long start = System.currentTimeMillis();
        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        long end = System.currentTimeMillis();
        System.out.printf("Uploading time: %.3f seconds%n", (end - start) / 1000.0);

        start = System.currentTimeMillis();
        try {
            pdf.save(OUTPUT_PATH);
        } finally {
            end = System.currentTimeMillis();
            System.out.printf("Saving time: %.3f seconds%n", (end - start) / 1000.0);
        }
    }
}
