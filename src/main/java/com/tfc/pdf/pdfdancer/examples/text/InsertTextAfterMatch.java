package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TextInsertRequest;
import java.io.File;

public final class InsertTextAfterMatch {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var response = pdf.text().insert(TextInsertRequest.after("PDFDancer", " — current SDK").build());
        System.out.println("Inserted at " + response.changed() + " target(s).");
        pdf.save("output/working-with-text/inserted_text.pdf");
    }
}


