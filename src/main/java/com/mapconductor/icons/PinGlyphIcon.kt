package com.mapconductor.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.PathParser
import com.mapconductor.core.marker.AbstractDefaultIcon
import com.mapconductor.settings.Settings
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface

/** Displays a [MapIconGlyph] in MapConductor's default pin container. */
class PinGlyphIcon(
    val glyph: MapIconGlyph,
    val fillColor: Color = Color.Red,
    val glyphColor: Color = Color.White,
    strokeColor: Color = Color.White,
    strokeWidth: Dp = Settings.Default.iconStroke,
    scale: Float = 1f,
    infoAnchor: Offset = Offset(0.5f, 0f),
    iconSize: Dp = Settings.Default.iconSize,
    debug: Boolean = false,
) : AbstractDefaultIcon(
        BaseIconProperties(
            strokeColor = strokeColor,
            strokeWidth = strokeWidth,
            scale = scale,
            label = null,
            labelTextColor = null,
            labelTextSize = 18.sp,
            labelTypeFace = Typeface.DEFAULT,
            labelStrokeColor = Color.Transparent,
            infoAnchor = infoAnchor,
            iconSize = iconSize,
            debug = debug,
        ),
    ) {
    override fun drawMarkerFill(
        canvas: Canvas,
        path: Path,
        canvasSize: Float,
        iconScale: Float,
    ) {
        canvas.drawPath(
            path,
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.FILL
                color = fillColor.toArgb()
            },
        )

        val glyphPath = PathParser.createPathFromPathData(glyph.pathData)
        val glyphSize = canvasSize * 0.42f
        val target =
            RectF(
                (canvasSize - glyphSize) / 2f,
                canvasSize * 0.35f - glyphSize / 2f,
                (canvasSize + glyphSize) / 2f,
                canvasSize * 0.35f + glyphSize / 2f,
            )
        glyphPath.transform(
            Matrix().apply {
                setRectToRect(
                    RectF(0f, 0f, glyph.viewBoxSize, glyph.viewBoxSize),
                    target,
                    Matrix.ScaleToFit.CENTER,
                )
            },
        )
        canvas.drawPath(
            glyphPath,
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.FILL
                color = glyphColor.toArgb()
            },
        )
    }

    override fun getUniqueProperties(): Any = listOf(glyph, fillColor, glyphColor)

    fun copy(
        glyph: MapIconGlyph = this.glyph,
        fillColor: Color = this.fillColor,
        glyphColor: Color = this.glyphColor,
        strokeColor: Color = this.strokeColor,
        strokeWidth: Dp = this.strokeWidth,
        scale: Float = this.scale,
        infoAnchor: Offset = this.infoAnchor,
        iconSize: Dp = this.iconSize,
        debug: Boolean = this.debug,
    ): PinGlyphIcon =
        PinGlyphIcon(
            glyph = glyph,
            fillColor = fillColor,
            glyphColor = glyphColor,
            strokeColor = strokeColor,
            strokeWidth = strokeWidth,
            scale = scale,
            infoAnchor = infoAnchor,
            iconSize = iconSize,
            debug = debug,
        )
}
