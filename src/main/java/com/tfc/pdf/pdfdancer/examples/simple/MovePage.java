package com.tfc.pdf.pdfdancer.examples.simple;

import com.pdfdancer.client.rest.PDFDancer;

import java.io.File;

/**
 * Simple example: Move a page to a different position.
 *
 * This example demonstrates:
 * - Reordering pages in a PDF
 * - Saving the modified PDF
 */
public final class MovePage {
    public static void main(String[] args) {
        String inputPath = args.length > 0 ? args[0] : "src/main/resources/Showcase.pdf";
        String outputPath = args.length > 1 ? args[1] : "output/reordered.pdf";

        File inputPdf = new File(inputPath);
        if (!inputPdf.exists()) {
            System.err.println("PDF not found: " + inputPath);
            System.exit(1);
        }

        PDFDancer pdf = PDFDancer.createSession(inputPdf);

        // Move page 0 to position 2
        pdf.movePage(0, 2);
        pdf.save(outputPath);

        System.out.println("Moved page 0 to position 2");
        System.out.println("Saved to: " + outputPath);
    }
}
