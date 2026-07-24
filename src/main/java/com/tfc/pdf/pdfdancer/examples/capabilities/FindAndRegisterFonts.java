package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import java.io.File;

public final class FindAndRegisterFonts {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        System.out.println("Matching fonts: " + pdf.findFonts("Helvetica", 12).size());
        if (System.getenv("PDFDANCER_FONT_PATH") != null) {
            System.out.println("Registered font: " + pdf.registerFont(new File(System.getenv("PDFDANCER_FONT_PATH"))));
        }
    }
}


