package com.teamar.selfserviceorder.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.os.Build
import android.support.annotation.RequiresApi
import com.waynelee.wayneleetestproject.R


/**
 * @author levin
 * @Summary ActSwitchHelper
 * @email levin.li@teamar.cn
 * @data 2018/8/4
 * @org Aurora Team
 */
class ActSwitchHelper(activity: Activity) {

    var mActivity: Activity? = null

    init {
        this.mActivity = activity
    }

    /**
     * @param currentActivity 当前activity
     * @param targetActivityClass 目标activity
     * @param sharedElementView Transition类型view
     * @param tranName Transition类型名称
     */
    fun sharedElementSwitch(currentActivity: Activity, targetActivityClass: Class<*>,
                            sharedElementView: View, tranName: String) {
        val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                currentActivity, sharedElementView, tranName)
        ActivityCompat.startActivity(currentActivity,
                Intent(currentActivity, targetActivityClass), compat.toBundle())
    }

    /**
     * 启动activity  跳转目标页面
     *
     * @param fromActivity    从哪个activity 跳转
     * @param toActivityClass 前往哪个activity class
     * @param isFinishCurrent 是否finish 当前activity
     */
    fun startActivityToTargetPage(fromActivity: Activity, toActivityClass: Class<*>, isFinishCurrent: Boolean) {
        fromActivity.startActivity(Intent(fromActivity, toActivityClass))
        if (isFinishCurrent) {
            fromActivity.finish()
        }
        fromActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    /**
     * 跳转web 页面
     *
     * @param fromActivity    从哪个activity 跳转
     * @param title           web title
     * @param webUrl          web url
     * @param isFinishCurrent 是否finish 当前activity
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun startActivityToWebPage(fromActivity: Activity, toActivityClass: Class<*>,
                               title: Int, webUrl: String, isFinishCurrent: Boolean) {
        fromActivity.startActivity(getWebTypeIntent(fromActivity, toActivityClass, title, webUrl))
        if (isFinishCurrent) {
            fromActivity.finish()
        }
        fromActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    /**
     * web view 页面传递的参数intent
     * @param context
     * @param toActivityClass
     * @param title
     * @param loadUrl
     * @return
     */
    fun getWebTypeIntent(context: Context, toActivityClass: Class<*>, title: Int, loadUrl: String): Intent {
        val intent = Intent(context, toActivityClass)
//        intent.putExtra(Constants.WEB_VIEW_TITLE, context.getString(title))
//        intent.putExtra(Constants.WEB_VIEW_URL, loadUrl)
        return intent
    }

    /**
     * finish当前页面
     *
     * @param currentActivity 当前页面
     */
    fun finishActivity(currentActivity: Activity) {
        currentActivity.finish()
        currentActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

}
