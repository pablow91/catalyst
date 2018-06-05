package pl.info.qwerty.catalyst.ui

import javafx.fxml.FXML

class FXMLBondController {

    @FXML
    private var generalController: FXMLGeneralBondController? = null

    @FXML
    private var lastSessionController: FXMLLastSessionController? = null

    @FXML
    private var bondCalcController: FXMLBondCalcController? = null

    fun init(bond: BondProperties): BondController {
        val gc = generalController ?: throw Exception("General controller is null")
        gc.init(bond)
        val lsc = lastSessionController ?: throw Exception("Last session controller is null")
        lsc.init(bond)
        val bcc = bondCalcController ?: throw Exception("Bond controller is null")
        bcc.init(bond)
        return BondController()
    }

}

class BondController