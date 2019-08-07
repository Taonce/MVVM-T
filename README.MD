LifeCycle+DataBinding+LiveData+ViewModel+Coroutines+KTX = MVVM-T

#### 基类中封装
* BaseActivity
* BaseFragment
* BaseDialogFragment
* BasePopupWindow
* BaseRecyclerViewAdapter

#### `BindingAdapter` 中封装
* `RecyclerView` 的点击事件和长按事件
* `EditText` 的文本改变事件
* `WebView` 的加载 `url` 事件

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