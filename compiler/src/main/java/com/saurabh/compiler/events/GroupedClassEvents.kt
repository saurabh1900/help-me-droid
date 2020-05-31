package com.saurabh.compiler.events

class GroupedClassEvents(
    private val identifier: String
) {

    /*
    * map of event name and annotation method
     */
    private val eventsMap: MutableMap<String, OnEventAnnotatedMethod> = mutableMapOf()

    fun add(annotation: OnEventAnnotatedMethod) {
        eventsMap[annotation.getEvent()] = annotation
    }

    fun getEvents() = eventsMap
}
