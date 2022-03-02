package com.tanidev.view.disease

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_appbar.view.*
import kotlinx.android.synthetic.main.fragment_disease.*
import kotlinx.android.synthetic.main.fragment_plant.progressBar

@AndroidEntryPoint
class DiseaseFragment : Fragment() {

    private val diseaseViewModel: DiseaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disease, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            view.toolbar.tv_toolbar_title.text = "Penyakit"

            val diseAdapter = DiseaseAdapter()
            diseAdapter.onItemClick = {selectedData ->
                val action = DiseaseFragmentDirections.actionDiseaseFragmentToDetailDiseaseFragment(selectedData)
                findNavController().navigate(action)
            }

            diseaseViewModel.disease.observe(viewLifecycleOwner, Observer { disease ->
                if (disease != null) {
                    when (disease) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            diseAdapter.setData(disease.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                disease.message?: getString(R.string.lbl_something_wrong),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rv_disease) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = diseAdapter
            }
        }
    }


}