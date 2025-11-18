package com.tfc.pdf.pdfdancer.examples;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.PageRef;

import java.io.File;
import java.util.List;

/**
 * Minimal example showing how to bootstrap the PDFDancer client from a standalone project.
 *
 * NOTE: No API token needed! SDK automatically gets an anonymous token.
 * For production use, set PDFDANCER_API_TOKEN environment variable.
 */
public final class ExampleApp {
    private ExampleApp() {}

    public static void main(String[] args) {
        File inputPdf = resolveInputPdf(args);
        if (!inputPdf.exists()) {
            System.out.printf("Input PDF %s not found. Aborting.%n", inputPdf.getAbsolutePath());
            return;
        }

        PDFDancer client = PDFDancer.createSession(inputPdf);
        List<PageRef> pages = client.getPages();
        System.out.printf("Fetched %d pages for %s%n", pages.size(), inputPdf.getName());

        // Download the (unmodified) document back to disk
        client.save("output/example-app-output.pdf");
        System.out.println("Saved session PDF to output/example-app-output.pdf");
    }

    private static File resolveInputPdf(String[] args) {
        if (args.length > 0) {
            return new File(args[0]);
        }
        return new File("src/main/resources/Showcase.pdf");
    }
}
