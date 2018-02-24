package com.ly.autoscrolllayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
        initSmoothScrollLayout();
        getFlipperView();
        initPushUpViewFlipper();
        initPushLeftViewFlipper();
        initFadeInViewFlipper();
        initHyperspaceViewFlipper();
    }

    /**
     * 新增仿中奖滚动
     */
    private void initSmoothScrollLayout() {
        SmoothScrollLayout layout = (SmoothScrollLayout) findViewById(R.id.smoothScrollLayout);
        List<String> list = new ArrayList<>();
        list.add("张三购买彩票中了20W");
        list.add("187****0405购买彩票中了20W");
        list.add("李四购买彩票中了超级大礼包一个，获得飞机票两张");
        list.add("156***9876购买彩票中了三等奖");
        list.add("134***4235购买彩票中了特等奖，并获得海南三亚双人双飞游套餐一个");
        layout.setData(list);
    }

    private void initPushUpViewFlipper() {
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.push_up_flipper);
        flipper.removeAllViews();
        for (View flipperView : getFlipperView()) {
            flipper.addView(flipperView);
        }
        flipper.startFlipping();
        flipper.setFlipInterval(2000);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_out));
    }

    private void initPushLeftViewFlipper() {
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.push_left_flipper);
        flipper.removeAllViews();
        for (View flipperView : getFlipperView()) {
            flipper.addView(flipperView);
        }
        flipper.startFlipping();
        flipper.setFlipInterval(2000);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
    }

    private void initFadeInViewFlipper() {
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.fade_in_flipper);
        flipper.removeAllViews();
        for (View flipperView : getFlipperView()) {
            flipper.addView(flipperView);
        }
        flipper.startFlipping();
        flipper.setFlipInterval(2000);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    }

    private void initHyperspaceViewFlipper() {
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.hyperspace_flipper);
        flipper.removeAllViews();
        for (View flipperView : getFlipperView()) {
            flipper.addView(flipperView);
        }
        flipper.startFlipping();
        flipper.setFlipInterval(2000);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.hyperspace_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.hyperspace_out));
    }

    private List<View> getFlipperView() {
        List<View> flipperViews = new ArrayList<>();
        View view1 = View.inflate(this, R.layout.item_text_view, null);
        ((TextView) view1).setText("床前明月光");
        flipperViews.add(view1);
        View view2 = View.inflate(this, R.layout.item_text_view, null);
        ((TextView) view2).setText("疑是地上霜");
        flipperViews.add(view2);
        View view3 = View.inflate(this, R.layout.item_text_view, null);
        ((TextView) view3).setText("举头望明月");
        flipperViews.add(view3);
        View view4 = View.inflate(this, R.layout.item_text_view, null);
        ((TextView) view4).setText("低头思故乡");
        flipperViews.add(view4);
        return flipperViews;
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
