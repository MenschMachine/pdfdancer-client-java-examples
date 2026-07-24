package com.tfc.pdf.pdfdancer.examples.images;

import com.pdfdancer.client.rest.PDFDancer;
import java.io.File;

public final class MoveScaleRotateAndFlipImage {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var images = pdf.selectImages();
        if (images.isEmpty()) throw new IllegalStateException("No images found.");
        var image = images.get(0);
        image.moveTo(80, 80);
        image.scale(0.8);
        image.rotate(15);
        image.opacity(0.8);
        image.flipHorizontal();
        pdf.save("output/working-with-images/moved_scaled_rotated_flipped_image.pdf");
    }
}


