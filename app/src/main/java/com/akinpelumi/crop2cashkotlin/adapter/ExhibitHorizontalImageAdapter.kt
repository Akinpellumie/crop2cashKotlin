package com.akinpelumi.crop2cashkotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akinpelumi.crop2cashkotlin.R
import com.squareup.picasso.Picasso


class ExhibitHorizontalImageAdapter(context: Context?, private val localDataSet: List<String>) :
    RecyclerView.Adapter<ExhibitHorizontalImageAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = mInflater.inflate(R.layout.horizontal_image_view, viewGroup, false)
        val viewHolder = ViewHolder(view)
        Log.d(TAG, "CreateView")
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(localDataSet[position]).into(holder.imgView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return localDataSet.size
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView
        private val view: View

        init {
            imgView = itemView.findViewById<View>(R.id.ivExhibit) as ImageView
            view = itemView
        }
    }

    companion object {
        const val TAG = "ExhibitHorImageAdapter"
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    init {
        mInflater = LayoutInflater.from(context)
    }
}
