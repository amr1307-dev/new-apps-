package com.example

import com.example.data.model.Language
import com.example.data.repository.BenchmarkData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SalesCloserUnitTest {

    @Test
    fun benchmarkData_containsLuxuryProductsAndPriceGaps() {
        val products = BenchmarkData.PRODUCTS
        assertTrue("Must have scanned luxury products", products.isNotEmpty())

        val bag = products.first { it.id == "scan_bag_01" }
        assertEquals(2450.0, bag.officialSitePriceEur, 0.01)
        assertEquals(400.0, bag.suggestedRetailPriceEur, 0.01)

        val savings = bag.officialSitePriceEur - bag.suggestedRetailPriceEur
        assertTrue("Value gap must exceed 2000 Euros", savings >= 2000.0)
        assertTrue("Must have artisan features", bag.artisanFeaturesAr.isNotEmpty())
    }

    @Test
    fun closerPitch_hasGermanTranslationAndPhonetics() {
        val bag = BenchmarkData.PRODUCTS.first { it.id == "scan_bag_01" }
        val germanPitch = bag.pitchesByLang["de"]

        assertNotNull("Must have German closing pitch", germanPitch)
        assertTrue("Foreign text must not be empty", germanPitch!!.foreignText.isNotBlank())
        assertTrue("Arabic translation must not be empty", germanPitch.arabicTranslation.isNotBlank())
        assertTrue("Arabic phonetics must be provided", germanPitch.arabicPhonetics.isNotBlank())
    }

    @Test
    fun objectionHandling_containsTacticsWithForeignSpeechAndPhonetics() {
        val objections = BenchmarkData.OBJECTIONS
        assertTrue("Must have recorded objection cases", objections.isNotEmpty())

        val expensiveObjection = objections.first { it.detectedObjectionType == "price_high" && it.languageCode == "de" }
        assertTrue("Must offer at least 2 counter tactics", expensiveObjection.tactics.size >= 2)

        val firstTactic = expensiveObjection.tactics.first()
        assertTrue("Tactic must have foreign speech", firstTactic.foreignSpeech.isNotBlank())
        assertTrue("Tactic must have Arabic phonetics", firstTactic.arabicPhonetics.isNotBlank())
        assertTrue("Tactic must have Arabic explanation", firstTactic.strategyExplanationAr.isNotBlank())
    }

    @Test
    fun languages_defaultToGerman() {
        val defaultLang = Language.DEFAULT
        assertEquals("de", defaultLang.code)
        assertEquals("الألمانية", defaultLang.nameAr)
        assertEquals("Deutsch", defaultLang.nameNative)
    }
}
