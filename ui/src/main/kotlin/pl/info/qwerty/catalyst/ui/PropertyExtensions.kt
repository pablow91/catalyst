package pl.info.qwerty.catalyst.ui

import javafx.beans.binding.NumberBinding
import javafx.beans.binding.StringBinding
import javafx.beans.property.DoubleProperty

fun DoubleProperty.asPercent(): StringBinding {
    return this.asString("%.2f%%")
}

fun DoubleProperty.asMoney(): StringBinding {
    return this.asString("%.2f zł")
}

fun NumberBinding.asPercent(): StringBinding {
    return this.asString("%.2f%%")
}

fun NumberBinding.asMoney(): StringBinding {
    return this.asString("%.2f zł")
}

