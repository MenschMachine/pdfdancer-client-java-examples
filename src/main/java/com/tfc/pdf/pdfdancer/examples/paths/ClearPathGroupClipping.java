package com.tfc.pdf.pdfdancer.examples.paths;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.PathGroupReference;
import com.pdfdancer.client.rest.PathReference;

import java.io.File;
import java.util.List;

/**
 * Working with Paths 06 — Clear clipping from a grouped path to reveal hidden vector content.
 */
public final class ClearPathGroupClipping {
    private static final String CLIPPING_FIXTURE_PATH = "src/main/resources/invisible-content-clipping-test.pdf";
    private static final String OUTPUT_PATH = "output/working-with-paths/cleared_group_clipping.pdf";
    private static final String TARGET_PATH_ID = "PATH_0_000004";

    public static void main(String[] args) {
        runExample(new File(CLIPPING_FIXTURE_PATH), OUTPUT_PATH);
    }

    public static void runExample(File pdfPath, String outputPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PathReference> paths = pdf.page(1).selectPaths();
        boolean hasTargetPath = paths.stream()
                .anyMatch(path -> TARGET_PATH_ID.equals(path.getInternalId()));
        if (!hasTargetPath) {
            throw new IllegalStateException("Target clipped path " + TARGET_PATH_ID + " not found on page 1.");
        }

        PathGroupReference group = pdf.page(1).groupPaths(List.of(TARGET_PATH_ID));
        if (!group.clearClipping()) {
            throw new IllegalStateException("Failed to clear clipping for group " + group.getGroupId() + ".");
        }

        pdf.save(outputPath);
        System.out.println("Cleared clipping for grouped path " + TARGET_PATH_ID + " and saved to " + outputPath + ".");
    }
}
