package com.teamar.selfserviceorder.helper

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.wang.avi.AVLoadingIndicatorView
import com.waynelee.wayneleetestproject.R

/**
 * @author levin
 * @Summary LoadingHelper
 * @email levin.li@teamar.cn
 * @data 2018/8/3
 * @org Aurora Team
 */
class LoadingHelper(context: Context, progressBarColor: Int, tipsText: Int, tipsColor: Int) {

    val DEFAULT_VALUE: Int = -1
    var mProgressDialog: ProgressbarDialog? = null
    //提示信息文本颜色
    var mTipsColor: Int = DEFAULT_VALUE
    //提示信息的文本
    private var mTipsText: Int = DEFAULT_VALUE
    // 加载view的颜色
    private var mProgressBarColor: Int = DEFAULT_VALUE
    private var mContext: Context? = null

    /**
     * @param progressBarColor 加载view颜色
     * @param tipsText         提示文本
     * @param tipsColor        提示语颜色
     */
    init {
        this.mContext = context
        this.mProgressBarColor = progressBarColor
        this.mTipsText = tipsText
        this.mTipsColor = tipsColor
    }

    /**
     * 显示数据加载进度条
     */
    fun showLoadingProgressBar() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressbarDialog(this!!.mContext!!)
            mProgressDialog?.setProgressBarStyle(mProgressBarColor, mTipsText, mTipsColor)
            mProgressDialog?.setCanceledOnTouchOutside(true)
            mProgressDialog?.show()
        } else {
            mProgressDialog?.show()
        }
    }

    /**
     * 取消加载进度条
     */
    fun dismissLoadingProgressBar() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            mProgressDialog?.cancel()
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }

    class ProgressbarDialog(context: Context) : Dialog(context, R.style.TransParentDialogStyle) {
        val DEFAULT_VALUE: Int = -1
        //提示信息文本颜色
        var mTipsColor: Int = DEFAULT_VALUE
        //提示信息的文本
        var mTipsText: Int = DEFAULT_VALUE
        // 加载view的颜色
        var mProgressBarColor: Int = DEFAULT_VALUE
        private var mContext: Context? = null

        init {
            this.mContext = context
        }

        /**
         * @param progressBarColor 加载view颜色
         * @param tipsText         提示文本
         * @param tipsColor        提示语颜色
         */
        fun setProgressBarStyle(progressBarColor: Int, tipsText: Int, tipsColor: Int): ProgressbarDialog {
            mProgressBarColor = progressBarColor
            mTipsText = tipsText
            mTipsColor = tipsColor
            return this
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            var view: View = layoutInflater.inflate(R.layout.progressbar_dialog, null)
            var loadAVI: AVLoadingIndicatorView = view.findViewById(R.id.progress_bar_avi)
            var progressTips: TextView = view.findViewById(R.id.progress_bar_msg_tips)
            loadAVI.setIndicatorColor(mContext!!.resources.getColor(
                    if (mProgressBarColor != DEFAULT_VALUE) mProgressBarColor else R.color.black))
            progressTips.setTextColor(mContext!!.resources.getColor(
                    if (mTipsColor != DEFAULT_VALUE) mTipsColor else R.color.black))
            progressTips.text = mContext!!.getString(if (mTipsText != DEFAULT_VALUE) mTipsText else R.string.loading_data)
            setContentView(view)
        }
    }
}