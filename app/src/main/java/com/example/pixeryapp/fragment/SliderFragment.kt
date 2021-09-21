package com.example.pixeryapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pixeryapp.MainActivity
import com.example.pixeryapp.R
import com.example.pixeryapp.databinding.FragmentSettingsBinding
import com.example.pixeryapp.databinding.FragmentSliderBinding
import com.example.pixeryapp.vm.MainViewModel
import com.google.android.material.slider.Slider

class SliderFragment : Fragment(R.layout.fragment_slider) {

    private var  _binding : FragmentSliderBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var touchlistener : Slider.OnSliderTouchListener
    var stopFlag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSliderBinding.bind(view)

        binding.apply {
            slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                    Log.d("onStartTrackingTouch", slider.value.toString())
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("onStopTrackingTouch", slider.value.toString())
                    viewModel.currentIndex.value = slider.value.toInt()
                    image.setImageResource((activity as MainActivity).ImageList[slider.value.toInt()])

                }
            })

            bSettings.setOnClickListener {
                val action = SliderFragmentDirections.actionSliderFragmentToSettingsFragment()
                findNavController().navigate(action)
            }

            bPlayPause.setOnClickListener {
                stopFlag = !stopFlag
                Log.d("stopFlag" , "current value of stopFlag is ${stopFlag}")
            }

            viewModel.currentIndex.observe(viewLifecycleOwner, Observer { index ->
                slider.value = index.toFloat()
                tvProjectFrame.text = getString(R.string.current_project_frame) + index.toString()
                tvMediaFrame.text = getString(R.string.media_frame_index) + index.toString()
            })

            /*kotlin.run {
                while (!stopFlag)
                    Log.d("stopFlag" , "Thread is running")
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