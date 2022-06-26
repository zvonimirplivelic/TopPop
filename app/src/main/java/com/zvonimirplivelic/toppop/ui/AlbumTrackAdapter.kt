package com.zvonimirplivelic.toppop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.model.AlbumDetailResponse
import com.zvonimirplivelic.toppop.util.DiffUtilExtension.autoNotify
import kotlin.properties.Delegates

class AlbumTrackAdapter : RecyclerView.Adapter<AlbumTrackAdapter.AlbumTrackItemViewHolder>() {

    private var albumTrackList: List<AlbumDetailResponse.Tracks.Data>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }

    inner class AlbumTrackItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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
        val currentTrack = albumTrackList[position]

        holder.itemView.apply {
            val trackPosition: TextView = findViewById(R.id.tv_track_position)
            val trackTitle: TextView = findViewById(R.id.tv_track_title)

            trackPosition.text = (position + 1).toString()
            trackTitle.text = currentTrack.title
        }
    }

    override fun getItemCount() = albumTrackList.size

    fun setData(trackList: List<AlbumDetailResponse.Tracks.Data>) {
        this.albumTrackList = trackList
    }
}