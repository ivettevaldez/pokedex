package com.ivettevaldez.pokedex.ui.common

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

object Utils {

    fun String.toTitleCase(): String = this.replaceFirstChar(Char::titlecase)

    fun String.cleanFormat(): String = this.replace("\n", " ")

    fun Int.dmToCentimeters(): Int = this * 10

    fun Int.dmToMeters(): Float = this / 10f

    fun Int.hgToKilograms(): Float = this / 10f

    fun ChipGroup.populate(items: List<String>) {
        this.removeAllViews()
        items.forEach { item ->
            this.addView(
                Chip(this.context, null, 0).apply { text = item.toTitleCase() }
            )
        }
    }
}