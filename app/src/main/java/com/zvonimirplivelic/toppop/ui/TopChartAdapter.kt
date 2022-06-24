package com.zvonimirplivelic.toppop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.model.TopChartResponse

class TopChartAdapter : RecyclerView.Adapter<TopChartAdapter.TopChartItemViewHolder>() {

    inner class TopChartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback =
        object : DiffUtil.ItemCallback<TopChartResponse.Tracks.Data>() {
            override fun areItemsTheSame(
                oldItem: TopChartResponse.Tracks.Data,
                newItem: TopChartResponse.Tracks.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TopChartResponse.Tracks.Data,
                newItem: TopChartResponse.Tracks.Data
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, diffCallback)

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
        val chartItem = differ.currentList[position]

        holder.itemView.apply {
            val tvTrackPosition: TextView = findViewById(R.id.tv_track_position)
            val tvTrackTitle: TextView = findViewById(R.id.tv_track_title)
            val tvArtistName: TextView = findViewById(R.id.tv_artist_name)
            val tvTrackDuration: TextView = findViewById(R.id.tv_track_duration)

            tvTrackPosition.text = chartItem.position.toString()
            tvTrackTitle.text = chartItem.title
            tvArtistName.text = chartItem.artist.name
            tvTrackDuration.text = chartItem.duration.toString()
        }
    }

    override fun getItemCount() = differ.currentList.size

}