package com.hengxian.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.hengxian.baselib.R;

/**
 * Fragment 基类
 *
 * @author wfq
 * @date 2018/6/4
 */
public class BaseFragment extends Fragment
{

    /**
     * 界面传整形值中，key的值
     */
    protected static final String KEY = "activity_num";

    /**
     * 界面传字符串类型的值时，key的值
     */
    protected static final String KEY_STR = "activity_str";

    /**
     * 分页时，每页的数据量
     */
    protected static final int PAGE_NUM = 10;

    /**
     * 起始页
     */
    protected static final int PAGE_START = 0;

    /**
     * 多次点击超时时间
     */
    protected static final int MULTIPLE_CLICK_TIMEOUT = 300;

    /**
     * 用于保存上次点击的View
     */
    protected View tempView;

    /**
     * 用于保存最后一次点击时间
     */
    protected long lastClickTime;

    //加载图层
    private FrameLayout flLoad;
    //加载动画
    private LottieAnimationView animationView;
    //加载失败和数据为空图层
    private LinearLayout llError;
    //加载失败或数据为空展示图片
    private ImageView imgError;
    //加载失败或数据为空提示
    private TextView tvError;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    protected void initToolbar()
    {
        Toolbar toolbar = findView(R.id.toolbar);
        if (toolbar != null && getActivity() instanceof BaseActivity)
        {
            BaseActivity sActivity = (BaseActivity) getActivity();
            sActivity.setSupportActionBar(toolbar);
            sActivity.getSupportActionBar().setElevation(3);
            sActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            sActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * 设置toolbar标题
     *
     * @param titleId 文本资源ID
     */
    protected void setToolbarTitle(int titleId)
    {
        TextView tvTitle = findView(R.id.toolbar_title);
        if (tvTitle != null)
        {
            tvTitle.setText(titleId);
        }
    }


    /**
     * 通过资源Id查找控件
     *
     * @param id
     * @param <T> 返回继承至View的对象
     * @return
     */
    protected <T extends View> T findView(int id)
    {
        return (T) getView().findViewById(id);
    }

    /**
     * @param view 触发点击事件的View
     * @return 如果小于指定的间隔，那么返回true
     * @author：frj 功能:判断两次点击间隔是否小于指定的间隔，如果小于指定的间隔，那么返回true 使用方法：
     */
    protected boolean isFastDoubleClick(View view)
    {
        if (view != tempView)
        { // 如果两次点击的控件不一样，那么不验证
            tempView = view;
            return false;
        }
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < MULTIPLE_CLICK_TIMEOUT)
        {
            return true;
        }
        lastClickTime = time;
        tempView = null;
        return false;
    }

    /**
     * @param clazz    要跳转的类
     * @param isFinish 表示是否关闭当前的Activity true表示关闭当前的Activity
     * @param bundle   传输的参数
     * @author：Frj 功能:Activity跳转的方法 使用方法：
     */
    protected void jump(Class<?> clazz, Bundle bundle, boolean isFinish)
    {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        getActivity().startActivity(intent);
        if (isFinish)
        {
            getActivity().finish();
        }
    }

    /**
     * @param clazz    要填转到的类
     * @param param    参数
     * @param isFinish 是否关闭当前Activity
     * @author：Frj 功能:传输一个String类型的参数，跳转到下一个Activity 使用方法：
     */
    protected void jump(Class<?> clazz, String param, boolean isFinish)
    {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STR, param);
        jump(clazz, bundle, isFinish);
    }

