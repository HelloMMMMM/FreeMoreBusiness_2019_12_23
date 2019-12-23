package com.fx.feemore.business.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.CategoryBean;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.config.Constants;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.BitmapUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.utils.RxSchedules;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * function:录入入驻资料
 * author: frj
 * modify date: 2018/12/23
 */
public class VMInputData extends ViewModel
{
    private DataSource dataSource;

    public MutableLiveData<File> compressPathRes = new MutableLiveData<>();

    public MutableLiveData<HttpFailBean> httpFailRes = new MutableLiveData();

    public MutableLiveData<StoryUserBean> registerSuccRes = new MutableLiveData();

    public MutableLiveData<List<CategoryBean>> categoriesSuccRes = new MutableLiveData<>();

    public ObservableField<String> nameStr = new ObservableField<>();

    public ObservableField<String> addressStr = new ObservableField<>();

    @Inject
    public VMInputData(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 图片压缩处理
     *
     * @param imgPath 图片地址
     */
    public void compressBitmap(String imgPath)
    {
        if (TextUtils.isEmpty(imgPath))
        {
            return;
        }
        Observable.just(imgPath).map(s -> {
            byte[] bytes = BitmapUtil.decodeThumbBitmapByteForFile(s, BitmapUtil.DEFAULT_WIDTH, BitmapUtil.DEFAULT_HEIGHT, true);
            File cacheDir = AppContext.getInstanceBase().getCacheDir();
            File saveDir = new File(cacheDir, Constants.ROOT_DIR);
            if (!saveDir.exists())
            {
                saveDir.mkdirs();
            }
            String fileName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
            File file = new File(saveDir, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            return file;
        }).compose(RxSchedules.ioToMain()).subscribe(new ApiCallback<File>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                httpFailRes.setValue(httpFail);
            }

            @Override
            public void onNext(File s)
            {
                compressPathRes.setValue(s);
            }
        });
    }

    /**
     * 图片处理
     */
    public void handleImg(String name, String phone, String areaCode, String address, String lon, String lat, String type, String storePhotoPath, String inStorePhotoPath, String idCardPhotoPath, String businessPhotoPath)
    {
        List<String> imgs = new ArrayList<>();
        imgs.add(storePhotoPath);
        imgs.add(inStorePhotoPath);
        imgs.add(idCardPhotoPath);
        imgs.add(businessPhotoPath);
        Observable.just(imgs).map(s -> {
            List<String> list = new ArrayList<>(4);
            if (VerificationUtil.noEmpty(s))
            {
                for (String str : s)
                {
                    byte[] bytes = BitmapUtil.decodeThumbBitmapByteForFile(str, BitmapUtil.DEFAULT_WIDTH, BitmapUtil.DEFAULT_HEIGHT, true);
                    File cacheDir = AppContext.getInstanceBase().getCacheDir();
                    File saveDir = new File(cacheDir, Constants.ROOT_DIR);
                    if (!saveDir.exists())
                    {
                        saveDir.mkdirs();
                    }
                    String fileName = str.substring(str.lastIndexOf("/") + 1);
                    File file = new File(saveDir, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                    list.add(file.getAbsolutePath());
                }
            }
            return list;
        }).compose(RxSchedules.ioToMain()).subscribe(new Observer<List<String>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(List<String> strings)
            {
                if (strings.size() == 4)
                {
                    registerStore(name, phone, areaCode, address, lon, lat, type, strings.get(0), strings.get(1), strings.get(2), strings.get(3));
                } else
                {
                    httpFailRes.setValue(new HttpFailBean(0x10, "图片未全部处理成功"));
                }
            }

            @Override
            public void onError(Throwable e)
            {
                if (BuildConfig.DEBUG)
                {
                    e.printStackTrace();
                }
                HttpFailBean httpFailBean = new HttpFailBean(1, "图片处理失败");
                httpFailRes.setValue(httpFailBean);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    /**
     * 注册开店
     */
    public void registerStore(String name, String phone, String areaCode, String address, String lon, String lat, String type, String storePhotoPath, String inStorePhotoPath, String idCardPhotoPath, String businessPhotoPath)
    {
        dataSource.registerStore(name, phone, areaCode, address, lon, lat, type, storePhotoPath, inStorePhotoPath, idCardPhotoPath, businessPhotoPath).subscribe(new ApiCallback<StoryUserBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                httpFailRes.setValue(httpFail);
            }

            @Override
            public void onNext(StoryUserBean userBean)
            {

                if (userBean != null && !TextUtils.isEmpty(userBean.getSTORE_ID()))
                {
                    AppContext.getInstanceBase().setStoryUserBean(userBean);
                }
                registerSuccRes.setValue(userBean);
            }
        });
    }

    /**
     * 获取店铺栏目
     */
    public void getStoryCategory()
    {
        dataSource.getStoryCategory().subscribe(new ApiCallback<List<CategoryBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                httpFailRes.setValue(httpFail);
            }

            @Override
            public void onNext(List<CategoryBean> categoryBeans)
            {
                categoriesSuccRes.setValue(categoryBeans);
            }
        });
    }
}
