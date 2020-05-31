package com.saurabh.compiler.logger

import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic.Kind.ERROR

class Logger(private val messager: Messager) {

    fun logError(element: Element? = null, message: String? = null) {
        messager.printMessage(ERROR, message, element)
    }
}
