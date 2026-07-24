package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TextReplaceRequest;
import java.io.File;

public final class ReplaceTextUsingSelector {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var response = pdf.text().replace(TextReplaceRequest.literal("PDFDancer", "PDFDancer SDK").build());
        System.out.println("Matched " + response.matched() + " text range(s).");
        pdf.save("output/working-with-text/replaced_text.pdf");
    }
}


