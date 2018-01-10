package com.example.cooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static TextView V1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//将布局xml文件引入到activity中
        V1 = (TextView)findViewById(R.id.button2);
    }

    public void CookRecordFun(View view){
        Intent intent = new Intent(MainActivity.this,CookRecord.class);
     //   Intent intent = new Intent(MainActivity.this,displayRecord.class);
        startActivity(intent);
    }
    public void GetConnection(View view){ //点击的按钮是view参数
  //      Toast.makeText(getApplicationContext(),"单击完成",Toast.LENGTH_SHORT).show();
       if (V1.getText().equals("煮饭")) {
           Intent intent = new Intent(MainActivity.this, cooking.class);
           startActivity(intent);
       }else{
           Intent intent = new Intent(MainActivity.this, CookWait.class);
           startActivity(intent);
       }
        /*
         *startactivityforresult（intent，1） 这个函数请求其他页面
         * 第一个参数是intent
         * 第二个参数是请求的一个标识
         * 以下是接受数据的函数
         *  onActivityResult(int requestcode,int resultcode,intent data);
         *  requestcode：请求的标识，谁发出的请求
         *  resultcode：哪一个一面返回的请求标识
         *  data：数据
         *
         *  这个是其他页面设置完数据，返回给上一个页面一个intent对象
         *  intent data; data.putextra("data1",数据)
         *  setresult(2,data);//2是我们定义的这个页面的标识
         *  finish(); 销毁这个页面就返回到第一个页面了
         */

    }

}
