package com.fx.feemore.business.ui.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.ui.finance.adapter.AdSelectCard;
import com.hengxian.common.DisplayUtil;

import java.util.ArrayList;

/**
 * function:选择银行卡
 * author: frj
 * modify date: 2018/12/31
 */
public class DialogSelectCard extends DialogFragment
{

    private OnItemSelectListener onItemSelectListener;

    public static DialogSelectCard newInstance(ArrayList<BankCardBean> list)
    {
        Bundle bundle = new Bundle();
        DialogSelectCard dialogSelectCard = new DialogSelectCard();
        bundle.putParcelableArrayList("list", list);
        dialogSelectCard.setArguments(bundle);
        return dialogSelectCard;
    }

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
        return inflater.inflate(R.layout.dialog_select_card, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setWindowAnimations(R.style.Dialog_Bottom);
        getDialog().getWindow().getAttributes().width = DisplayUtil.getScreenSize(getActivity()).widthPixels;
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        ListView listView = getView().findViewById(R.id.listView);
        AdSelectCard adapter = new AdSelectCard(getActivity());
        listView.setAdapter(adapter);
        if (getArguments() != null)
        {
            adapter.addList(getArguments().getParcelableArrayList("list"), false);
        }
        getView().findViewById(R.id.tv_add_card).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AcAddCard.class);
            startActivity(intent);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (onItemSelectListener != null)
            {
                onItemSelectListener.onSelect(position, adapter.getItem(position));
            }
            dismiss();
        });
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener)
    {
        this.onItemSelectListener = onItemSelectListener;
    }

    /**
     * 项选中监听
     */
    public interface OnItemSelectListener
    {
        /**
         * 获取选中项
         *
         * @param pos  选中项
         * @param bean 实体信息
         */
        void onSelect(int pos, BankCardBean bean);
    }
}
