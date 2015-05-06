AndroidDevtf
=======

an open source project devtf.
开发技术前线( Dev Tech Frontier ) http://www.devtf.cn 的android版


关于
------- 
开发技术前线 ( Dev Tech Frontier ) 是一个各个IT领域 ( 前期只有Android和iOS )高质量技术文章、开源库、技术咨询的聚合网站，通过引入最新的优质文章，让我们的技术跟上国际步伐。该网站由Mr.Simple、chaossss、tiny-times、dupengwei创立，并且由Android开发技术前线开源组维护,希望我们做的这些对大家有一点帮助。
AndroidDevtf是这个网站的android端，方便大家随时随地方便的浏览各位大牛的技术文章。
同时 AndroidDevtf 也是开源的，希望能有更多的朋友参与进来。


依赖的开源库
------- 
######Material-dialogs-master
Material-Design 风格的对话框，此库需要依赖support-v7库

######support-v7
V7库的libs里面额外添加了v7-palette.jar和v7-recycleview.jar，方便v7扩展使用
说明：
    由于Material-Design库中已经依赖了v7库，所以项目里面不再直接引入v7了。
    v7库必须是API21环境，很多人都没注意到这点，导致不能生成R文件。

######supertoasts-library
一个开源的Toast提示框架

######网络框架 volley.jar
google 的网络框架

######图片加载框架 glide.jar
google 的图片加载框架

######butterknife.jar
最近有小伙伴down下去的时候跑不了，这里说明一下，因为用的eclipse，所以butterknife运行环境需要你手动配置
配置教程：http://www.cnblogs.com/MonkeyAC/articles/4242312.html

######gson.jar

######pg-2.0.1.jar
这个库是采用注解的方式简化android中实现Parcelable接口
谢谢鲍哥。 https://github.com/baoyongzhang/ParcelableGenerator

######图标资源

其中第一、二、三个库需要你自己下载并添加依赖，其他的库已经集成到了项目中

目前该项目的功能还很单一，后续会增加更多的功能。

###QQ讨论群 Android框架设计交流群 ( 413864859 )
###不想编译代码可以直接下载项目根目录里面的apk，如果apk有新的版本可以直接自动更新，今后就不用下载了

