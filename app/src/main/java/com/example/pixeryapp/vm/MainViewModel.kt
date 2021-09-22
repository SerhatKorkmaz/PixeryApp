package com.example.pixeryapp.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pixeryapp.R

class MainViewModel : ViewModel() {

    val startingSpeed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val middleSpeed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val endingSpeed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val currentProjectIndex : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val currentMediaIndex : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val firstToMiddleStepSize : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val middleToEndStepSize : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val mediaSize : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val middleIndex : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val currentSpeed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val currentIndex : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val currentImage : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    init {
        currentIndex.value = 0
        startingSpeed.value = 1.0
        middleSpeed.value = 1.0
        endingSpeed.value = 1.0
        middleIndex.value = 0
        currentProjectIndex.value = 0
        currentMediaIndex.value = 0
        firstToMiddleStepSize.value = 0.0
        middleToEndStepSize.value = 0.0
        currentSpeed.value = 33.0
        mediaSize.value = 100
        currentImage.value = R.drawable.p0

    }

    fun setInitialSpeed(){
        currentSpeed.value = 33.0 / startingSpeed.value!!
    }

    fun calculateFTMSS() { //FTMSS : first to middle step size
        val startSpeed = 33.0 / startingSpeed.value!!
        val targetSpeed = 33.0 / middleSpeed.value!!
        val mIndex = middleIndex.value!!

        if (startSpeed != targetSpeed) {

            if(mIndex != 0 ) firstToMiddleStepSize.value = (targetSpeed - startSpeed) / mIndex
            else firstToMiddleStepSize.value = targetSpeed - startSpeed

        }

        else firstToMiddleStepSize.value = 0.0
    }

    fun calculateMTESS() { //MTESS : middle to end step size
        val startSpeed = 33.0 / middleSpeed.value!!
        val targetSpeed = 33.0 / endingSpeed.value!!
        val size = mediaSize.value!!
        val mIndex = middleIndex.value!!

        if (startSpeed != targetSpeed) {

            if(mIndex != size ) middleToEndStepSize.value = (targetSpeed - startSpeed) / (size - mIndex)
            else middleToEndStepSize.value = (targetSpeed - startSpeed)

        }

        else middleToEndStepSize.value = 0.0
    }

    fun changeSpeed(){
        if(currentIndex.value!! < middleIndex.value!!) currentSpeed.value = currentSpeed.value!! + firstToMiddleStepSize.value!!
        else currentSpeed.value = currentSpeed.value!! + middleToEndStepSize.value!!
    }


}