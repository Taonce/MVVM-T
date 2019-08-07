## LifeCycle+DataBinding+LiveData+ViewModel+Coroutines+KTX = MVVM-T
> 使用前请务必通读此篇README文档

#### 使用

`git clone https://github.com/Taonce/MVVM-T`

剥离出**mvvm**模块即可，可以根据自己的需求剔除无关封装类和工具类，以此来缩减此Module的体积

以Module的形式依赖到自己的项目中，同时在项目的**build.gradle**中添加以下代码

```
android {
    dataBinding {
        enabled = true
    }
}
```

#### 基类中封装
* BaseActivity
* BaseFragment
* BaseDialogFragment
* BasePopupWindow
* BaseRecyclerViewAdapter
* BaseFragmentPagerAdapter
* BaseListActivity
* BaseListFragment

#### `BindingAdapter` 中封装
* `RecyclerView` 的点击事件和长按事件
* `EditText` 的文本改变事件
* `WebView` 的加载 `url` 事件
* `ViewPager` 的 `transform` 属性

#### 网络请求
* Retrofit中设置下 `BASE_URL`
* 请求推荐使用协程: `safeLaunch(Dispatchers.IO) { RetrofitManager.getApi.xxx() }`

#### 工具类
* `CoroutineUtils`:统一处理协程中抛出的异常
* `DensityUtils`:屏幕相关
* `DialogUtils`:弹框
* `FileUtils`:文件
* `IntentUtils`:界面跳转
* `LogUtils`:日志
* `NetUtils`:手机网络
* `PackageUtils`:apk信息
* `PhoneUtils`:手机信息
* `SpUtils`:SP
* `StringUtils`:字符串
* `ToastUtils`:吐司