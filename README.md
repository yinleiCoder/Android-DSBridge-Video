# Android混合开发DSBridge实现短视频通信:video_camera:


### 技术栈：

- DS-bridge
- Hybrid混合开发
- 短视频业务



### 需求点：

- 视频播放H5页面编写
- 客户端原生播放器的使用
- 基于DSBridge搭建数据通道
- 暴露视频播放能力提供给javascript



### 当下混合开发技术简单对比:

| Web开发                                                      | Hybrid                                                       | RN                                                           | Flutter                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------ |
| 开发成本低、效率高、与客户端解耦、及时上线、纯WebView环境，体验较差 | 介于原生和Web之间，包含web开发的所有优点、部分功能接近原生APP体验、能够调用原生能力 | 摆脱WebView，体验可和原生媲美、支持热更新、页面加载较慢、控件不够完善 | 最佳运行体验、性能强、采用Dart开发、跨平台 |



### Hybrid框架：

解决的问题：

- web调用原生——JavaScript -> java
- 原生调用web——java -> javascript
- 数据通道的搭建——性能及易用性

> 框架的性能需要扎实的技术能力，易用性则更加侧重于架构设计能力



### DSBridge：

- 国内推出的Javascript bridge跨平台混合开发框架
- 官方提供了android/ios,真正实现跨平台
- 支持同步和异步调用
- 无需iFrame,性能好

其作者是wendux，很牛的字节跳动大神，著有“Flutter中文网”

接入方式：https://github.com/wendux/DSBridge-Android

​	A. 直接源码接入：下载DSBridge源码、作为独立Module接入工程、学习源码并做个性化修改

​	B. Gradle依赖接入：配置Gradle脚本，Sync自动接入、简单方便



前端调用客户端：js -> java

​	Android客户端提供js方法---> h5初始化dsbridge--->h5通过dsbridge.call直接调用

客户端调用前端：java -> js

​	h5前端注册js方法--->android获取DWebView实例--->android通过callHandler调用，传入回调接口

同步调用和异步调用：

​	A. 同步调用：

- public object handler(Object msg)

  B. 异步调用：

  - public void handler(Object arg, CompletionHandler handler)

  同步调用是DSBridget的特色，考虑到兼容IOS，参数中的Object msg是必须的。

