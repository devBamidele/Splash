package com.example.splash.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.splash.R
import com.example.splash.network.UnsplashPhoto
import com.example.splash.viewmodel.UnsplashApiStatus

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UnsplashPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
* Uses the Coil library to load an image by URL into an [ImageView]
*/
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {

            // Effects that can be applied to the image
            crossfade(true)
            crossfade(600)
            transformations(RoundedCornersTransformation(25f))

            // The loading animation
            placeholder(R.drawable.loading_animation)

            // The error image used when coil is unable to get the image
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * This binding adapter displays the [UnsplashApiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation. If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("UnsplashApiStatus")
fun bindStatus(statusImageView: ImageView, status: UnsplashApiStatus?) {
    when (status) {
        UnsplashApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        UnsplashApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UnsplashApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}

