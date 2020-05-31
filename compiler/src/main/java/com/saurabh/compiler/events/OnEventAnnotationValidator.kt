package com.saurabh.compiler.events

import com.saurabh.annotation.events.OnEvent
import com.saurabh.compiler.logger.Logger
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.Modifier.*

class OnEventAnnotationValidator(
    private val logger: Logger
) {
    fun isValid(element: Element): Boolean {
        if (element.kind != ElementKind.METHOD) {
            logger.logError(
                element,
                "Only methods can be annotated with @${OnEvent::class}"
            )
            return false
        }

        return true
    }
}
