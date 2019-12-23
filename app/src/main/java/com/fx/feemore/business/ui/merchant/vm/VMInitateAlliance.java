package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.app.AppContext;
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

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * function:发起商家联盟ViewModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMInitateAlliance extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();

    @Inject
    public VMInitateAlliance(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 发起联盟
     *
     * @param name     联盟主题
     * @param filePath 二维码路径
     */
    public void publish(String name, String filePath)
    {
        handleImg(name, filePath);
    }

    /**
     * 处理图片
     *
     * @param name     联盟主题
     * @param filePath 二维码路径
     */
    private void handleImg(String name, String filePath)
    {
        Observable.just(filePath).map(s -> {
            if (VerificationUtil.noEmpty(s))
            {

                byte[] bytes = BitmapUtil.decodeThumbBitmapByteForFile(s, BitmapUtil.DEFAULT_WIDTH, BitmapUtil.DEFAULT_HEIGHT, true);
                File cacheDir = AppContext.getInstanceBase().getCacheDir();
                File saveDir = new File(cacheDir, Constants.ROOT_DIR);
                if (!saveDir.exists())
                {
                    saveDir.mkdirs();
                }
                String fileName = s.substring(s.lastIndexOf("/") + 1);
                File file = new File(saveDir, fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.flush();
                fos.close();
                return file.getAbsolutePath();
            }
            return "";
        }).compose(RxSchedules.ioToMain()).subscribe(new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
            }

            @Override
            public void onNext(String filePath)
            {
                publishAlliance(name, filePath);
            }

            @Override
            public void onError(Throwable e)
            {
                if (BuildConfig.DEBUG)
                {
                    e.printStackTrace();
                }
                HttpFailBean httpFailBean = new HttpFailBean(1, "图片上传失败");
                resHttpFail.setValue(httpFailBean);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    /**
     * 发起商家联盟
     *
     * @param name     主题名称
     * @param filePath 二维码路径
     */
    private void publishAlliance(String name, String filePath)
    {
        dataSource.publishAlliance(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), name, filePath).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resSucc.setValue(responseBean);
            }
        });
    }
}
