package com.tanidev.view.disease

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_detail_disease.*

@AndroidEntryPoint
class DetailDiseaseFragment : Fragment() {

    private val detailDiseaseViewModel: DetailDiseaseViewModel by viewModels()
    private val args by navArgs<DetailDiseaseFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_disease, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            view.toolbar.tv_toolbar_title.text = args.disease.diseaseName

//            tv_title.text = args.disease.diseaseName
//            tv_general_desc.text = args.disease.content
//            tv_counter_measure_info.text = args.disease.counterMeasureInfo
//            tv_prevention_info.text = args.disease.preventionInfo

            detailDiseaseViewModel.detailDesease(args.disease.diseaseId, args.disease.diseaseName)
                .observe(viewLifecycleOwner, Observer { disease ->
                    if (disease != null) {
                        when(disease) {
                            is Resource.Loading -> {
                                progressBar.visibility = View.VISIBLE
                                llContent.visibility = View.GONE
                            }
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                tv_title.text = disease.data?.diseaseName
                                tv_general_desc.text = HtmlCompat.fromHtml(disease.data!!.content, 0)
                                tv_counter_measure_info.text = ""
                                tv_prevention_info.text = ""
                            }
                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                Toast.makeText(requireContext(), disease.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            detailDiseaseViewModel.updateViewTotal(args.disease.viewTotal+1, args.disease.diseaseId)
        }
    }
}