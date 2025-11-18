<p align="center">
  <img src="src/main/resources/logo.png" alt="PDFDancer logo" width="80" />
</p>

# PDFDancer Client Java Examples

Edit text in real-world PDFs — even ones you didn't create — from Java. This repository provides small, working examples of the [pdfdancer-api-client](https://central.sonatype.com/artifact/com.tfc.pdf.pdfdancer.api/pdfdancer-api-client), each focused on a single task.

## Prerequisites

- Java 11+
- A PDFDancer API token (`PDFDANCER_API_TOKEN`)
- Gradle (or use the included Gradle wrapper)

## Getting Started

```bash
# Set your API token
export PDFDANCER_API_TOKEN=your-token-here

# Build the project (defaults to Java 11 toolchain)
./gradlew build

# Or specify a Java version
export JAVA_VERSION=17
./gradlew build

# Run an example
./gradlew run --args="src/main/resources/Showcase.pdf"
```

## Available Examples

All examples are in `src/main/java/com/tfc/pdf/pdfdancer/examples/simple/`:

### InspectPDF.java
Inspect a PDF's basic structure - count pages, paragraphs, images, and form fields.

```bash
java -cp build/libs/* com.tfc.pdf.pdfdancer.examples.simple.InspectPDF src/main/resources/Showcase.pdf
```

### AddPage.java
Add a blank page to a PDF.

```bash
java -cp build/libs/* com.tfc.pdf.pdfdancer.examples.simple.AddPage \
  src/main/resources/Showcase.pdf output/with_page.pdf
```

### MovePage.java
Reorder pages in a PDF (moves page 0 to position 2).

```bash
java -cp build/libs/* com.tfc.pdf.pdfdancer.examples.simple.MovePage \
  src/main/resources/Showcase.pdf output/reordered.pdf
```

## Running from Your IDE

1. Open the project in IntelliJ IDEA or your preferred IDE
2. Set the `PDFDANCER_API_TOKEN` environment variable in your run configuration
3. Run any of the example classes directly

## Adding PDFDancer to Your Project

### Gradle

```kotlin
dependencies {
    implementation("com.tfc.pdf.pdfdancer.api:pdfdancer-api-client:0.1.1")
}
```

### Maven

```xml
<dependency>
    <groupId>com.tfc.pdf.pdfdancer.api</groupId>
    <artifactId>pdfdancer-api-client</artifactId>
    <version>0.1.1</version>
</dependency>
```

## Basic Usage Pattern

```java
import com.tfc.pdf.pdfdancer.api.client.rest.PDFDancer;
import java.io.File;

// 1. Get your API token
String token = System.getenv("PDFDANCER_API_TOKEN");

// 2. Open a PDF
PDFDancer pdf = PDFDancer.createSession(token, new File("document.pdf"));

// 3. Perform operations
pdf.selectParagraphs();  // Get all paragraphs
pdf.selectImages();       // Get all images
pdf.addPage();           // Add a blank page
pdf.movePage(0, 1);      // Move pages

// 4. Save the result
pdf.save("output.pdf");
```

## Helpful Links

- API Docs: https://docs.pdfdancer.com
- Maven Central: https://central.sonatype.com/artifact/com.tfc.pdf.pdfdancer.api/pdfdancer-api-client
- PDFDancer: https://www.pdfdancer.com
- Issues: https://github.com/MenschMachine/pdfdancer
