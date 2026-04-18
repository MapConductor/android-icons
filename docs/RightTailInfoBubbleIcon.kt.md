# RightTailInfoBubbleIcon

## Signature

```kotlin
class RightTailInfoBubbleIcon(
    iconDrawable: Drawable,
    label: String,
    snippet: String,
    fillColor: Color = Color.LightGray,
    labelTextColor: Color = Color.Yellow,
    scale: Float = 1f,
    iconSize: Dp = MarkerIconSize.Small,
    debug: Boolean = false,
) : AbstractMarkerIcon()
```

## Description

The `RightTailInfoBubbleIcon` class creates a custom map marker icon styled as
an information bubble with a pointer tail on the bottom-right. This composable icon is designed
to display rich information directly on the map.

The bubble layout consists of:
- An icon (`Drawable`) on the left.
- A primary text `label` to the right of the icon.
- A secondary text `snippet` at the bottom.

The entire element is rendered onto a bitmap with a specified `fillColor`. The icon's anchor is
set to the bottom-center of the pointer tail (`Offset(0.5f, 1.0f)`), ensuring it points directly
to a map coordinate.

## Parameters

The constructor accepts the following parameters to customize the icon's appearance:

- `iconDrawable`
    - Type: `Drawable`
    - Description: The drawable resource to be displayed as the icon on the left side of the bubble.
- `label`
    - Type: `String`
    - Description: The primary text content displayed to the right of the icon.
- `snippet`
    - Type: `String`
    - Description: The secondary, smaller text content displayed at the bottom of the bubble.
- `fillColor`
    - Type: `Color`
    - Default: `Color.LightGray`
    - Description: The background color of the info bubble.
- `labelTextColor`
    - Type: `Color`
    - Default: `Color.Yellow`
    - Description: The color of the primary `label` text.
- `scale`
    - Type: `Float`
    - Default: `1f`
    - Description: A scaling factor applied to the entire icon. Affects size and text rendering.
- `iconSize`
    - Type: `Dp`
    - Default: `MarkerIconSize.Small`
    - Description: The base size for the `iconDrawable` part of the bubble.
- `debug`
    - Type: `Boolean`
    - Default: `false`
    - Description: If `true`, a black border is drawn around the bitmap canvas for layout debugging.

## Properties

- `iconDrawable`
    - Type: `Drawable`
    - Description: The drawable resource displayed as the icon on the left side of the bubble.
- `label`
    - Type: `String`
    - Description: The primary text content displayed to the right of the icon.
- `snippet`
    - Type: `String`
    - Description: The secondary, smaller text content displayed at the bottom of the bubble.
- `fillColor`
    - Type: `Color`
    - Description: The background color of the info bubble.
- `labelTextColor`
    - Type: `Color`
    - Description: The color of the primary `label` text.
- `scale`
    - Type: `Float`
    - Description: The scaling factor applied to the icon.
- `iconSize`
    - Type: `Dp`
    - Description: The base size for the `iconDrawable` part of the bubble.
- `debug`
    - Type: `Boolean`
    - Description: A flag to enable or disable debug visuals on the icon bitmap.
- `anchor`
    - Type: `Offset`
    - Value: `Offset(0.5f, 1.0f)`
    - Description: **Read-only.** The anchor point of the icon aligned to the map coordinate.
      Set to the bottom-center of the pointer tail.
- `infoAnchor`
    - Type: `Offset`
    - Value: `Offset(0.5f, 1.0f)`
    - Description: **Read-only.** The anchor point for an info window, aligned with `anchor`
      at the bottom-center of the icon.

## Methods

### toBitmapIcon

Converts the `RightTailInfoBubbleIcon` definition into a renderable `BitmapIcon`.
This method handles the drawing logic, including layout calculations, text rendering, and caching.

**Signature**

```kotlin
override fun toBitmapIcon(): BitmapIcon
```

**Description**

This function generates a `BitmapIcon` representation of the info bubble. It renders the complete
view—including the icon, labels, background, and tail—onto a `Bitmap`. The result is cached
based on the icon's properties to optimize performance; subsequent calls with identical properties
will retrieve the cached bitmap instead of re-rendering.

**Returns**

- Type: `BitmapIcon`
- Description: An object containing the rendered `Bitmap`, its calculated size, and anchor point,
  ready for map display.

### copy

Creates a new `RightTailInfoBubbleIcon` instance with one or more properties modified.

**Signature**

```kotlin
fun copy(
    iconDrawable: Drawable = this.iconDrawable,
    label: String = this.label,
    snippet: String = this.snippet,
    fillColor: Color = this.fillColor,
    scale: Float = this.scale,
    iconSize: Dp,
): RightTailInfoBubbleIcon
```

**Description**

This method provides a convenient way to create variations of an existing icon. It returns a new
instance, inheriting properties from the original object unless they are explicitly overridden
in the function call.

**Returns**

- Type: `RightTailInfoBubbleIcon`
- Description: A new `RightTailInfoBubbleIcon` instance with the updated properties.

### copy (scale, iconSize)

A convenience overload of `copy` that updates only the `scale` and `iconSize` properties.

**Signature**

```kotlin
fun copy(
    scale: Float,
    iconSize: Dp,
): RightTailInfoBubbleIcon
```

**Parameters**

- `scale`
    - Type: `Float`
    - Description: The new scaling factor for the copied icon.
- `iconSize`
    - Type: `Dp`
    - Description: The new base size for the copied icon.

**Returns**

- Type: `RightTailInfoBubbleIcon`
- Description: A new `RightTailInfoBubbleIcon` instance with the updated `scale` and `iconSize`.

## Example

The following example demonstrates how to create a `RightTailInfoBubbleIcon` and display it
within a Jetpack Compose `Image` composable. In a real-world application, the generated `BitmapIcon`
would typically be passed to a map component to be displayed as a marker.

```kotlin
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mapconductor.core.R // Your project's R file
import com.mapconductor.settings.MarkerIconSize

@Composable
fun RightTailInfoBubbleIconExample() {
    val context = LocalContext.current

    // 1. Define the icon's properties
    val infoBubbleIcon = remember {
        RightTailInfoBubbleIcon(
            iconDrawable = ContextCompat.getDrawable(context, R.drawable.default_marker)!!,
            label = "5h 37m",
            snippet = "304 miles",
            fillColor = Color.White,
            labelTextColor = Color.Black,
            scale = 1.2f, // Make it slightly larger
            iconSize = MarkerIconSize.Medium,
            debug = true // Show debug borders
        )
    }

    // 2. Convert the definition to a BitmapIcon
    val bitmapIcon = remember(infoBubbleIcon) {
        infoBubbleIcon.toBitmapIcon()
    }

    // 3. Get the ImageBitmap to display in a Composable
    val imageBitmap = remember(bitmapIcon) {
        bitmapIcon.bitmap.asImageBitmap()
    }

    // 4. Display the generated icon
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(24.dp)
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "Right Tail Info Bubble Icon Example"
        )
    }
}
```
