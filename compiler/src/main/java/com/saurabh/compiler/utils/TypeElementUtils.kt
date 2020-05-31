package com.saurabh.compiler.utils

import me.eugeniomarletti.kotlin.metadata.KotlinClassMetadata
import me.eugeniomarletti.kotlin.metadata.isPrimary
import me.eugeniomarletti.kotlin.metadata.kotlinMetadata
import javax.lang.model.element.TypeElement

/*
* Returns map of [Key,Value] pair, where key is the argument name
* and value is argument type.
* */
fun TypeElement.getConstructorMap(): MutableMap<String, String> {
    val metadata = this.kotlinMetadata as KotlinClassMetadata
    val nameResolver = metadata.data.nameResolver
    val mainConstructor = metadata.data.classProto.constructorList.find { it.isPrimary }
    val constructorList = mainConstructor?.valueParameterList

    val constructorMap = mutableMapOf<String, String>()
    constructorList?.forEach {
        val key = nameResolver.getString(it.name)
        val value = nameResolver.getQualifiedClassName(it.type.className)
        constructorMap[key] = value.replace("/", ".")
    }

    return constructorMap
}
