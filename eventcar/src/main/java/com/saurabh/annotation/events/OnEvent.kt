package com.saurabh.annotation.events

/*
* Put documentation around its usability
* */
@Target(AnnotationTarget.FUNCTION)
annotation class OnEvent(
    val event: String
)



