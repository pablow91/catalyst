package pl.info.qwerty.catalyst.ui

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListView
import java.time.LocalDate


class GeneralBondController(
        issuerLabel: Label,
        segmentLabel: Label,
        beginDateLabel: Label,
        endDateLabel: Label,
        transactionUnitLabel: Label,
        faceValueLabel: Label,
        interestTypeLabel: Label,
        issueValueLabel: Label,
        currentInterestRateLabel: Label,
        cumulativeInterestLabel: Label,
        collapseDays: ListView<String>,
        payoutDays: ListView<LocalDate>,
        interestPeriodLabel: Label,
        couponValueLabel: Label,
        marketNameLabel: Label,
        bond: BondProperties
) {

    init {
        issuerLabel.textProperty().bind(bond.issuer)
        segmentLabel.textProperty().bind(bond.segment)
        beginDateLabel.textProperty().bind(bond.additionalInfo.beginDate)
        endDateLabel.textProperty().bind(bond.additionalInfo.endDate)
        transactionUnitLabel.textProperty().bind(bond.transactionUnit.asString())
        faceValueLabel.textProperty().bind(bond.additionalInfo.faceValue.asString())
        interestTypeLabel.textProperty().bind(bond.additionalInfo.interestType)
        issueValueLabel.textProperty().bind(bond.additionalInfo.issueValue.asMoney())
        currentInterestRateLabel.textProperty().bind(bond.additionalInfo.currentInterestRate.asPercent())
        cumulativeInterestLabel.textProperty().bind(bond.additionalInfo.cumulativeInterest.asMoney())
        payoutDays.items = bond.payoutDays
        interestPeriodLabel.textProperty().bind(bond.interestInterval.asString())
        marketNameLabel.textProperty().bind(bond.marketName)
    }
}

class FXMLGeneralBondController {

    @FXML
    var issuerLabel: Label? = null
    @FXML
    var segmentLabel: Label? = null
    @FXML
    var beginDateLabel: Label? = null
    @FXML
    var endDateLabel: Label? = null
    @FXML
    var transactionUnitLabel: Label? = null
    @FXML
    var faceValueLabel: Label? = null
    @FXML
    var interestTypeLabel: Label? = null
    @FXML
    var issueValueLabel: Label? = null
    @FXML
    var currentInterestRateLabel: Label? = null
    @FXML
    var cumulativeInterestLabel: Label? = null
    @FXML
    var collapseDays: ListView<String>? = null
    @FXML
    var payoutDays: ListView<LocalDate>? = null
    @FXML
    var interestPeriodLabel: Label? = null
    @FXML
    var couponValueLabel: Label? = null
    @FXML
    var marketNameLabel: Label? = null

    fun init(bond: BondProperties): GeneralBondController {
        return GeneralBondController(
                issuerLabel!!,
                segmentLabel!!,
                beginDateLabel!!,
                endDateLabel!!,
                transactionUnitLabel!!,
                faceValueLabel!!,
                interestTypeLabel!!,
                issueValueLabel!!,
                currentInterestRateLabel!!,
                cumulativeInterestLabel!!,
                collapseDays!!,
                payoutDays!!,
                interestPeriodLabel!!,
                couponValueLabel!!,
                marketNameLabel!!,
                bond
        )
    }

}
