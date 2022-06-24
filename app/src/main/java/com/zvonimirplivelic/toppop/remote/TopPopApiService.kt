package com.zvonimirplivelic.toppop.remote

import com.zvonimirplivelic.toppop.model.AlbumDetailResponse
import com.zvonimirplivelic.toppop.model.TopChartResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TopPopApiService {

    @GET("chart")
    suspend fun getTopChart(): Response<TopChartResponse>

    @GET("album/{albumId}")
    suspend fun getAlbumDetails(
        @Path("albumId") albumId: Int
    ): Response<AlbumDetailResponse>
}