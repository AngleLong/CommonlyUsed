package com.jinlong.used.common.base;

import java.lang.ref.WeakReference;

/**
 * @author : 贺金龙
 * email : 753355530@qq.com
 * create at 2018/8/2  15:59
 * description : Presenter的基类
 */
public class BasePresenter<V> {
    /**
     * 使用弱引用,避免内存泄漏
     */
    private WeakReference<V> mReference;

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:00
     * description : 挂在Presenter的方法
     */
    public void attachView(V view) {
        mReference = new WeakReference<>(view);
    }


    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:02
     * description : 断开链接，防止内存泄漏
     */
    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:04
     * description : 是否挂载成功
     */
    public boolean isViewAttached() {
        return mReference != null && mReference.get() != null;
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:04
     * description : 获取相应的View从而判断是否为空
     */
    protected V getView() {
        return (mReference != null && mReference.get() != null) ? mReference.get() : null;
    }
}
