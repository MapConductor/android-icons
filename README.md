# MapConductor Icons for Android

Map-ready Jetpack Compose icon containers and region-neutral glyphs. The map SDK draws the map; this package draws application-owned markers such as a hospital symbol inside a pin, circle, flag, or information bubble.

Locale never changes an icon automatically. Choose a regional pack explicitly when local conventions matter.

## Installation

Add Maven Central and the library dependency:

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories { mavenCentral() }
}

// app/build.gradle.kts
dependencies {
    implementation("com.mapconductor:icons:1.2.0")
}
```

The glyph API documented below is available on the `1.3.0` branch and will be included in the next registry release.

## Quick start

```kotlin
import androidx.compose.ui.graphics.Color
import com.mapconductor.icons.CommonMapIcons
import com.mapconductor.icons.PinGlyphIcon

val hospitalMarker = PinGlyphIcon(
    glyph = CommonMapIcons.hospital,
    fillColor = Color(0xFFE53935),
    glyphColor = Color.White,
)
```

`PinGlyphIcon` is ready to use as a MapConductor marker icon. The package also provides `CircleIcon`, `FlagIcon`, `RoundInfoBubbleIcon`, and `RightTailInfoBubbleIcon` for their existing image and label use cases. Rendered bitmap icons are cached by MapConductor Core.

## Regional packs

- [Japan](https://github.com/MapConductor/android-icons-jp)
- [United States](https://github.com/MapConductor/android-icons-us)
- [Weather](https://github.com/MapConductor/android-icons-weather)

## Contributing icons

SVG artwork uses a `0 0 24 24` view box and one path. `artwork/manifest.json` is the cross-platform source of truth. Run `node scripts/generate-icon-packs.mjs`, then `node scripts/generate-icon-packs.mjs --check`.

<!-- BEGIN GENERATED ICON CATALOG -->
## Included glyphs

Glyph IDs are stable across Android, iOS, and React.

| Preview | API | Stable ID | Description |
|---|---|---|---|
| <img src="docs/icons/hospital.svg" width="40" height="40" alt="Hospital or medical facility"> | `CommonMapIcons.hospital` | `hospital` | Hospital or medical facility |
<!-- END GENERATED ICON CATALOG -->
