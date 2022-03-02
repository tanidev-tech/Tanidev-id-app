package com.tanidev.view.product

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanidev.R
import com.tanidev.core.data.Resource
import com.tanidev.view.plant.PlantAdapter
import com.tanidev.view.plant.PlantFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_appbar.view.*
import kotlinx.android.synthetic.main.fragment_plant.*
import kotlinx.android.synthetic.main.fragment_plant.progressBar
import kotlinx.android.synthetic.main.fragment_product.*

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            view.toolbar.setBackgroundResource(android.R.color.transparent)
            view.toolbar.tv_toolbar_title.text = "Produk"

            val prodAdapter = ProductAdapter()
            prodAdapter.onItemClick = {selectedData ->
                val action = ProductFragmentDirections.actionProductFragmentToDetailProductFragment(selectedData)
                findNavController().navigate(action)
            }

            productViewModel.product.observe(viewLifecycleOwner, Observer { product ->
                if (product != null) {
                    when (product) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            prodAdapter.setData(product.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                product.message?: getString(R.string.lbl_something_wrong),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rv_product) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = prodAdapter
            }
        }
    }


}