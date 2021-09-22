package com.example.pixeryapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private lateinit var handler : Handler
    private lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding = FragmentSliderBinding.bind(view)

        binding.apply {

            handler = Handler(Looper.getMainLooper())
            runnable = Runnable {
                    image.setImageResource((activity as MainActivity).ImageList[viewModel.currentIndex.value!!])
                    Log.d("Animation", "Current Image index is ${viewModel.currentIndex.value} and delay is${viewModel.currentSpeed.value!!}")
                    if(viewModel.currentIndex.value != 99 ) viewModel.currentIndex.value = viewModel.currentIndex.value!! + 1
                    else viewModel.currentIndex.value = 0
                    handler.postDelayed( runnable, viewModel.currentSpeed.value!!.toLong())
            }




            slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                    Log.d("onStartTrackingTouch", slider.value.toString())
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d("onStopTrackingTouch", slider.value.toString())
                    viewModel.currentIndex.value = slider.value.toInt()
                    //image.setImageResource((activity as MainActivity).ImageList[slider.value.toInt()])

                }
            })

            bSettings.setOnClickListener {
                val action = SliderFragmentDirections.actionSliderFragmentToSettingsFragment()
                findNavController().navigate(action)
            }

            bPlayPause.setOnClickListener {
                if(!stopFlag) handler.post(runnable)
                else handler.removeCallbacks(runnable)
                stopFlag = !stopFlag
                Log.d("stopFlag" , "current value of stopFlag is ${stopFlag}")
            }

            viewModel.currentIndex.observe(viewLifecycleOwner, Observer { index ->
                slider.value = index.toFloat()
                viewModel.currentImage.value = (activity as MainActivity).ImageList[index]
                tvProjectFrame.text = getString(R.string.current_project_frame) + index.toString()
                tvMediaFrame.text = getString(R.string.media_frame_index) + index.toString()
            })

            viewModel.currentImage.observe(viewLifecycleOwner, Observer { Image ->
                image.setImageResource(Image)
            })

        }


    }


}