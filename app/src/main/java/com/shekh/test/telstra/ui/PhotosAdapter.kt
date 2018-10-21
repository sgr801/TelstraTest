package com.shekh.test.telstra.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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
            photo.title?.let {
                holder.title.text = it
            }

            photo.description?.let {
                holder.description.text = it
            }

            photo.imageHref?.let {
                CustomViewUtils.showImageWithGlide(context, photo.imageHref, holder.photoProgressbar, holder.photo)
            }
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