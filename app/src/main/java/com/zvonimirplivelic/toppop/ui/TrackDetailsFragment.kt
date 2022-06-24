package com.zvonimirplivelic.toppop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zvonimirplivelic.toppop.R
import com.zvonimirplivelic.toppop.TopPopRepository
import com.zvonimirplivelic.toppop.model.TopChartResponse
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

        val currentTrackDetails: TopChartResponse.Tracks.Data  = args.selectedTrack

        val civAlbumImage: CircleImageView = view.findViewById(R.id.civ_album_picture)
        val tvTrackTitle: TextView = view.findViewById(R.id.tv_title_track)
        val tvArtistName: TextView = view.findViewById(R.id.tv_artist_name)
        val tvAlbumName: TextView = view.findViewById(R.id.tv_album_name)
        val rvTrackList: RecyclerView = view.findViewById(R.id.rv_album_tracks)

        viewModel = ViewModelProvider(this)[TopPopViewModel::class.java]

        Picasso.get()
            .load(currentTrackDetails.album.coverXl)
            .fit()
            .noFade()
            .into(civAlbumImage)

        tvTrackTitle.text = currentTrackDetails.title
        tvArtistName.text = currentTrackDetails.artist.name
        tvAlbumName.text = currentTrackDetails.album.title


        return view
    }
}