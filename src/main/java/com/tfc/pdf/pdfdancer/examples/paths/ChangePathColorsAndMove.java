package com.tfc.pdf.pdfdancer.examples.paths;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;
import java.io.File;

public final class ChangePathColorsAndMove {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/basic-paths.pdf"));
        var paths = pdf.selectPaths();
        if (paths.isEmpty()) throw new IllegalStateException("No paths found.");
        var path = paths.get(0);
        path.edit().strokeColor(Color.RED).fillColor(new Color(255, 255, 0)).apply();
        path.moveTo(180, 500);
        pdf.save("output/working-with-paths/changed_path_colors_and_position.pdf");
    }
}

