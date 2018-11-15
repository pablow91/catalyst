package pl.info.qwerty.catalyst.backend

import org.springframework.stereotype.Service
import pl.info.qwerty.catalyst.model.BondType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit


interface BondCalculator {
    fun calculateInterestInterval(
            endDate: LocalDate,
            currentInterestRate: Double,
            faceValue: Double,
            cumulativeInterest: Double
    ): Int

    fun calculatePayoutsDays(interestType: BondType, beginDate: LocalDate, endDate: LocalDate, interestPeriod: Int): List<LocalDate>
}

@Service
class BondCalculatorImpl : BondCalculator {
    override fun calculateInterestInterval(
            endDate: LocalDate,
            currentInterestRate: Double,
            faceValue: Double,
            cumulativeInterest: Double
    ): Int {
        val now = LocalDate.now()
        var minDifference = 10000.0
        var bestPeriod = 0
        for (i in arrayOf(1, 3, 6, 12)) {
            var tmp = endDate
            while (tmp.isAfter(now)) {
                tmp = tmp.minusMonths(i.toLong())
            }
            val tmpEnd = tmp.plusMonths(i.toLong())
            val daysFromBegin = ChronoUnit.DAYS.between(tmp, now)
            val daysInPeriod = ChronoUnit.DAYS.between(now, tmpEnd)
            val currentPercent = currentInterestRate * i.toDouble() / 12
            val currentReward = currentPercent * (daysFromBegin.toDouble() / daysInPeriod.toDouble())
            if (Math.abs(currentReward * faceValue / 100.0 - cumulativeInterest) < minDifference) {
                minDifference = Math.abs(currentReward * faceValue / 100.0 - cumulativeInterest)
                bestPeriod = i
            }
        }
        if (bestPeriod == 0) {
            return 1
        }
        return bestPeriod
    }

    override fun calculatePayoutsDays(interestType: BondType, beginDate: LocalDate, endDate: LocalDate, interestPeriod: Int): List<LocalDate> {
        if (interestPeriod <= 0) {
            throw Exception("Wrong interestPeriod - $interestPeriod")
        }
        val bondList = mutableListOf<LocalDate>()
        if (interestType == BondType.SINGLE) {
            return listOf(endDate)
        }
        val months = 12 / interestPeriod
        var tmpEnd = endDate
        while (tmpEnd.isAfter(LocalDate.now())) {
            var dd = tmpEnd
            if (dd.dayOfWeek == DayOfWeek.SATURDAY) {
                dd = dd.plusDays(2)
            } else if (dd.dayOfWeek == DayOfWeek.SATURDAY) {
                dd = dd.plusDays(1)
            }
            bondList.add(0, dd)
            tmpEnd = tmpEnd.minusMonths(months.toLong())
        }
        if (bondList.isEmpty()) {
            throw Exception()
        }
        return bondList
    }
}