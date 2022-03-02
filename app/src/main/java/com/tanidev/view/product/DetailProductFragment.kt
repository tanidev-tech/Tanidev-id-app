package com.tanidev.view.product

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
import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.android.synthetic.main.fragment_detail_product.tv_general_desc
import kotlinx.android.synthetic.main.fragment_detail_product.tv_title

@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    private val detailProductViewModel: DetailProductViewModel by viewModels()
    private val args by navArgs<DetailProductFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            view.toolbar.tv_toolbar_title.text = args.product.productName

//            tv_title.text = args.product.productName
//            tv_general_desc.text = args.product.generalInfo
//            tv_how_to_info.text = args.product.generalInfo
//            tv_dosis_recommendation_info.text = args.product.recommentionDosisInfo

            detailProductViewModel.detailProduct(args.product.productId, args.product.productName)
                .observe(viewLifecycleOwner, Observer { product ->
                    if (product != null) {
                        when(product) {
                            is Resource.Loading -> {
                                progressBar.visibility = View.VISIBLE
                                llContent.visibility = View.GONE
                            }
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                tv_title.text = product.data?.productName
                                tv_general_desc.text = HtmlCompat.fromHtml(product.data!!.content, 0)
                            }
                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                llContent.visibility = View.VISIBLE
                                Toast.makeText(requireContext(), product.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            detailProductViewModel.updateViewTotal(args.product.viewTotal+1, args.product.productId)
        }
    }
}