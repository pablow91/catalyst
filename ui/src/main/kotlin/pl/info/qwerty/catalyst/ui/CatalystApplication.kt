package pl.info.qwerty.catalyst.ui

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import pl.info.qwerty.catalyst.ui.fxml.KotlinFXMLLoader
import java.io.IOException

class Obligacje : Application() {

    @Throws(IOException::class)
    override fun start(stage: Stage) {
        val root: Parent = KotlinFXMLLoader.load(javaClass.getResource("Layout.fxml"))
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
