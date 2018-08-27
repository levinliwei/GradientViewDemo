package com.waynelee.wayneleetestproject.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.waynelee.wayneleetestproject.ProjectApplication;
import com.waynelee.wayneleetestproject.R;

/**
 * @author levin
 * @Summary ToastUtils
 * <p>
 * 单例toast 工具类 根据项目需要 更换背景、位置等信息
 * </p>
 * @email levin.li@teamar.cn
 * @data 2018/7/26
 * @org Aurora Team
 */
public class ToastUtils {

    /**
     * {@link Toast#LENGTH_SHORT} default time is 3500ms
     */
    private static final int LENGTH_SHORT_TIME = 2000;
    private Context mContext;
    private Toast mToast;
    private TextView mTextView;
    private static ToastUtils mInstance;

    private static int mDuration = 0;
    private Handler mHandler = new Handler();

    private ToastUtils(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        createToast();
    }

    public static ToastUtils getInstance() {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils(ProjectApplication.getAppContext());
                }
            }
        }
        return mInstance;
    }

    private void createToast() {
        mToast = new Toast(mContext);
        View v = LayoutInflater.from(mContext).inflate(R.layout.view_toast, null);
        mTextView = v.findViewById(R.id.tv_toast);
        mToast.setView(v);
    }

    /**
     * 取消Toast
     */
    public void hide() {
        mDuration = 0;
        if (mToast != null) {
            mToast.cancel();
        }
    }

    private Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            if (mDuration != 0) {
                mToast.show();
            } else {
                hide();
                return;
            }

            if (mDuration > LENGTH_SHORT_TIME) {
                mHandler.postDelayed(showRunnable, LENGTH_SHORT_TIME);
                mDuration -= LENGTH_SHORT_TIME;
            } else {
                mHandler.postDelayed(showRunnable, mDuration);
                mDuration = 0;
            }
        }
    };

    /**
     * 显示Toast
     *
     * @param message  显示文本
     * @param duration 显示时长
     */
    private void show(final String message, final int duration) {
        if (mToast != null) {
            mTextView.setText(message);
        } else {
            createToast();
        }
        mDuration = duration;
        mHandler.post(showRunnable);
    }

    /**
     * 设置Toast显示位置
     */
    public void setToastPosition(int xOffset, int yOffset) {
        //设置Toast显示位置，顶部居中，向 X、Y轴偏移量
        mToast.setGravity(Gravity.TOP, xOffset, yOffset);
    }

    public void showToast(final String message, final int duration) {
        setToastPosition(0, 0);
        show(message, duration);
    }

    public void showToast(final String message, final int duration, int xOffset, int yOffset) {
        setToastPosition(xOffset, yOffset);
        show(message, duration);
    }

    public void showToast(final String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    public void showToast(final int stringId) {
        show(mContext.getString(stringId), Toast.LENGTH_SHORT);
    }
}
