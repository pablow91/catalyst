package pl.info.qwerty.catalyst.ui.fxml

import com.sun.javafx.fxml.LoadListener
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.util.BuilderFactory
import java.net.URL
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class AbstractLoadListener : LoadListener {
    override fun readLanguageProcessingInstruction(language: String?) {
    }

    override fun beginUnknownTypeElement(name: String?) {
    }

    override fun beginScriptElement() {
    }

    override fun beginUnknownStaticPropertyElement(name: String?) {
    }

    override fun beginReferenceElement() {
    }

    override fun readInternalAttribute(name: String?, value: String?) {
    }

    override fun readComment(comment: String?) {
    }

    override fun readPropertyAttribute(name: String?, sourceType: Class<*>?, value: String?) {
    }

    override fun endElement(value: Any?) {
    }

    override fun beginInstanceDeclarationElement(type: Class<*>?) {
    }

    override fun readEventHandlerAttribute(name: String?, value: String?) {
    }

    override fun beginIncludeElement() {
    }

    override fun beginRootElement() {
    }

    override fun readImportProcessingInstruction(target: String?) {
    }

    override fun beginPropertyElement(name: String?, sourceType: Class<*>?) {
    }

    override fun readUnknownStaticPropertyAttribute(name: String?, value: String?) {
    }

    override fun beginDefineElement() {
    }

    override fun beginCopyElement() {
    }

}


class bindFXML<T : Node>() : ReadOnlyProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw Exception()
    }
}

class bindOptionalFXML<T : Node> : ReadOnlyProperty<Any?, T?> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return value
    }
}

object KotlinFXMLLoader {
    fun <T> load(location: URL, resources: ResourceBundle? = null, builderFactory: BuilderFactory? = null): T {
        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = location
        fxmlLoader.resources = resources
        fxmlLoader.builderFactory = builderFactory
        fxmlLoader.impl_setLoadListener(object : AbstractLoadListener() {

            var counter = 0

            override fun beginInstanceDeclarationElement(type: Class<*>?) {
                counter++
            }

            override fun endElement(value: Any?) {
                //If created element has 'id' set try to inject to bindFXML or bindOptionalFXML field delegate
//                println(value)
                if (value is Node && value.id != null) {
                    val controller: Any = fxmlLoader.getController()
                    try {
//                        println(value.id)
                        val field = controller.javaClass.getDeclaredField("${value.id}\$delegate")
//                        if (field.type == bindFXML<Node>::javaClass || field.type == bindOptionalFXML<Node>::javaClass) {
                        field.isAccessible = true
                        val delegate: Any = field.get(controller)
                        val valueField = delegate.javaClass.getDeclaredField("value")
                        valueField.isAccessible = true
                        val fieldType = controller.javaClass.getMethod("get${value.getId().capitalize()}").returnType
                        //Check if field type and created object have compatible types
                        if (!fieldType.isInstance(value)) {
                            throw IllegalArgumentException("Node '${value.id}' type [${value.javaClass.name}] is is not subtype of [${fieldType.name}] ")
                        }
                        valueField.set(delegate, value)
                        valueField.isAccessible = false
                        field.isAccessible = false
//                        }
                    } catch (e: NoSuchFieldException) {
                        println("No field for element ${value.id}")
                    }
                }
                counter--

                //When counter is 0 the root tag has been closed.
                //Now is good time to validate if all fields with bindFXML are set
                if (counter == 0) {
                    val controller: Any = fxmlLoader.getController()
                    val invalidFields = controller.javaClass.declaredFields.filter {
                        if (bindFXML<Node>::javaClass != it.type) {
                            return@filter false
                        }
                        try {
                            it.isAccessible = true
                            val valueField = it.type.getDeclaredField("value")
                            valueField.isAccessible = true
                            if (valueField.get(it.get(controller)) == null) {
                                return@filter true
                            }
                        } finally {
                            it.isAccessible = false
                        }
                        return@filter false
                    }.map { it.name.substringBefore("$") }.toCollection(ArrayList())
                    if (invalidFields.isNotEmpty()) {
                        throw IllegalStateException("Those field $invalidFields has not been injected. It they are optional use bindOptionalFXML()")
                    }
                }
            }
        })
        return fxmlLoader.load()
    }
}