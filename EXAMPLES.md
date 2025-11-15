# PDFDancer Java Examples

This project contains comprehensive examples demonstrating how to use the PDFDancer Java SDK.

## Running Examples

You can run examples individually, by category, or all at once using Gradle tasks.

### Run All Examples

```bash
./gradlew runAllExamples
```

### Run Examples by Category

#### Quickstart Examples
```bash
./gradlew runQuickstartExamples
```
- InspectDocument - Inspect PDF structure
- ExtractText - Extract text from PDF

#### Simple Examples
```bash
./gradlew runSimpleExamples
```
- InspectPDF - Basic PDF inspection
- AddPage - Add blank pages
- MovePage - Reorder pages

#### Forms Examples
```bash
./gradlew runFormsExamples
```
- ListFields - List all form fields
- FillFields - Fill form fields with values
- CheckBoxes - Toggle checkboxes
- ClearFields - Clear all form fields

#### Working with Pages
```bash
./gradlew runPagesExamples
```
- ReorderPages - Move pages to new positions
- ExtractPages - Extract first N pages
- DeletePages - Delete specific pages
- AddBlankPage - Add blank pages with custom size

#### Working with Text
```bash
./gradlew runTextExamples
```
- FindAndReplace - Replace text in paragraphs
- RedactPhrases - Delete paragraphs containing phrases
- HighlightMatches - Highlight matching text
- MoveText - Move paragraphs to new coordinates
- ChangeFont - Apply new fonts to text
- AddWatermark - Add watermarks to all pages

#### Working with Images
```bash
./gradlew runImagesExamples
```
- ListImages - List all images and positions
- MoveImage - Move images to new coordinates
- DeleteImages - Remove images from pages

### Run Individual Examples

You can also run specific examples:

```bash
./gradlew runInspectDocument
./gradlew runFillFields
./gradlew runAddWatermark
# ... etc
```

## List All Available Tasks

To see all available example tasks:

```bash
./gradlew tasks --group examples
```

## Output Files

All examples save their output PDFs to the `output/` directory, organized by category:
- `output/quickstart/`
- `output/simple/`
- `output/working-with-forms/`
- `output/working-with-pages/`
- `output/working-with-text/`
- `output/working-with-images/`

## Requirements

- Java 11 or higher
- PDFDancer API token (optional - anonymous tokens are used by default)

## Environment Variables

You can optionally set these environment variables:

- `PDFDANCER_TOKEN` - Your API token for authenticated requests
- `PDFDANCER_BASE_URL` - Custom API endpoint (for self-hosted instances)

## Example Code Structure

All examples follow this structure:
```java
public static void main(String[] args) {
    runExample(inputFile, outputPath);
}

public static void runExample(File pdfPath, String outputPath) {
    PDFDancer pdf = PDFDancer.createSession(pdfPath);
    // ... PDF operations ...
    pdf.save(outputPath);
}
```

This makes it easy to integrate example code into your own applications.

## GitHub Actions

This repository includes an automated workflow (`.github/workflows/daily-examples.yml`):

- Runs all examples daily at 2 AM UTC
- Can be manually triggered from GitHub Actions tab
- Tests on Java 11, 17, and 21 to ensure compatibility
- Tests all example categories independently
- Uploads generated PDFs as artifacts (retained for 7 days, Java 21 only)
- Uses anonymous PDFDancer tokens by default (or `PDFDANCER_TOKEN` secret if configured)

The workflow can be triggered manually from the GitHub Actions tab for immediate testing.
