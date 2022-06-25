package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.model.TopChartResponse
import com.zvonimirplivelic.toppop.util.Resource
import com.zvonimirplivelic.toppop.util.TopPopViewModel

class TopChartFragment : Fragment() {

    private lateinit var viewModel: TopPopViewModel
    private lateinit var rvTopChart: RecyclerView
    private lateinit var topChartAdapter: TopChartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_chart, container, false)
        setHasOptionsMenu(true)

        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)

        topChartAdapter = TopChartAdapter()
        rvTopChart = view.findViewById(R.id.rv_top_chart)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]

        rvTopChart.apply {
            adapter = topChartAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getTopChart()

        viewModel.topChartData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    progressBar.isVisible = false
                    rvTopChart.isVisible = true

                    response.data?.let { topChart ->
                        topChartAdapter.differ.submitList(topChart.tracks.data)
                    }
                }

                is Resource.Error -> {
                    progressBar.isVisible = false
                    rvTopChart.isVisible = false
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    progressBar.isVisible = true
                    rvTopChart.isVisible = false
                }
            }
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val topChart = topChartAdapter.differ.currentList

        when (item.itemId) {
            R.id.sort_normal -> {
                topChartAdapter.differ.submitList(topChart.sortedBy { it.position })
                return true
            }
            R.id.sort_ascending -> {
                topChartAdapter.differ.submitList(topChart.sortedBy { it.duration })
                return true
            }
            R.id.sort_descending -> {
                topChartAdapter.differ.submitList(topChart.sortedByDescending { it.duration })
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}