package com.fx.feemore.business.ui.finance.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BtAdapter;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.util.BankCardNoUtil;
import com.fx.feemore.business.util.VerificationUtil;

/**
 * function:选择银行卡列表适配器
 * author: frj
 * modify date: 2018/12/31
 */
public class AdSelectCard extends BtAdapter<BankCardBean>
{
    /**
     * 项描述格式化
     */
    private static final String DES_FORMAT = "%s(%s)";

    private SparseBooleanArray map;
    private int currPos = -1;


    public AdSelectCard(Context context)
    {
        super(context);
        map = new SparseBooleanArray();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.ad_select_card, null);
        }
        TextView textView = (TextView) convertView;
        textView.setText(getItemDes(position));
        textView.setSelected(map.get(position, false));
        return convertView;
    }

    /**
     * 获取项描述
     *
     * @param position 位置
     * @return
     */
    private String getItemDes(int position)
    {
        BankCardBean bean = getItem(position);
        if (bean != null)
        {
            return String.format(DES_FORMAT, VerificationUtil.verifyDefault(bean.getBankname()), BankCardNoUtil.getLastFourNo(bean.getBankno()));
        }
        return "";
    }

    /**
     * 设置选中
     *
     * @param pos 项位置
     */
    public void setSelect(int pos)
    {
        if (currPos != pos)
        {
            map.put(pos, true);
            map.put(currPos, false);
            currPos = pos;
            notifyDataSetChanged();
        }
    }
}
