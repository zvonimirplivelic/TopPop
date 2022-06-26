package com.zvonimirplivelic.toppop

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zvonimirplivelic.toppop.model.AlbumDetailResponse
import com.zvonimirplivelic.toppop.model.TopChartResponse
import com.zvonimirplivelic.toppop.util.Constants.TIME_DELAY
import com.zvonimirplivelic.toppop.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class TopPopViewModel(
    private val app: Application
) : AndroidViewModel(app) {
    private val topPopRepository = TopPopRepository()

    val topChartData: MutableLiveData<Resource<TopChartResponse>> = MutableLiveData()
    private var topChartDataResponse: TopChartResponse? = null

    val albumTracksData: MutableLiveData<Resource<AlbumDetailResponse>> = MutableLiveData()
    private var albumTracksDataResponse: AlbumDetailResponse? = null

    fun getTopChart() = viewModelScope.launch {
        safeTopChartNetworkCall()
    }

    fun getAlbumTrackList(albumId: Int) = viewModelScope.launch {
        safeAlbumTrackListNetworkCall(albumId)
    }

    private suspend fun safeTopChartNetworkCall() {

        topChartData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = topPopRepository.getTopChart()
                delay(TIME_DELAY)
                topChartData.postValue(handleTopChartResponse(response))
            } else {
                topChartData.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> topChartData.postValue(Resource.Error("Network Failure"))
                else -> topChartData.postValue(Resource.Error("Conversion Error: $t"))
            }
            Timber.d("Throwable: $t")
        }
    }

    private suspend fun safeAlbumTrackListNetworkCall(albumId: Int) {

        albumTracksData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = topPopRepository.getAlbumDetails(albumId)
                delay(TIME_DELAY)
                albumTracksData.postValue(handleAlbumTrackListResponse(response))
            } else {
                albumTracksData.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> topChartData.postValue(Resource.Error("Network Failure"))
                else -> topChartData.postValue(Resource.Error("Conversion Error: $t"))
            }
            Timber.d("Throwable: $t")
        }
    }

    private fun handleTopChartResponse(response: Response<TopChartResponse>): Resource<TopChartResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                topChartDataResponse = resultResponse

                return Resource.Success(topChartDataResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleAlbumTrackListResponse(response: Response<AlbumDetailResponse>): Resource<AlbumDetailResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                albumTracksDataResponse = resultResponse

                return Resource.Success(albumTracksDataResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<TopPopApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}