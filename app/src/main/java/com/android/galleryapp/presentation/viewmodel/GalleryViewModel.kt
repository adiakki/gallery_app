package com.android.galleryapp.presentation.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.galleryapp.domain.model.DataState
import com.android.galleryapp.domain.model.GalleryDataResponse
import com.android.galleryapp.domain.model.ResultData
import com.android.galleryapp.domain.usecase.GalleryUseCase
import com.android.galleryapp.presentation.application.GalleryApplication
import com.android.galleryapp.presentation.utils.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryUseCase: GalleryUseCase) : ViewModel(){

    val galleryListLiveData = MutableLiveData<ResultData<out GalleryDataResponse>>()
    val errorLiveData = MutableLiveData<CustomError>()

    fun fetchGalleryData(isFromSwipeToRefresh : Boolean = false) {
        viewModelScope.launch (Dispatchers.IO) {
            val isInternetConnected = GalleryApplication.getAppInstance().isConnectedToInternet()
            if (!isFromSwipeToRefresh) {
                galleryListLiveData.postValue(ResultData(null, DataState.LOADING))
                val localResponse =
                    PreferenceHelper.getPrefInstance().getString(AppConstants.KEY_GALLERY_DATA)

                if (!TextUtils.isEmpty(localResponse)) {
                    val galleryResponse = Gson().fromJson(
                        localResponse,
                        GalleryDataResponse::class.java
                    )
                    galleryResponse?.let {
                        galleryListLiveData.postValue(
                            ResultData(
                                galleryResponse,
                                DataState.SUCCESS
                            )
                        )
                    }
                } else {
                    if (!isInternetConnected) {
                        galleryListLiveData.postValue(ResultData(null, DataState.ERROR))
                        errorLiveData.postValue(NoConnectError())
                        return@launch
                    }
                }
            }

            if (isInternetConnected) {
                galleryUseCase.fetchGalleryList()
                    .catch {
                        galleryListLiveData.postValue(ResultData(null, DataState.ERROR, it.message))
                    }
                    .collect { response ->
                        galleryListLiveData.postValue(response)
                    }
            }
        }
    }
}