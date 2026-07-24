package com.tfc.pdf.pdfdancer.examples.capabilities;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.common.model.BoundingRect;
import java.io.File;

public final class GroupPathsInRegionAndResize {
    public static void main(String[] args) {
        PDFDancer pdf = PDFDancer.createSession(new File("src/main/resources/basic-paths.pdf"));
        var group = pdf.page(1).groupPathsInRegion(new BoundingRect(0, 0, 600, 800));
        // Preserve the original 260:480 aspect ratio while resizing the group.
        group.resize(162.5, 300);
        pdf.save("output/capabilities/grouped_paths_in_region_and_resized.pdf");
    }
}
