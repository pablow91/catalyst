package pl.info.qwerty.catalyst.ui

import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun priceForBondsProvision(minProvision: Double, provision: Double, priceForBond: Double): Double {
    val p = provision * priceForBond / 100
    return priceForBond + if (p < minProvision) minProvision else p
}

fun finalPercent(finalPriceAfterTax: Double, priceForBond: Double, endDate: LocalDate): Double {
    return try {
        val a = finalPriceAfterTax / priceForBond - 1
        val b = 365.0 / ChronoUnit.DAYS.between(LocalDate.now(), endDate))
        a * b * 100
    } catch (ex: Exception) {
        ex.printStackTrace()
        0.0
    }
}