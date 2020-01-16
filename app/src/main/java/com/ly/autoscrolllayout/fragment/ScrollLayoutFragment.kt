package com.ly.autoscrolllayout.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ly.autoscrolllayout.R
import com.ly.autoscrolllayout.adapter.TestAdapter
import com.ly.autoscrolllayout.entity.Item
import com.ly.autoscrolllayout.entity.SwitcherItem
import kotlinx.android.synthetic.main.fragment_scroll_layout.*

/**
 * A simple [Fragment] subclass.
 */
class ScrollLayoutFragment : Fragment() {

    companion object {
        fun getNewInstance(): ScrollLayoutFragment = ScrollLayoutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scroll_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSmoothScrollLayout()
        initVScrollLayout()
        initVTextView()
        initTextSwitcherView()
    }

    override fun onResume() {
        super.onResume()
        scroll_layout?.startFlipping()
        v_text_view?.startPlay()
        textSwitcherView?.startPlay()
    }

    override fun onPause() {
        super.onPause()
        scroll_layout?.stopFlipping()
        v_text_view?.stopPlay()
        textSwitcherView?.stopPlay()
    }

    private fun initTextSwitcherView() {
        textSwitcherView.setSwitcherLayout(R.layout.item_switcher_view)
        val list = mutableListOf<SwitcherItem>()
        list.add(SwitcherItem("热门", "冬季韩版女子大衣，折扣价15.6元起"))
        list.add(SwitcherItem("新品", "兔耳朵卡通亲子保暖防滑棉拖鞋"))
        list.add(SwitcherItem("如何", "黄色卫衣+牛仔裤，开春少女必备穿搭"))
        textSwitcherView.setData(list)
    }


    /**
     * 仿中奖滚动
     */
    private fun initSmoothScrollLayout() {
        val list: MutableList<String> = mutableListOf()
        list.add("张三购买彩票中了20W")
        list.add("187****0405购买彩票中了20W")
        list.add("李四购买彩票中了超级大礼包一个，获得飞机票两张")
        list.add("156***9876购买彩票中了三等奖")
        list.add("134***4235购买彩票中了特等奖，并获得海南三亚双人双飞游套餐一个")
        smoothScrollLayout.setData(list)
    }


    private fun initVScrollLayout() {
        val adapter = TestAdapter()
        scroll_layout.setAdapter(adapter)
        val items: MutableList<Item> = mutableListOf()
        for (i in 0..5) {
            val item = Item()
            item.title = "标签$i"
            item.text = "应该显示的内容标题$i"
            items.add(item)
        }
        adapter.setList(items)
    }

    private fun initVTextView() {
        val data: MutableList<String> = mutableListOf()
        for (i in 0..4) {
            data.add(String.format("测试竖向滚动文字%s", i))
        }
        v_text_view.setDataSource(data)
    }
}
