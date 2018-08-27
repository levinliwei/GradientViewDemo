package com.waynelee.wayneleetestproject.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * @author      levin
 * @Summary     BaseViewHolder
 * @email       levin.li@teamar.cn
 * @data        2018/7/25
 * @org         Aurora Team
 */
class BaseViewHolder : RecyclerView.ViewHolder {
    var viewMap= SparseArray<View>()
    constructor(itemView: View?) : super(itemView)
}