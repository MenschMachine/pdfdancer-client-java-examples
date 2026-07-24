plugins {
    java
    application
}

group = "com.tfc.pdf.pdfdancer.examples"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.pdfdancer.client:pdfdancer-client-java:3.0.0")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.13")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
}

java {
    // Use Java version from environment or default to 11
    val javaVersion = System.getenv("JAVA_VERSION")?.toIntOrNull() ?: 17
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

// Simple examples
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

// Working with images examples
createExampleTask("runMoveScaleRotateAndFlipImage", "com.tfc.pdf.pdfdancer.examples.images.MoveScaleRotateAndFlipImage", "Run move, scale, rotate, and flip image example")
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
createExampleTask("runChangePathColorsAndMove", "com.tfc.pdf.pdfdancer.examples.paths.ChangePathColorsAndMove", "Run path color and movement example")
createExampleTask("runListPaths", "com.tfc.pdf.pdfdancer.examples.paths.ListPaths", "Run ListPaths example")
createExampleTask("runGroupAndMovePaths", "com.tfc.pdf.pdfdancer.examples.paths.GroupAndMovePaths", "Run GroupAndMovePaths example")
createExampleTask("runScalePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.ScalePathGroup", "Run ScalePathGroup example")
createExampleTask("runRotatePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.RotatePathGroup", "Run RotatePathGroup example")
createExampleTask("runRemovePathGroup", "com.tfc.pdf.pdfdancer.examples.paths.RemovePathGroup", "Run RemovePathGroup example")
createExampleTask("runClearPathGroupClipping", "com.tfc.pdf.pdfdancer.examples.paths.ClearPathGroupClipping", "Run ClearPathGroupClipping example")

// Grouped tasks
createExampleTask("runReplaceTextUsingSelector", "com.tfc.pdf.pdfdancer.examples.text.ReplaceTextUsingSelector", "Run text replacement using a selector example")
createExampleTask("runDeleteTextUsingSelector", "com.tfc.pdf.pdfdancer.examples.text.DeleteTextUsingSelector", "Run text deletion using a selector example")
createExampleTask("runInsertTextAfterMatch", "com.tfc.pdf.pdfdancer.examples.text.InsertTextAfterMatch", "Run text insertion after a match example")
createExampleTask("runStyleTextUsingSelector", "com.tfc.pdf.pdfdancer.examples.text.StyleTextUsingSelector", "Run text styling using a selector example")
createExampleTask("runCreatePagesAndDrawingObjects", "com.tfc.pdf.pdfdancer.examples.capabilities.CreatePagesAndDrawingObjects", "Run page and drawing object creation example")
createExampleTask("runReadSnapshotsAndUseCoordinateSelectors", "com.tfc.pdf.pdfdancer.examples.capabilities.ReadSnapshotsAndUseCoordinateSelectors", "Run snapshots and coordinate selectors example")
createExampleTask("runFindAndRegisterFonts", "com.tfc.pdf.pdfdancer.examples.capabilities.FindAndRegisterFonts", "Run font lookup and registration example")
createExampleTask("runFillRegionOfImage", "com.tfc.pdf.pdfdancer.examples.capabilities.FillRegionOfImage", "Run image region fill example")
createExampleTask("runGroupPathsInRegionAndResize", "com.tfc.pdf.pdfdancer.examples.capabilities.GroupPathsInRegionAndResize", "Run path grouping and resizing example")
createExampleTask("runRegexReplaceAndStyleText", "com.tfc.pdf.pdfdancer.examples.capabilities.RegexReplaceAndStyleText", "Run regex text replacement and styling example")

tasks.register("runSimpleExamples") {
    group = "examples"
    description = "Run all simple examples"
    dependsOn("runSimpleAddPage", "runSimpleMovePage")
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

tasks.register("runImagesExamples") {
    group = "examples"
    description = "Run all working with images examples"
    dependsOn("runMoveScaleRotateAndFlipImage", "runListImages", "runMoveImage", "runDeleteImages",
              "runScaleImage", "runRotateImage", "runCropImage",
              "runSetImageOpacity", "runFlipImage", "runReplaceImage")
}

tasks.register("runPathsExamples") {
    group = "examples"
    description = "Run all working with paths examples"
    dependsOn("runChangePathColorsAndMove", "runListPaths", "runGroupAndMovePaths", "runScalePathGroup",
              "runRotatePathGroup", "runRemovePathGroup", "runClearPathGroupClipping")
}

// Master task to run all examples
tasks.register("runAllExamples") {
    group = "examples"
    description = "Run all examples compatible with the v3 SDK"
    dependsOn(
        "runSimpleExamples",
        "runFormsExamples",
        "runPagesExamples",
        "runReplaceTextUsingSelector",
        "runDeleteTextUsingSelector",
        "runInsertTextAfterMatch",
        "runStyleTextUsingSelector",
        "runCreatePagesAndDrawingObjects",
        "runReadSnapshotsAndUseCoordinateSelectors",
        "runFindAndRegisterFonts",
        "runFillRegionOfImage",
        "runGroupPathsInRegionAndResize",
        "runRegexReplaceAndStyleText",
        "runImagesExamples",
        "runPathsExamples"
    )
}
