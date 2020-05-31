package com.saurabh.annotation.events

import com.saurabh.annotation.events.internal.RxBus

object Events {

    /*
    * Put documentation around its usability
    * */
    fun raiseEvent(event: String) {
        RxBus.getDefault().post(event)
    }
}
