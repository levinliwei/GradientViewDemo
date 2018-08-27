package com.teamar.electronicstickets.helper

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.SimpleViewSwitcher
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView
import com.waynelee.wayneleetestproject.R

/**
 * @author      levin
 * @Summary     XRecyclerViewHelper XRecyclerView 辅助类
 * @email       levin.li@teamar.cn
 * @data        2018/8/15
 * @org         Aurora Team
 */
class XRecyclerViewHelper(context: Context) {

    private var mContext: Context? = null

    init {
        this.mContext = context
    }

    /**
     * 设置 XRecyclerView 样式 属性
     */
    fun setXRecyclerViewStyle(xRecyclerView: XRecyclerView, isSetLoadMore: Boolean) {
        xRecyclerView.setLoadingMoreEnabled(true)
        xRecyclerView.setPullRefreshEnabled(isSetLoadMore)
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)

        setHeadFootView(xRecyclerView) //如果不适用类库自带的颜色样式 需要修改此方法中view

        //设置加载更多view文本padding
        xRecyclerView.defaultFootView.setPadding(20, 20, 20, 20)
    }

    /****
     * 因为类库暂时没有设置头部底部的view
     * 设置刷新头部、底部的加载view 状态文本 如果app背景色和自定义view文本颜色冲突需要以下方法
     * ****/
    private fun setHeadFootView(xRecyclerView: XRecyclerView) {
        //headView
        var headTextView: TextView = xRecyclerView.defaultRefreshHeaderView.rootView.findViewById(R.id.refresh_status_textview)
        headTextView.setTextColor(mContext!!.getColor(R.color.colorAccent))
        var progressBar: SimpleViewSwitcher = xRecyclerView.defaultRefreshHeaderView.rootView.findViewById(R.id.listview_header_progressbar)
        var progressView = AVLoadingIndicatorView(mContext)
        progressView.setIndicatorColor(mContext!!.resources.getColor(R.color.colorAccent))
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader)
        progressBar.setView(progressView)

        //footView
        xRecyclerView.defaultFootView.removeAllViews()

        xRecyclerView.defaultFootView.gravity = Gravity.CENTER
        xRecyclerView.defaultFootView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        var progressCon = SimpleViewSwitcher(mContext)
        progressCon.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        var footProgressView = AVLoadingIndicatorView(mContext)
        footProgressView.setIndicatorColor(mContext!!.resources.getColor(R.color.colorAccent))
        footProgressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader)
        progressCon.setView(footProgressView)
        xRecyclerView.defaultFootView.addView(progressCon)

        var footTextView = TextView(mContext)
        footTextView.text = mContext!!.getString(com.jcodecraeer.xrecyclerview.R.string.listview_loading)
        footTextView.setTextColor(mContext!!.resources.getColor(R.color.colorAccent))
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(mContext!!.resources.getDimension(com.jcodecraeer.xrecyclerview.R.dimen.textandiconmargin).toInt(), 0, 0, 0)
        footTextView.layoutParams = layoutParams
        xRecyclerView.defaultFootView.addView(footTextView)
    }
}