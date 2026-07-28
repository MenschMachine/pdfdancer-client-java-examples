<p align="center">
  <img src="src/main/resources/logo-silver-60h.webp" alt="PDFDancer logo" height="60" />
</p>

# PDFDancer Client Java Examples

Small Java programs demonstrating the PDFDancer v3 SDK for editing real-world PDFs.

## Prerequisites

- Java 17 or newer
- The included Gradle wrapper
- Network access to the PDFDancer API
- An API token is optional for these examples; the SDK uses an anonymous session when no token is supplied. Set `PDFDANCER_API_TOKEN` when authenticated requests are required.

## Quick start

The repository defaults to the published `3.0.0` SDK:

```bash
./gradlew clean build
./gradlew runSimpleAddPage
```

If you are developing against the locally published release-candidate artifact, opt into `DEV` explicitly:

```bash
./gradlew -PpdfdancerSdkVersion=DEV clean build
./gradlew -PpdfdancerSdkVersion=DEV runSimpleAddPage
```

The simple examples accept an optional input and output path:

```bash
./gradlew runSimpleAddPage \
  --args="src/main/resources/Showcase.pdf output/with_new_page.pdf"
```

To run the application smoke example, which reads the document and saves an unmodified copy:

```bash
./gradlew run \
  --args="src/main/resources/Showcase.pdf"
```

The `DEV` artifact must be installed in your local Maven repository. CI and release validation use the published SDK explicitly:

```bash
./gradlew -PpdfdancerSdkVersion=3.0.0 clean build
./gradlew -PpdfdancerSdkVersion=3.0.0 runAllExamples
```

## Example tasks

List all available tasks with:

```bash
./gradlew tasks --group examples
```

The category tasks are:

| Category | Gradle task | Coverage |
| --- | --- | --- |
| Simple | `runSimpleExamples` | Add and move pages |
| Forms | `runFormsExamples` | List, fill, check, and clear fields |
| Pages | `runPagesExamples` | Reorder, extract, delete, and add pages |
| Images | `runImagesExamples` | List, move, delete, scale, rotate, crop, flip, replace, and set opacity |
| Paths | `runPathsExamples` | List, group, move, scale, rotate, remove, and clear clipping |
| All | `runAllExamples` | Every SDK-backed example |

Text and capability examples have individual tasks, including `runReplaceTextUsingSelector`, `runStyleTextUsingSelector`, `runCreatePagesAndDrawingObjects`, and `runRegexReplaceAndStyleText`.

Every example uses a fixture from `src/main/resources/` and writes generated PDFs below `output/`. The complete catalog and output paths are in [EXAMPLES.md](EXAMPLES.md).

## Running from an IDE

Import the project as a Gradle project and run an example class or Gradle task. Set `PDFDANCER_API_TOKEN` in the run configuration only when authenticated requests are needed. Most examples use their checked-in fixture and do not accept command-line arguments.

## Using the SDK in another project

The published v3 dependency is:

### Gradle

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.pdfdancer.client:pdfdancer-client-java:3.0.0")
}
```

### Maven

```xml
<dependency>
    <groupId>com.pdfdancer.client</groupId>
    <artifactId>pdfdancer-client-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Basic usage

```java
import com.pdfdancer.client.rest.PDFDancer;
import java.io.File;

String token = System.getenv("PDFDANCER_API_TOKEN");
File input = new File("document.pdf");

PDFDancer pdf = token == null || token.isBlank()
        ? PDFDancer.createSession(input)
        : PDFDancer.createSession(token, input);

pdf.getPages();
pdf.selectImages();
pdf.addPage();
pdf.movePage(0, 1);
pdf.save("output.pdf");
```

## Contributing and validation

Before submitting changes, run:

```bash
./gradlew -PpdfdancerSdkVersion=DEV clean build verifyExampleCatalog
```

CI runs the same checks and the complete example set against SDK `3.0.0`.

## Helpful links

- [API documentation](https://docs.pdfdancer.com?utm_source=github&utm_medium=readme&utm_campaign=pdfdancer-java-examples)
- [Product overview](https://www.pdfdancer.com?utm_source=github&utm_medium=readme&utm_campaign=pdfdancer-java-examples)
- [Maven Central](https://central.sonatype.com/artifact/com.pdfdancer.client/pdfdancer-client-java)
- [Changelog](https://www.pdfdancer.com/changelog/?utm_source=github&utm_medium=readme&utm_campaign=pdfdancer-java-examples)
- [Status](https://status.pdfdancer.com?utm_source=github&utm_medium=readme&utm_campaign=pdfdancer-java-examples)
- [Issue tracker](https://github.com/MenschMachine/pdfdancer)
