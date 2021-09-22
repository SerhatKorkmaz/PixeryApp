package com.example.pixeryapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    var stopFlag : Boolean = false
    private lateinit var handler : Handler
    private lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

        binding.apply {

            sliderLastSpeed.value = viewModel.endingSpeed.value!!.toFloat()
            sliderFrameIndex.value = viewModel.middleIndex.value!!.toFloat()
            sliderCenterSpeed.value = viewModel.middleSpeed.value!!.toFloat()
            sliderFirstSpeed.value = viewModel.startingSpeed.value!!.toFloat()

            image.setImageResource(viewModel.currentImage.value!!)

            handler = Handler(Looper.getMainLooper())
            runnable = Runnable {
                image.setImageResource((activity as MainActivity).ImageList[viewModel.currentIndex.value!!])
                Log.d("Animation", "Current Image index is ${viewModel.currentIndex.value} and delay is${viewModel.currentSpeed.value!!}")
                if(viewModel.currentIndex.value != 99 ) viewModel.currentIndex.value = viewModel.currentIndex.value!! + 1
                else{
                    viewModel.currentIndex.value = 0
                    viewModel.setInitialSpeed()
                }
                viewModel.changeSpeed()
                handler.postDelayed( runnable, viewModel.currentSpeed.value!!.toLong())
            }


            sliderFirstSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Start Speed : ", slider.value.toString())
                    viewModel.startingSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateFTMSS()
                    viewModel.setInitialSpeed()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.value!!.toString())
                }
            })

            sliderCenterSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Center Speed : ", slider.value.toString())
                    viewModel.middleSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateFTMSS()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.value!!.toString())
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.value!!.toString())
                }
            })

            sliderFrameIndex.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Middle Frame : ", slider.value.toString())
                    viewModel.middleIndex.value = slider.value.toInt()
                    viewModel.calculateFTMSS()
                    Log.d("New FTMSS is : ", viewModel.firstToMiddleStepSize.value!!.toString())
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.value!!.toString())
                }
            })

            sliderLastSpeed.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("Slider Last Speed : ", slider.value.toString())
                    viewModel.endingSpeed.value = String.format("%.2f", slider.value).toDouble()
                    viewModel.calculateMTESS()
                    Log.d("New MTESS is : ", viewModel.middleToEndStepSize.value!!.toString())
                }
            })

            bPlayPause.setOnClickListener {
                if(!stopFlag) handler.post(runnable)
                else handler.removeCallbacks(runnable)
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

            viewModel.currentImage.observe(viewLifecycleOwner, Observer { Image ->
                image.setImageResource(Image)
            })
        }

    }
}