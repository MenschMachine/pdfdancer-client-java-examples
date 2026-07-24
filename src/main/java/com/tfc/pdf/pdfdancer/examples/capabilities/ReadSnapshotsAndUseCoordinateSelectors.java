package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import java.io.File;

public final class ReadSnapshotsAndUseCoordinateSelectors {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/Showcase.pdf"));
        var document = pdf.getDocumentSnapshot();
        var page = pdf.page(1);
        var pageSnapshot = page.getSnapshot();
        var elements = page.selectElements();
        var imagesAtOrigin = page.selectImagesAt(60, 60, 10);
        var pathsAtOrigin = page.selectPathsAt(80, 580, 10);
        var forms = page.selectForms();
        System.out.println("Document pages: " + document.pages().size());
        System.out.println("Page elements: " + elements.size() + ", snapshot elements: " + pageSnapshot.elements().size());
        System.out.println("Images near (60,60): " + imagesAtOrigin.size());
        System.out.println("Paths near (80,580): " + pathsAtOrigin.size());
        System.out.println("Form XObjects: " + forms.size());
    }
}

