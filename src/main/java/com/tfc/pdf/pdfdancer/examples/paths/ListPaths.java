package com.tfc.pdf.pdfdancer.examples.paths;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.PathReference;
import com.pdfdancer.common.model.BoundingRect;
import com.pdfdancer.common.model.Position;

import java.io.File;
import java.util.List;

/**
 * Working with Paths 01 — List all paths on page 1.
 */
public final class ListPaths {
    private static final String BASIC_PATHS_PATH = "src/main/resources/basic-paths.pdf";

    public static void main(String[] args) {
        runExample(new File(BASIC_PATHS_PATH));
    }

    public static void runExample(File pdfPath) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<PathReference> paths = pdf.page(1).selectPaths();
        if (paths.isEmpty()) {
            System.out.println("No paths found on page 1.");
            return;
        }

        System.out.println("Found " + paths.size() + " paths on page 1:\n");
        for (PathReference path : paths) {
            Position position = path.getPosition();
            String coords;
            if (position.getX() != null && position.getY() != null) {
                coords = String.format("(%.1f, %.1f)", position.getX(), position.getY());
            } else {
                coords = "(unknown coordinates)";
            }

            System.out.println("- ID: " + path.getInternalId() + " at " + coords);
        }
    }
}
