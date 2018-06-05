package pl.info.qwerty.catalyst.ui

import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import pl.info.qwerty.catalyst.model.AdditionalInfo
import pl.info.qwerty.catalyst.model.Bond
import pl.info.qwerty.catalyst.model.LastTransaction
import pl.info.qwerty.catalyst.model.Triple1
import java.time.LocalDate

data class LastTransactionProperties(
        val lastTransaction: StringProperty = SimpleStringProperty(),
        val lastTransactionVolume: IntegerProperty = SimpleIntegerProperty(),
        val lastPrice: DoubleProperty = SimpleDoubleProperty()
) {
    fun use(lt: LastTransaction) {
        lastTransaction.set(lt.lastTransaction)
        lastTransactionVolume.set(lt.lastTransactionVolume)
        lastPrice.set(lt.lastPrice)
    }

    fun clean() {
        lastTransaction.set("")
        lastTransactionVolume.set(0)
        lastPrice.set(0.0)
    }
}

data class Triple1Properties(
        val volume: IntegerProperty = SimpleIntegerProperty(),
        val ordersCount: IntegerProperty = SimpleIntegerProperty(),
        val price: DoubleProperty = SimpleDoubleProperty()
) {
    fun use(triple: Triple1) {
        volume.set(triple.volume)
        ordersCount.set(triple.ordersCount)
        price.set(triple.price)
    }

    fun clean() {
        volume.set(0)
        ordersCount.set(0)
        price.set(0.0)
    }
}

data class AdditionalInfoProperties(
        val beginDate: StringProperty = SimpleStringProperty(), //TODO: Change to DateProperty
        val endDate: StringProperty = SimpleStringProperty(), //TODO: Change to DateProperty
        val interestType: StringProperty = SimpleStringProperty(),
        val issueValue: DoubleProperty = SimpleDoubleProperty(),
        val faceValue: DoubleProperty = SimpleDoubleProperty(),
        val currentInterestRate: DoubleProperty = SimpleDoubleProperty(),
        val cumulativeInterest: DoubleProperty = SimpleDoubleProperty()
) {
    fun use(additionalInfo: AdditionalInfo) {
        beginDate.set(additionalInfo.beginDate.toString())
        endDate.set(additionalInfo.endDate.toString())
        interestType.set(additionalInfo.interestType.toString()) //TODO: Change to enum property
        issueValue.set(additionalInfo.issueValue)
        faceValue.set(additionalInfo.faceValue)
        currentInterestRate.set(additionalInfo.currentInterestRate)
        cumulativeInterest.set(additionalInfo.cumulativeInterest)
    }

    fun clean() {
        beginDate.set("")
        endDate.set("")
        interestType.set("")
        issueValue.set(0.0)
        faceValue.set(0.0)
        currentInterestRate.set(0.0)
        cumulativeInterest.set(0.0)
    }
}

data class BondProperties(
        val marketName: StringProperty = SimpleStringProperty(),
        val issuer: StringProperty = SimpleStringProperty(),
        val name: StringProperty = SimpleStringProperty(),
        val segment: StringProperty = SimpleStringProperty(),
        val transactionUnit: IntegerProperty = SimpleIntegerProperty(),
        val change: DoubleProperty = SimpleDoubleProperty(),
        val referencePrice: DoubleProperty = SimpleDoubleProperty(),
        val openingPrice: DoubleProperty = SimpleDoubleProperty(),
        val minimalPrice: DoubleProperty = SimpleDoubleProperty(),
        val maximalPrice: DoubleProperty = SimpleDoubleProperty(),
        val lastTransaction: LastTransactionProperties = LastTransactionProperties(),
        val buyTriple: Triple1Properties = Triple1Properties(),
        val sellTriple: Triple1Properties = Triple1Properties(),
        val dayVolume: IntegerProperty = SimpleIntegerProperty(),
        val dayValue: DoubleProperty = SimpleDoubleProperty(),
        val interestInterval: IntegerProperty = SimpleIntegerProperty(),
        val payoutDays: ObservableList<LocalDate> = FXCollections.observableArrayList(),
        val remainingPeriods: IntegerProperty = SimpleIntegerProperty(),
        val additionalInfo: AdditionalInfoProperties = AdditionalInfoProperties()
) {
    fun use(bond: Bond) {
        marketName.set(bond.marketName)
        issuer.set(bond.issuer)
        name.set(bond.name)
        segment.set(bond.segment)
        transactionUnit.set(bond.transactionUnit)
        change.set(bond.change ?: 0.0)
        referencePrice.set(bond.referencePrice ?: 0.0)
        openingPrice.set(bond.openingPrice ?: 0.0)
        minimalPrice.set(bond.minimalPrice ?: 0.0)
        maximalPrice.set(bond.maximalPrice ?: 0.0)
        if (bond.lastTransaction != null) {
            lastTransaction.use(bond.lastTransaction!!)
        } else {
            lastTransaction.clean()
        }
        if (bond.buyTriple != null) {
            buyTriple.use(bond.buyTriple!!)
        } else {
            buyTriple.clean()
        }
        if (bond.sellTriple != null) {
            sellTriple.use(bond.sellTriple!!)
        } else {
            sellTriple.clean()
        }
        dayVolume.set(bond.dayVolume ?: 0)
        dayValue.set(bond.dayValue ?: 0.0)
        interestInterval.set(bond.interestInterval)
        payoutDays.clear()
        payoutDays.addAll(bond.payoutDays)
        remainingPeriods.set(payoutDays.size)
        additionalInfo.use(bond.additionalInfo)
    }

    fun clean() {
        marketName.set("")
        issuer.set("")
        name.set("")
        segment.set("")
        transactionUnit.set(0)
        change.set(0.0)
        referencePrice.set(0.0)
        openingPrice.set(0.0)
        minimalPrice.set(0.0)
        maximalPrice.set(0.0)
        lastTransaction.clean()
        buyTriple.clean()
        sellTriple.clean()
        dayVolume.set(0)
        dayValue.set(0.0)
        interestInterval.set(0)
        payoutDays.clear()
        remainingPeriods.set(0)
        additionalInfo.clean()
    }
}