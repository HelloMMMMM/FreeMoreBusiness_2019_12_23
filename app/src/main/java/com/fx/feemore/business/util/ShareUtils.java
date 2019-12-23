package com.fx.feemore.business.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.hengxian.common.ToastUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * function:分享工具类
 * author: frj
 * modify date: 2018/5/9
 */
public class ShareUtils
{

    /**
     * 注册链接
     */
    private static final String REGISTER_URL = "https://mhd.feixingtech.com/static/mhd-front/register.html?";

    /**
     * 腾讯开放平台AppId
     */
    public static final String TENCENT_APPID = "1108194825";

    /**
     * 微信开放平台AppId
     */
    public static final String WECHAT_APPID = "wxa51fa6abccdc3084";
    /**
     * 小程序AppId
     */
    public static final String WECHAT_MINI_PROGRAM_APPID = "wx3b30ea24005005ba";
    /**
     * 小程序原始id
     */
    public static final String WECHAT_MINI_PROGRAM_ORIGIN_APPID = "gh_e8c37755bf8f";

    /**
     * 新浪开放平台AppKey
     */
    public static final String SINA_APPKEY = "";

    /**
     * 默认回调页
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    /**
     * 新浪微博权限Scope
     */
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";


    /**
     * 当前类静态对象
     */
    private static ShareUtils mUtils;

    private Tencent mTencent;   //腾讯
    private IWXAPI mApi;        //微信Api对象

    private ShareUtils()
    {
        init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        mTencent = Tencent.createInstance(TENCENT_APPID, AppContext.getInstanceBase());
        //第三个参数Boolean表示是否校验签名，true表示校验，false反之。如果是测试版，那么不校验
        mApi = WXAPIFactory.createWXAPI(AppContext.getInstanceBase(), WECHAT_APPID, !BuildConfig.DEBUG);
    }

    private static ShareUtils getInstance()
    {
        if (mUtils == null)
        {
            synchronized (ShareUtils.class)
            {
                if (mUtils == null)
                {
                    mUtils = new ShareUtils();
                }
            }
        }
        return mUtils;
    }

    /**
     * 调起微信小程序
     *
     * @param path 小程序路径
     */
    private void callWxMiniProgram(String path, String param, String title, String des)
    {
//        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = WECHAT_MINI_PROGRAM_ORIGIN_APPID; // 填小程序原始id
//        req.path = path;                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
//        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_TEST;// 可选打开 开发版，体验版和正式版


        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
        miniProgramObj.webpageUrl = REGISTER_URL + param; // 兼容低版本的网页链接
        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
        miniProgramObj.userName = WECHAT_MINI_PROGRAM_ORIGIN_APPID;     // 小程序原始id
        miniProgramObj.path = path;            //小程序页面路径

        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
        msg.title = title;                    // 小程序消息title
        msg.description = des;               // 小程序消息desc

        Bitmap bmp = BitmapFactory.decodeResource(AppContext.getInstanceBase().getResources(), R.mipmap.icons);
        byte[] bytes = null;
        if (bmp != null)
        {
            bytes = BitmapUtil.Bitmap2Bytes(bmp);
        }
        msg.thumbData = bytes;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
        mApi.sendReq(req);
    }

    /**
     * 调起微信小程序注册页面
     *
     * @param memberId 当前店铺会员id
     */
    public static void callWxMiniProgramRegister(String memberId)
    {
        getInstance().callWxMiniProgram("pages/login/register/index?id=" + memberId + "&type=2", "id=" + memberId + "&type=2", "免好多-会员免费尊享", "快来快来享受超级多的免费权益吧");
    }

    /**
     * 调起微信小程序拼团页面
     *
     * @param groupId 拼团id
     */
    public static void callWxMiniProgramGroup(String groupId)
    {
        getInstance().callWxMiniProgram("pages/index/groupDetail/groupDetail?id=" + groupId, "id=" + AppContext.getInstanceBase().getStoryUserBean() + "&type=2", "免好多-会员免费尊享", "快来助他一臂之力");
    }

    /**
     * 分享到QQ
     *
     * @param activity
     * @param shareContent 分享跳转的链接
     * @param listener     分享回调结果
     */
    public static void shareToQQ(Activity activity, Bundle shareContent, IUiListener listener)
    {
        if (activity != null)
        {
            if (getInstance().mTencent.isQQInstalled(activity))
            {

                if (listener == null)
                {
                    listener = new IUiListener()
                    {
                        @Override
                        public void onComplete(Object o)
                        {
                        }

                        @Override
                        public void onError(UiError uiError)
                        {
                        }

                        @Override
                        public void onCancel()
                        {
                        }
                    };
                }
                if (shareContent == null)
                {
                    shareContent = buildQQShareContent("", "", "");
                }
                getInstance().mTencent.shareToQQ(activity, shareContent, listener);
            } else
            {
                ToastUtil.show("您没有安装QQ客户端");
            }
        }
    }

    /**
     * 构建QQ分享内容
     *
     * @param title 标题
     * @param intro 简介
     * @param id    链接对应id
     * @return
     */
    public static Bundle buildQQShareContent(String title, String intro, String id)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, TextUtils.isEmpty(title) ? AppContext.getInstanceBase().getString(R.string.app_name) : title);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, generateShareUrl(id));
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, VerificationUtil.verifyDefault(intro, ""));
//        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, URL_ICON);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, AppContext.getInstanceBase().getString(R.string.app_name) + TENCENT_APPID);
        return bundle;
    }

    /**
     * 仅分享图片
     *
     * @param imgPath 本地图片地址
     * @return
     */
    public static Bundle buildQQShareOnlyImage(@NonNull String imgPath)
    {
        Bundle bundle = new Bundle();
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgPath);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, AppContext.getInstanceBase().getString(R.string.app_name));
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.
                SHARE_TO_QQ_TYPE_IMAGE);
//        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.
//                SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        return bundle;
    }


    /**
     * 构建微信分享内容
     *
     * @param bitmap 图片
     * @return
     */
    public static WXMediaMessage buildWXShareContent(Bitmap bitmap)
    {
        WXImageObject imgObj = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage();
        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
//        bitmap.recycle();
        msg.mediaObject = imgObj;
        msg.thumbData = BitmapUtil.Bitmap2Bytes(thumbBitmap);
        thumbBitmap.recycle();
        return msg;
    }


    /**
     * 生成分享链接
     *
     * @param id 商家id
     */
    public static String generateShareUrl(String id)
    {
        return REGISTER_URL + "id=" + id + "&type=2";
    }
}
