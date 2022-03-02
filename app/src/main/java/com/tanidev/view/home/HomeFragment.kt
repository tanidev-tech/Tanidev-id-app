package com.tanidev.view.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.DialogCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanidev.R
import com.tanidev.core.domain.model.MostViewed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dashboard_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val homeContentAdapter = HomeContentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            homeContentAdapter.clearData()
            homeContentAdapter.setData(ArrayList<MostViewed>())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.btn_plant.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_plantFragment) }
        view.btn_disease.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_diseaseFragment) }
        view.btn_product.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_productFragment) }
        view.llSearch.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_searchFragment) }
        view.btn_dashboard.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_dashboardFragment) }

        return view;
    }

    private fun dashboard() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dashboard_dialog)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.TOP
        lp.verticalMargin = 0.12f

        dialog.btn_close.setOnClickListener { dialog.dismiss() }

        dialog.btn_registration.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.attributes = lp
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            homeContentAdapter.onItemClick = { selectedData ->
                Toast.makeText(activity, "${selectedData.mostViewdName}", Toast.LENGTH_SHORT)
                    .show()
//                val action = PlantFragmentDirections.navigateToDetailPlant(selectedData)
//                findNavController().navigate(action)
            }

            homeViewModel.mostViewed.observe(viewLifecycleOwner, Observer { mostViewed ->
                if (mostViewed != null) {
                    homeContentAdapter.setData(mostViewed)
                }
            })

            with(rv_most_viewed) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeContentAdapter
            }
        }
    }
}