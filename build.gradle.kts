plugins {
    java
    application
}

group = "com.tfc.pdf.pdfdancer.examples"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.pdfdancer.client:pdfdancer-client-java:0.2.7")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.13")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
}

java {
    // Use Java version from environment or default to 11
    val javaVersion = System.getenv("JAVA_VERSION")?.toIntOrNull() ?: 11
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

application {
    mainClass.set("com.tfc.pdf.pdfdancer.examples.ExampleApp")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Helper function to create example runner tasks
fun createExampleTask(taskName: String, mainClassName: String, description: String) {
    tasks.register<JavaExec>(taskName) {
        group = "examples"
        this.description = description
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set(mainClassName)
        standardOutput = System.out
        errorOutput = System.err
        // Fail the build if the example fails
        isIgnoreExitValue = false
    }
}

// Quickstart examples
createExampleTask("runInspectDocument", "com.tfc.pdf.pdfdancer.examples.quickstart.InspectDocument", "Run InspectDocument example")
createExampleTask("runExtractText", "com.tfc.pdf.pdfdancer.examples.quickstart.ExtractText", "Run ExtractText example")

// Simple examples
createExampleTask("runSimpleInspectPDF", "com.tfc.pdf.pdfdancer.examples.simple.InspectPDF", "Run simple InspectPDF example")
createExampleTask("runSimpleAddPage", "com.tfc.pdf.pdfdancer.examples.simple.AddPage", "Run simple AddPage example")
createExampleTask("runSimpleMovePage", "com.tfc.pdf.pdfdancer.examples.simple.MovePage", "Run simple MovePage example")

// Forms examples
createExampleTask("runListFields", "com.tfc.pdf.pdfdancer.examples.forms.ListFields", "Run ListFields example")
createExampleTask("runFillFields", "com.tfc.pdf.pdfdancer.examples.forms.FillFields", "Run FillFields example")
createExampleTask("runCheckBoxes", "com.tfc.pdf.pdfdancer.examples.forms.CheckBoxes", "Run CheckBoxes example")
createExampleTask("runClearFields", "com.tfc.pdf.pdfdancer.examples.forms.ClearFields", "Run ClearFields example")

// Working with pages examples
createExampleTask("runReorderPages", "com.tfc.pdf.pdfdancer.examples.pages.ReorderPages", "Run ReorderPages example")
createExampleTask("runExtractPages", "com.tfc.pdf.pdfdancer.examples.pages.ExtractPages", "Run ExtractPages example")
createExampleTask("runDeletePages", "com.tfc.pdf.pdfdancer.examples.pages.DeletePages", "Run DeletePages example")
createExampleTask("runAddBlankPage", "com.tfc.pdf.pdfdancer.examples.pages.AddBlankPage", "Run AddBlankPage example")

// Working with text examples
createExampleTask("runFindAndReplace", "com.tfc.pdf.pdfdancer.examples.text.FindAndReplace", "Run FindAndReplace example")
createExampleTask("runHighlightMatches", "com.tfc.pdf.pdfdancer.examples.text.HighlightMatches", "Run HighlightMatches example")
createExampleTask("runMoveText", "com.tfc.pdf.pdfdancer.examples.text.MoveText", "Run MoveText example")
createExampleTask("runChangeFont", "com.tfc.pdf.pdfdancer.examples.text.ChangeFont", "Run ChangeFont example")
createExampleTask("runAddWatermark", "com.tfc.pdf.pdfdancer.examples.text.AddWatermark", "Run AddWatermark example")

// Redaction examples
createExampleTask("runRedactPhrases", "com.tfc.pdf.pdfdancer.examples.redaction.RedactPhrases", "Run RedactPhrases example")
createExampleTask("runRedactTextAndImage", "com.tfc.pdf.pdfdancer.examples.redaction.RedactTextAndImage", "Run RedactTextAndImage example")
createExampleTask("runRedactImages", "com.tfc.pdf.pdfdancer.examples.redaction.RedactImages", "Run RedactImages example")
createExampleTask("runRedactByPattern", "com.tfc.pdf.pdfdancer.examples.redaction.RedactByPattern", "Run RedactByPattern example")
createExampleTask("runRedactFormFields", "com.tfc.pdf.pdfdancer.examples.redaction.RedactFormFields", "Run RedactFormFields example")

// Working with images examples
createExampleTask("runListImages", "com.tfc.pdf.pdfdancer.examples.images.ListImages", "Run ListImages example")
createExampleTask("runMoveImage", "com.tfc.pdf.pdfdancer.examples.images.MoveImage", "Run MoveImage example")
createExampleTask("runDeleteImages", "com.tfc.pdf.pdfdancer.examples.images.DeleteImages", "Run DeleteImages example")
createExampleTask("runScaleImage", "com.tfc.pdf.pdfdancer.examples.images.ScaleImage", "Run ScaleImage example")
createExampleTask("runRotateImage", "com.tfc.pdf.pdfdancer.examples.images.RotateImage", "Run RotateImage example")
createExampleTask("runCropImage", "com.tfc.pdf.pdfdancer.examples.images.CropImage", "Run CropImage example")
createExampleTask("runSetImageOpacity", "com.tfc.pdf.pdfdancer.examples.images.SetImageOpacity", "Run SetImageOpacity example")
createExampleTask("runFlipImage", "com.tfc.pdf.pdfdancer.examples.images.FlipImage", "Run FlipImage example")
createExampleTask("runReplaceImage", "com.tfc.pdf.pdfdancer.examples.images.ReplaceImage", "Run ReplaceImage example")

// Working with paths examples
createExampleTask("runListPaths", "com.tfc.pdf.pdfdancer.examples.paths.ListPaths", "Run ListPaths example")
createExampleTask("runGroupAndMovePaths", "com.tfc.pdf.pdfdancer.examples.paths.GroupAndMovePaths", "Run GroupAndMovePaths example")
createExampleTask("runScalePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.ScalePathGroup", "Run ScalePathGroup example")
createExampleTask("runRotatePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.RotatePathGroup", "Run RotatePathGroup example")
createExampleTask("runRemovePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.RemovePathGroup", "Run RemovePathGroup example")

// Working with templates examples
createExampleTask("runCreateTemplatePDF", "com.tfc.pdf.pdfdancer.examples.templates.CreateTemplatePDF", "Create sample template PDF")
createExampleTask("runFillTemplate", "com.tfc.pdf.pdfdancer.examples.templates.FillTemplate", "Run FillTemplate example")
createExampleTask("runFillTemplateByPage", "com.tfc.pdf.pdfdancer.examples.templates.FillTemplateByPage", "Run FillTemplateByPage example")
createExampleTask("runTemplateWithReflow", "com.tfc.pdf.pdfdancer.examples.templates.TemplateWithReflow", "Run TemplateWithReflow example")
createExampleTask("runTemplateWithFormatting", "com.tfc.pdf.pdfdancer.examples.templates.TemplateWithFormatting", "Run TemplateWithFormatting example")
createExampleTask("runMailMerge", "com.tfc.pdf.pdfdancer.examples.templates.MailMerge", "Run MailMerge example")

// Grouped tasks
tasks.register("runQuickstartExamples") {
    group = "examples"
    description = "Run all quickstart examples"
    dependsOn("runInspectDocument", "runExtractText")
}

tasks.register("runSimpleExamples") {
    group = "examples"
    description = "Run all simple examples"
    dependsOn("runSimpleInspectPDF", "runSimpleAddPage", "runSimpleMovePage")
}

tasks.register("runFormsExamples") {
    group = "examples"
    description = "Run all forms examples"
    dependsOn("runListFields", "runFillFields", "runCheckBoxes", "runClearFields")
}

tasks.register("runPagesExamples") {
    group = "examples"
    description = "Run all working with pages examples"
    dependsOn("runReorderPages", "runExtractPages", "runDeletePages", "runAddBlankPage")
}

tasks.register("runTextExamples") {
    group = "examples"
    description = "Run all working with text examples"
    dependsOn("runFindAndReplace", "runHighlightMatches",
              "runMoveText", "runChangeFont", "runAddWatermark")
}

tasks.register("runRedactionExamples") {
    group = "examples"
    description = "Run all redaction examples"
    dependsOn("runRedactPhrases", "runRedactTextAndImage", "runRedactImages",
              "runRedactByPattern", "runRedactFormFields")
}

tasks.register("runImagesExamples") {
    group = "examples"
    description = "Run all working with images examples"
    dependsOn("runListImages", "runMoveImage", "runDeleteImages",
              "runScaleImage", "runRotateImage", "runCropImage",
              "runSetImageOpacity", "runFlipImage", "runReplaceImage")
}

tasks.register("runPathsExamples") {
    group = "examples"
    description = "Run all working with paths examples"
    dependsOn("runListPaths", "runGroupAndMovePaths", "runScalePathGroup",
              "runRotatePathGroup", "runRemovePathGroup")
}

tasks.register("runTemplatesExamples") {
    group = "examples"
    description = "Run all working with templates examples"
    dependsOn("runCreateTemplatePDF", "runFillTemplate", "runFillTemplateByPage",
              "runTemplateWithReflow", "runTemplateWithFormatting", "runMailMerge")
}

// Master task to run all examples
tasks.register("runAllExamples") {
    group = "examples"
    description = "Run ALL examples (quickstart, simple, forms, pages, text, images, redaction, paths, templates)"
    dependsOn(
        "runQuickstartExamples",
        "runSimpleExamples",
        "runFormsExamples",
        "runPagesExamples",
        "runTextExamples",
        "runImagesExamples",
        "runRedactionExamples",
        "runPathsExamples",
        "runTemplatesExamples"
    )
}
