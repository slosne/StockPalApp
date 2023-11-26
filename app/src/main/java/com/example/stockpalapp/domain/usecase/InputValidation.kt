package com.example.stockpalapp.domain.usecase

import javax.inject.Inject

class InputValidation @Inject constructor() {

    fun isValidProductName(text: String): Boolean {
        return text.matches(Regex("[a-zA-Z0-9,æøåÆØÅ\\s]{1,25}"))
    }

    fun isValidEanNumber(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidAmmount(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidDatePicker(text: String): Boolean {
        return text.matches(Regex("[0-9/]{1,8}"))
    }

    fun isValidImageUrl(url: String): Boolean {
        return url.matches(Regex("""^(https?|ftp):\/\/[^\s\/$.?#].[^\s]*$"""))
    }

    fun isNameValid(name: String): Boolean {
        val validNamePattern = Regex("^[A-Za-z ]+$")
        return validNamePattern.matches(name)
    }

    fun isPasswordValid(password: String): Boolean {
        val lengthPattern = Regex(".{6,}")
        val digitPattern = Regex(".*[0-9].*")

        return lengthPattern.matches(password) &&
                digitPattern.matches(password)
    }

}