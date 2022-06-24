package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.util.Resource
import com.zvonimirplivelic.toppop.util.TopPopViewModel
import timber.log.Timber

class TopChartFragment : Fragment() {

    private lateinit var viewModel: TopPopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_chart, container, false)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]

        viewModel.getTopChart()

        viewModel.topChartData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let { topChart ->
                        Timber.d("TopChart: $topChart")
                    }
                }

                is Resource.Error -> {

                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {

                }
            }

        }

        return view
    }
}