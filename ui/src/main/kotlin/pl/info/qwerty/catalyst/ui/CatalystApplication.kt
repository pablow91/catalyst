package pl.info.qwerty.catalyst.ui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Obligacje : Application() {

    override fun start(stage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("Layout.fxml"))
        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
        stage.sizeToScene()
        stage.title = "Catalyst"
        stage.show()
    }

}

fun main(args: Array<String>) {
    Application.launch(Obligacje::class.java, *args)
}
