package com.fx.feemore.business.ui.order;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BtAdapter;
import com.fx.feemore.business.util.VerificationUtil;
import com.fx.feemore.business.util.ViewHolder;

/**
 * function:驳回原因类型
 * author: frj
 * modify date: 2018/12/29
 */
public class AdReasonType extends BtAdapter<String>
{

    private SparseBooleanArray map;
    private int currPos = -1;

    AdReasonType(Context context)
    {
        super(context);
        map = new SparseBooleanArray();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.ad_reason_type, null);
        }
        TextView textView = ViewHolder.get(convertView, R.id.tv_text);
        VerificationUtil.setViewValue(textView, getItem(position));
        textView.setSelected(map.get(position, false));
        return convertView;
    }

    /**
     * 设置项选中
     *
     * @param position 选中项
     */
    void setSelect(int position)
    {
        if (currPos != position)
        {
            map.put(currPos, false);
            map.put(position, true);
            currPos = position;
            notifyDataSetChanged();
        }
    }
}
