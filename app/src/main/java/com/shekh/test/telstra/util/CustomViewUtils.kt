package com.shekh.test.telstra.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.shekh.test.telstra.R

/**
 * Class to show image using glide
 */
class CustomViewUtils {

    companion object {
        /**
         * Show image using glide
         *
         * @param context application context
         * @param url url path of image
         * @param progressBar ProgressBar shows while loading image
         * @param imageView View where the image will be loaded
         */
        fun showImageWithGlide(context: Context, url: String?, progressBar: ProgressBar?, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(DrawableTransitionOptions().crossFade())
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.broken_image))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            progressBar?.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            progressBar?.visibility = View.GONE
                            return false
                        }
                    })
                    .into(imageView)
        }
    }
}
