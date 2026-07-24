# PDFDancer Java examples

This branch contains examples compatible with the v3 SDK. Ordinary checkouts and CI use published SDK `3.0.0`; local SDK development can opt into the locally published `DEV` artifact.

Run one example with its Gradle task:

```bash
./gradlew runReplaceTextUsingSelector
```

Run every SDK-backed example with:

```bash
./gradlew -PpdfdancerSdkVersion=DEV runAllExamples
```

All examples use checked-in fixtures under `src/main/resources/` and write generated PDFs under `output/`. Examples that only list objects print their result and do not create an output PDF.

## Categories

### Simple

| Task | Class | Output |
| --- | --- | --- |
| `runSimpleAddPage` | `simple/AddPage.java` | `output/with_new_page.pdf` |
| `runSimpleMovePage` | `simple/MovePage.java` | `output/reordered.pdf` |

These two examples accept an optional input path and output path through `--args`. Their default input is `Showcase.pdf`.

### Forms

| Task | Class | Output |
| --- | --- | --- |
| `runListFields` | `forms/ListFields.java` | None; prints fields |
| `runFillFields` | `forms/FillFields.java` | `output/working-with-forms/filled.pdf` |
| `runCheckBoxes` | `forms/CheckBoxes.java` | `output/working-with-forms/checked.pdf` |
| `runClearFields` | `forms/ClearFields.java` | `output/working-with-forms/cleared.pdf` |

Run all forms examples with `runFormsExamples`.

### Pages

| Task | Class | Output |
| --- | --- | --- |
| `runReorderPages` | `pages/ReorderPages.java` | `output/working-with-pages/reordered.pdf` |
| `runExtractPages` | `pages/ExtractPages.java` | `output/working-with-pages/first_three_pages.pdf` |
| `runDeletePages` | `pages/DeletePages.java` | `output/working-with-pages/deleted_page.pdf` |
| `runAddBlankPage` | `pages/AddBlankPage.java` | `output/working-with-pages/extra_page.pdf` |

Run all page examples with `runPagesExamples`.

### Text

| Task | Class | Output |
| --- | --- | --- |
| `runReplaceTextUsingSelector` | `text/ReplaceTextUsingSelector.java` | `output/working-with-text/replaced_text.pdf` |
| `runDeleteTextUsingSelector` | `text/DeleteTextUsingSelector.java` | `output/working-with-text/deleted_text.pdf` |
| `runInsertTextAfterMatch` | `text/InsertTextAfterMatch.java` | `output/working-with-text/inserted_text.pdf` |
| `runStyleTextUsingSelector` | `text/StyleTextUsingSelector.java` | `output/working-with-text/styled_text.pdf` |

These examples select text by literal content or a matching range.

### Images

| Task | Class | Output |
| --- | --- | --- |
| `runMoveScaleRotateAndFlipImage` | `images/MoveScaleRotateAndFlipImage.java` | `output/working-with-images/moved_scaled_rotated_flipped_image.pdf` |
| `runListImages` | `images/ListImages.java` | None; prints image positions |
| `runMoveImage` | `images/MoveImage.java` | `output/working-with-images/moved_image.pdf` |
| `runDeleteImages` | `images/DeleteImages.java` | `output/working-with-images/no_images_page.pdf` |
| `runScaleImage` | `images/ScaleImage.java` | `output/working-with-images/scaled_image.pdf` |
| `runRotateImage` | `images/RotateImage.java` | `output/working-with-images/rotated_image.pdf` |
| `runCropImage` | `images/CropImage.java` | `output/working-with-images/cropped_image.pdf` |
| `runSetImageOpacity` | `images/SetImageOpacity.java` | `output/working-with-images/opacity_image.pdf` |
| `runFlipImage` | `images/FlipImage.java` | `output/working-with-images/flipped_image.pdf` |
| `runReplaceImage` | `images/ReplaceImage.java` | `output/working-with-images/replaced_image.pdf` |

Run all image examples with `runImagesExamples`.

### Paths

| Task | Class | Fixture | Output |
| --- | --- | --- | --- |
| `runChangePathColorsAndMove` | `paths/ChangePathColorsAndMove.java` | `basic-paths.pdf` | `output/working-with-paths/changed_path_colors_and_position.pdf` |
| `runListPaths` | `paths/ListPaths.java` | `basic-paths.pdf` | None; prints paths |
| `runGroupAndMovePaths` | `paths/GroupAndMovePaths.java` | `basic-paths.pdf` | `output/working-with-paths/moved_group.pdf` |
| `runScalePathGroup` | `paths/ScalePathGroup.java` | `basic-paths.pdf` | `output/working-with-paths/scaled_group.pdf` |
| `runRotatePathGroup` | `paths/RotatePathGroup.java` | `basic-paths.pdf` | `output/working-with-paths/rotated_group.pdf` |
| `runRemovePathGroup` | `paths/RemovePathGroup.java` | `basic-paths.pdf` | `output/working-with-paths/removed_group.pdf` |
| `runClearPathGroupClipping` | `paths/ClearPathGroupClipping.java` | `invisible-content-clipping-test.pdf` | `output/working-with-paths/cleared_group_clipping.pdf` |

Run all path examples with `runPathsExamples`.

### Capabilities

| Task | Class | Output |
| --- | --- | --- |
| `runCreatePagesAndDrawingObjects` | `capabilities/CreatePagesAndDrawingObjects.java` | `output/capabilities/created_pages_and_drawing_objects.pdf` |
| `runReadSnapshotsAndUseCoordinateSelectors` | `capabilities/ReadSnapshotsAndUseCoordinateSelectors.java` | None; prints selections |
| `runFindAndRegisterFonts` | `capabilities/FindAndRegisterFonts.java` | None; prints font results |
| `runFillRegionOfImage` | `capabilities/FillRegionOfImage.java` | `output/capabilities/filled_image_region.pdf` |
| `runGroupPathsInRegionAndResize` | `capabilities/GroupPathsInRegionAndResize.java` | `output/capabilities/grouped_paths_in_region_and_resized.pdf` |
| `runRegexReplaceAndStyleText` | `capabilities/RegexReplaceAndStyleText.java` | `output/capabilities/regex_replaced_and_styled_text.pdf` |

`runFindAndRegisterFonts` optionally registers the font at `PDFDANCER_FONT_PATH` when that environment variable is set.

## Useful Gradle commands

```bash
# List all example tasks
./gradlew tasks --group examples

# Verify source classes and runner registrations agree
./gradlew verifyExampleCatalog

# Run one category
./gradlew runImagesExamples

# Run the local release-candidate SDK
./gradlew -PpdfdancerSdkVersion=DEV runAllExamples

# Run the published SDK version used by CI
./gradlew -PpdfdancerSdkVersion=3.0.0 runAllExamples
```
