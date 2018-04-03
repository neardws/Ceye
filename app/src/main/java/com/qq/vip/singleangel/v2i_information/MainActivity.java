package com.qq.vip.singleangel.v2i_information;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.TextScale;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATA_UI        = "UPDATA_UI";
    public static final String UPDATA_SUCCESS   = "UPDATA_SUCCESS";
    public static final String UPDATA_FAILED    = "UPDATA_FAILED";
    public static final String START_SEND       = "START_SEND";
    public static final String STOP_SEND        = "STOP_SEND";

    private BroadcastReceiver broadcastReceiver = null;
    private final IntentFilter intentFilter = new IntentFilter();

    /**
     * indexNum     序号
     * longitude    经度
     * latitude     纬度
     * speed        速度
     * direction    方向
     * timeNow      时间戳
     * packageNum   包大小
     * frequency    频率
     */

    private int frequency = 5;

    private TextView log;
    private WebView webView;

    private TextView tv_deviceNo;
    private TextView tv_indexNum;
    private TextView tv_longitude;
    private TextView tv_latitude;
    private TextView tv_speed;
    private TextView tv_direction;
    private TextView tv_timeNow;
    private TextView tv_packageNum;
    private TextView tv_context;
    private TextView gps_tpye;
    private TextView hint_gps_type;

    private TextView hint_deviceNo;
    private TextView hint_indexNum;
    private TextView hint_longitude;
    private TextView hint_latitude;
    private TextView hint_speed;
    private TextView hint_direction;
    private TextView hint_timeNow;
    private TextView hint_packageNum;

    private EditText ed_deviceName;
    private EditText ed_frequency;
    private Spinner  spin_frequency;
    private ArrayAdapter arrayAdapter;

    private Button btn_gps;
    private Button btn_time;
    private Button btn_start;
    private Button btn_stop;

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter.addAction(MainActivity.UPDATA_UI);
        intentFilter.addAction(MainActivity.UPDATA_SUCCESS);
        intentFilter.addAction(MainActivity.UPDATA_FAILED);
        intentFilter.addAction(MainActivity.START_SEND);
        intentFilter.addAction(MainActivity.STOP_SEND);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        log             = (TextView) findViewById(R.id.log);
        webView         = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        tv_deviceNo     = (TextView) findViewById(R.id.con_deviceNo);
        tv_indexNum     = (TextView) findViewById(R.id.con_indexNum);
        tv_longitude    = (TextView) findViewById(R.id.con_longitude);
        tv_latitude     = (TextView) findViewById(R.id.con_latitude);
        tv_speed        = (TextView) findViewById(R.id.con_speed);
        tv_direction    = (TextView) findViewById(R.id.con_direction);
        tv_timeNow      = (TextView) findViewById(R.id.con_timeNow);
        tv_packageNum   = (TextView) findViewById(R.id.con_packageNum);
        tv_context      = (TextView) findViewById(R.id.context);
        gps_tpye        = (TextView) findViewById(R.id.gps_type);
        hint_gps_type   = (TextView) findViewById(R.id.hint_gps_type);

        hint_deviceNo   = (TextView) findViewById(R.id.hint_deviceNo);
        hint_indexNum   = (TextView) findViewById(R.id.hint_indexNum);
        hint_longitude  = (TextView) findViewById(R.id.hint_longitude);
        hint_latitude   = (TextView) findViewById(R.id.hint_latitude);
        hint_speed      = (TextView) findViewById(R.id.hint_speed);
        hint_direction  = (TextView) findViewById(R.id.hint_direction);
        hint_timeNow    = (TextView) findViewById(R.id.hint_timeNow);
        hint_packageNum = (TextView) findViewById(R.id.hint_packageNum);

        ed_deviceName = (EditText) findViewById(R.id.edit_deviceName);
        ed_frequency = (EditText) findViewById(R.id.ed_frequency);
        spin_frequency = (Spinner) findViewById(R.id.spin_frequency);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.frequency,
                        android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);
        spin_frequency.setAdapter(arrayAdapter);
        spin_frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String freq = (String) arrayAdapter.getItem(position);
                if (freq != null){
                    setFrequency(Integer.parseInt(freq));
                    Toast.makeText(getApplicationContext(), freq +" is selected.",Toast.LENGTH_SHORT).show();
                }else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_gps     = (Button) findViewById(R.id.btn_GPS);
        btn_time    = (Button) findViewById(R.id.btn_timeNow);
        btn_start   = (Button) findViewById(R.id.btn_start);
        btn_stop    = (Button) findViewById(R.id.btn_stop);

       /* btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetInfo getInfo = new GetInfo();
                tv_latitude.setText(String.valueOf(
                        getInfo.getInformation().getLatitude()));
                tv_longitude.setText(String.valueOf
                        (getInfo.getInformation().getLongitude()));

            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int frequency = 1;
                if (!ed_frequency.getEditableText().toString().equals("")){
                    try {
                        frequency = Integer.parseInt(ed_frequency.getEditableText().toString());
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "格式错误，请输入数字", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "没有输入信息，默认使用下拉框中内容", Toast.LENGTH_SHORT).show();
                    frequency = getFrequency();
                }
                Intent intent = new Intent(MainActivity.this, GetInfo.class);
                //intent.setAction(GetInfo.StartThread);
                intent.putExtra(GetInfo.FREQUENCY, frequency);
                startService(intent);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetInfo.class);
                //intent.setAction(GetInfo.StopThread);
                stopService(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new ReactionBroadcast();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 接收更新UI的广播
     */
    public class ReactionBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case MainActivity.UPDATA_UI:
                    Toast.makeText(MainActivity.this, "更新信息", Toast.LENGTH_SHORT).show();
                    Information information = (Information) intent.getSerializableExtra(Information.IOFMATION);

                    if (information != null){
                        if (information.getIndexNum() != 0){
                            tv_indexNum     .setText(String.valueOf(information.getIndexNum()));
                        }
                        if (information.getLongitude() != 0){
                            tv_longitude    .setText(String.valueOf(information.getLongitude()));
                        }
                        if (information.getLatitude() != 0){
                            tv_latitude     .setText(String.valueOf(information.getLatitude()));
                        }

                            tv_speed        .setText(String.valueOf(information.getSpeed()));

                        if (information.getDirection() != 0){
                            tv_direction    .setText(String.valueOf(information.getDirection()));
                        }
                        if (information.getTimeNow() != 0){
                            tv_timeNow      .setText(String.valueOf(information.getTimeNow()));
                        }
                        if (information.getPackageNum() != 0){
                            tv_packageNum   .setText(String.valueOf(information.getPackageNum()));
                        }

                        tv_context      .setText(information.toString());
                        if (information.getDeviceNo() != 0){
                            tv_deviceNo     .setText(String.valueOf(information.getDeviceNo()));
                        }

                        if (!information.getCoord_type_input().equals("")){
                            gps_tpye        .setText(information.getCoord_type_input());
                        }

                        hint_gps_type   .setText("BD09");
                        if (!information.getMacAdd().equals("")){
                            hint_deviceNo   .setText(information.getMacAdd());
                        }

                        hint_indexNum   .setText("由1开始编号");
                        hint_longitude  .setText("GPS经度");
                        hint_latitude   .setText("GPS纬度");
                        hint_speed      .setText("单位为m/s");
                        hint_direction  .setText("0正北90正东-90正西180或-180正南");
                        if (!information.getTime().equals("")){
                            hint_timeNow    .setText(information.getTime());
                        }
                        hint_packageNum .setText("发送数据包的个数");

                        if (!ed_deviceName.getText().toString().equals("")){
                            information.setDeviceName(ed_deviceName.getText().toString());
                        }

                        try {
                            String deviceNo         = URLEncoder.encode(String.valueOf(information.getDeviceNo()),  "utf-8");
                            String indexNum         = URLEncoder.encode(String.valueOf(information.getIndexNum()), "utf-8");
                            String packageNum       = URLEncoder.encode(String.valueOf(information.getPackageNum()), "utf-8");
                            String macAdd           = URLEncoder.encode(information.getMacAdd(), "utf-8");
                            String speed            = URLEncoder.encode(String.valueOf(information.getSpeed()), "utf-8");
                            String timeNow          = URLEncoder.encode(String.valueOf(information.getTimeNow()), "utf-8");
                            String latitude         = URLEncoder.encode(String.valueOf(information.getLatitude()),"utf-8");
                            String longitude        = URLEncoder.encode(String.valueOf(information.getLongitude()), "utf-8");
                            String direction        = URLEncoder.encode(String.valueOf((int) information.getDirection()), "utf-8");
                            String coord_type_input = URLEncoder.encode(information.getCoord_type_input(), "utf-8");
                            String url = "http://118.24.19.160:8088/V2I/collect"
                                    + "?deviceNo="+deviceNo
                                    + "&indexNum="+indexNum
                                    + "&packageNum="+packageNum
                                    + "&macAdd="+macAdd
                                    + "&speed="+speed
                                    + "&timeNow="+timeNow
                                    + "&latitude="+latitude
                                    + "&longitude="+longitude
                                    + "&direction="+direction
                                    + "&coord_type_input="+coord_type_input;
                            webView.loadUrl(url);
                            log.append("发送成功\n"+url+"\n");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            Intent intent1 = new Intent();
                            intent.setAction(MainActivity.UPDATA_FAILED);
                            sendBroadcast(intent1);
                        }

                    }
                    break;
                case MainActivity.UPDATA_SUCCESS:
                    String result = (String) intent.getStringExtra(Information.IOFMATION);
                    tv_context.setText(result);
                    log.append(result);
                    break;
                case MainActivity.UPDATA_FAILED:
                    Toast.makeText(MainActivity.this, "发送信息失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
