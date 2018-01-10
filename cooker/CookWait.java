package com.example.cooker;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CookWait extends AppCompatActivity {
    private TextView V1;
    private TextView V2;
    private TextView V3;
    private TextView V4;
    private Button btn1;
    String workPattern;//煮饭
    String workTmp;   //温度
    String workPart; //阶段
    String wTime; //时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_wait);
        V1 = (TextView) findViewById(R.id.textView2);
        V2 = (TextView) findViewById(R.id.textView3);
        V3 = (TextView) findViewById(R.id.textView4);
        V4 = (TextView) findViewById(R.id.textView5);
        if (MainActivity.V1.getText().equals("状态监控"))
        {
            btn1 = (Button)findViewById(R.id.OKBut);
            refleshClick(btn1);
        }
    }

    public void refleshClick(View view){
        //点击刷新
        new Thread(){
            //   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void run() {
                try {
                    URL url3=new URL("http://47.93.17.28/stateReflesh.php");
                    HttpURLConnection conn=(HttpURLConnection) url3.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.connect();
                    if(conn.getResponseCode()==200){
                        InputStream input1 = conn.getInputStream();
                        BufferedReader reader1 = new BufferedReader(new InputStreamReader(input1));
                        StringBuilder result1 = new StringBuilder();
                        String line;
                        while ((line = reader1.readLine()) != null) {
                            result1.append(line);
                        }
                        line = result1.toString();
                        final StateList data = returnParsedJsonObject(line);
                        if (data.workPart.equals("1")){
                            workPattern = "煮饭";
                        }else{
                            workPattern = "煮粥";
                        }
                        //判断工作模式
                        if(data.workPart.equals("1")) {//煮饭
                            switch (data.workState) {
                                case "1":
                                    workPart = "无热浸泡";
                                    break;
                                case "2":
                                    workPart = "加热吸水";
                                    break;
                                case "3":
                                    workPart = "断电吸水";
                                    break;
                                case "4":
                                    workPart = "持续加热";
                                    break;
                                case "5":
                                    workPart = "间歇式加热";
                                    break;
                                case "6":
                                    workPart = "煮饭完成，保温开始";
                                    break;
                                case "7":
                                    workPart = "保温(间歇式加热)";
                                    break;
                                case "8":
                                    workPart = "工作完成";
                                    break;
                                default:
                                    break;
                            }
                        }else if(data.workPart.equals("2")){//煮粥
                            switch (data.workState) {
                                case "1":
                                    workPart = "持续加热至98度";
                                    break;
                                case "2":
                                    workPart = "间歇式加热";
                                    break;
                                case "3":
                                    workPart = "煮粥完成，保温开始";
                                    break;
                                case "4":
                                    workPart = "工作结束";
                                    break;
                                default:
                                    break;
                            }

                        }
                        workTmp = data.workTemp+"℃";
                        int hour = Integer.parseInt(data.workTime)/3600;
                        int minute = Integer.parseInt(data.workTime)%3600/60;
                        wTime = hour+" 时"+minute+" 分";
                       runOnUiThread(new Runnable() {
                            public void run() {
                                V1.setText("工作模式："+workPattern); // 工作模式 煮饭
                                V2.setText("工作温度："+workTmp);  //温度
                                V3.setText("工作阶段："+workPart); //工作阶段  1到8
                                V4.setText("工作时间："+wTime); //时间
                                if(data.workState.equals("1")) {//煮饭
                                    if (data.workState.equals("6")) {
                                        MainActivity.V1.setText("煮饭");
                                        new AlertDialog.Builder(CookWait.this).setTitle("状态提示：").setMessage("工作完成").setPositiveButton("确定", null).show();
                                        finish();
                                    } else {
                                        MainActivity.V1.setText("状态监控");
                                    }
                                } else if (data.workState.equals("2")){ //煮粥
                                    if (data.workState.equals("3")) {
                                        MainActivity.V1.setText("煮饭");
                                        new AlertDialog.Builder(CookWait.this).setTitle("状态提示：").setMessage("工作完成").setPositiveButton("确定", null).show();
                                        finish();
                                    } else {
                                        MainActivity.V1.setText("状态监控");
                                    }
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
    private StateList returnParsedJsonObject (String result) {

        final StateList reData = new StateList();
        JSONObject resultObject = null;
        try {
            resultObject = new JSONObject(result);
            reData.workState = resultObject.getString("workState");//工作阶段
            reData.workTemp = resultObject.getString("workTemp");//工作温度
            reData.workPart = resultObject.getString("workPattern");//工作模式 饭 粥
            reData.workTime = resultObject.getString("cookTime");//工作时间
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return reData;
    }

    public class StateList {

        public String workState;
        public String workTemp;
        public String workPart;
        public String workTime;
    }
}
