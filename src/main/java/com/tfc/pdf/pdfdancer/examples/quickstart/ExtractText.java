package com.tfc.pdf.pdfdancer.examples.quickstart;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.TextParagraphReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Quickstart 02 — Export Showcase.pdf text to a plaintext file.
 */
public final class ExtractText {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/quickstart/extracted_text.txt";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), Paths.get(OUTPUT_PATH));
    }

    public static void runExample(File pdfPath, Path outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        List<String> paragraphs = new ArrayList<>();
        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        for (TextParagraphReference para : pdf.selectParagraphs()) {
            String text = para.getText();
            if (text != null && !text.trim().isEmpty()) {
                paragraphs.add(text.strip());
            }
        }

        try {
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, String.join("\n\n", paragraphs));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write output file", e);
        }

        System.out.println("Exported " + paragraphs.size() + " paragraphs to " + outputPath);
        if (!paragraphs.isEmpty()) {
            int previewLimit = Math.min(3, paragraphs.size());
            List<String> preview = paragraphs.subList(0, previewLimit);
            System.out.println("\nPreview:\n" + String.join("\n", preview));
        }
    }
}
