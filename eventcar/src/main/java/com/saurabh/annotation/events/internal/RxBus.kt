package com.saurabh.annotation.events.internal

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    private val subject = PublishSubject.create<Any>().toSerialized()

    fun allEvents(): Observable<Any> = subject

    fun onEvent(eventName: String): Observable<String> =
        subject.filter { anObject -> eventName == anObject }.cast(String::class.java)

    fun <C> onEvent(theClass: Class<C>): Observable<C> {
        return subject.filter { item -> theClass.isInstance(item) || theClass == item }
            .cast(theClass)
    }

    fun post(event: Any) {
        subject.onNext(event)
    }

    companion object {
        private val bus = RxBus()
        fun getDefault() =
            bus
    }
}

