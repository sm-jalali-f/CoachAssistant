package com.freez.domain.model.person

@JvmInline
value class SkillLevel private constructor(val value: Int) {
    companion object {
        fun of(value: Int): SkillLevel =
            requireNotNull(ofOrNull(value))

        fun ofOrNull(value: Int): SkillLevel? =
            if (value in 0..10) SkillLevel(value) else null
    }
}