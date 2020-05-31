package com.saurabh.compiler.events

import com.saurabh.annotation.events.OnEvent
import javax.lang.model.element.ExecutableElement

class OnEventAnnotatedMethod(
    private val element: ExecutableElement
) {
    private val event = element.getAnnotation(OnEvent::class.java).event

    fun getEvent(): String = event

    fun getMethodName(): String = element.simpleName.toString()

    fun getPackageName(): String {
        return getQualifiedName().replace(
            ".${getClassSimpleName()}",
            ""
        )
    }

    fun getClassSimpleName() = element.enclosingElement.simpleName.toString()

    fun getQualifiedName() = element.enclosingElement.toString()
}
