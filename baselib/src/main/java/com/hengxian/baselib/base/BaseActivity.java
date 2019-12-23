package com.hengxian.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.BarUtils;
import com.hengxian.baselib.R;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.DisplayUtil;
import com.hengxian.common.StatusBarUtil;
import com.hengxian.common.permission.PermissionHelper;
import com.hengxian.common.permission.callback.OnPermissionCallback;

/**
 * 基础
 *
 * @author wfq
 * @date 2018/6/4
 */
public abstract class BaseActivity extends AppCompatActivity implements OnPermissionCallback, ActivityDelegate {

    /**
     * 权限设置界面请求码
     */
    protected static final int REQUEST_SETTING = 0x200;

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
    public static final int PAGE_NUM = 10;

    /**
     * 起始页
     */
    public static final int PAGE_START = 0;

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

    /**
     * 权限请求帮助
     */
    protected PermissionHelper mPermissionHelper;

    /**
     * 需要申请的权限数组
     */
    protected String[] permissionArrays;

    /**
     * res值，表示需要申请的权限对应的提示说明
     */
    @IdRes
    protected int[] permissionInfoTips;

    /**
     * 表示是否初始化
     */
    private boolean isInit = false;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        DensityUtil.setDensityWithWidth(this, getApplication());
        handleCreate(savedInstanceState);
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, 0xffffffff);
    }

    @Override
    public void handleCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId());
        permissionInit();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        if (isFitWindow() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (toolbar != null) {
                toolbar.setPadding(0, DisplayUtil.getStatusBarHeight(this), 0, 0);
            }
        }
        super.setSupportActionBar(toolbar);
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        int toolbarId = getResources().getIdentifier("toolbar", "id", getPackageName());
        Toolbar toolbar = findViewById(toolbarId);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setElevation(3);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    /**
     * 获取Toolbar高度
     *
     * @return
     */
    public Toolbar getToolbar() {
        int toolbarId = getResources().getIdentifier("toolbar", "id", getPackageName());
        Toolbar toolbar = findViewById(toolbarId);
        return toolbar;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (isFitWindow()) {
            StatusBarUtil.initStatusBar(getWindow());
        }
        initToolbar();
    }

    /**
     * 获取布局文件id
     *
     * @return 布局文件id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 优先初始化权限信息
     */
    protected void permissionInit() {
        permissionArrays = getPermissionArrays();
        permissionInfoTips = getPermissionInfoTips();

        /*
            表示无需权限
         */
        if (permissionArrays == null || permissionArrays.length == 0) {
            init();
            isInit = true;
        } else {
            doPermissionCheck();
        }
    }

    /**
     * 获取需要申请的权限的数组集
     *
     * @return 权限数组集
     */
    protected String[] getPermissionArrays() {
        return null;
    }

    /**
     * 获取需要申请的权限对应的提示语的资源id数组集
     *
     * @return 权限提示资源id数组集
     */
    protected int[] getPermissionInfoTips() {
        return null;
    }


    /**
     * 权限检查
     */
    private void doPermissionCheck() {
        mPermissionHelper = PermissionHelper.getInstance(this);
        mPermissionHelper
                // default is false. its here so you know that it exists.
                .setForceAccepting(true)
                .request(permissionArrays);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        //权限点击允许
        if (isInit) {
            return;
        }
        for (String permission : permissionArrays) {
            if (!mPermissionHelper.isPermissionGranted(permission)) {
                doPermissionCheck();
                return;
            }
        }
        init();
        isInit = true;
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        mPermissionHelper.requestAfterExplanation(permissionName);
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {

        String permissionTips = "";
        for (int i = 0, length = permissionArrays.length; i < length; i++) {
            if (permissionArrays[i].equals(permissionName)) {
                if (i < permissionInfoTips.length) {
                    permissionTips = getString(permissionInfoTips[i]);
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(permissionTips)) {
            permissionTips = getString(R.string.should_permission_default_tips);
        }
        new AlertDialog.Builder(this).setCancelable(false).setMessage(permissionTips).setPositiveButton(R.string.baselib_permission_dialog_confirm, (dialog, which) -> {
            //禁止权限
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.parse("package:" + getPackageName());
            intent.setData(uri);
            startActivityForResult(intent, REQUEST_SETTING);
        }).setNegativeButton(R.string.baselib_permission_dialog_cancel, (dialog, which) ->
                finish()
        ).create().show();
    }

    @Override
    public void onNoPermissionNeeded() {
        init();
    }

    /**
     * 记得手动重写这个方法
     *
     * @param requestCode  请求码
     * @param permissions  权限集
     * @param grantResults 权限允许状态集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_SETTING == requestCode && Activity.RESULT_OK == resultCode) {
            //重新执行权限检查
            doPermissionCheck();
        }
    }


    /**
     * 控制是否设置沉浸式状态栏
     *
     * @return true表示设置沉浸式，false反之。
     */
    protected boolean isFitWindow() {
        return true;
    }

    /**
     * 设置toolbar标题
     *
     * @param titleId 文本资源ID
     */
    protected void setToolbarTitle(int titleId) {
        TextView tvTitle = findViewById(R.id.toolbar_title);
        if (tvTitle != null) {
            tvTitle.setText(titleId);
        }
    }

    /**
     * 设置toolbar标题
     *
     * @param titleId 文本资源ID
     */
    protected void setToolbarTitle(String titleId) {
        TextView tvTitle = findViewById(R.id.toolbar_title);
        if (tvTitle != null) {
            tvTitle.setText(titleId);
        }
    }

    /**
     * 开始加载
     */
    public void startLoading() {
        startLoading(Color.WHITE);
    }

    /**
     * 开始加载
     *
     * @param color 颜色值
     */
    public void startLoading(@ColorInt int color) {
        if (flLoad == null) {
            flLoad = findViewById(R.id.base_fl_load);
        }
        if (flLoad == null) {
            return;
        }

        flLoad.setBackgroundColor(color);
        flLoad.setVisibility(View.VISIBLE);

        if (animationView == null) {
            animationView = findViewById(R.id.base_loading_view);
            flLoad.setOnClickListener(v -> {
            });
        }
        if (animationView != null) {
            animationView.setVisibility(View.VISIBLE);
            animationView.playAnimation();
        }

        if (llError == null) {
            llError = findViewById(R.id.base_ll_error);
        }
        if (llError != null) {
            llError.setVisibility(View.GONE);
        }
    }

    /**
     * 是否正在加载中
     *
     * @return
     */
    public boolean isLoading() {
        if (flLoad == null) {
            flLoad = findViewById(R.id.base_fl_load);
        }
        if (flLoad == null) {
            return false;
        }
        return flLoad.isShown();
    }

    /**
     * 加载完成
     */
    public void loadComplete() {
        if (flLoad == null) {
            flLoad = findViewById(R.id.base_fl_load);
        }
        if (flLoad == null) {
            return;
        }
        flLoad.setVisibility(View.GONE);
        if (animationView == null) {
            animationView = findViewById(R.id.base_loading_view);
        }
        if (animationView != null) {
            animationView.cancelAnimation();
        }
    }

    /**
     * 加载失败或加载为空
     *
     * @param tips 提示
     * @param res  图片资源
     */
    public void loadErrOrEmpty(String tips, @DrawableRes int res) {
        if (flLoad == null) {
            flLoad = findViewById(R.id.base_fl_load);
        }
        if (flLoad == null) {
            return;
        }

        flLoad.setVisibility(View.VISIBLE);

        if (animationView == null) {
            animationView = findViewById(R.id.base_loading_view);
        }
        if (animationView != null) {
            animationView.cancelAnimation();
            animationView.setVisibility(View.GONE);
        }
        if (llError == null) {
            llError = findViewById(R.id.base_ll_error);
        }
        if (llError != null) {
            llError.setVisibility(View.VISIBLE);

            if (imgError == null) {
                imgError = findViewById(R.id.base_img_error);
            }
            if (tvError == null) {
                tvError = findViewById(R.id.base_tv_tips);
            }
            if (tvError != null) {
                tvError.setText(tips == null ? "" : tips);
            }
            if (imgError != null) {
                imgError.setImageResource(res);
            }
        }
    }

    /**
     * 设置Toolbar返回按钮默认实现onBackPressed()
     *
     * @param item 菜单项
     * @return true表示当前已处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                if (v != null) {
                    v.clearFocus();
                    hideSoftInput(v.getWindowToken());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v     拥有焦点的控件
     * @param event 触摸动作
     * @return true表示需要隐藏输入框，false反之。
     */
    protected boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 隐藏软件盘
     *
     * @param token 焦点控件的token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * @param view 触发点击事件的View
     * @return 如果小于设定的超时时间（MULTIPLE_CLICK_TIMEOUT值），那么返回true
     * @author frj 功能:判断两次点击间隔是否小于设定的超时时间，如果小于设定的超时时间，那么返回true 使用方法：
     */
    protected boolean isFastDoubleClick(View view) {
        return isFastDoubleClick(MULTIPLE_CLICK_TIMEOUT, view);
    }

    /**
     * @param interval 时间间隔，单位为毫秒
     * @param view     触发点击事件的View
     * @return 如果小于指定的间隔，那么返回true
     * @author frj 功能:判断两次点击间隔是否小于指定的间隔，如果小于指定的间隔，那么返回true 使用方法：
     */
    protected boolean isFastDoubleClick(long interval, View view) {
        // 如果两次点击的控件不一样，那么不验证
        if (view != tempView) {
            tempView = view;
            return false;
        }
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < interval) {
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
     * @author Frj 功能:Activity跳转的方法 使用方法：
     */
    public void jump(Class<?> clazz, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // 屏幕右边进，屏幕内从左边移出 动画
//        overridePendingTransition(ResourceHelper.getInstance(getApplicationContext()).getAnimId("right_in_anim"),
//                ResourceHelper.getInstance(getApplicationContext()).getAnimId("left_out_anim"));
//        overridePendingTransition(R.anim.right_in_anim,
//                R.anim.left_out_anim);
        if (isFinish) {
            this.finish();
        }
    }

    /**
     * @param clazz    要填转到的类
     * @param param    参数
     * @param isFinish 是否关闭当前Activity
     * @author Frj 功能:传输一个String类型的参数，跳转到下一个Activity 使用方法：
     */
    public void jump(Class<?> clazz, String param, boolean isFinish) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STR, param);
        jump(clazz, bundle, isFinish);
    }

    /**
     * @param clazz    要填转到的类
     * @param param    参数
     * @param isFinish 是否关闭当前Activity
     * @author Frj 功能:传输一个整形的参数跳转到下一个Activity 使用方法：
     */
    public void jump(Class<?> clazz, int param, boolean isFinish) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, param);
        jump(clazz, bundle, isFinish);
    }

    /**
     * @param clazz    要填转到的类
     * @param isFinish 是否关闭当前Activity
     * @author Frj 功能:跳转界面，无参数传输 使用方法：
     */
    public void jump(Class<?> clazz, boolean isFinish) {
        jump(clazz, "", isFinish);
    }

    /**
     * @param clazz 要填转到的类
     * @param param 参数
     * @author Frj 功能:传输一个String类型的参数，跳转到下一个Activity(不关闭当前的Activity) 使用方法：
     */
    public void jump(Class<?> clazz, String param) {
        jump(clazz, param, false);
    }

    /**
     * @param paramClass 跳转到的页面
     * @author Frj 功能:跳转Activity（不finish当前页） 使用方法：
     */
    public void jump(Class<?> paramClass) {
        jump(paramClass, "", false);
    }

    /**
     * 带回调结果的跳转
     *
     * @param paramClass  要跳转到的Activity
     * @param requestCode 请求码
     */
    public void jump(Class<?> paramClass, int requestCode) {
        jump(paramClass, null, requestCode);
    }

    /**
     * 带回调结果的跳转
     *
     * @param paramClass  要跳转到的Activity
     * @param bundle      参数
     * @param requestCode 请求码
     */
    public void jump(Class<?> paramClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, paramClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
//        // 屏幕右边进，屏幕内从左边移出 动画
//        overridePendingTransition(R.anim.right_in_anim,
//                R.anim.left_out_anim);
    }

    @Override
    protected void onDestroy() {
        loadComplete();
        super.onDestroy();
    }
}
