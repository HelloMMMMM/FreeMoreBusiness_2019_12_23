package com.fx.feemore.business.ui.register;

import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcMapSelectionBinding;
import com.fx.feemore.business.util.Params;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.Log;

/**
 * function:地图选点
 * author: frj
 * modify date: 2019/1/21
 */
public class AcMapSelection extends BaseBindActivity<AcMapSelectionBinding, VMMapSelection>
{
    // 定位相关
    private LocationClient mLocClient;
    //百度地图
    private BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位

    private Point mScreenCenterPoint;

    private Marker mMarkerF;

    BitmapDescriptor bdF = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_marka);

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_map_selection;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("地图选点");

        mBaiduMap = binding.mapView.getMap();
        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(viewModel.bdLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        initLiveData();

        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback()
        {
            @Override
            public void onMapLoaded()
            {
                if (null != mBaiduMap.getMapStatus())
                {
                    LatLng latLng = mBaiduMap.getMapStatus().target;
                    mScreenCenterPoint = mBaiduMap.getProjection().toScreenLocation(latLng);
                    MarkerOptions ooF = new MarkerOptions().position(latLng).icon(bdF).perspective(true)
                            .fixedScreenPosition(mScreenCenterPoint);
                    mMarkerF = (Marker) (mBaiduMap.addOverlay(ooF));

                }
            }
        });


        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener()
        {
            @Override
            public void onMapStatusChangeStart(MapStatus status)
            {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus status, int reason)
            {

            }

            @Override
            public void onMapStatusChange(MapStatus status)
            {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus status)
            {
                if (null == mMarkerF)
                {
                    return;
                }
                mMarkerF.setAnimation(getTransformationPoint());
                mMarkerF.startAnimation();
            }
        });
    }

    private void initLiveData()
    {
        viewModel.locationData.observe(this, result -> {
            // map view 销毁后不在处理新接收的位置
            if (result == null)
            {
                return;
            }
//            double mCurrentLat = result.getLatitude();
//            double mCurrentLon = result.getLongitude();
//            double mCurrentAccracy = result.getRadius();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(result.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(result.getLatitude())
                    .longitude(result.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc)
            {
                isFirstLoc = false;
                LatLng ll = new LatLng(result.getLatitude(),
                        result.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                viewModel.startReverse(ll);

                viewModel.cityCode = result.getCityCode();
            }


        });

    }

    /**
     * 创建平移坐标动画
     */
    private Animation getTransformationPoint()
    {

        if (null != mScreenCenterPoint)
        {
            Point pointTo = new Point(mScreenCenterPoint.x, mScreenCenterPoint.y - 100);
            Transformation mTransforma = new Transformation(mScreenCenterPoint, pointTo, mScreenCenterPoint);
            mTransforma.setDuration(500);
            mTransforma.setRepeatMode(Animation.RepeatMode.RESTART);//动画重复模式
            mTransforma.setRepeatCount(1);//动画重复次数
            mTransforma.setAnimationListener(new Animation.AnimationListener()
            {
                @Override
                public void onAnimationStart()
                {
                }

                @Override
                public void onAnimationEnd()
                {
                }

                @Override
                public void onAnimationCancel()
                {
                }

                @Override
                public void onAnimationRepeat()
                {

                }
            });
            LatLng latLng = mBaiduMap.getProjection().fromScreenLocation(mScreenCenterPoint);
            viewModel.startReverse(latLng);
            return mTransforma;
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_map_selection, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_finish:
                LatLng latLng = mBaiduMap.getProjection().fromScreenLocation(mScreenCenterPoint);
                Intent intent = new Intent();
                intent.putExtra(KEY, latLng);
                intent.putExtra(KEY_STR, viewModel.locStr.get());
                intent.putExtra(Params.PARAM_AREA_CODE, viewModel.cityCode);
                Log.e("cityCode:" + viewModel.cityCode);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPause()
    {
        binding.mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        binding.mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        // 退出时销毁定位
        if (mLocClient != null)
        {
            mLocClient.stop();
            mLocClient.unRegisterLocationListener(viewModel.bdLocationListener);
        }
        // 关闭定位图层
        if (mBaiduMap != null)
        {
            mBaiduMap.setMyLocationEnabled(false);
        }
        binding.mapView.onDestroy();
        viewModel.destroy();
        if (mMarkerF != null)
        {
            mMarkerF.cancelAnimation();
        }

        bdF.recycle();
        super.onDestroy();
    }
}
