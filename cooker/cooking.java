package com.example.cooker;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cooking extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup SexRg;
    public String RiceType="煮饭";
    public String HardType="正常";
    public int WarmTime=0,hour=0,minute=0,hours=0,minutes=0,delayTime=0;
    public String cookTime="";
    private RadioGroup HardRg;
    private Spinner sp1,sp2,sp3,sp4;
    private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);
        SexRg =(RadioGroup) findViewById(R.id.RadioGroup1);
        SexRg.setOnCheckedChangeListener(this);
        HardRg = (RadioGroup) findViewById(R.id.RadioGroup2);
        HardRg.setOnCheckedChangeListener(this);
        sp1 = (Spinner) findViewById(R.id.spinner2);
        sp2 = (Spinner) findViewById(R.id.spinner3);
        sp3 = (Spinner) findViewById(R.id.spinner4);
        sp4 = (Spinner) findViewById(R.id.spinner5);
        but = (Button) findViewById(R.id.OKBut);
        SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        cookTime    =    formatter.format(curDate);
        sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 hour= Integer.parseInt( sp1.getItemAtPosition(position).toString());
                if(hour==2)
                {
                    sp2.setSelection(0);
                    sp2.setEnabled(false);

                }else{
                    sp2.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hour= Integer.parseInt( sp3.getItemAtPosition(position).toString());
                if(hours==10)
                {
                    sp4.setSelection(0);
                    sp4.setEnabled(false);

                }else{
                    sp4.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minute= Integer.parseInt( sp2.getItemAtPosition(position).toString());;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minutes= Integer.parseInt( sp4.getItemAtPosition(position).toString());;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarmTime = hour*3600+minute*60;
                delayTime = hours*3600+minutes*60;
                Toast.makeText(cooking.this, "设置参数："+RiceType+","+HardType+","+WarmTime, Toast.LENGTH_SHORT).show();
                Toast.makeText(cooking.this, "正在启动电饭煲...", Toast.LENGTH_SHORT).show();
                new Thread(){
                    public void run() {
                        try {
                            URL url2=new URL("http://47.93.17.28/recordInsert.php?RiceType="+RiceType+"&HardType="+HardType+"&WarmTime="+WarmTime+"&cookTime="+cookTime+"&delayTime="+delayTime);
                            HttpURLConnection conn=(HttpURLConnection) url2.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(8000);
                            conn.setReadTimeout(8000);
                            conn.connect();
                            if(conn.getResponseCode()==200){
                                InputStream inputStream=conn.getInputStream();
                                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                                byte[]b=new byte[512];
                                int len;
                                while ((len=inputStream.read(b))!=-1) {
                                    byteArrayOutputStream.write(b,0,len);
                                }
                                String text=new String(byteArrayOutputStream.toByteArray(),"UTF-8");
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    };
                }.start();
                 Intent intent= new Intent(cooking.this,CookWait.class);
                 startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group.equals(SexRg))
        {
            if (checkedId==R.id.radioButton3)
            {
                RiceType = "煮饭";
            }
            else{
                RiceType = "煮粥";
            }
        }
        if (group.equals(HardRg))
        {
            if (checkedId==R.id.radioButton5)
            {
                HardType = "软";
            }
            else if (checkedId==R.id.radioButton6)
            {
                HardType = "正常";
            }
            else if (checkedId==R.id.radioButton7)
            {
                HardType = "硬";
            }
        }
    }

}


