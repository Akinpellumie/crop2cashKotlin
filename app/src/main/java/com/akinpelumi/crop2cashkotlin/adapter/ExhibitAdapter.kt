package com.akinpelumi.crop2cashkotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akinpelumi.crop2cashkotlin.R
import com.akinpelumi.crop2cashkotlin.callback.ISingleExhibitSelectionCallback
import com.akinpelumi.crop2cashkotlin.model.ExhibitModel


class ExhibitAdapter(
    context: Context,
    dataSet: List<ExhibitModel>,
    singleExhibitSelectionCallBack: ISingleExhibitSelectionCallback
) :
    RecyclerView.Adapter<ExhibitAdapter.ViewHolder>() {
    private val localDataSet: List<ExhibitModel>
    private val exhibitImages: ExhibitHorizontalImageAdapter? = null
    private val mContext: Context
    private val mInflater: LayoutInflater
    private val mSingleExhibitSelectionCallBack: ISingleExhibitSelectionCallback

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = mInflater.inflate(R.layout.exhibit_card_view, viewGroup, false)
        val viewHolder = ViewHolder(view)
        Log.d(TAG, "CreateView")
        return viewHolder
        //        View view = LayoutInflater.from(mContext)
//                .inflate(R.layout.exhibit_card_view, viewGroup, false);
//
//        return new ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem: ExhibitModel = localDataSet[position]
        //holder.imageView.setImageResource();
        holder.imageRecyclerView.adapter =
            localDataSet[position].images?.let { ExhibitHorizontalImageAdapter(mContext, it) }
        holder.imageRecyclerView.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        holder.imageRecyclerView.setHasFixedSize(true)
        holder.titleTitleView.setText(currentitem.title)

        //set click callback
        holder.view.setOnClickListener { mSingleExhibitSelectionCallBack.onSelect(currentitem) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return localDataSet.size
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTitleView: TextView
        val imageRecyclerView: RecyclerView

        //public TextView getAmountTextView() {
        //            return amountTextView;
        //        }
        val view: View

        fun getxhibitTitleTextView(): TextView {
            return titleTitleView
        }

        fun getxhibitImageView(): RecyclerView {
            return imageRecyclerView
        }

        init {
            // Define click listener for the ViewHolder's View
            titleTitleView = view.findViewById<View>(R.id.xhibitTitle) as TextView
            imageRecyclerView = view.findViewById<View>(R.id.xhibitImageRecycler) as RecyclerView
            this.view = view
        }
    }

    companion object {
        const val TAG = "ExhibitAdapter"
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    //    public TransactionsRecyclerAdapter(List<Datum> dataSet) {
    //        localDataSet = dataSet;
    //    }
    init {
        localDataSet = dataSet
        mInflater = LayoutInflater.from(context)
        mContext = context
        mSingleExhibitSelectionCallBack = singleExhibitSelectionCallBack
    }
}
