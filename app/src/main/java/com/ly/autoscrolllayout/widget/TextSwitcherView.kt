package com.ly.autoscrolllayout.widget

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewSwitcher
import com.ly.autoscrolllayout.R
import com.ly.autoscrolllayout.entity.SwitcherItem
import java.lang.ref.WeakReference

class TextSwitcherView(context: Context, attrs: AttributeSet?) : ViewSwitcher(context, attrs) {

    private val data = mutableListOf<SwitcherItem>()
    private var mIndex = 0
    private val mHandler = SwitchHandler(this)
    private val delayTime = 3000L

    init {
        setInAnimation(context, R.anim.push_up_in)
        setOutAnimation(context, R.anim.push_up_out)
    }


    fun setSwitcherLayout(resId: Int) {
        setFactory {
            LayoutInflater.from(context).inflate(resId, null)
        }
    }

    fun setData(newData: MutableList<SwitcherItem>) {
        data.clear()
        data.addAll(newData)
        startPlay()
    }

    private fun setText(item: SwitcherItem) {
        val tagTv: TextView = (nextView as ViewGroup).getChildAt(0) as TextView
        val contentTv: TextView = (nextView as ViewGroup).getChildAt(1) as TextView
        tagTv.text = item.tag
        contentTv.text = item.content
        showNext()
    }

    fun startPlay() {
        showNextView()
    }

    fun stopPlay() {
        mHandler.removeCallbacksAndMessages(null)
    }

    private fun showNextView() {
        if (data.size <= 0) return
        if (mIndex >= data.size) {
            mIndex = 0
        }
        mHandler.removeCallbacksAndMessages(null)
        setText(data[mIndex])
        mIndex++
        mHandler.sendEmptyMessageDelayed(0, delayTime)
    }

    companion object {

        private class SwitchHandler(view: TextSwitcherView) : Handler() {
            val view: WeakReference<TextSwitcherView> = WeakReference(view)

            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                view.get()?.showNextView()
            }
        }
    }


}