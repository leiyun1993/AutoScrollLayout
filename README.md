 这是一个小demo展示文字自动横向滚动、竖向滚动和View竖向滚动。

 1、文字横向滚动只是直接用TextView实现跑马灯效果

 2、文字竖向滚动实际上是在布局中加入了两个TextView使用，使用handler控制显示和隐藏，加上动画实现的竖向滚动。

 3、竖向的View滚动可实现类似淘宝首页的效果，通过继承ViewFlipper实现，这里用Adapter控制数据实际上是复杂的做法，
 实际上传入一个List<View>即可简单实现，可自行修改；
 
 4、实现自动滚动的View，仿中奖缓慢滚动效果

 5~7、是使用ViewFlipper实现的几种不同的切换效果

 示例图如下，有需要的自己扣源码。当然如果没有想要的效果，也可以继续搜一搜，类似的东西很多。
 ![image](https://github.com/leiyun1993/AutoScrollLayout/raw/master/image/01.gif)
