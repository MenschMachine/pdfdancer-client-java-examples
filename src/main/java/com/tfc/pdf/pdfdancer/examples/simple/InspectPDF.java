package com.tfc.pdf.pdfdancer.examples.simple;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.PageRef;

import java.io.File;
import java.util.List;

/**
 * Simple example: Inspect a PDF's basic structure.
 *
 * This example demonstrates:
 * - Opening a PDF with PDFDancer
 * - Counting pages
 * - Counting paragraphs, images, and form fields
 */
public final class InspectPDF {
    public static void main(String[] args) {
        String pdfPath = args.length > 0 ? args[0] : "src/main/resources/Showcase.pdf";
        File pdf = new File(pdfPath);

        if (!pdf.exists()) {
            System.err.println("PDF not found: " + pdfPath);
            System.exit(1);
        }

        PDFDancer dancer = PDFDancer.createSession(pdf);
        List<PageRef> pages = dancer.getPages();

        System.out.println("PDF Analysis");
        System.out.println("============");
        System.out.println("File: " + pdf.getName());
        System.out.println("Pages: " + pages.size());
        System.out.println("Paragraphs: " + dancer.selectParagraphs().size());
        System.out.println("Images: " + dancer.selectImages().size());
        System.out.println("Form Fields: " + dancer.selectFormFields().size());
    }
}
