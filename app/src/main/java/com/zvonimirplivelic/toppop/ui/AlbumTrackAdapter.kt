package com.zvonimirplivelic.toppop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.model.AlbumDetailResponse

class AlbumTrackAdapter: RecyclerView.Adapter<AlbumTrackAdapter.AlbumTrackItemViewHolder>() {

    inner class AlbumTrackItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private val diffCallback =
        object : DiffUtil.ItemCallback<AlbumDetailResponse.Tracks.Data>() {
            override fun areItemsTheSame(
                oldItem: AlbumDetailResponse.Tracks.Data,
                newItem: AlbumDetailResponse.Tracks.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AlbumDetailResponse.Tracks.Data,
                newItem: AlbumDetailResponse.Tracks.Data
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumTrackItemViewHolder {
        return AlbumTrackItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.track_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumTrackItemViewHolder, position: Int) {
        val currentTrack = differ.currentList[position]

        holder.itemView.apply {
            val trackPosition: TextView = findViewById(R.id.tv_track_position)
            val trackTitle: TextView = findViewById(R.id.tv_track_title)

            trackPosition.text = (position + 1).toString()
            trackTitle.text = currentTrack.title
        }
    }

    override fun getItemCount() = differ.currentList.size
}