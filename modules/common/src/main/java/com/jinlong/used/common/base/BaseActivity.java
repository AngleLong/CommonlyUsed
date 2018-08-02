package com.jinlong.used.common.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author : 贺金龙
 * email : 753355530@qq.com
 * create at 2018/8/2  16:09
 * description : 所有Activity的基类
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity implements IBaseView {


    /**
     * Presenter泛型引用的实体
     */
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加相应的显示页面
        setContentView(getView());

        getExtra();

        //设置相应的Presenter
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        //设置相应的方法
        initView();
        //初始化数据的方法
        initDate();
        //初始化监听的方法
        initListener();
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:17
     * description : 获取相应页面传递的一些Intent参数
     */
    private void getExtra() {
        Intent intent = getIntent();
        if (intent != null) {
            doGetExtra();
        }
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:18
     * description : 这个方法已经判断了Intent的值，
     * 如果Intent为空的话，不会回调这个方法
     */
    public void doGetExtra() {
    }


    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  15:47
     * description : 添加相应的显示页面，这里之所以没有直接返回ID是因为有的时候，
     * 可能有的时候你会对这个View进行一些相应的操作，需要这个View的时候就可以直接重写这个方法了，
     * 一般情况下这个方法是不用重写的！！！
     */
    public View getView() {
        return View.inflate(this, getLayoutId(), null);
    }

    /**
     * 根据用户传递的页面ID构建显示的View
     *
     * @return 页面的资源ID
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  15:55
     * description : 返回相应的页面ID
     */
    public abstract int getLayoutId();

    /**
     * 返回相应Presenter实体
     *
     * @return 相应的Presenter实体类
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:07
     * description : 创建相应的Presenter的方法
     */
    protected abstract P createPresenter();

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:12
     * description : 初始话相应控件的方法，这里也可以初始化黄油刀，
     * 这个方法我觉得可以选择性的实现，因为在这个类中都不是必须的，
     * 比如说有了黄油刀之后这个方法就不是必须的了！
     */
    public void initView() {
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:14
     * description : 请求数据的方法
     */
    public void initDate() {
    }

    /**
     * @author : 贺金龙
     * email : 753355530@qq.com
     * create at 2018/8/2  16:14
     * description : 监听的方法
     */
    public void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放相应的Presenter属性
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
