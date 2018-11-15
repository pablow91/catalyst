package pl.info.qwerty.catalyst.ui

import javafx.beans.binding.Bindings
import javafx.beans.binding.DoubleBinding
import javafx.beans.property.SimpleDoubleProperty
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Slider
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class BondCalcController(
        dirtyPriceLabel: Label,
        remainingPeriodsLabel: Label,
        numberOfBoundsSlider: Slider,
        numberOfBoundsLabel: Label,
        priceForBondsLabel: Label,
        priceForBondsProvisionLabel: Label,
        finalPriceLabel: Label,
        finalTaxLabel: Label,
        finalPriceTaxLabel: Label,
        finalPercentLabel: Label,
        simplePriceSlider: Slider,
        simplePriceLabel: Label,
        bond: BondProperties
) {
    init {

        val simplePrice = SimpleDoubleProperty(100.0)
        val dirtyPrice = Bindings.add(Bindings.divide(Bindings.multiply(simplePrice, bond.additionalInfo.faceValue), 100.00), bond.additionalInfo.cumulativeInterest)
        val numberOfBonds = numberOfBoundsSlider.valueProperty()
        val priceForBonds = Bindings.multiply(dirtyPrice, numberOfBonds) as DoubleBinding
        val priceForBondsProvision = object : DoubleBinding() {
            init {
                super.bind(priceForBonds)
            }

            override fun computeValue() = priceForBondsProvision(3.0, 0.19, priceForBonds.get())
        }
        val currentPercent = Bindings.multiply(bond.additionalInfo.currentInterestRate, Bindings.divide(bond.interestInterval, 12.00)) as DoubleBinding
        val couponValue = Bindings.multiply(currentPercent, Bindings.divide(bond.additionalInfo.faceValue, 100.0)) as DoubleBinding
        val couponTax = Bindings.multiply(couponValue, 0.19)
        val finalPrice = Bindings.add(Bindings.multiply(bond.remainingPeriods, Bindings.multiply(couponValue, numberOfBonds)), Bindings.multiply(bond.additionalInfo.faceValue, numberOfBonds)) as DoubleBinding
        val finalTax = Bindings.multiply(couponTax, Bindings.multiply(numberOfBonds, bond.remainingPeriods)) as DoubleBinding
        val finalPriceAfterTax = Bindings.subtract(finalPrice, finalTax) as DoubleBinding
        val finalPercent = object : DoubleBinding() {
            init {
                super.bind(finalPriceAfterTax, priceForBondsProvision, bond.additionalInfo.endDate, simplePrice)
            }

            override fun computeValue(): Double = finalPercent(finalPriceAfterTax.get(), priceForBondsProvision.get(), LocalDate.parse(bond.additionalInfo.endDate.get()))
        }
        simplePriceSlider.valueProperty().bindBidirectional(simplePrice)
        simplePriceLabel.textProperty().bind(simplePrice.asString("%.2f"))
        dirtyPriceLabel.textProperty().bind(dirtyPrice.asMoney())
        remainingPeriodsLabel.textProperty().bind(bond.remainingPeriods.asString())

        numberOfBoundsLabel.textProperty().bind(numberOfBonds.asString("%.0f"))
        priceForBondsLabel.textProperty().bind(priceForBonds.asMoney())
        priceForBondsProvisionLabel.textProperty().bind(priceForBondsProvision.asMoney())
        finalPriceLabel.textProperty().bind(finalPrice.asMoney())
        finalTaxLabel.textProperty().bind(finalTax.asMoney())
        finalPriceTaxLabel.textProperty().bind(finalPriceAfterTax.asMoney())
        finalPercentLabel.textProperty().bind(finalPercent.asPercent())
    }
}

class FXMLBondCalcController {
    @FXML
    private var dirtyPriceLabel: Label? = null

    @FXML
    private var remainingPeriodsLabel: Label? = null

    @FXML
    private var numberOfBoundsSlider: Slider? = null

    @FXML
    private var numberOfBoundsLabel: Label? = null

    @FXML
    private var priceForBondsLabel: Label? = null

    @FXML
    private var priceForBondsProvisionLabel: Label? = null

    @FXML
    private var finalPriceLabel: Label? = null

    @FXML
    private var finalTaxLabel: Label? = null

    @FXML
    private var finalPriceTaxLabel: Label? = null

    @FXML
    private var finalPercentLabel: Label? = null

    @FXML
    private var simplePriceSlider: Slider? = null

    @FXML
    private var simplePriceLabel: Label? = null

    fun init(bond: BondProperties): BondCalcController {
        return BondCalcController(
                dirtyPriceLabel!!,
                remainingPeriodsLabel!!,
                numberOfBoundsSlider!!,
                numberOfBoundsLabel!!,
                priceForBondsLabel!!,
                priceForBondsProvisionLabel!!,
                finalPriceLabel!!,
                finalTaxLabel!!,
                finalPriceTaxLabel!!,
                finalPercentLabel!!,
                simplePriceSlider!!,
                simplePriceLabel!!,
                bond
        )
    }
}

