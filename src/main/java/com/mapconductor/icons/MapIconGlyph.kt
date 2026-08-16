package com.mapconductor.icons

/**
 * A single-color map symbol in a normalized SVG view box.
 *
 * Glyphs carry meaning only. Marker shape, colors, anchor and bitmap caching are
 * handled by containers such as [PinGlyphIcon].
 */
data class MapIconGlyph(
    val id: String,
    val pathData: String,
    val viewBoxSize: Float = 24f,
) {
    init {
        require(id.matches(Regex("[a-z0-9]+(?:[._][a-z0-9]+)*"))) {
            "Map icon glyph IDs must be lowercase ASCII identifiers: $id"
        }
        require(pathData.isNotBlank()) { "Map icon glyph pathData must not be blank" }
        require(viewBoxSize > 0f) { "Map icon glyph viewBoxSize must be positive" }
    }
}
