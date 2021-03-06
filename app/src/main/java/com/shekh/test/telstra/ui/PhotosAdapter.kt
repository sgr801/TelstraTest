package com.shekh.test.telstra.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shekh.test.telstra.R
import com.shekh.test.telstra.model.Photo
import com.shekh.test.telstra.util.CustomViewUtils
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(private val context: Context, private val mList: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = mList[position]

        if (holder is PhotoViewHolder) {
            holder.title.text = photo.title?.let {
                it
            } ?: context.getString(R.string.title_unavailable)

            holder.description.text = photo.description?.let {
                it
            } ?: context.getString(R.string.description_unavailable)

            CustomViewUtils.showImageWithGlide(context, photo.imageHref, holder.photoProgressbar, holder.photo)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.titleTextView
        var description: TextView = itemView.descriptionTextView
        var photo: ImageView = itemView.photoImageView
        var photoProgressbar: ProgressBar = itemView.photo_progress_bar
    }
}