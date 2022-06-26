package com.zvonimirplivelic.toppop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.model.AlbumDetailResponse
import com.zvonimirplivelic.toppop.model.TopChartResponse
import com.zvonimirplivelic.toppop.util.DiffUtilExtension.autoNotify
import kotlin.properties.Delegates

class TopChartAdapter : RecyclerView.Adapter<TopChartAdapter.TopChartItemViewHolder>() {

    private var topChart: List<TopChartResponse.Tracks.Data>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }

    inner class TopChartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopChartItemViewHolder {
        return TopChartItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.top_chart_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopChartItemViewHolder, position: Int) {
        val chartItem = topChart[position]

        holder.itemView.apply {
            val tvTrackPosition: TextView = findViewById(R.id.tv_track_position)
            val tvTrackTitle: TextView = findViewById(R.id.tv_track_title)
            val tvArtistName: TextView = findViewById(R.id.tv_artist_name)
            val tvTrackDuration: TextView = findViewById(R.id.tv_track_duration)

            tvTrackPosition.text = chartItem.position.toString()
            tvTrackTitle.text = chartItem.title
            tvArtistName.text = chartItem.artist.name
            tvTrackDuration.text = convertTime(chartItem.duration)

            setOnClickListener {
                val action =
                    TopChartFragmentDirections.actionTopChartFragmentToTrackDetailsFragment(
                        chartItem
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = topChart.size

    private fun convertTime(duration: Int): String {
        val minutes = duration / 60
        var seconds = (duration % 60).toString()

        if(seconds.toInt() < 10) {
            seconds = "0$seconds"
        }

        return "$minutes:$seconds"
    }

    fun setData(topChart: List<TopChartResponse.Tracks.Data>) {
        this.topChart = topChart
    }
}