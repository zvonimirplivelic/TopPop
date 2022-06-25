package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.TopPopRepository
import com.zvonimirplivelic.toppop.model.TopChartResponse
import com.zvonimirplivelic.toppop.util.Resource
import com.zvonimirplivelic.toppop.util.TopPopViewModel
import de.hdodenhof.circleimageview.CircleImageView


class TrackDetailsFragment : Fragment() {

    private val args by navArgs<TrackDetailsFragmentArgs>()
    private lateinit var viewModel: TopPopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_track_details, container, false)

        val currentTrackDetails: TopChartResponse.Tracks.Data = args.selectedTrack

        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val trackDataLayout: ConstraintLayout = view.findViewById(R.id.cl_track_data)

        val civAlbumImage: CircleImageView = view.findViewById(R.id.civ_album_picture)
        val tvTrackTitle: TextView = view.findViewById(R.id.tv_title_track)
        val tvArtistName: TextView = view.findViewById(R.id.tv_artist_name)
        val tvAlbumName: TextView = view.findViewById(R.id.tv_album_name)

        val albumTrackAdapter = AlbumTrackAdapter()
        val rvTrackList: RecyclerView = view.findViewById(R.id.rv_album_tracks)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]

        rvTrackList.apply {
            adapter = albumTrackAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getAlbumTrackList(currentTrackDetails.album.id)

        Picasso.get()
            .load(currentTrackDetails.album.coverXl)
            .fit()
            .noFade()
            .into(civAlbumImage)

        tvTrackTitle.text = currentTrackDetails.title
        tvArtistName.text = currentTrackDetails.artist.name
        tvAlbumName.text = currentTrackDetails.album.title

        viewModel.albumTracksData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    progressBar.isVisible = false
                    trackDataLayout.isVisible = true

                    response.data?.let { trackList ->
                        albumTrackAdapter.differ.submitList(trackList.tracks.data)
                    }
                }

                is Resource.Error -> {
                    progressBar.isVisible = false
                    trackDataLayout.isVisible = false
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    progressBar.isVisible = true
                    trackDataLayout.isVisible = false
                }
            }
        }

        return view
    }
}