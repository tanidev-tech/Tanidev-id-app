package com.tanidev.view.login

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.tanidev.R
import com.tanidev.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.snackbar_icon_text.view.*

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btn_login?.isEnabled = false
        view.et_phone_number.doAfterTextChanged {
            view.btn_login?.isEnabled = it.toString().isNotEmpty() && it.toString().isNotEmpty()
        }
        view.et_password.doAfterTextChanged {
            val value = it.toString().isNotEmpty() && it.toString().isNotEmpty()
            print(value)
            view.btn_login?.isEnabled = value
        }

        view.btn_login.setOnClickListener {
            val phoneNo = view.et_phone_number.text.toString()
            val password = view.et_password.text.toString()
            auth(phoneNo, password)
        }

        view.tv_signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        return view
    }

    private fun auth(phoneNo: String, password: String) {
        loginViewModel.login(phoneNo, password).observe(viewLifecycleOwner, Observer { user ->
            if (user !== null) {
                when(user) {
                    is Resource.Loading ->  {
                        btn_login.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        btn_login.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showSuccessMessage()
                        findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                    }
                    is Resource.Error -> {
                        btn_login.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showErrorMessage(user.message)
                    }
                }
            }
        })
    }

    private fun showErrorMessage(message: String?) {
        val snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG)
        val customView = layoutInflater.inflate(R.layout.snackbar_icon_text, null)

        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackbarView = snackbar.view as Snackbar.SnackbarLayout
        snackbarView.setPadding(0, 0 ,0 ,0)

        customView.message.text = message
        customView.icon.setImageResource(R.drawable.ic_close)
        customView.parent_view.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light))
        snackbarView.addView(customView, 0)
        snackbar.show()
    }

    fun showSuccessMessage() {
        val snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG)
        val customView = layoutInflater.inflate(R.layout.snackbar_icon_text, null)

        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackbarView = snackbar.view as Snackbar.SnackbarLayout
        snackbarView.setPadding(0, 0 ,0 ,0)

        customView.message.text = "Success"
        customView.icon.setImageResource(R.drawable.ic_done)
        customView.parent_view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.light_green_A700))
        snackbarView.addView(customView, 0)
        snackbar.show()
    }

}