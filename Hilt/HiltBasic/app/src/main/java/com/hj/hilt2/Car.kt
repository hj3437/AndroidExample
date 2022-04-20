package com.hj.hilt2

import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine){
    fun turnOn() = engine.start()
}

class Engine @Inject constructor(){
    fun start() = "Engine start...."
}
