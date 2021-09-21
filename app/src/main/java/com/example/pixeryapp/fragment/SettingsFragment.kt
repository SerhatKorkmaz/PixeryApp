package com.example.pixeryapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pixeryapp.MainActivity
import com.example.pixeryapp.R
import com.example.pixeryapp.databinding.FragmentSettingsBinding
import com.example.pixeryapp.databinding.FragmentSliderBinding
import com.example.pixeryapp.vm.MainViewModel
import com.google.android.material.slider.Slider

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var  _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    var stopFlag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)



        binding.apply {

            sliderFirstSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Start Speed : ", slider.value.toString())
                    viewModel.startingSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateFTMSS()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.toString())
                }
            })

            sliderCenterSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Center Speed : ", slider.value.toString())
                    viewModel.middleSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateFTMSS()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.toString())
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.toString())
                }
            })

            sliderFrameIndex.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Middle Frame : ", slider.value.toString())
                    viewModel.middleIndex.value = slider.value.toInt()
                    viewModel.calculateFTMSS()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.toString())
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.toString())
                }
            })

            sliderLastSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Last Speed : ", slider.value.toString())
                    viewModel.endingSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.toString())
                }
            })

            bPlayPause.setOnClickListener {
                stopFlag = !stopFlag
                Log.d("stopFlag" , "current value of stopFlag is ${stopFlag}")
            }

            viewModel.startingSpeed.observe(viewLifecycleOwner, Observer { startingSpeed ->
                tvStartSpeed.text = getString(R.string.starting_speed) + startingSpeed.toString()
            })

            viewModel.middleSpeed.observe(viewLifecycleOwner, Observer { middleSpeed ->
                tvMiddleSpeed.text = getString(R.string.middle_speed) + middleSpeed.toString()
            })

            viewModel.middleIndex.observe(viewLifecycleOwner, Observer { middleIndex ->
                tvFrameIndex.text = getString(R.string.media_frame_index) + middleIndex.toString()
            })

            viewModel.endingSpeed.observe(viewLifecycleOwner, Observer { endingSpeed ->
                tvFinishSpeed.text = getString(R.string.ending_speed) + endingSpeed.toString()
            })

            /* kotlin.run {
                while (!stopFlag)
                for (i in /*viewModel.currentIndex.value!!*/0 .. 99){
                    image.setImageResource((activity as MainActivity).ImageList[i])
                    Thread.sleep(viewModel.currentSpeed.value!!.toLong())
                    viewModel.currentIndex.value = i
                    viewModel.changeSpeed()
                    //viewModel.currentIndex.value!!
                }
            }*/
        }

    }
}