package com.ly.autoscrolllayout.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.ly.autoscrolllayout.R
import kotlinx.android.synthetic.main.fragment_view_flipper.*

/**
 * A simple [Fragment] subclass.
 */
class ViewFlipperFragment : Fragment() {

    companion object{
        fun getNewInstance():ViewFlipperFragment = ViewFlipperFragment()
    }

    private lateinit var flipperList: MutableList<ViewFlipper>
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_flipper, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flipperList = mutableListOf()
        initPushUpViewFlipper()
        initPushLeftViewFlipper()
        initFadeInViewFlipper()
        initHyperspaceViewFlipper()
    }

    override fun onResume() {
        super.onResume()
        flipperList.forEach {
            it.startFlipping()
        }
    }

    override fun onPause() {
        super.onPause()
        flipperList.forEach {
            it.stopFlipping()
        }
    }

    private fun initPushUpViewFlipper() {
        val flipper: ViewFlipper = push_up_flipper
        flipper.removeAllViews()
        for (flipperView in getFlipperView()) {
            flipper.addView(flipperView)
        }
//        flipper.startFlipping()
        flipper.setFlipInterval(2000)
        flipper.inAnimation = AnimationUtils.loadAnimation(context, R.anim.push_up_in)
        flipper.outAnimation = AnimationUtils.loadAnimation(context, R.anim.push_up_out)

        flipperList.add(flipper)
    }

    private fun initPushLeftViewFlipper() {
        val flipper: ViewFlipper = push_left_flipper
        flipper.removeAllViews()
        for (flipperView in getFlipperView()) {
            flipper.addView(flipperView)
        }
//        flipper.startFlipping()
        flipper.setFlipInterval(2000)
        flipper.inAnimation = AnimationUtils.loadAnimation(context, R.anim.push_left_in)
        flipper.outAnimation = AnimationUtils.loadAnimation(context, R.anim.push_left_out)

        flipperList.add(flipper)
    }

    private fun initFadeInViewFlipper() {
        val flipper: ViewFlipper = fade_in_flipper
        flipper.removeAllViews()
        for (flipperView in getFlipperView()) {
            flipper.addView(flipperView)
        }
//        flipper.startFlipping()
        flipper.setFlipInterval(2000)
        flipper.inAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        flipper.outAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        flipperList.add(flipper)
    }

    private fun initHyperspaceViewFlipper() {
        val flipper: ViewFlipper = hyperspace_flipper
        flipper.removeAllViews()
        for (flipperView in getFlipperView()) {
            flipper.addView(flipperView)
        }
//        flipper.startFlipping()
        flipper.setFlipInterval(2000)
        flipper.inAnimation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_in)
        flipper.outAnimation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out)

        flipperList.add(flipper)
    }

    private fun getFlipperView(): MutableList<View> {
        val flipperViews: MutableList<View> = mutableListOf()
        val view1 = View.inflate(context, R.layout.item_text_view, null)
        (view1 as TextView).text = "床前明月光"
        flipperViews.add(view1)
        val view2 = View.inflate(context, R.layout.item_text_view, null)
        (view2 as TextView).text = "疑是地上霜"
        flipperViews.add(view2)
        val view3 = View.inflate(context, R.layout.item_text_view, null)
        (view3 as TextView).text = "举头望明月"
        flipperViews.add(view3)
        val view4 = View.inflate(context, R.layout.item_text_view, null)
        (view4 as TextView).text = "低头思故乡"
        flipperViews.add(view4)
        return flipperViews
    }

}
