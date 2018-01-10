package com.example.cooker;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class CookRecord extends AppCompatActivity {
    public String data1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_record);

        data1 = new String[10];
            int i= 0;
        final LinearLayout textV = (LinearLayout) findViewById(R.id.lay);
        new Thread(){
        //   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void run() {
                try {
                    URL url2=new URL("http://47.93.17.28/recordSelect.php");
                    HttpURLConnection conn=(HttpURLConnection) url2.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.connect();
                    if(conn.getResponseCode()==200){
                        InputStream inputStream=conn.getInputStream();
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        reader= null;
                        input.close();
                        line = result.toString();
                        JSONObject mainObject = new JSONObject(line);
                      final List<recordList> data = returnParsedJsonObject(line);
                        runOnUiThread(new Runnable() {
                            public void run() {

                                for (recordList list :data) {
                                    int hour = Integer.parseInt(list.WarmTime)/3600;
                                    int minute = Integer.parseInt(list.WarmTime)%3600/60;
                                    //把数据显示至屏幕
                                    //1.集合中每有一条元素，就new一个textView
                                    TextView tv = new TextView(CookRecord.this);
                                    //2.把信息设置为文本框的内容
                                    tv.setText(list.cookTime);
                                    tv.setPadding(5,15,0,5);
                                    tv.setTextSize(20);
                                    tv.setGravity(Gravity.CENTER_HORIZONTAL);

                                    TextView tv1 = new TextView(CookRecord.this);
                                    //2.把信息设置为文本框的内容
                                    tv1.setText("模式："+list.RiceType);
                                    tv1.setPadding(5,5,0,5);
                                    tv1.setTextSize(20);

                                    TextView tv2 = new TextView(CookRecord.this);
                                    //2.把信息设置为文本框的内容
                                    tv2.setText("口感："+list.HardType);
                                    tv2.setPadding(5,5,0,5);
                                    tv2.setTextSize(20);

                                    TextView tv3 = new TextView(CookRecord.this);
                                    //2.把信息设置为文本框的内容
                                    tv3.setText("保温时间："+String.valueOf(hour)+"小时"+String.valueOf(minute)+"分钟");
                                    tv3.setPadding(5,5,0,40);
                                    tv3.setTextSize(20);

                                    textV.addView(tv);
                                    textV.addView(tv1);
                                    textV.addView(tv2);
                                    textV.addView(tv3);
                                }

                            }
                        });

                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }
            };
        }.start();
    }
    //将json字符串存到数组里
  private List<recordList> returnParsedJsonObject (String result){

        List<recordList> jsonObject = new ArrayList<recordList>();

        JSONObject resultObject = null;

        JSONArray jsonArray = null;

        recordList newItemObject = null;

        try {

            resultObject = new JSONObject(result);

            System.out.println("Testing the water " + resultObject.toString());

            jsonArray = resultObject.optJSONArray("records");

        } catch (JSONException e) {

            e.printStackTrace();

        }

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonChildNode = null;

            try {
                jsonChildNode = jsonArray.getJSONObject(i);
                recordList reData = new recordList();
                reData.HardType = jsonChildNode.getString("HardType");
                reData.RiceType = jsonChildNode.getString("RiceType");
                reData.cookTime = jsonChildNode.getString("cookTime");
                reData.WarmTime = jsonChildNode.getString("WarmTime");
                jsonObject.add(reData);

            } catch (JSONException e) {

                e.printStackTrace();

            }
        }

        return jsonObject;
    }

    public class recordList {

        public String HardType;
        public String RiceType;
        public String cookTime;
        public String WarmTime;
    }



}