    /**
     * @param clazz    要填转到的类
     * @param param    参数
     * @param isFinish 是否关闭当前Activity
     * @author：Frj 功能:传输一个整形的参数跳转到下一个Activity 使用方法：
     */
    protected void jump(Class<?> clazz, int param, boolean isFinish)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, param);
        jump(clazz, bundle, isFinish);
    }

    /**
     * @param clazz    要填转到的类
     * @param isFinish 是否关闭当前Activity
     * @author：Administrator 功能:跳转界面，无参数传输 使用方法：
     */
    protected void jump(Class<?> clazz, boolean isFinish)
    {
        jump(clazz, "", isFinish);
    }

    /**
     * @param clazz 要填转到的类
     * @param param 参数
     * @author：Frj 功能:传输一个String类型的参数，跳转到下一个Activity(不关闭当前的Activity) 使用方法：
     */
    protected void jump(Class<?> clazz, String param)
    {
        jump(clazz, param, false);
    }

    /**
     * @param paramClass 跳转到的页面
     * @author：Frj 功能:跳转Activity（不finish当前页） 使用方法：
     */
    protected void jump(Class<?> paramClass)
    {
        jump(paramClass, "", false);
    }

    /**
     * 带回调结果的跳转
     *
     * @param paramClass  要跳转到的Activity
     * @param requestCode 请求码
     */
    protected void jump(Class<?> paramClass, int requestCode)
    {
        jump(paramClass, null, requestCode);
    }

    /**
     * 带回调结果的跳转
     *
     * @param paramClass  要跳转到的Activity
     * @param bundle      参数
     * @param requestCode 请求码
     */
    protected void jump(Class<?> paramClass, Bundle bundle, int requestCode)
    {
        Intent intent = new Intent(getActivity(), paramClass);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 隐藏软件盘
     *
     * @param token 焦点控件的token
     */
    public void hideSoftInput(IBinder token)
    {
        if (token != null && getContext() != null)
        {
            InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null)
            {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 开始加载
     */
    public void startLoading()
    {
        startLoading(Color.WHITE);
    }

    /**
     * 开始加载
     *
     * @param color 颜色值
     */
    public void startLoading(@ColorInt int color)
    {
        if (getView() == null)
        {
            return;
        }
        if (flLoad == null)
        {
            flLoad = findView(R.id.base_fl_load);
        }
        if (flLoad == null)
        {
            return;
        }

        flLoad.setBackgroundColor(color);
        flLoad.setVisibility(View.VISIBLE);

        if (animationView == null)
        {
            animationView = findView(R.id.base_loading_view);
            flLoad.setOnClickListener(v -> {
            });
        }
        if (animationView != null)
        {
            animationView.setVisibility(View.VISIBLE);
            animationView.playAnimation();
        }

        if (llError == null)
        {
            llError = findView(R.id.base_ll_error);
        }
        if (llError != null)
        {
            llError.setVisibility(View.GONE);
        }
    }

    /**
     * 是否正在加载中
     *
     * @return
     */
    public boolean isLoading()
    {
        if (getView() == null)
        {
            return false;
        }
        if (flLoad == null)
        {
            flLoad = findView(R.id.base_fl_load);
        }
        if (flLoad == null)
        {
            return false;
        }
        return flLoad.isShown();
    }

    /**
     * 加载完成
     */
    public void loadComplete()
    {
        if (getView() == null)
        {
            return;
        }
        if (flLoad == null)
        {
            flLoad = findView(R.id.base_fl_load);
        }
        if (flLoad == null)
        {
            return;
        }
        flLoad.setVisibility(View.GONE);
        if (animationView == null)
        {
            animationView = findView(R.id.base_loading_view);
        }
        if (animationView != null)
        {
            animationView.cancelAnimation();
        }
    }

    /**
     * 加载失败或加载为空
     *
     * @param tips 提示
     * @param res  图片资源
     */
    public void loadErrOrEmpty(String tips, @DrawableRes int res)
    {
        if (getView() == null)
        {
            return;
        }
        if (flLoad == null)
        {
            flLoad = findView(R.id.base_fl_load);
        }
        if (flLoad == null)
        {
            return;
        }

        flLoad.setVisibility(View.VISIBLE);

        if (animationView == null)
        {
            animationView = findView(R.id.base_loading_view);
        }
        if (animationView != null)
        {
            animationView.cancelAnimation();
            animationView.setVisibility(View.GONE);
        }
        if (llError == null)
        {
            llError = findView(R.id.base_ll_error);
        }
        if (llError != null)
        {
            llError.setVisibility(View.VISIBLE);

            if (imgError == null)
            {
                imgError = findView(R.id.base_img_error);
            }
            if (tvError == null)
            {
                tvError = findView(R.id.base_tv_tips);
            }
            if (tvError != null)
            {
                tvError.setText(tips == null ? "" : tips);
            }
            if (imgError != null)
            {
                imgError.setImageResource(res);
            }
        }
    }

    /**
     * 获取输入框的字符串
     *
     * @param textView
     * @return
     */
    protected String getTextView(TextView textView)
    {
        if (textView == null)
        {
            return "";
        }
        return textView.getText().toString().trim();
    }


    @Override
    public void onDestroy()
    {
        loadComplete();
        super.onDestroy();
//        AppContext.getRefWatcher().watch(this);
    }
}
