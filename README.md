Cooker
======

Cooker is an APP  which is used to control the working status of rice cookers.

Cooker来自于参加的一个赛百特杯物联网大赛，设计了一个远程控制家中电饭锅煮饭，监控状态的一个app。基本完成了控制与监控功能。

功能界面展示
------

 <img src="https://github.com/WenHui-Zhou/Images-repository/blob/master/CookerImage/72135480710912143.jpg" width = "200" height = "350" alt="主界面" align="center" />

设置煮饭参数界面：

 <img src="https://github.com/WenHui-Zhou/Images-repository/blob/master/CookerImage/216958659597325522.jpg" width = "200" height = "350" alt="设置煮饭参数" align="center" />

文件目录介绍
------

 - Cooker：该文件夹下存放着界面时间相应的操作代码。
 - res：该文件加下存放着APP需要的资源文件，其中layout文件夹中存放着界面布局`xml` 代码。
 - AndroidManifest.xml：这个文件是系统的配置文件
 - 后台逻辑代码：该文件夹下为后台的PHP代码

**以上代码不能直接运行，需要新建好安卓项目后，在相应的位置添加。**

涉及到的技术
------

因为项目要求，在项目实现的时候赶鸭子上架，学习了Android编程，PHP编程，阿里云centos7.2搭建，数据库环境为lamp（PHP环境），数据库为MySQL。

 - android官方教程：http://hukai.me/android-training-course-in-chinese/basics/
 - PHP教程：https://laravel-china.github.io/php-the-right-way/
 - 阿里云后台搭建：阿里云使用CentOs+lamp系统

阿里云后台
-----

 -  系统搭建教程：先上阿里云买一个后台，然后一步步按照这个后台搭建教程搭建。
 - lamp系统使用教程：https://oss.aliyuncs.com/netmarket/e1f47515-c24c-4b4c-aab8-b644d6baee41.pdf?spm=5176.2020520132.101.7.aTmgiI&file=e1f47515-c24c-4b4c-aab8-b644d6baee41.pdf
 - WinScp：该软件能够用可视化的方式向后台系统传递文件
 - 阿里云后台网址：http://47.93.17.28/
 - 数据库网址：http://47.93.17.28/phpmyadmin/
 - 账号密码：我就不说了
 - 后台本质上就相当于一个文件夹，每次访问后台就是去后台中寻找PHP文件，进行执行，也就是说PHP操作将完成数据库的所有操作。


收获
--
这个repository 是项目完成好几个月后才想起来添上的，这个项目完成的时间大概是在2017年8月10日左右，项目过程中接触到很多新的东西，多记录十分有好处，不然像我现在又忘了大半了。这个项目最有意义的地方，我认为在于接触到后台，安卓的开发， 了解到安卓这一套和B/S前端那一套简直一样，都是前端界面，中间写逻辑，后台PHP写数据库的操作。写起来十分顺手。整个智能家居电饭锅项目最后也获得了全国一等奖。
