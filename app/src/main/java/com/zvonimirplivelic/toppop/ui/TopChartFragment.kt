package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.util.Resource
import com.zvonimirplivelic.toppop.TopPopViewModel
import com.zvonimirplivelic.toppop.model.TopChartResponse

class TopChartFragment : Fragment() {

    private lateinit var viewModel: TopPopViewModel
    private lateinit var rvTopChart: RecyclerView
    private lateinit var topChartAdapter: TopChartAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var topChart: List<TopChartResponse.Tracks.Data> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_chart, container, false)
        setHasOptionsMenu(true)

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)

        linearLayoutManager = LinearLayoutManager(activity)
        topChartAdapter = TopChartAdapter()
        rvTopChart = view.findViewById(R.id.rv_top_chart)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]

        rvTopChart.apply {
            setHasFixedSize(true)
            adapter = topChartAdapter
            layoutManager = linearLayoutManager
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTopChart()
        }

        if (topChart.isEmpty()) {
            viewModel.getTopChart()
        }

        viewModel.topChartData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    swipeRefreshLayout.isRefreshing = false
                    rvTopChart.isVisible = true

                    response.data?.let { topChart ->
                        this.topChart = topChart.tracks.data
                        topChartAdapter.setData(topChart.tracks.data)
                    }
                }

                is Resource.Error -> {
                    swipeRefreshLayout.isRefreshing = false
                    rvTopChart.isVisible = false
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    swipeRefreshLayout.isRefreshing = true
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

        val sortTopChart = this.topChart

        when (item.itemId) {
            R.id.sort_normal -> {
                topChartAdapter.setData(sortTopChart.sortedBy { it.position })
                return true
            }
            R.id.sort_ascending -> {
                topChartAdapter.setData(sortTopChart.sortedBy { it.duration })
                return true
            }
            R.id.sort_descending -> {
                topChartAdapter.setData(sortTopChart.sortedByDescending { it.duration })
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}