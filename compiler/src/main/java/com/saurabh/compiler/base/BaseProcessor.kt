package com.saurabh.compiler.base

import com.saurabh.compiler.logger.Logger
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.SourceVersion

abstract class BaseProcessor : AbstractProcessor() {

    lateinit var logger: Logger
    protected var fileDir: File? = null

    override fun init(environment: ProcessingEnvironment) {
        super.init(environment)

        logger = Logger(environment.messager)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
