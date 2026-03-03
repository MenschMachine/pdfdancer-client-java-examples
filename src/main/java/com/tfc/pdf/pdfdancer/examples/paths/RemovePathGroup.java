package com.tfc.pdf.pdfdancer.examples.paths;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.PathGroupReference;
import com.pdfdancer.client.rest.PathReference;

import java.io.File;
import java.util.List;

/**
 * Working with Paths 05 — Group a path and remove it from the PDF.
 */
public final class RemovePathGroup {
    private static final String BASIC_PATHS_PATH = "src/main/resources/basic-paths.pdf";
    private static final String OUTPUT_PATH = "output/working-with-paths/removed_group.pdf";

    public static void main(String[] args) {
        runExample(new File(BASIC_PATHS_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PathReference> paths = pdf.page(1).selectPaths();
        if (paths.isEmpty()) {
            throw new IllegalStateException("No paths found on page 1 to remove.");
        }

        List<String> pathIds = List.of(paths.get(0).getInternalId());

        PathGroupReference group = pdf.page(1).groupPaths(pathIds);
        group.remove();

        pdf.save(outputPath);
        System.out.println("Removed path group with " + group.getPathCount() + " path(s), saved to " + outputPath + ".");
    }
}
