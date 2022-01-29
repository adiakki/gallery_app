package com.android.galleryapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.galleryapp.R
import com.android.galleryapp.domain.model.DataState
import com.android.galleryapp.presentation.adapter.GalleryAdapter
import com.android.galleryapp.presentation.factory.GalleryViewModelFactory
import com.android.galleryapp.presentation.viewmodel.GalleryViewModel
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.galleryapp.databinding.FragmentGalleryBinding
import com.android.galleryapp.presentation.utils.*
import com.google.gson.Gson

class GalleryFragment : Fragment() {

    private val galleryViewModel : GalleryViewModel by viewModels { GalleryViewModelFactory() }
    private lateinit var galleryDataBinding: FragmentGalleryBinding
    private val galleryAdapter by lazy { GalleryAdapter(arrayListOf()) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        return galleryDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addGalleryListObserver()
        fetchGalleryData()
    }

    private fun fetchGalleryData(isFromSwipeToRefresh : Boolean = false) {
        galleryViewModel.fetchGalleryData(isFromSwipeToRefresh)
    }

    private fun initUI() {
       galleryDataBinding.rvGallery.apply {
           setHasFixedSize(true)
           val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
           sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
           layoutManager = sGrid
           adapter = galleryAdapter
       }

       galleryDataBinding.swipeRefreshLayout.setOnRefreshListener {
           if (requireContext().isConnectedToInternet()) {
               fetchGalleryData(true)
           } else {
               galleryDataBinding.swipeRefreshLayout.isRefreshing = false
               showNoInternetConnectionError()
           }
       }
    }

    private fun addGalleryListObserver() {
        galleryViewModel.galleryListLiveData.observe(viewLifecycleOwner) { response ->
            when(response.dataState) {
                DataState.LOADING -> {
                    galleryDataBinding.progressBar.visible()
                }
                DataState.SUCCESS -> {
                    galleryDataBinding.progressBar.gone()
                    galleryDataBinding.swipeRefreshLayout.isRefreshing = false
                    response.data?.let { galleryResponse ->
                        galleryAdapter.setData(galleryResponse.galleryData?.galleryList?: arrayListOf())
                        PreferenceHelper.getPrefInstance().putString(AppConstants.KEY_GALLERY_DATA, Gson().toJson(galleryResponse))
                    }
                }
                DataState.ERROR -> {
                    galleryDataBinding.swipeRefreshLayout.isRefreshing = false
                    galleryDataBinding.progressBar.gone()
                }
            }
        }

        galleryViewModel.errorLiveData.observe(viewLifecycleOwner) {error ->
            when (error) {
                is NoConnectError -> {
                    showNoInternetConnectionError()
                }
            }
        }
    }

    private fun showNoInternetConnectionError() {
        Toast.makeText(requireContext(), getString(R.string.no_internet_connection),
            Toast.LENGTH_SHORT).show()
    }
}