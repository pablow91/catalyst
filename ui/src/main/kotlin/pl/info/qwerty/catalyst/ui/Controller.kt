package pl.info.qwerty.catalyst.ui

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.Slider
import pl.info.qwerty.catalyst.ui.fxml.bindFXML
import java.net.URL
import java.util.*


class GeneralBondController : Initializable {

    @FXML private var emitentLabel: Label? = null
    @FXML private var segmentLabel: Label? = null
    @FXML private var dataEmisjiLabel: Label? = null
    @FXML private var dataWykupuLabel: Label? = null
    @FXML private var jednostkaTransakcyjnaLabel: Label? = null
    @FXML private var wartośćNominalnaLabel: Label? = null
    @FXML private var rodzajOprocentowaniaLabel: Label? = null
    @FXML private var wartośćEmisjiLabel: Label? = null
    @FXML private var oprocentowanieBieżąceLabel: Label? = null
    @FXML private var skumulowaneOdsetkiLabel: Label? = null
    @FXML private var datyZapadania: ListView<*>? = null
    @FXML private var datyWypłaty: ListView<*>? = null
    @FXML private var okresOdsetkowySlider: Slider? = null
    @FXML private var okresOdsetkowyLabel: Label? = null
    @FXML private var okresOdsetkowyAutoLabel: Label? = null
    @FXML private var wielkośćKuponuLabel: Label? = null
    @FXML private var marketNameLabel: Label? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Init ${javaClass.name}")
        println(emitentLabel)
    }

}

class BondController : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Init!! ${javaClass.name}")
    }

}

