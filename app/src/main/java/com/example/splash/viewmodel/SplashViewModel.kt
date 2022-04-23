package com.example.splash.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splash.network.UnsplashApi
import com.example.splash.network.UnsplashPhoto
import kotlinx.coroutines.launch

/**
 * A set of constants that will show the status of the API
 */
enum class UnsplashApiStatus { LOADING, ERROR, DONE }

class SplashViewModel : ViewModel() {

    // To store the kotlin objects received in the predefined format
    private val _photos = MutableLiveData<List<UnsplashPhoto>>()
    val photos: LiveData<List<UnsplashPhoto>> = _photos

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<UnsplashApiStatus>()
    val status: LiveData<UnsplashApiStatus> = _status

    /**
     * Call the getUnsplashPhoto() on the constructor so we can display status immediately
     */
    init{
        getUnsplashPhoto()
    }

    /**
     * Gets Unsplash photos information from the API service created by Retrofit service and updates
     * the [UnsplashPhoto] [List] [LiveData]
     */
    private fun getUnsplashPhoto() {

        viewModelScope.launch {
            _status.value = UnsplashApiStatus.LOADING
            try {
                _photos.value = UnsplashApi.retrofitService.getPhotos()
                _status.value = UnsplashApiStatus.DONE
            } catch (e : Exception) {
                _status.value =  UnsplashApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

}