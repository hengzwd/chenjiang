package com.shtoone.chenjiang.mvp.view.adapter;

import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.chenjiang.R;
import com.shtoone.chenjiang.mvp.model.entity.db.StaffData;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class StaffRVAdapter extends BaseQuickAdapter<StaffData, BaseViewHolder> {
    private static final String TAG = StaffRVAdapter.class.getSimpleName();

    public StaffRVAdapter() {
        super(R.layout.item_rv_project_staff_fragment, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, StaffData staffData) {
        holder.setText(R.id.tv_name_item_rv_project_staff_fragment, "姓名：" + staffData.getUserName())
                .setText(R.id.tv_type_item_rv_project_staff_fragment, Html.fromHtml("类别：<font color=black>" + "司镜人员" + "</font>"))
                .setText(R.id.tv_phone_number_item_rv_project_staff_fragment, Html.fromHtml("电话：<font color=black>" + staffData.getUserPhone() + "</font>"));
    }
}
