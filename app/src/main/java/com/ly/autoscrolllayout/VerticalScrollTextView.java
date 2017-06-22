package com.ly.autoscrolllayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

public class VerticalScrollTextView extends RelativeLayout {
    /**
     * 默认轮播时间间隔：3s
     */
    private final static int SLEEP_TIME = 3000;

    /**
     * 默认动画执行时间：1s
     */
    private final static int ANIM_DURATION = 1000;

    /**
     * 轮播时间间隔
     */
    private int sleepTime = SLEEP_TIME;

    /**
     * 动画执行时间
     */
    private int animDuration = ANIM_DURATION;
    private boolean mSingleLine;
    private int mTextColor;
    private int mTextSize;
    /**
     * 滚动方向
     */
    private int scrollOrientation;

    private List<String> mDataSource;

    private TextView mTvContentTop;

    private TextView mTvContentBottom;

    /**
     * 是否运行轮播图
     */
    protected boolean mIsRun;

    /**
     * 自动轮播使用的handler
     */
    private Handler mHandler;

    /**
     * 当前轮播的项索引
     */
    private int mCurrentItemIndex;
    private int heightSize;

    public VerticalScrollTextView(Context context) {
        super(context);

        init(context);
    }

    public VerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.VerticalScrollTextView);
        sleepTime = array.getInt(R.styleable.VerticalScrollTextView_vst_sleepTime, SLEEP_TIME);
        animDuration = array.getInt(R.styleable.VerticalScrollTextView_vst_animDuration, ANIM_DURATION);
        scrollOrientation = array.getInt(R.styleable.VerticalScrollTextView_vst_scrollOrientation, 0);
        mTextSize = array.getDimensionPixelSize(R.styleable.VerticalScrollTextView_vst_textSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                12, getResources().getDisplayMetrics()));
        mTextColor = array.getColor(R.styleable.VerticalScrollTextView_vst_textColor, Color.BLACK);
        mSingleLine = array.getBoolean(R.styleable.VerticalScrollTextView_vst_singleLine, true);
        array.recycle();

        init(context);
    }

    private void init(Context context) {
        initTextView(context);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.CENTER_VERTICAL);

        addView(mTvContentTop, lp1);
        addView(mTvContentBottom, lp1);

        mHandler = new SliderScrollHandler(this);
    }

    private void initTextView(Context context) {
        mTvContentTop = new TextView(context);
        mTvContentTop.setTextColor(mTextColor);
        mTvContentTop.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mTvContentTop.setSingleLine(mSingleLine);

        mTvContentBottom = new TextView(context);
        mTvContentBottom.setTextColor(mTextColor);
        mTvContentBottom.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mTvContentBottom.setSingleLine(mSingleLine);
        if (mSingleLine) {
            mTvContentTop.setEllipsize(TextUtils.TruncateAt.END);

            mTvContentBottom.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        heightSize = getMeasuredHeight();
    }

    /**
     * 重置
     */
    private void resetData() {
        if (mDataSource == null || mDataSource.size() == 0) {
            return;
        }

        String normal = mDataSource.get(0);

        if (normal != null) {
            mTvContentTop.setText(normal);
        }
    }

    /**
     * 滚动
     */
    private void autoSlider() {
        if (mDataSource == null || mDataSource.size() < 2) {
            return;
        }

        String normal = mDataSource.get(mCurrentItemIndex);

        if (normal != null) {
            mTvContentTop.setText(normal);
        }

        if (mCurrentItemIndex == mDataSource.size() - 1) {
            mCurrentItemIndex = 0;
        } else {
            mCurrentItemIndex = mCurrentItemIndex + 1;
        }

        String next = mDataSource.get(mCurrentItemIndex);

        if (next != null) {
            mTvContentBottom.setText(next);
        }
        startTopAnim();
        startBottomAnim();
    }

    private void startTopAnim() {
        int value = -heightSize;  //默认朝上
        if (scrollOrientation != 0) {
            //朝下
            value = heightSize;
        }

        ObjectAnimator.ofFloat(mTvContentTop, "translationY", 0F, value)
                // 设置执行时间(1000ms)
                .setDuration(animDuration)
                // 开始动画
                .start();
    }

    private void startBottomAnim() {
        int value = heightSize;  //默认朝上
        if (scrollOrientation != 0) {
            //朝下
            value = -heightSize;
        }

        ObjectAnimator.ofFloat(mTvContentBottom, "translationY", value, 0F)
                // 设置执行时间(1000ms)
                .setDuration(animDuration)
                // 开始动画
                .start();
    }

    /**
     * 轮播滚动Handler
     */
    private static class SliderScrollHandler extends Handler {
        private WeakReference<VerticalScrollTextView> mSliderView;

        SliderScrollHandler(VerticalScrollTextView sliderView) {
            mSliderView = new WeakReference<>(sliderView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    VerticalScrollTextView sliderView = mSliderView.get();
                    if (sliderView != null && mSliderView.get().mIsRun) {
                        sliderView.autoSlider();
                        //重复
                        this.sendEmptyMessageDelayed(0, mSliderView.get().sleepTime);
                    }
                    break;
            }
        }
    }

    public void setDataSource(List<String> dataSource) {
        this.mDataSource = dataSource;
        mCurrentItemIndex = 0;
        resetData();
    }

    /**
     * Description: 开始轮播
     */
    public void startPlay() {
        if (mHandler != null) {
            mIsRun = true;
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageDelayed(0, sleepTime);
        }
    }

    /**
     * Description: 暂停轮播
     */
    public void stopPlay() {
        if (mHandler != null) {
            mIsRun = false;
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 设置轮播的间隔时间
     *
     * @param sleepTime 单位：毫秒
     */
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * 设置动画执行时间
     *
     * @param animDuration 单位：毫秒
     */
    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    public void setScrollOrientation(@IntRange(from = 0, to = 1) int scrollOrientation) {
        this.scrollOrientation = scrollOrientation;
    }
}