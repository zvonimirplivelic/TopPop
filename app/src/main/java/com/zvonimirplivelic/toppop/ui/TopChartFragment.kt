package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.util.Resource
import com.zvonimirplivelic.toppop.util.TopPopViewModel
import timber.log.Timber

class TopChartFragment : Fragment() {

    private lateinit var viewModel: TopPopViewModel

    private lateinit var topChartAdapter: TopChartAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_chart, container, false)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]
        recyclerView = view.findViewById(R.id.rv_top_chart)
        progressBar = view.findViewById(R.id.progress_bar)

        topChartAdapter = TopChartAdapter()
        recyclerView.apply {
            adapter = topChartAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getTopChart()

        viewModel.topChartData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    progressBar.isVisible = false
                    recyclerView.isVisible = true

                    response.data?.let { topChart ->
                        topChartAdapter.differ.submitList(topChart.tracks.data)
                    }
                }

                is Resource.Error -> {
                    progressBar.isVisible = false
                    recyclerView.isVisible = false
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    progressBar.isVisible = true
                    recyclerView.isVisible = false
                }
            }
        }

        return view
    }
}