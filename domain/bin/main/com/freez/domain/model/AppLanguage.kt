package com.freez.domain.model

enum class AppLanguage {
    English, Persian;

    override fun toString(): String {
        return when (this) {
            English -> "en"
            Persian -> "fa"
        }
    }
}