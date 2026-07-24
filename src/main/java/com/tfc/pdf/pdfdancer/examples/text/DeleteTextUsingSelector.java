package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TextDeleteRequest;
import java.io.File;

public final class DeleteTextUsingSelector {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var response = pdf.text().delete(TextDeleteRequest.literal("PDFDancer").build());
        System.out.println("Deleted " + response.changed() + " text range(s).");
        pdf.save("output/working-with-text/deleted_text.pdf");
    }
}


