package com.tanidev.view.plant

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.tanidev.R
import com.tanidev.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_appbar.view.*
import kotlinx.android.synthetic.main.fragment_detail_plant.llContent
import kotlinx.android.synthetic.main.fragment_detail_plant.progressBar
import kotlinx.android.synthetic.main.fragment_detail_plant.tv_general_desc
import kotlinx.android.synthetic.main.fragment_detail_plant.tv_title

@AndroidEntryPoint
class DetailPlantFragment : Fragment() {

    private val detailPlantViewModel: DetailPlantViewModel by viewModels()
    private val args by navArgs<DetailPlantFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            view?.toolbar.tv_toolbar_title.text = args.plant.plantName

//            tv_title.text = args.plant.plantName
//            tv_general_desc.text = args.plant.generalInfo
//            tv_nutrition_info.text = args.plant.nutritionInfo
//            tv_disease_info.text = args.plant.diseaseInfo

            detailPlantViewModel.detailPlant(args.plant.plantId, args.plant.plantName)
                .observe(viewLifecycleOwner, Observer { plant ->
                    if (plant != null) {
                        when(plant) {
                            is Resource.Loading -> {
                                progressBar.visibility = View.VISIBLE
                                llContent.visibility = View.GONE
                            }
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                tv_title.text = plant.data?.plantName
                                tv_general_desc.text = HtmlCompat.fromHtml(plant.data!!.content, 0)
                            }
                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                Toast.makeText(requireContext(), plant.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            detailPlantViewModel.updateViewTotal(args.plant.viewTotal+1, args.plant.plantId)
        }
    }
}