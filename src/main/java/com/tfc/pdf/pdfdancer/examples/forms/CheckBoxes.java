package com.tfc.pdf.pdfdancer.examples.forms;

import com.pdfdancer.client.rest.PDFDancer;
import com.pdfdancer.client.rest.FormFieldReference;

import java.io.File;
import java.util.List;

/**
 * Working with Forms 03 — Toggle checkbox widgets.
 */
public final class CheckBoxes {
    private static final String SHOWCASE_PATH = "src/main/resources/Showcase.pdf";
    private static final String OUTPUT_PATH = "output/working-with-forms/checked.pdf";
    private static final String CHECKBOX_NAME = "Subscribe";
    private static final String CHECKED_VALUE = "Yes";

    public static void main(String[] args) {
        runExample(new File(SHOWCASE_PATH), OUTPUT_PATH, CHECKBOX_NAME, CHECKED_VALUE);
    }

    public static void runExample(File pdfPath, String outputPath, String checkboxName, String checkedValue) {
        if (!pdfPath.exists()) {
            throw new IllegalArgumentException("PDF file not found: " + pdfPath);
        }

        PDFDancer pdf = PDFDancer.createSession(pdfPath);
        List<FormFieldReference> matches = pdf.selectFormFieldsByName(checkboxName);
        if (matches.isEmpty()) {
            throw new IllegalStateException("No checkbox found with name '" + checkboxName + "'.");
        }

        for (FormFieldReference field : matches) {
            field.setValue(checkedValue);
        }

        pdf.save(outputPath);
        System.out.println("Checked '" + checkboxName + "' and saved to " + outputPath + ".");
    }
}
