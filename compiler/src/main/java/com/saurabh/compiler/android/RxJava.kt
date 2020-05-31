package com.saurabh.compiler.android

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

val androidScheduler = ClassName("io.reactivex.android.schedulers", "AndroidSchedulers")
val schedulers = ClassName("io.reactivex.schedulers", "Schedulers")
val disposable = ClassName("io.reactivex.disposables", "CompositeDisposable")
val rxBus = MemberName("com.saurabh.annotation.events.internal", "RxBus")
