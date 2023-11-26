package com.example.stockpalapp

import com.example.stockpalapp.domain.usecase.InputValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class InputValidationTest {

    private val inputValidation = InputValidation()

    @Test
    fun `isValidProductName should return true for valid product name`() {
        val productName = "ValidProductName"
        val isValid = inputValidation.isValidProductName(productName)
        assertTrue(isValid)
    }

    @Test
    fun `isValidProductName should return false for invalid product name`() {
        val invalidProductName = "!@#InvalidName"
        val isValid = inputValidation.isValidProductName(invalidProductName)
        assertFalse(isValid)
    }

    @Test
    fun `isValidEanNumber should return true for valid EAN number`() {
        val validEanNumber = "2754864287654"
        val isValid = inputValidation.isValidEanNumber(validEanNumber)
        assertTrue(isValid)
    }

    @Test
    fun `isValidEanNumber should return false for invalid EAN number`() {
        val invalidEanNumber = "abc123"
        val isValid = inputValidation.isValidEanNumber(invalidEanNumber)
        assertFalse(isValid)
    }

    @Test
    fun `isValidAmmount should return true for valid amount`() {
        val validAmount = "22"
        val isValid = inputValidation.isValidAmmount(validAmount)
        assertTrue(isValid)
    }

    @Test
    fun `isValidAmmount should return false for invalid amount`() {
        val invalidAmount = "abc"
        val isValid = inputValidation.isValidAmmount(invalidAmount)
        assertFalse(isValid)
    }

    @Test
    fun `isValidDatePicker should return true for valid date picker input`() {
        val validDatePickerInput = "123123"
        val isValid = inputValidation.isValidDatePicker(validDatePickerInput)
        assertTrue(isValid)
    }

    @Test
    fun `isValidDatePicker should return false for invalid date picker input`() {
        val invalidDatePickerInput = "12-31-2023"
        val isValid = inputValidation.isValidDatePicker(invalidDatePickerInput)
        assertFalse(isValid)
    }

    @Test
    fun `isValidImageUrl should return true for valid image URL`() {
        val validImageUrl = "https://example.com/image.jpg"
        val isValid = inputValidation.isValidImageUrl(validImageUrl)
        assertTrue(isValid)
    }

    @Test
    fun `isValidImageUrl should return false for invalid image URL`() {
        val invalidImageUrl = "invalid-url"
        val isValid = inputValidation.isValidImageUrl(invalidImageUrl)
        assertFalse(isValid)
    }

    @Test
    fun `isNameValid should return true for valid name`() {
        val validName = "Jacob Heb"
        val isValid = inputValidation.isNameValid(validName)
        assertTrue(isValid)
    }

    @Test
    fun `isNameValid should return false for invalid name`() {
        val invalidName = "John123"
        val isValid = inputValidation.isNameValid(invalidName)
        assertFalse(isValid)
    }

    @Test
    fun `isPasswordValid should return true for valid password`() {
        val validPassword = "Test12345"
        val isValid = inputValidation.isPasswordValid(validPassword)
        assertTrue(isValid)
    }

    @Test
    fun `isPasswordValid should return false for invalid password`() {
        val invalidPassword = "test"
        val isValid = inputValidation.isPasswordValid(invalidPassword)
        assertFalse(isValid)
    }

}