package com.mapconductor.icons

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class MapIconGlyphTest {
    @Test
    fun commonHospitalUsesStableIdentifier() {
        assertEquals("hospital", CommonMapIcons.hospital.id)
        assertEquals(24f, CommonMapIcons.hospital.viewBoxSize)
    }

    @Test
    fun rejectsInvalidIdentifier() {
        assertThrows(IllegalArgumentException::class.java) {
            MapIconGlyph(id = "Hospital Icon", pathData = "M0 0 L1 1 Z")
        }
    }
}
