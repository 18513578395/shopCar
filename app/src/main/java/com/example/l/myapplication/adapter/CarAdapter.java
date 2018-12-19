package com.example.l.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.l.myapplication.R;
import com.example.l.myapplication.bean.CarBean;
import com.example.l.myapplication.weight.MyView;


import java.util.List;

public class CarAdapter extends BaseExpandableListAdapter {

    List<CarBean.DataBean> mList;
    Context mContext;

    public CarAdapter(List<CarBean.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getSpus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder=null;
        if(convertView==null){
            groupHolder=new GroupHolder();
            convertView=View.inflate(mContext,R.layout.group_item,null);
            groupHolder.g_cb=convertView.findViewById(R.id.group_cb);
            groupHolder.g_tv=convertView.findViewById(R.id.group_tv);

            convertView.setTag(groupHolder);
        }else{
            groupHolder= (GroupHolder) convertView.getTag();
        }

        //groupHolder.g_cb.setChecked();
        groupHolder.g_tv.setText(mList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if(convertView==null){
            childHolder=new ChildHolder();
            convertView= View.inflate(mContext,R.layout.child_item,null);

            childHolder.c_cb=convertView.findViewById(R.id.child_cb);
            childHolder.c_img=convertView.findViewById(R.id.child_img);
            childHolder.c_tv=convertView.findViewById(R.id.child_tv);
            childHolder.c_price=convertView.findViewById(R.id.child_price);
            childHolder.myView=convertView.findViewById(R.id.myview);

            convertView.setTag(childHolder);
        }else {
            childHolder= (ChildHolder) convertView.getTag();
        }
        CarBean.DataBean.SpusBean spusBean = mList.get(groupPosition).getSpus().get(childPosition);

        childHolder.c_cb.setChecked(spusBean.isChildcheck());
        //加载图片
        Glide.with(mContext).load(spusBean.getPic_url()).into(childHolder.c_img);

        //****
        childHolder.c_price.setText(spusBean.getSkus().get(0).getPrice()+"");
        childHolder.c_tv.setText(spusBean.getName());
        childHolder.myView.setNumber(spusBean.getPraise_num());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class GroupHolder{
        CheckBox g_cb;
        TextView g_tv;
    }
    class ChildHolder{
        CheckBox c_cb;
        ImageView c_img;
        TextView c_tv;
        TextView c_price;
        MyView myView;
    }
}
