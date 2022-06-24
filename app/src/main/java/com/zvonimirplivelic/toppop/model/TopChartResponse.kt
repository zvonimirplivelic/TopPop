package com.zvonimirplivelic.toppop.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopChartResponse(
    @SerializedName("albums")
    val albums: Albums,
    @SerializedName("artists")
    val artists: Artists,
    @SerializedName("playlists")
    val playlists: Playlists,
    @SerializedName("podcasts")
    val podcasts: Podcasts,
    @SerializedName("tracks")
    val tracks: Tracks
) : Parcelable {
    @Parcelize
    data class Albums(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("artist")
            val artist: Artist,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("cover_big")
            val coverBig: String,
            @SerializedName("cover_medium")
            val coverMedium: String,
            @SerializedName("cover_small")
            val coverSmall: String,
            @SerializedName("cover_xl")
            val coverXl: String,
            @SerializedName("explicit_lyrics")
            val explicitLyrics: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("md5_image")
            val md5Image: String,
            @SerializedName("position")
            val position: Int,
            @SerializedName("record_type")
            val recordType: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("tracklist")
            val tracklist: String,
            @SerializedName("type")
            val type: String
        ) : Parcelable {
            @Parcelize
            data class Artist(
                @SerializedName("id")
                val id: Int,
                @SerializedName("link")
                val link: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("picture")
                val picture: String,
                @SerializedName("picture_big")
                val pictureBig: String,
                @SerializedName("picture_medium")
                val pictureMedium: String,
                @SerializedName("picture_small")
                val pictureSmall: String,
                @SerializedName("picture_xl")
                val pictureXl: String,
                @SerializedName("radio")
                val radio: Boolean,
                @SerializedName("tracklist")
                val tracklist: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable
        }
    }

    @Parcelize
    data class Artists(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("picture_big")
            val pictureBig: String,
            @SerializedName("picture_medium")
            val pictureMedium: String,
            @SerializedName("picture_small")
            val pictureSmall: String,
            @SerializedName("picture_xl")
            val pictureXl: String,
            @SerializedName("position")
            val position: Int,
            @SerializedName("radio")
            val radio: Boolean,
            @SerializedName("tracklist")
            val tracklist: String,
            @SerializedName("type")
            val type: String
        ) : Parcelable
    }

    @Parcelize
    data class Playlists(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("checksum")
            val checksum: String,
            @SerializedName("creation_date")
            val creationDate: String,
            @SerializedName("id")
            val id: Long,
            @SerializedName("link")
            val link: String,
            @SerializedName("md5_image")
            val md5Image: String,
            @SerializedName("nb_tracks")
            val nbTracks: Int,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("picture_big")
            val pictureBig: String,
            @SerializedName("picture_medium")
            val pictureMedium: String,
            @SerializedName("picture_small")
            val pictureSmall: String,
            @SerializedName("picture_type")
            val pictureType: String,
            @SerializedName("picture_xl")
            val pictureXl: String,
            @SerializedName("public")
            val `public`: Boolean,
            @SerializedName("title")
            val title: String,
            @SerializedName("tracklist")
            val tracklist: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("user")
            val user: User
        ) : Parcelable {
            @Parcelize
            data class User(
                @SerializedName("id")
                val id: Long,
                @SerializedName("name")
                val name: String,
                @SerializedName("tracklist")
                val tracklist: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable
        }
    }

    @Parcelize
    data class Podcasts(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("available")
            val available: Boolean,
            @SerializedName("description")
            val description: String,
            @SerializedName("fans")
            val fans: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("picture_big")
            val pictureBig: String,
            @SerializedName("picture_medium")
            val pictureMedium: String,
            @SerializedName("picture_small")
            val pictureSmall: String,
            @SerializedName("picture_xl")
            val pictureXl: String,
            @SerializedName("share")
            val share: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String
        ) : Parcelable
    }

    @Parcelize
    data class Tracks(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {

        @Parcelize
        data class Data(
            @SerializedName("album")
            val album: Album,
            @SerializedName("artist")
            val artist: Artist,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("explicit_content_cover")
            val explicitContentCover: Int,
            @SerializedName("explicit_content_lyrics")
            val explicitContentLyrics: Int,
            @SerializedName("explicit_lyrics")
            val explicitLyrics: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("md5_image")
            val md5Image: String,
            @SerializedName("position")
            val position: Int,
            @SerializedName("preview")
            val preview: String,
            @SerializedName("rank")
            val rank: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("title_short")
            val titleShort: String,
            @SerializedName("title_version")
            val titleVersion: String,
            @SerializedName("type")
            val type: String
        ) : Parcelable {

            @Parcelize
            data class Album(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("cover_big")
                val coverBig: String,
                @SerializedName("cover_medium")
                val coverMedium: String,
                @SerializedName("cover_small")
                val coverSmall: String,
                @SerializedName("cover_xl")
                val coverXl: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("md5_image")
                val md5Image: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("tracklist")
                val tracklist: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable

            @Parcelize
            data class Artist(
                @SerializedName("id")
                val id: Int,
                @SerializedName("link")
                val link: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("picture")
                val picture: String,
                @SerializedName("picture_big")
                val pictureBig: String,
                @SerializedName("picture_medium")
                val pictureMedium: String,
                @SerializedName("picture_small")
                val pictureSmall: String,
                @SerializedName("picture_xl")
                val pictureXl: String,
                @SerializedName("radio")
                val radio: Boolean,
                @SerializedName("tracklist")
                val tracklist: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable
        }
    }
}