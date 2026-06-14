package com.freez.coachassistant.ui.newEvent

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.freez.coachassistant.util.PersianNumberFormatter

class CurrencyVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        if (digits.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        val formatted = digits
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()

        val output = PersianNumberFormatter.toPersianDigits(formatted)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                val commas = (offset - 1) / 3
                return (offset + commas).coerceAtMost(output.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val before = output.take(offset.coerceIn(0, output.length))
                return before.count { it.isDigit() || it in '۰'..'۹' }
                    .coerceIn(0, digits.length)
            }
        }

        return TransformedText(AnnotatedString(output), offsetMapping)
    }
}
