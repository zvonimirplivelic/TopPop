package com.zvonimirplivelic.toppop.model


import com.google.gson.annotations.SerializedName


data class AlbumDetailResponse(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("contributors")
    val contributors: List<Contributor>,
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
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("genre_id")
    val genreId: Int,
    @SerializedName("genres")
    val genres: Genres,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("nb_tracks")
    val nbTracks: Int,
    @SerializedName("record_type")
    val recordType: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("tracks")
    val tracks: Tracks,
    @SerializedName("type")
    val type: String,
    @SerializedName("upc")
    val upc: String
) {
    data class Artist(
        @SerializedName("id")
        val id: Int,
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
        @SerializedName("tracklist")
        val tracklist: String,
        @SerializedName("type")
        val type: String
    )

    data class Contributor(
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
        @SerializedName("role")
        val role: String,
        @SerializedName("share")
        val share: String,
        @SerializedName("tracklist")
        val tracklist: String,
        @SerializedName("type")
        val type: String
    )

    data class Genres(
        @SerializedName("data")
        val `data`: List<Data>
    ) {
        data class Data(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("type")
            val type: String
        )
    }

    data class Tracks(
        @SerializedName("data")
        val `data`: List<Data>
    ) {
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
            @SerializedName("preview")
            val preview: String,
            @SerializedName("rank")
            val rank: Int,
            @SerializedName("readable")
            val readable: Boolean,
            @SerializedName("title")
            val title: String,
            @SerializedName("title_short")
            val titleShort: String,
            @SerializedName("title_version")
            val titleVersion: String,
            @SerializedName("type")
            val type: String
        ) {
            data class Album(
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
            )

            data class Artist(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("tracklist")
                val tracklist: String,
                @SerializedName("type")
                val type: String
            )
        }
    }
}