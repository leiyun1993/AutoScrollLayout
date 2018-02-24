package com.ly.autoscrolllayout;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：SmoothScrollLayout
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-02-24 10:24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SmoothScrollLayout extends FrameLayout {

    private ScrollHandler mHandler;
    private MyAdapter mAdapter;
    private RecyclerView recyclerView;

    public SmoothScrollLayout(@NonNull Context context) {
        this(context, null);
    }

    public SmoothScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmoothScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.layout_smooth_scroll, this);
        mHandler = new ScrollHandler(this);
        mAdapter = new MyAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.rvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;       //拦截事件
    }

    public void setData(List<String> data) {
        mAdapter.setList(data);
        if (data != null && data.size() > 0) {
            mHandler.sendEmptyMessageDelayed(0, 100);
        }
    }

    public void smoothScroll() {
        recyclerView.smoothScrollBy(0, 5);
        mHandler.sendEmptyMessageDelayed(0, 100);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 弱引用防止内存泄露
     */
    private static class ScrollHandler extends Handler {
        private WeakReference<SmoothScrollLayout> view;

        public ScrollHandler(SmoothScrollLayout mView) {
            view = new WeakReference<>(mView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (view.get() != null) {
                view.get().smoothScroll();
            }
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<String> list;

        public MyAdapter() {
            list = new ArrayList<>();
        }

        public void setList(List<String> list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smooth_scroll, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindData(list.get(position % list.size()));
        }

        @Override
        public int getItemCount() {
            return list.size() > 0 ? Integer.MAX_VALUE : 0;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contentView;

        public ViewHolder(View itemView) {
            super(itemView);
            contentView = (TextView) itemView.findViewById(R.id.content);
        }

        public void bindData(String content) {
            contentView.setText(content);
        }
    }

}
