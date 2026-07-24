package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.Color;
import java.io.File;

public final class CreatePagesAndDrawingObjects {
    public static void main(String[] args) throws Exception {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        pdf.newPage().a4().landscape().add();
        int page = 8;
        pdf.newLine(page).from(30, 30).to(180, 80).color(Color.RED).lineWidth(2).add();
        pdf.newBezier(page).from(40, 120).control1(80, 180).control2(150, 60).to(220, 120).color(Color.BLACK).add();
        pdf.newRectangle(page).at(40, 180).size(100, 60).color(Color.RED).add();
        pdf.newPath(page).rect(180, 170, 80, 60).color(Color.BLACK).fillColor(new Color(220, 220, 80)).add();
        pdf.newImage(page).fromFile(new File("src/main/resources/experiment.png")).at(120, 30).add();
        pdf.save("output/capabilities/created_pages_and_drawing_objects.pdf");
    }
}
