package com.chuyao.xbo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chuyao.xbo.R;
import com.chuyao.xbo.model.Status;

import java.util.List;

/**
 * Created by chuyao on 16-1-8.
 */
public class TimeLineLIstAdapter extends BaseAdapter{

    private Context context;
    private List<Status> data;

    public TimeLineLIstAdapter(Context context, List<Status> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_status, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            viewHolder.tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            viewHolder.tvTime = (TextView) view.findViewById(R.id.tvTime);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final Status itemData = data.get(i);
        viewHolder.tvTitle.setText(itemData.text);
        viewHolder.tvUserName.setText(itemData.user.name);
        viewHolder.tvTime.setText(itemData.created_at);
        return view;
    }

    class ViewHolder{
        TextView tvUserName;
        TextView tvTitle;
        TextView tvTime;
    }
}