class MainController : Initializable {

//    val mainPanel: VBox by bindFXML()
//    val minimalProvisionField: TextField by bindFXML()
//    val percentageProvisionField: TextField by bindFXML()
//    val refreshMarkets: Button by bindFXML()
//    val filterEditorButton: ToggleButton by bindFXML()
//    val bondList: ListView<Bond> by bindFXML()
//    val bondDetails: Accordion by bindFXML()
//    val x1: TitledPane by bindFXML()
//    val emitentLabel: Label by bindFXML()
//    val segmentLabel: Label by bindFXML()
//    val dataEmisjiLabel: Label by bindFXML()
//    val dataWykupuLabel: Label by bindFXML()
//    val jednostkaTransakcyjnaLabel: Label by bindFXML()
//    val wartośćNominalnaLabel: Label by bindFXML()
//    val rodzajOprocentowaniaLabel: Label by bindFXML()
//    val wartośćEmisjiLabel: Label by bindFXML()
//    val oprocentowanieBieżąceLabel: Label by bindFXML()
//    val skumulowaneOdsetkiLabel: Label by bindFXML()
//    val datyZapadania: ListView<*> by bindFXML()
//    val datyWypłaty: ListView<*> by bindFXML()
//    val okresOdsetkowySlider: Slider by bindF)
//    val okresOdsetkowyLabel: Label by bindF)
//    val okresOdsetkowyAutoLabel: Label by bindF)
//    val wielkośćKuponuLabel: Label by bindFXML()
//    val marketNameLabel: Label by bindFXML()
//    val x2: TitledPane by bindFXML()
//    val lastTransactionLabel: Label by bindFXML()
//    val lastTransactionVolumeLabel: Label by bindFXML()
//    val numberOfOrdersBuyLabel: Label by bindFXML()
//    val buyVolumeLabel: Label by bindFXML()
//    val buyPriceLimitLabel: Label by bindFXML()
//    val numberOfOrdersSellLabel: Label by bindFXML()
//    val sellVolumeLabel: Label by bindFXML()
//    val sellPriceLimitLabel: Label by bindFXML()
//    val dayVolumeLabel: Label by bindFXML()
//    val dayValueLabel: Label by bindFXML()
//    val referencePriceLabel: Label by bindFXML()
//    val openingPriceLabel: Label by bindFXML()
//    val minimalPriceLabel: Label by bindFXML()
//    val changeLabel: Label by bindFXML()
//    val lastPriceLabel: Label by bindFXML()
//    val maximalPriceLabel: Label by bindFXML()
//    val x3: TitledPane by bindFXML()
//    val dirtyPriceLabel: Label by bindFXML()
//    val remainingPeriodsLabel: Label by bindFXML()
//    val numberOfBoundsSlider: Slider by bindFXML()
//    val numberOfBoundsLabel: Label by bindFXML()
//    val priceForBondsLabel: Label by bindFXML()
//    val priceForBondsProvisionLabel: Label by bindFXML()
//    val finalPriceLabel: Label by bindFXML()
//    val finalTaxLabel: Label by bindFXML()
//    val finalPriceTaxLabel: Label by bindFXML()
//    val finalPercentLabel: Label by bindFXML()
//    val simplePriceSlider: Slider by bindFXML()
//    val simplePriceLabel: Label by bindFXML()
//    val updateProgressLabel: Label by bindFXML()
//    val updateProgressIndicator: ProgressIndicator by bindFXML()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
//        val markets = BackendService.service.markets().execute().body()
        val bonds = BackendService.service.bonds(1).execute().body() ?: throw Exception("Unable to fetch bonds")

//        se.getUpdateMarketsInfo().setOnRunning(t -> {
//            updateProgressLabel.setVisible(true);
//            updateProgressLabel.textProperty().bind(
//                    se.getUpdateMarketsInfo().messageProperty());
//            mainPanel.setDisable(true);
//            mainPanel.setEffect(new GaussianBlur(10));
//        });
//        se.getUpdateMarketsInfo().setOnSucceeded(t -> {
//            updateProgressLabel.setVisible(false);
//            mainPanel.setDisable(false);
//            mainPanel.setEffect(null);
//        });
//        minimalProvisionField.textProperty().bindBidirectional(se.minimalProvisionProperty(), new SimpleDoubleConverter());
//        percentageProvisionField.textProperty().bindBidirectional(se.percentageProvisionProperty(), new SimpleDoubleConverter());
//        setSiteMarket();
//        bondList.items = FXCollections.observableList(bonds.toMutableList())
//        bondList.setCellFactory {
//            object : ListCell<Bond>() {
//                override fun updateItem(item: Bond?, empty: Boolean) {
//                    super.updateItem(item, empty)
//                    if (item != null) {
//                        text = "${item.name} ${item.segment}"
//                    }
//                }
//            }
//        }
//        bondList.selectionModel.selectedItemProperty().addListener({ arg0, arg1, arg2 ->
//            if (arg2 != null) {
//                if (bondDetails.isDisable) {
//                    bondDetails.isDisable = false
//                    x1.isExpanded = true
//                }
//                if (arg1 != null) {
//                    okresOdsetkowySlider.valueProperty().unbindBidirectional(arg1.interestPeriodProperty())
//                    simplePriceSlider.valueProperty().unbindBidirectional(arg1.simplePriceProperty())
//                    numberOfBoundsSlider.valueProperty().unbindBidirectional(arg1.numberOfBondsProperty())
//                }

//                dataEmisjiLabel.textProperty().bind(SimpleStringProperty(arg2.additionalInfo.beginDate))
//                dataWykupuLabel.textProperty().bind(SimpleStringProperty(arg2.additionalInfo.endDate))
//                emitentLabel.textProperty().bind(SimpleStringProperty(arg2.issuer))
//                marketNameLabel.textProperty().bind(SimpleStringProperty(arg2.marketName))
//                jednostkaTransakcyjnaLabel.textProperty().bind(SimpleIntegerProperty(arg2.transactionUnit).asString())
//                oprocentowanieBieżąceLabel.textProperty().bind(SimpleDoubleProperty(arg2.additionalInfo.currentInterestRate).asString("%.2f%%"))
//                rodzajOprocentowaniaLabel.textProperty().bind(SimpleStringProperty(arg2.additionalInfo.interestType))
//                segmentLabel.textProperty().bind(SimpleStringProperty(arg2.segment))
//                skumulowaneOdsetkiLabel.textProperty().bind(SimpleDoubleProperty(arg2.additionalInfo.cumulativeInterest).asString("%.2f zł"))
//                wartośćEmisjiLabel.textProperty().bind(SimpleDoubleProperty(arg2.additionalInfo.issueValue).asString("%.0f zł"))
//                wartośćNominalnaLabel.textProperty().bind(SimpleDoubleProperty(arg2.additionalInfo.faceValue).asString("%.0f zł"))
//                okresOdsetkowySlider.valueProperty().bindBidirectional(arg2.interestPeriodProperty())
//                okresOdsetkowyLabel.textProperty().bind(arg2.interestPeriodProperty().asString())
//                okresOdsetkowyAutoLabel.textProperty().bind(arg2.autoGeneratedPeriodProperty().asString())
//                wielkośćKuponuLabel.textProperty().bind(arg2.couponValueProperty().asString("%.2f zł"))
//                datyZapadania.setItems(arg2.entitledToInterestDaysProperty())
//                datyWypłaty.setItems(arg2.payoutDaysProperty())
//                datyZapadania.getSelectionModel().select(arg2.getNextEntitledToInterestDay())
//                datyWypłaty.getSelectionModel().select(arg2.getNextPayoutDay())
//                remainingPeriodsLabel.textProperty().bind(arg2.remainingPeriodsProperty().asString())
//                simplePriceSlider.valueProperty().bindBidirectional(arg2.simplePriceProperty())
//                simplePriceLabel.textProperty().bind(simplePriceSlider.valueProperty().asString("%.2f %%"))
//                dirtyPriceLabel.textProperty().bind(arg2.dirtyPriceProperty().asString("%.2f zł"))
//                numberOfBoundsSlider.valueProperty().bindBidirectional(arg2.numberOfBondsProperty())
//                numberOfBoundsLabel.textProperty().bind(numberOfBoundsSlider.valueProperty().asString("%.0f"))
//                priceForBondsLabel.textProperty().bind(arg2.priceForBondsProperty().asString("%.2f zł"))
//                priceForBondsProvisionLabel.textProperty().bind(arg2.priceForBondsProvisionProperty().asString("%.2f zł"))
//                finalPriceLabel.textProperty().bind(arg2.finalPriceProperty().asString("%.2f zł"))
//                finalTaxLabel.textProperty().bind(arg2.finalTaxProperty().asString("%.2f zł"))
//                finalPriceTaxLabel.textProperty().bind(arg2.finalPriceAfterTaxProperty().asString("%.2f zł"))
//                finalPercentLabel.textProperty().bind(arg2.finalPercentProperty().asString("%.2f%%"))

//
//                val lt = arg2.lastTransaction
//                if (lt != null) {
//                    lastTransactionLabel.text = lt.lastTransaction
//                    lastTransactionVolumeLabel.text = lt.lastTransactionVolume.toString()
//                }
//                val bt = arg2.buyTriple
//                if (bt != null) {
//                    numberOfOrdersBuyLabel.text = bt.ordersCount.toString()
//                    buyVolumeLabel.text = bt.volume.toString()
//                    buyPriceLimitLabel.text = String.format("%.2f zł", bt.price)
//                }
//                val st = arg2.sellTriple
//                if (st != null) {
//                    numberOfOrdersSellLabel.text = st.ordersCount.toString()
//                    sellVolumeLabel.text = st.volume.toString()
//                    sellPriceLimitLabel.text = String.format("%.2f zł", st.price)
//                }
//
////                dayVolumeLabel.textProperty().bind(arg2.dayVolumeProperty().asString())
////                dayValueLabel.textProperty().bind(arg2.dayValueProperty().asString("%.2f zł"))
//
//                referencePriceLabel.text = String.format("%.2f zł", arg2.referencePrice)

        /* TODO */
//                openingPriceLabel.text = arg2.openingPriceProperty().asString("%.2f zł"))
//                minimalPriceLabel.textProperty().bind(arg2.minimalPriceProperty().asString("%.2f zł"))
//                maximalPriceLabel.textProperty().bind(arg2.maximalPriceProperty().asString("%.2f zł"))
//                lastPriceLabel.textProperty().bind(arg2.lastPriceProperty().asString("%.2f zł"))
//                changeLabel.textProperty().bind(arg2.changeProperty().asString("%.2f%%"))
        /* END TODO */


//                arg2.entitledToInterestDaysProperty().addListener((ListChangeListener<LocalDateTime>) change -> datyZapadania.getSelectionModel().select(arg2.getNextEntitledToInterestDay()));
//                arg2.payoutDaysProperty().addListener((ListChangeListener<LocalDateTime>) change -> datyWypłaty.getSelectionModel().select(arg2.getNextPayoutDay()));
//            }
//        })
//        se.getUpdateMarketsInfo().start();
    }

}
