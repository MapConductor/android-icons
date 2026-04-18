# FlagIcon

The `FlagIcon` class represents a customizable, vector-drawn, flag-shaped marker icon.
It extends `AbstractMarkerIcon` and is used to create icons that can be displayed on a map.

The icon's appearance, such as its color, size, and scale, can be fully customized.
The final rendered icon is a `BitmapIcon` generated on-the-fly,
with results cached for optimal performance.

## Signature

```kotlin
class FlagIcon(
    private val properties: IconProperties,
) : AbstractMarkerIcon()
```

## Constructors

### FlagIcon()

Creates a new instance of `FlagIcon` with customizable properties.

**Signature**

```kotlin
constructor(
    fillColor: Color = Color.Red,
    strokeColor: Color = Color.White,
    strokeWidth: Dp = Settings.Default.iconStroke,
    scale: Float = 1f,
    iconSize: Dp = Settings.Default.iconSize,
    debug: Boolean = false,
)
```

**Description**

Initializes a flag icon with specified or default visual properties.

**Parameters**

- `fillColor`
    - Type: `Color`
    - Default: `Color.Red`
    - Description: The fill color for the flag and its pole.
- `strokeColor`
    - Type: `Color`
    - Default: `Color.White`
    - Description: The color of the outline around the flag and pole.
- `strokeWidth`
    - Type: `Dp`
    - Default: `Settings.Default.iconStroke`
    - Description: The width of the outline.
- `scale`
    - Type: `Float`
    - Default: `1f`
    - Description: The scaling factor applied to the entire icon. A value of `2f` would double
      its rendered size.
- `iconSize`
    - Type: `Dp`
    - Default: `Settings.Default.iconSize`
    - Description: The base size of the icon before scaling is applied.
- `debug`
    - Type: `Boolean`
    - Default: `false`
    - Description: If `true`, a black rectangular frame is drawn around the icon's bitmap bounds
      for debugging purposes.

## Properties

- `fillColor`
    - Type: `Color`
    - Description: The fill color of the icon.
- `strokeColor`
    - Type: `Color`
    - Description: The stroke (outline) color of the icon.
- `strokeWidth`
    - Type: `Dp`
    - Description: The width of the icon's stroke.
- `scale`
    - Type: `Float`
    - Description: The scaling factor applied to the icon.
- `iconSize`
    - Type: `Dp`
    - Description: The base size of the icon.
- `anchor`
    - Type: `Offset`
    - Description: The point on the icon that aligns with the map coordinate.
      The value `Offset(0.176f, 0.91f)` anchors the icon at the base of its pole. **(Read-only)**
- `infoAnchor`
    - Type: `Offset`
    - Description: The point relative to the icon where an info window should be anchored. The
      value `Offset(0.5f, 0f)` anchors the info window to the top-center of the icon. **(Read-only)**
- `debug`
    - Type: `Boolean`
    - Description: A flag to enable or disable the debug frame.

## Methods

### copy

Creates a copy of the `FlagIcon` with the ability to override its properties.
This is useful for creating variations of an icon (e.g., for selected or focused states)
without modifying the original.

**Signature**

```kotlin
fun copy(
    fillColor: Color = this.fillColor,
    strokeColor: Color = this.strokeColor,
    strokeWidth: Dp = this.strokeWidth,
    scale: Float = this.scale,
    iconSize: Dp = this.iconSize,
    debug: Boolean = this.debug,
): FlagIcon
```

**Parameters**

All parameters are optional. If a parameter is not provided, its value is inherited from
the current `FlagIcon` instance.

- `fillColor`
    - Type: `Color`
    - Description: The new fill color for the copied icon.
- `strokeColor`
    - Type: `Color`
    - Description: The new stroke color for the copied icon.
- `strokeWidth`
    - Type: `Dp`
    - Description: The new stroke width for the copied icon.
- `scale`
    - Type: `Float`
    - Description: The new scale for the copied icon.
- `iconSize`
    - Type: `Dp`
    - Description: The new base size for the copied icon.
- `debug`
    - Type: `Boolean`
    - Description: The new debug flag for the copied icon.

**Returns**

- Type: `FlagIcon`
- Description: A new `FlagIcon` instance with the updated properties.

### copy (scale, iconSize)

A convenience overload of `copy` that updates only the `scale` and `iconSize` properties.

**Signature**

```kotlin
fun copy(
    scale: Float,
    iconSize: Dp,
): FlagIcon
```

**Parameters**

- `scale`
    - Type: `Float`
    - Description: The new scaling factor for the copied icon.
- `iconSize`
    - Type: `Dp`
    - Description: The new base size for the copied icon.

**Returns**

- Type: `FlagIcon`
- Description: A new `FlagIcon` instance with the updated `scale` and `iconSize`.

### toBitmapIcon

Renders the vector definition of the `FlagIcon` into a raster `BitmapIcon`. The resulting bitmap
is cached based on the icon's properties to improve performance, preventing re-rendering of
identical icons.

**Signature**

```kotlin
override fun toBitmapIcon(): BitmapIcon
```

**Returns**

- Type: `BitmapIcon`
- Description: The generated bitmap icon, which can be directly used by a map provider.

## Example

Here is an example of how to create a `FlagIcon`, create a larger "selected" version using `copy`,
and then convert both to `BitmapIcon` objects for map display.

```kotlin
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mapconductor.icons.FlagIcon

// 1. Create a standard blue flag icon.
val defaultFlag = FlagIcon(
    fillColor = Color.Blue,
    strokeColor = Color.White,
    strokeWidth = 2.dp,
    iconSize = 48.dp
)

// 2. Create a larger, "selected" version using the copy() method.
//    This version will be yellow and 1.5 times larger.
val selectedFlag = defaultFlag.copy(
    fillColor = Color.Yellow,
    scale = 1.5f
)

// 3. Convert the abstract icon definitions into renderable BitmapIcons.
//    These can now be passed to a map marker.
val defaultMarkerIcon = defaultFlag.toBitmapIcon()
val selectedMarkerIcon = selectedFlag.toBitmapIcon()

// You can now use defaultMarkerIcon and selectedMarkerIcon with your map's marker system.
// For example, show defaultMarkerIcon normally, and switch to selectedMarkerIcon
// when the user taps on the marker.
```
