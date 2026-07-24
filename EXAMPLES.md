# PDFDancer Java examples

The `release/v3` branch contains examples compatible with the current v3 SDK:

- `simple/` — add and move pages
- `forms/` — list and mutate form fields
- `pages/` — reorder, extract, delete, and add pages
- `images/` — list, move, delete, scale, rotate, crop, flip, replace, and set opacity
- `paths/` — list, group, move, scale, rotate, remove, and clear clipping

Run the complete compatible set with `./gradlew runAllExamples`.

The API-focused examples are:

- `text/ReplaceTextUsingSelector.java` — replace text selected by literal content
- `text/DeleteTextUsingSelector.java` — delete text selected by literal content
- `text/InsertTextAfterMatch.java` — insert text after a matching range
- `text/StyleTextUsingSelector.java` — style text selected by literal content
- `images/MoveScaleRotateAndFlipImage.java` — apply several image transformations
- `paths/ChangePathColorsAndMove.java` — change path colors and move a path

Additional capability coverage is under `capabilities/`: object creation,
snapshots and coordinate selectors, font lookup/registration, image fill-region,
path-group variants, and advanced text selectors.
