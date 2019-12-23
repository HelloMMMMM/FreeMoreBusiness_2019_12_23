package com.fx.feemore.business.ui.merchant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.config.Constants;
import com.fx.feemore.business.util.BitmapUtil;
import com.fx.feemore.business.util.ShareUtils;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.DisplayUtil;
import com.hengxian.common.Log;
import com.hengxian.common.ToastUtil;
import com.king.zxing.util.CodeUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * function:商家二维码弹窗
 * author: frj
 * modify date: 2018/12/31
 */
public class DialogMerchantQRCode extends DialogFragment
{
    private static final int PLATFORM_QQ = 1;
    private static final int PLATFORM_WEIBO = 2;
    private static final int PLATFORM_WECHAT = 3;
    private static final int PLATFORM_TIMELINE = 4;

    private IWXAPI mApi;        //微信Api对象

    //分享平台
    private int platform = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Dialog_Bottom);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.dialog_merchant_qr_code, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setWindowAnimations(R.style.Dialog_Bottom);
        getDialog().getWindow().getAttributes().width = DisplayUtil.getScreenSize(getActivity()).widthPixels - 2 * DensityUtil.dip2px(getActivity(), 22);
        init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        mApi = WXAPIFactory.createWXAPI(AppContext.getInstanceBase(), ShareUtils.WECHAT_APPID, BuildConfig.DEBUG);
        mApi.registerApp(ShareUtils.WECHAT_APPID);

        Bitmap bitmap = CodeUtils.createQRCode(ShareUtils.generateShareUrl(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID()), DisplayUtil.getRealScreenSize(getActivity()).widthPixels - DensityUtil.dip2px(getActivity(), 42));
        saveToFile(bitmap, false);
        ImageView img_qrcode = getView().findViewById(R.id.img_qrcode);
        img_qrcode.setImageBitmap(bitmap);

        img_qrcode.setOnLongClickListener(v -> {
            saveToFile(bitmap, true);
            return true;
        });

        ImageButton img_qq = getView().findViewById(R.id.img_qq);
        ImageButton img_weibo = getView().findViewById(R.id.img_weibo);
        ImageButton img_cycle = getView().findViewById(R.id.img_cycle);
        ImageButton img_wechat = getView().findViewById(R.id.img_wechat);

        img_qq.setOnClickListener(v -> {
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constants.ROOT_DIR);
            File file = new File(dir, AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID() + ".jpg");
            if (!file.exists())
            {
                saveToFile(bitmap, false);
            }
            ShareUtils.shareToQQ(getActivity(), ShareUtils.buildQQShareOnlyImage(file.getAbsolutePath()), new QQShareListener(this));
        });
        img_weibo.setOnClickListener(v -> {

        });
        img_cycle.setOnClickListener(v -> {
            shareToWechatFriends(ShareUtils.buildWXShareContent(bitmap));
        });
        img_wechat.setOnClickListener(v -> {
//            shareToWechat(ShareUtils.buildWXShareContent(bitmap));
            ShareUtils.callWxMiniProgramRegister(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());
        });
    }

    /**
     * 分享给微信好友
     *
     * @param msg
     */
    private void shareToWechat(WXMediaMessage msg)
    {
        if (mApi.isWXAppInstalled())
        {
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;

            platform = PLATFORM_WECHAT;
            //表示分享至微信好友
            req.scene = SendMessageToWX.Req.WXSceneSession;
            mApi.sendReq(req);
        } else
        {
            ToastUtil.show("您没有安装微信客户端");
        }
    }


    /**
     * 分享至微信朋友圈
     *
     * @param msg
     */
    private void shareToWechatFriends(WXMediaMessage msg)
    {
        if (mApi.isWXAppInstalled())
        {
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;

            platform = PLATFORM_TIMELINE;
            //表示分享至朋友圈
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
            mApi.sendReq(req);
        } else
        {
            ToastUtil.show("您没有安装微信客户端");
        }
    }


    /**
     * 保存到文件
     *
     * @param bitmap
     */
    private void saveToFile(Bitmap bitmap, boolean isShowToast)
    {
        FileOutputStream fos = null;
        try
        {
            if (bitmap != null && !bitmap.isRecycled())
            {
                byte[] bytes = BitmapUtil.Bitmap2Bytes(bitmap);
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constants.ROOT_DIR);
                if (!dir.exists())
                {
                    boolean isSucc = dir.mkdirs();
                    if (!isSucc)
                    {
                        if (isShowToast)
                        {
                            ToastUtil.show("创建文件失败");
                        }
                        return;
                    }
                }
                File file = new File(dir, AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID() + ".jpg");
                if (!file.exists())
                {
                    fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    // 通知系统更新图库
                    AppContext.getInstanceBase().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                }
                if (isShowToast)
                {
                    ToastUtil.show("保存成功，文件存储于：" + file.getAbsolutePath());
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (PLATFORM_QQ == platform)
        {
            Tencent.onActivityResultData(requestCode, resultCode, data, new QQShareListener(this));
        }
//        if (PLATFORM_WEIBO == platform)
//        {
//            if (wbShareHandler != null)
//            {
//                wbShareHandler.doResultIntent(data, this);
//            }
//        }
        platform = 0;
    }

    /**
     * QQ分享监听
     */
    public static class QQShareListener implements IUiListener
    {

        private WeakReference<DialogMerchantQRCode> activityWeakReference;

        QQShareListener(DialogMerchantQRCode acShareDialog)
        {
            activityWeakReference = new WeakReference<>(acShareDialog);
        }

        @Override
        public void onComplete(Object o)
        {
            Log.e("tencent share", "share success");
            ToastUtil.show("分享成功");
            if (activityWeakReference != null && activityWeakReference.get() != null)
            {
                activityWeakReference.get().dismiss();
            }
        }

        @Override
        public void onError(UiError uiError)
        {
            Log.e("tencent share", "share error. message:" + uiError.errorMessage);
            if (activityWeakReference != null && activityWeakReference.get() != null)
            {
                activityWeakReference.get().dismiss();
            }
        }

        @Override
        public void onCancel()
        {
            Log.e("tencent share", "share cancel");
            if (activityWeakReference != null && activityWeakReference.get() != null)
            {
                activityWeakReference.get().dismiss();
            }
        }
    }
}
