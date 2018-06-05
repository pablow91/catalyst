package pl.info.qwerty.catalyst.ui

import javafx.fxml.FXML
import javafx.scene.control.Label

class LastSessionController(
        lastTransactionLabel: Label,
        lastTransactionVolumeLabel: Label,
        numberOfOrdersBuyLabel: Label,
        buyVolumeLabel: Label,
        buyPriceLimitLabel: Label,
        numberOfOrdersSellLabel: Label,
        sellVolumeLabel: Label,
        sellPriceLimitLabel: Label,
        dayVolumeLabel: Label,
        dayValueLabel: Label,
        referencePriceLabel: Label,
        openingPriceLabel: Label,
        minimalPriceLabel: Label,
        changeLabel: Label,
        lastPriceLabel: Label,
        maximalPriceLabel: Label,
        bond: BondProperties
) {

    init {
        lastTransactionLabel.textProperty().bind(bond.lastTransaction.lastTransaction)
        lastTransactionVolumeLabel.textProperty().bind(bond.lastTransaction.lastTransactionVolume.asString())
        numberOfOrdersBuyLabel.textProperty().bind(bond.buyTriple.ordersCount.asString())
        buyVolumeLabel.textProperty().bind(bond.buyTriple.volume.asString())
        buyPriceLimitLabel.textProperty().bind(bond.buyTriple.price.asString())
        numberOfOrdersSellLabel.textProperty().bind(bond.sellTriple.ordersCount.asString())
        sellVolumeLabel.textProperty().bind(bond.sellTriple.volume.asString())
        sellPriceLimitLabel.textProperty().bind(bond.sellTriple.price.asString())
        dayVolumeLabel.textProperty().bind(bond.dayVolume.asString())
        dayValueLabel.textProperty().bind(bond.dayValue.asString())
        referencePriceLabel.textProperty().bind(bond.referencePrice.asString())
        openingPriceLabel.textProperty().bind(bond.openingPrice.asString())
        minimalPriceLabel.textProperty().bind(bond.minimalPrice.asString())
        maximalPriceLabel.textProperty().bind(bond.maximalPrice.asString())
        changeLabel.textProperty().bind(bond.change.asString())
        lastPriceLabel.textProperty().bind(bond.lastTransaction.lastPrice.asString())

    }

}

class FXMLLastSessionController {

    @FXML
    private var lastTransactionLabel: Label? = null

    @FXML
    private var lastTransactionVolumeLabel: Label? = null

    @FXML
    private var numberOfOrdersBuyLabel: Label? = null

    @FXML
    private var buyVolumeLabel: Label? = null

    @FXML
    private var buyPriceLimitLabel: Label? = null

    @FXML
    private var numberOfOrdersSellLabel: Label? = null

    @FXML
    private var sellVolumeLabel: Label? = null

    @FXML
    private var sellPriceLimitLabel: Label? = null

    @FXML
    private var dayVolumeLabel: Label? = null

    @FXML
    private var dayValueLabel: Label? = null

    @FXML
    private var referencePriceLabel: Label? = null

    @FXML
    private var openingPriceLabel: Label? = null

    @FXML
    private var minimalPriceLabel: Label? = null

    @FXML
    private var changeLabel: Label? = null

    @FXML
    private var lastPriceLabel: Label? = null

    @FXML
    private var maximalPriceLabel: Label? = null

    fun init(bond: BondProperties): LastSessionController {
        return LastSessionController(
                lastTransactionLabel!!,
                lastTransactionVolumeLabel!!,
                numberOfOrdersBuyLabel!!,
                buyVolumeLabel!!,
                buyPriceLimitLabel!!,
                numberOfOrdersSellLabel!!,
                sellVolumeLabel!!,
                sellPriceLimitLabel!!,
                dayVolumeLabel!!,
                dayValueLabel!!,
                referencePriceLabel!!,
                openingPriceLabel!!,
                minimalPriceLabel!!,
                changeLabel!!,
                lastPriceLabel!!,
                maximalPriceLabel!!,
                bond
        )
    }

}