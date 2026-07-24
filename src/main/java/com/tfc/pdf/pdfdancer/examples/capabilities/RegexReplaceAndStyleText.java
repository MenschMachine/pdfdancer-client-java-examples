package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.request.TextReplaceRequest;
import com.pdfdancer.common.request.TextStyleRequest;
import java.io.File;

public final class RegexReplaceAndStyleText {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var replacement = TextReplaceRequest.regex("PDFDancer", "PDFDancer SDK")
                .maxMatches(2).build();
        System.out.println("Replaced: " + pdf.text().replace(replacement).changed());
        var style = TextStyleRequest.literal("PDFDancer").font("Helvetica-Bold").size(16).build();
        System.out.println("Styled: " + pdf.page(1).text().style(style).changed());
        pdf.save("output/capabilities/regex_replaced_and_styled_text.pdf");
    }
}
