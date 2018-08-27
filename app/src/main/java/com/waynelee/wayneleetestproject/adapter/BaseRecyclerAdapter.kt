package com.waynelee.wayneleetestproject.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

/**
 * @author      levin
 * @Summary     BaseRecyclerAdapter
 * @email       levin.li@teamar.cn
 * @data        2018/7/25
 * @org         Aurora Team
 */
abstract class BaseRecyclerAdapter<T>( val ctx: Context,val datas: ArrayList<T>?) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var view = getViewResource(viewType)
        var holder = BaseViewHolder(view)
        addAllViewItems(holder?.viewMap, view)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindData(holder?.viewMap, datas?.get(position), position)
    }

    /**
     * 添加item布局
     */
    abstract fun getViewResource(viewType: Int): View

    /**
     * 记录下item布局下所有控件
     */
    abstract fun addAllViewItems(viewMap: SparseArray<View>?, root: View)

    /**
     * 绑定item数据
     */
    abstract fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int)
}


