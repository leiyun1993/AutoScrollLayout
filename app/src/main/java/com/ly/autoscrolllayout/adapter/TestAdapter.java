package com.ly.autoscrolllayout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ly.autoscrolllayout.R;
import com.ly.autoscrolllayout.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends BaseAdapter {
    private List<Item> list;

    public TestAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = getItem(position);
        holder.title.setText(item.title);
        holder.text.setText(item.text);
        holder.title1.setText(item.title);
        holder.text1.setText(item.text);
        return convertView;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView text;
        private TextView title1;
        private TextView text1;

        public ViewHolder(View view) {
            this.title = view.findViewById(R.id.title);
            this.text = view.findViewById(R.id.text);
            this.title1 = view.findViewById(R.id.title1);
            this.text1 = view.findViewById(R.id.text1);
        }
    }
}