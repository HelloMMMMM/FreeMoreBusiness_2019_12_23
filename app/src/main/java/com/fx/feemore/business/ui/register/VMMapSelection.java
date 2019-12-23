package com.fx.feemore.business.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:地图选点ViewModel
 * author: frj
 * modify date: 2019/1/21
 */
public class VMMapSelection extends ViewModel implements OnGetGeoCoderResultListener
{

    private DataSource dataSource;
    private GeoCoder geoCoder;

    public MutableLiveData<BDLocation> locationData = new MutableLiveData<>();
    public ObservableField<String> locStr = new ObservableField<>();

    //城市代码
    public String cityCode;

    public BdLocationListener bdLocationListener;

    @Inject
    public VMMapSelection(DataSource dataSource)
    {
        this.dataSource = dataSource;
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(this);
        bdLocationListener = new BdLocationListener(this);
    }

    /**
     * 开始转换
     *
     * @param latLng
     */
    public void startReverse(@NonNull LatLng latLng)
    {
        if (geoCoder != null)
        {
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng).radius(500));
        }
    }

    /**
     * 销毁数据
     */
    public void destroy()
    {
        if (geoCoder != null)
        {
            geoCoder.destroy();
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult)
    {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult)
    {
        cityCode = String.valueOf(reverseGeoCodeResult.getCityCode());

        ReverseGeoCodeResult.AddressComponent addressComponent = reverseGeoCodeResult.getAddressDetail();
        if (addressComponent != null)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(addressComponent.province);
            sb.append(addressComponent.city);
            sb.append(addressComponent.district);
            sb.append(addressComponent.town);
            sb.append(addressComponent.street);
            sb.append(addressComponent.streetNumber);
            locStr.set(sb.toString());
        }
    }

    public static class BdLocationListener extends BDAbstractLocationListener
    {

        private VMMapSelection vmMapSelection;

        public BdLocationListener(VMMapSelection viewModel)
        {
            vmMapSelection = viewModel;
        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation)
        {
            vmMapSelection.locationData.setValue(bdLocation);
        }
    }
}
