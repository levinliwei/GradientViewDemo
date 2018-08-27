package com.waynelee.wayneleetestproject.helper;


import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @auther levin
 * @Summary CountDownHelper
 * @email levin.li@teamar.cn
 * @data 2018/7/28
 * @org Aurora Team
 */

public class CountDownHelper {

    private Disposable mdDisposable;
    private CountDownObserverListener mObserverListener;
    private long mCount;
    private long mInitialDelay;
    private long mPeriod;
    private TimeUnit mUnit;

    /**
     * 实现倒计时
     *
     * @param count        总时间数
     * @param initialDelay 延迟多少执行
     * @param period       执行间隔
     * @param unit         时间单位
     */
    public CountDownHelper(final long count, long initialDelay,
                          long period, TimeUnit unit, final CountDownObserverListener observerListener) {
        Logger.d("CountDownHelper total time====>" + count);
        mCount = count;
        mInitialDelay = initialDelay;
        mPeriod = period;
        mUnit = unit;
        mObserverListener = observerListener;
    }

    public void start(){
        //从0开始发射11个数字为：0-10依次输出，延时0s执行，每1s发射一次。
        mdDisposable = Flowable.intervalRange(0, mCount + 1, mInitialDelay, mPeriod, mUnit)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //设置倒计时显示 aLong依次减少
                        mObserverListener.onCountDownProgress(mCount - aLong);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //倒计时完毕 恢复初始状态
                        mObserverListener.onCountDownComplete();
                    }
                }).subscribe();
    }

    public interface CountDownObserverListener {
        void onCountDownStart();

        void onCountDownProgress(Long aLong);

        void onCountDownComplete();
    }

    /**
     * 销毁倒计时的Disposable
     */
    public void onDisposable() {
        if (mdDisposable != null) {
            Logger.d("timer=====>stop");
            mdDisposable.dispose();
        }
    }
}
