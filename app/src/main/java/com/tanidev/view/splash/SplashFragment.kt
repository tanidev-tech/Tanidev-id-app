package com.tanidev.view.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tanidev.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    val splashViewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        var countDownTimer = object : CountDownTimer(1000, 100) {
            override fun onFinish() {
                navigate()
            }

            override fun onTick(p0: Long) {

            }
        }
        countDownTimer.start()

        return view
    }

    private fun navigate() {
        splashViewModel.isLogin.observe(viewLifecycleOwner, Observer { isLogin ->
            if (isLogin) {
                findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        })

    }
}