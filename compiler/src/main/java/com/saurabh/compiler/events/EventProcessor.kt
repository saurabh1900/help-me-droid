package com.saurabh.compiler.events

import com.google.auto.service.AutoService
import com.saurabh.annotation.events.OnEvent
import com.saurabh.compiler.base.BaseProcessor
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class EventsProcessor : BaseProcessor() {

    private var eventsMap: MutableMap<String, GroupedClassEvents> = mutableMapOf()
    private val identifiers: MutableList<String> = mutableListOf()
    private lateinit var onEventAnnotationValidator: OnEventAnnotationValidator

    override fun init(environment: ProcessingEnvironment) {
        super.init(environment)
        onEventAnnotationValidator = OnEventAnnotationValidator(logger)
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            OnEvent::class.java.name
        )
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]?.let {
            fileDir = File(it)
        } ?: {
            logger.logError(message = "File directory could not be set")
        }()

        roundEnv?.getElementsAnnotatedWith(OnEvent::class.java)
            ?.forEach { element ->
                if (onEventAnnotationValidator.isValid(element).not()) {
                    return true
                }

                val annotation = OnEventAnnotatedMethod(element as ExecutableElement)

                val identifier = annotation.getPackageName() + annotation.getClassSimpleName()
                identifiers.add(identifier)

                eventsMap[identifier]
                    ?.add(annotation)
                    ?: {
                        eventsMap[identifier] = GroupedClassEvents(identifier)
                        eventsMap[identifier]!!.add(annotation)
                    }()
            }

        identifiers.forEach {
            generateCode(it)
        }

        return true
    }

    private fun generateCode(identifier: String) {
        EventsClassGenerator(
            eventsMap[identifier]!!,
            fileDir!!
        ).generate()
    }
}
