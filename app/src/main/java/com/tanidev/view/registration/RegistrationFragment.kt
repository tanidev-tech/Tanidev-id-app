package com.tanidev.view.registration

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
import com.google.android.material.textfield.TextInputEditText
import com.tanidev.R
import com.tanidev.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.progressBar
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import kotlinx.android.synthetic.main.snackbar_icon_text.view.*

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        view.et_name.doAfterTextChanged {
            view.btn_registration.isEnabled = isNotEmpty(view)
        }

        view.et_phone_number.doAfterTextChanged {
            view.btn_registration.isEnabled = isNotEmpty(view)
        }

        view.et_region.doAfterTextChanged {
            view.btn_registration.isEnabled = isNotEmpty(view)
        }

        view.et_identification_no.doAfterTextChanged {
            view.btn_registration.isEnabled = isNotEmpty(view)
        }

        view.et_password.doAfterTextChanged {
            view.btn_registration.isEnabled = isNotEmpty(view)
        }

        view.btn_registration.setOnClickListener {
            val name = view.et_name.text.toString()
            val phoneNo = view.et_phone_number.text.toString()
            val region = view.et_region.text.toString()
            val identificationNo = view.et_identification_no.text.toString()
            val password = view.et_password.text.toString()
            register(name, phoneNo, region, identificationNo, password) }
        view.tv_go_to_login.setOnClickListener { findNavController().navigate(R.id.action_registrationFragment_to_loginFragment) }

        return view
    }

    private fun register(
        name: String,
        phoneNo: String,
        region: String,
        identificationNo: String,
        password: String
    ) {
        println("register")
        registrationViewModel.register(name,
            phoneNo,
            region,
            identificationNo,
            password)
            .observe(viewLifecycleOwner, Observer { user ->
            if (user !== null) {
                when(user) {
                    is Resource.Loading ->  {
                        btn_registration.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        btn_registration.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showSuccessMessage()
                        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                    }
                    is Resource.Error -> {
                        btn_registration.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showErrorMessage(user.message)
                    }
                }
            }
        })
    }

    private fun isNotEmpty(view: View): Boolean {
        val etName = view.findViewById<TextInputEditText>(R.id.et_name)
        val etPhoneNumber = view.findViewById<TextInputEditText>(R.id.et_phone_number)
        val etRegion = view.findViewById<TextInputEditText>(R.id.et_region)
        val etIdentificationo = view.findViewById<TextInputEditText>(R.id.et_identification_no)
        val etPassword = view.findViewById<TextInputEditText>(R.id.et_password)

        return etName.toString().isNotEmpty() && etPhoneNumber.toString().isNotEmpty() &&
                etRegion.toString().isNotEmpty() && etIdentificationo.toString().isNotEmpty() &&
                etPassword.toString().isNotEmpty()

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