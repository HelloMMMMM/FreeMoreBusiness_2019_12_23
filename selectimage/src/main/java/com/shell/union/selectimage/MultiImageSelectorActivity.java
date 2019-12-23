package com.shell.union.selectimage;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.hengxian.baselib.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * 多图选择 Created by Nereo on 2015/4/7.
 * <p/>
 * 请使用startActivityForResult启动， 传递参数（均为可选）：
 * ConstantValue.EXTRA_SELECT_COUNT（最大图片选择次数，int类型，默认9 ）、
 * ConstantValue.EXTRA_SELECT_MODE（图片选择模式，默认多选 多选为：ConstantValue.MODE_MULTI）、
 * ConstantValue.EXTRA_SHOW_CAMERA（是否显示相机，Boolean类型，默认显示 值为true ）、
 * ConstantValue.EXTRA_DEFAULT_SELECTED_LIST（当选择模式为多选时有效，表示选择集合，ArrayList
 * <String>集合）
 * <p/>
 * 返回： RESULT_CANCELED（未选择，直接返回）、 RESULT_OK（已选择）
 * <p/>
 * 结果参数（为RESULT_OK时有效）：
 * ConstantValue.EXTRA_RESULT（选择结果，返回为 ArrayList<String> 图片路径集合 ）
 */
public class MultiImageSelectorActivity extends BaseActivity implements MultiImageSelectorFragment.Callback
{

    private ArrayList<String> resultList = new ArrayList<>();
    private Button mSubmitButton;
    private int mDefaultCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.selectimage_activity_selector;
    }

    @Override
    protected void init()
    {
        // 完成按钮
        mSubmitButton = findViewById(R.id.btn_finish);
        if (resultList == null || resultList.size() <= 0)
        {
            mSubmitButton.setText("完成");
            mSubmitButton.setEnabled(false);
        } else
        {
            mSubmitButton.setText("完成");
            mSubmitButton.setEnabled(true);
        }
        mSubmitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (resultList != null && resultList.size() > 0)
                {
                    // 返回已选择的图片数据
                    Intent data = new Intent();
                    data.putStringArrayListExtra(ConstantValue.EXTRA_RESULT, resultList);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        Intent intent = getIntent();
        mDefaultCount = intent.getIntExtra(ConstantValue.EXTRA_SELECT_COUNT, 9);
        int mode = intent.getIntExtra(ConstantValue.EXTRA_SELECT_MODE, ConstantValue.MODE_MULTI);
        boolean isShow = intent.getBooleanExtra(ConstantValue.EXTRA_SHOW_CAMERA, true);
        if (mode == ConstantValue.MODE_MULTI && intent.hasExtra(ConstantValue.EXTRA_DEFAULT_SELECTED_LIST))
        {
            resultList = intent.getStringArrayListExtra(ConstantValue.EXTRA_DEFAULT_SELECTED_LIST);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.EXTRA_SELECT_COUNT, mDefaultCount);
        bundle.putInt(ConstantValue.EXTRA_SELECT_MODE, mode);
        bundle.putBoolean(ConstantValue.EXTRA_SHOW_CAMERA, isShow);
        bundle.putStringArrayList(ConstantValue.EXTRA_DEFAULT_SELECTED_LIST, resultList);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.image_grid,
                        Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle))
                .commit();
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
    public void onSingleImageSelected(String path)
    {
        Intent data = new Intent();
        resultList.add(path);
        data.putStringArrayListExtra(ConstantValue.EXTRA_RESULT, resultList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onImageSelected(String path)
    {
        if (!resultList.contains(path))
        {
            resultList.add(path);
        }
        // 有图片之后，改变按钮状态
        if (resultList.size() > 0)
        {
//			mSubmitButton.setText("完成(" + resultList.size() + "/" + mDefaultCount + ")");

            mSubmitButton.setText("完成");
            if (!mSubmitButton.isEnabled())
            {
                mSubmitButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onImageUnselected(String path)
    {
        if (resultList.contains(path))
        {
            resultList.remove(path);
//            mSubmitButton.setText("完成(" + resultList.size() + "/" + mDefaultCount + ")");
            mSubmitButton.setText("完成");
        } else
        {
//            mSubmitButton.setText("完成(" + resultList.size() + "/" + mDefaultCount + ")");
            mSubmitButton.setText("完成");
        }
        // 当为选择图片时候的状态
        if (resultList.size() == 0)
        {
            mSubmitButton.setText("完成");
            mSubmitButton.setEnabled(false);
        }
    }

    @Override
    public void onBackPressed()
    {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void onCameraShot(File imageFile)
    {
        if (imageFile != null)
        {
            // 通知系统更新图库
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
            Intent data = new Intent();
            resultList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(ConstantValue.EXTRA_RESULT, resultList);
            setResult(RESULT_OK, data);
            finish();
        }
    }

}
