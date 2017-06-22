package com.ly.autoscrolllayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private VerticalScrollTextView vTextView;
    private VerticalScrollLayout vScrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVTextView();
        initVScrollLayout();
    }

    private void initVScrollLayout() {
        vScrollLayout = (VerticalScrollLayout) findViewById(R.id.scroll_layout);
        TestAdapter adapter = new TestAdapter();
        vScrollLayout.setAdapter(adapter);
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Item item = new Item();
            item.title = "标签" + i;
            item.text = "应该显示的内容标题" + i;
            items.add(item);
        }
        adapter.setList(items);
    }

    private void initVTextView() {
        vTextView = (VerticalScrollTextView) findViewById(R.id.v_text_view);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(String.format("测试竖向滚动文字%s", i));
        }
        vTextView.setDataSource(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vTextView != null) {
            vTextView.startPlay();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vTextView != null) {
            vTextView.stopPlay();
        }
    }

    private static class Item {
        public String title;
        public String text;
    }

    private static class TestAdapter extends BaseAdapter {
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
    }

    private static class ViewHolder {
        private TextView title;
        private TextView text;
        private TextView title1;
        private TextView text1;

        public ViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.title);
            this.text = (TextView) view.findViewById(R.id.text);
            this.title1 = (TextView) view.findViewById(R.id.title1);
            this.text1 = (TextView) view.findViewById(R.id.text1);
        }
    }
}
