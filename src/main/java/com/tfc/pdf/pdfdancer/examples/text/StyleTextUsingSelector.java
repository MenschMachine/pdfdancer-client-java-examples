package com.tfc.pdf.pdfdancer.examples.text;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.PdfColorRequest;
import com.pdfdancer.common.request.TextStyleRequest;
import java.io.File;

public final class StyleTextUsingSelector {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var response = pdf.text().style(TextStyleRequest.literal("PDFDancer")
                .font("Helvetica-Bold")
                .size(18)
                .fillColor(PdfColorRequest.rgb(0.8, 0.1, 0.1))
                .build());
        System.out.println("Styled " + response.changed() + " text range(s).");
        pdf.save("output/working-with-text/styled_text.pdf");
    }
}


