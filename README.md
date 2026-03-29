# MapConductor Icons

## Description

MapConductor Icons provides a collection of pre-built marker icon types for the MapConductor SDK.
Each icon implements `MarkerIconInterface` and can be passed directly as the `icon` parameter of `MarkerState`.

## Setup

https://docs-android.mapconductor.com/setup/

------------------------------------------------------------------------

## Icons

### CircleIcon

A simple filled circle icon.

```kotlin
val icon = CircleIcon(
    fillColor = Color.Red,
    strokeColor = Color.White,
    strokeWidth = 2.dp,
    scale = 1f,
)

val markerState = remember {
    MarkerState(
        position = GeoPoint(latitude = 35.6762, longitude = 139.6503),
        icon = icon,
    )
}

XxxMapView(...) {
    Marker(markerState)
}
```

### FlagIcon

A flag-shaped icon with a pole.

```kotlin
val icon = FlagIcon(
    fillColor = Color.Blue,
    strokeColor = Color.White,
    strokeWidth = 2.dp,
    scale = 1f,
)
```

### RightTailInfoBubbleIcon

An info-bubble icon with a drawable, a label, and a snippet.
The tail points downward-right, anchored at the bottom of the bubble.

```kotlin
val icon = RightTailInfoBubbleIcon(
    iconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_car)!!,
    label = "5h 37m",
    snippet = "304 miles",
    fillColor = Color.White,
    labelTextColor = Color.Black,
    scale = 1f,
    iconSize = MarkerIconSize.Small,
)
```

### RoundInfoBubbleIcon

A rounded info-bubble icon with a drawable and a label.

```kotlin
val icon = RoundInfoBubbleIcon(
    iconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_store)!!,
    label = "Tokyo",
    fillColor = Color.White,
    scale = 1f,
    iconSize = MarkerIconSize.Small,
)
```

------------------------------------------------------------------------

## API Reference

### CircleIcon

| Parameter | Type | Default |
|---|---|---|
| `fillColor` | `Color` | `Color.Red` |
| `strokeColor` | `Color` | `Color.White` |
| `strokeWidth` | `Dp` | `Settings.Default.iconStroke` |
| `scale` | `Float` | `1f` |
| `iconSize` | `Dp` | `Settings.Default.iconSize` |

### FlagIcon

| Parameter | Type | Default |
|---|---|---|
| `fillColor` | `Color` | `Color.Red` |
| `strokeColor` | `Color` | `Color.White` |
| `strokeWidth` | `Dp` | `Settings.Default.iconStroke` |
| `scale` | `Float` | `1f` |
| `iconSize` | `Dp` | `Settings.Default.iconSize` |

### RightTailInfoBubbleIcon

| Parameter | Type | Default |
|---|---|---|
| `iconDrawable` | `Drawable` | required |
| `label` | `String` | required |
| `snippet` | `String` | required |
| `fillColor` | `Color` | `Color.LightGray` |
| `labelTextColor` | `Color` | `Color.Yellow` |
| `scale` | `Float` | `1f` |
| `iconSize` | `Dp` | `MarkerIconSize.Small` |

### RoundInfoBubbleIcon

| Parameter | Type | Default |
|---|---|---|
| `iconDrawable` | `Drawable` | required |
| `label` | `String` | required |
| `fillColor` | `Color` | `Color.White` |
| `scale` | `Float` | `1f` |
| `iconSize` | `Dp` | `MarkerIconSize.Small` |
