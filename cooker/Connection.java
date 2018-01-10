package com.example.cooker;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by ZHOU on 2017/6/23.
 */

public class Connection extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化连接leancloud参数
        AVOSCloud.initialize(this,"3DCJrdN8T6qQS4s1W758d6lz-gzGzoHsz","rRQKBYk9J9ex5s4kTi99wzI6");
        //调试日志函数调用
   //     AVOSCloud.setDebugLogEnabled(true);
    }
}
