package com.dalakoti07.android.caavoapplication.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxUtils private constructor(){
    companion object{
        private var mInstance: RxUtils? = null
        fun getInstance(): RxUtils? {
            if (mInstance == null) {
                mInstance = RxUtils()
            }
            return mInstance
        }
    }

    // use replay publisher
    // will take care of item count
    private val publisher = PublishSubject.create<Int>()

    fun publish(event: Int) {
        publisher.onNext(event)
    }

    // Listen should return an Observable
    fun listen(): Observable<Int>? {
        return publisher
    }
}