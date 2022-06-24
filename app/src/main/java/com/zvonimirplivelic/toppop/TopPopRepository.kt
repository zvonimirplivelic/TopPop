package com.zvonimirplivelic.toppop

import com.zvonimirplivelic.toppop.remote.RetrofitInstance

class TopPopRepository {

    suspend fun getTopChart() =
        RetrofitInstance.api.getTopChart()

    suspend fun getAlbumDetails(albumId: Int) =
        RetrofitInstance.api.getAlbumDetails(albumId)
}