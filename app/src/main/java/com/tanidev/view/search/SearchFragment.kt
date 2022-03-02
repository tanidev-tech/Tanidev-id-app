package com.tanidev.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanidev.R
import com.tanidev.core.domain.model.SearchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            searchAdapter.clearData()
            searchAdapter.setData(ArrayList<SearchResult>())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.et_search.doAfterTextChanged {
            lifecycleScope.launch {
                searchViewModel.search(it.toString())
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            searchAdapter.onItemClick = { selectedData ->
                Toast.makeText(activity, "${selectedData.searchName}", Toast.LENGTH_SHORT).show()
            }

            searchViewModel.searchResult.observe(viewLifecycleOwner, Observer { searchResult ->
                if (searchResult.isEmpty()) searchAdapter.clearData()
                searchAdapter.setData(searchResult)
            })

            with(rv_search) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = searchAdapter
            }
        }
    }
}