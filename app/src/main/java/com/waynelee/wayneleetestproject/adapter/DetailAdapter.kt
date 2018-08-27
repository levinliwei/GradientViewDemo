package com.waynelee.wayneleetestproject.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.waynelee.wayneleetestproject.R
import com.waynelee.wayneleetestproject.model.MovieTop250.SubjectsBean

/**
 * @author      levin
 * @Summary     DetailAdapter
 * @email       levin.li@teamar.cn
 * @data        2018/7/25
 * @org         Aurora Team
 */
class DetailAdapter(context: Context, dataList: List<SubjectsBean>) : RecyclerView.Adapter<DetailAdapter.SimpleViewHolder>() {

    private var mContext: Context? = null
    var mDataList: List<SubjectsBean>? = null

    init {
        this.mContext = context
        this.mDataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : SimpleViewHolder = SimpleViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.item_deatil, parent, false))

    override fun getItemCount(): Int = mDataList!!.size

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.titleTv.text = mDataList!![position].title
        holder.infoTv.text = mDataList!![position].title
        Glide.with(this!!.mContext!!).load(mDataList!![position].images!!.small).into(holder.imageIv)
    }

    //内部类(Inner classes)
    inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTv: TextView = view.findViewById(R.id.tvMovieTitle)
        var infoTv: TextView = view.findViewById(R.id.tvMovieInfo)
        var imageIv: ImageView = view.findViewById(R.id.ivMovieImage)
    }
}