package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;
import java.io.File;

public final class FillRegionOfImage {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var images = pdf.selectImages();
        if (images.isEmpty()) throw new IllegalStateException("No images found.");
        images.get(0).fillRegion(0, 0, 10, 10, Color.WHITE);
        pdf.save("output/capabilities/filled_image_region.pdf");
    }
}

