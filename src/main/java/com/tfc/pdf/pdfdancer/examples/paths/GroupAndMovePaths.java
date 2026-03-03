package com.tfc.pdf.pdfdancer.examples.paths;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.PathGroupReference;
import com.pdfdancer.client.rest.PathReference;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Working with Paths 02 — Group first 2 paths and move them.
 */
public final class GroupAndMovePaths {
    private static final String BASIC_PATHS_PATH = "src/main/resources/basic-paths.pdf";
    private static final String OUTPUT_PATH = "output/working-with-paths/moved_group.pdf";

    public static void main(String[] args) {
        runExample(new File(BASIC_PATHS_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PathReference> paths = pdf.page(1).selectPaths();
        if (paths.size() < 2) {
            throw new IllegalStateException("Need at least 2 paths on page 1, found " + paths.size() + ".");
        }

        List<String> pathIds = paths.subList(0, 2).stream()
                .map(PathReference::getInternalId)
                .collect(Collectors.toList());

        PathGroupReference group = pdf.page(1).groupPaths(pathIds);
        group.moveTo(200, 300);

        pdf.save(outputPath);
        System.out.println("Grouped " + group.getPathCount() + " paths, moved to (200, 300), saved to " + outputPath + ".");
    }
}
