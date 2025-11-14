package com.tfc.pdf.pdfdancer.examples.quickstart;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;
import com.pdfdancer.common.model.PageRef;
import com.pdfdancer.common.model.Position;

import java.io.File;
import java.util.List;

/**
 * Quickstart 01 — Inspect a PDF's high-level structure using Showcase.pdf.
 */
public final class InspectDocument {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH));
    }

    public static void runExample(File pdfPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PageRef> pages = pdf.getPages();
        System.out.println("Document Summary");
        System.out.println("================");
        System.out.println("Total pages: " + pages.size());
        System.out.println("Total paragraphs: " + pdf.selectParagraphs().size());
        System.out.println("Total text lines: " + pdf.selectTextLines().size());
        System.out.println("Total images: " + pdf.selectImages().size());
        System.out.println("Total form fields: " + pdf.selectFormFields().size());

        if (pages.isEmpty()) {
            System.out.println("\nThis PDF does not contain any pages.");
            return;
        }

        System.out.println("\nFirst Page Details");
        System.out.println("------------------");
        System.out.println("Page index: 0");
        System.out.println("Paragraphs on page: " + pdf.page(0).selectParagraphs().size());
        System.out.println("Images on page: " + pdf.page(0).selectImages().size());
        System.out.println("Form fields on page: " + pdf.page(0).selectFormFields().size());

        List<TextParagraphReference> sample = pdf.page(0).selectParagraphs();
        int limit = Math.min(5, sample.size());
        if (sample.isEmpty()) {
            System.out.println("\nNo paragraphs found on the first page.");
            return;
        }

        System.out.println("\nSample paragraphs:");
        for (int i = 0; i < limit; i++) {
            TextParagraphReference para = sample.get(i);
            Position position = para.getPosition();
            String coord = position == null ? "(?, ?)" :
                String.format("(%.1f, %.1f)", position.getX(), position.getY());
            String text = para.getText();
            String displayText = text != null ? shortenText(text.replace("\n", " "), 80) : "";
            System.out.println("- " + coord + " :: " + displayText);
        }
    }

    private static String shortenText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 1) + "…";
    }
}
