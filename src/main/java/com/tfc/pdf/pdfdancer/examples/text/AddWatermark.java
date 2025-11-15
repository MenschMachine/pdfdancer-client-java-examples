package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;
import com.pdfdancer.common.model.PageRef;

import java.io.File;
import java.util.List;

/**
 * Working with Text 06 — Add a DRAFT watermark to every page.
 */
public final class AddWatermark {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-text/watermarked.pdf";
    private static final String WATERMARK_TEXT = "DRAFT";
    private static final String WATERMARK_FONT = "Helvetica-Bold";
    private static final double WATERMARK_SIZE = 72.0;
    private static final Color WATERMARK_COLOR = new Color(200, 200, 200, 128);
    private static final double WATERMARK_X = 150.0;
    private static final double WATERMARK_Y = 400.0;

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PageRef> pages = pdf.getPages();
        for (int i = 0; i < pages.size(); i++) {
            pdf.newParagraph()
                .text(WATERMARK_TEXT)
                .font(WATERMARK_FONT, WATERMARK_SIZE)
                .color(WATERMARK_COLOR)
                .at(i, WATERMARK_X, WATERMARK_Y)
                .add();
        }

        pdf.save(outputPath);
        System.out.println("Watermarked " + pages.size() + " pages and saved to " + outputPath + ".");
    }
}
