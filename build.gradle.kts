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
    implementation("com.pdfdancer.client:pdfdancer-client-java:0.1.4")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.13")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
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
createExampleTask("runRedactPhrases", "com.tfc.pdf.pdfdancer.examples.text.RedactPhrases", "Run RedactPhrases example")
createExampleTask("runHighlightMatches", "com.tfc.pdf.pdfdancer.examples.text.HighlightMatches", "Run HighlightMatches example")
createExampleTask("runMoveText", "com.tfc.pdf.pdfdancer.examples.text.MoveText", "Run MoveText example")
createExampleTask("runChangeFont", "com.tfc.pdf.pdfdancer.examples.text.ChangeFont", "Run ChangeFont example")
createExampleTask("runAddWatermark", "com.tfc.pdf.pdfdancer.examples.text.AddWatermark", "Run AddWatermark example")

// Working with images examples
createExampleTask("runListImages", "com.tfc.pdf.pdfdancer.examples.images.ListImages", "Run ListImages example")
createExampleTask("runMoveImage", "com.tfc.pdf.pdfdancer.examples.images.MoveImage", "Run MoveImage example")
createExampleTask("runDeleteImages", "com.tfc.pdf.pdfdancer.examples.images.DeleteImages", "Run DeleteImages example")

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
    dependsOn("runFindAndReplace", "runRedactPhrases", "runHighlightMatches",
              "runMoveText", "runChangeFont", "runAddWatermark")
}

tasks.register("runImagesExamples") {
    group = "examples"
    description = "Run all working with images examples"
    dependsOn("runListImages", "runMoveImage", "runDeleteImages")
}

// Master task to run all examples
tasks.register("runAllExamples") {
    group = "examples"
    description = "Run ALL examples (quickstart, simple, forms, pages, text, images)"
    dependsOn(
        "runQuickstartExamples",
        "runSimpleExamples",
        "runFormsExamples",
        "runPagesExamples",
        "runTextExamples",
        "runImagesExamples"
    )
}
