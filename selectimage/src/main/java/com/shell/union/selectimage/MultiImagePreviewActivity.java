package com.shell.union.selectimage;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hengxian.baselib.base.BaseActivity;
import com.hengxian.baselib.glide.GlideApp;
import com.shell.union.selectimage.photoview.PhotoView;

import java.util.ArrayList;

/**
 * 选择图片预览
 *
 * @author frj
 */
public class MultiImagePreviewActivity extends BaseActivity implements OnPageChangeListener, View.OnClickListener
{

    private ViewPager viewPager;
    private ArrayList<String> imgPaths;

    @Override
    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.selectimage_activity_preview;
    }

    @Override
    protected void init()
    {
        viewPager = findViewById(R.id.viewPager);

        viewPager.addOnPageChangeListener(this);

        imgPaths = getIntent().getStringArrayListExtra(ConstantValue.EXTRA_DEFAULT_SELECTED_LIST);
        viewPager.setAdapter(mPagerAdapter);
        if (imgPaths != null && imgPaths.size() > 0)
        {
            viewPager.setCurrentItem(0);
            setToolbarTitle((0 + 1) + "/" + imgPaths.size());
        }
    }


    @Override
    protected String[] getPermissionArrays()
    {
        return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    }

    @Override
    protected int[] getPermissionInfoTips()
    {
        return new int[]{R.string.selectimage_selectimg_permission_write_external_storage_tips};
    }

    @Override
    public void onClick(View v)
    {
        if (isFastDoubleClick(v))
        {
            return;
        }
        finish();
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter()
    {

        @Override
        public int getCount()
        {
            if (imgPaths == null)
            {
                return 0;
            } else
            {
                return imgPaths.size();
            }
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position)
        {
            final PhotoView photoPreview = new PhotoView(MultiImagePreviewActivity.this);
            photoPreview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            String path = imgPaths.get(position);
            photoPreview.enable();
            if (path.indexOf("http://") != 0)
            {
                path = "file://" + path;
            }
            GlideApp.with(MultiImagePreviewActivity.this).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).fitCenter().into(photoPreview);
            ((ViewPager) container).addView(photoPreview);
            return photoPreview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

    };

    @Override
    public void onPageScrollStateChanged(int arg0)
    {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {

    }

    @Override
    public void onPageSelected(int arg0)
    {
        if (imgPaths != null && imgPaths.size() > 0)
        {
            setToolbarTitle((arg0 + 1) + "/" + imgPaths.size());
        }
    }
}
