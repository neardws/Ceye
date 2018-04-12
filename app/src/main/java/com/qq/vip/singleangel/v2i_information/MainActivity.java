package com.qq.vip.singleangel.v2i_information;

import android.app.ActivityManager;
import android.app.Service;
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
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.lang.System.in;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATA_UI        = "UPDATA_UI";
    public static final String UPDATA_LOG       = "UPDATA_LOG";
    public static final String UPDATA_SUCCESS   = "UPDATA_SUCCESS";
    public static final String UPDATA_FAILED    = "UPDATA_FAILED";
    public static final String SEND_MESSAGE     = "SEND_MESSAGE";
    public static final String START_SEND       = "START_SEND";
    public static final String STOP_SEND        = "STOP_SEND";

    public static final String _40B     = "40B";
    public static final String _100B    = "100B";
    public static final String _500B    = "500B";
    public static final String _1KB     = "1KB";

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

    private String frequency    = "100ms";
    private String packageSize  = "40B";

    private TextView log;

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


    private TextView tv_deviceName;
    private TextView is_End_of_Package;
/*    private EditText ed_deviceName;
    private EditText ed_frequency;*/
    private Spinner  spin_frequency;
    private ArrayAdapter arrayAdapter;

    private Spinner spin_package_size;
    private ArrayAdapter arraySize;

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageSize() {
        return packageSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *对xutils3.5进行初始化
         */
        x.Ext.init(getApplication());
        x.Ext.setDebug(true);

        intentFilter.addAction(MainActivity.UPDATA_UI);
        intentFilter.addAction(MainActivity.UPDATA_SUCCESS);
        intentFilter.addAction(MainActivity.UPDATA_FAILED);
        intentFilter.addAction(MainActivity.START_SEND);
        intentFilter.addAction(MainActivity.STOP_SEND);
        intentFilter.addAction(MainActivity.SEND_MESSAGE);
        intentFilter.addAction(MainActivity.UPDATA_LOG);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        log             = (TextView) findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());

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

        tv_deviceName       = (TextView) findViewById(R.id.tv_deviceName);
        is_End_of_Package   = (TextView) findViewById(R.id.isEndofPackage);

/*        ed_deviceName = (EditText) findViewById(R.id.edit_deviceName);
        ed_frequency = (EditText) findViewById(R.id.ed_frequency);*/
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
                    setFrequency(freq);
                    Toast.makeText(getApplicationContext(), "发送频率" + freq +"  任务开始后请勿更改.",Toast.LENGTH_SHORT).show();
                }else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin_package_size = (Spinner) findViewById(R.id.spin_package_size);
        arraySize = ArrayAdapter.createFromResource(this, R.array.package_size,
                    android.R.layout.simple_spinner_item);
        spin_package_size.setAdapter(arraySize);
        spin_package_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String size = (String) arraySize.getItem(position);
                if (size != null){
                    setPackageSize(size);
                    Toast.makeText(getApplicationContext(), "数据包大小" + size +"  任务开始后请勿更改.",Toast.LENGTH_SHORT).show();
                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_send_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isServiceRunning(getApplicationContext(),"com.qq.vip.singleangel.v2i_information.GetInfo")){
                    log.setText("");
                    /*if (!ed_frequency.getText().toString().equals("")){
                        try {
                            frequency = ed_frequency.getText().toString());
                        }catch (NumberFormatException e){
                            Toast.makeText(MainActivity.this, "格式错误，请输入数字", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "没有输入信息，默认使用下拉框中内容", Toast.LENGTH_SHORT).show();
                        frequency = getFrequency();
                    }*/
                    long nowTime = System.currentTimeMillis();
                    int timeHash = String.valueOf(nowTime).hashCode();
                    tv_deviceName.setText(String.valueOf(timeHash));

                    Intent intent = new Intent(MainActivity.this, GetInfo.class);
                    intent.putExtra(GetInfo.FREQUENCY, getFrequency());
                    startService(intent);
                    fab.setImageResource(R.drawable.ic_clear_black_24dp);
                    log.setMovementMethod(new ScrollingMovementMethod());
                    log.append("已开启服务\n");
                    Snackbar.make(view, "已开启服务", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, GetInfo.class);
                    stopService(intent);
                    fab.setImageResource(R.drawable.ic_send_black_24dp);
                    log.setMovementMethod(new ScrollingMovementMethod());

                    /**
                     * 发送最后一个包
                     */
                    is_End_of_Package.setText("1");
                    Information information = getInformation();
                    information.setIsEndofPackage(1);
                    Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                    intent1.setAction(DataPackageTool.THE_END_PACKAGE);
                    intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information);
                    startService(intent1);

                    log.append("已关闭服务\n");
                    Snackbar.make(view, "已关闭服务", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

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
        if (id == R.id.action_aboutme) {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 查看是否有服务正在运行
     * @param context
     * @param serviceName
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
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
                    //Toast.makeText(MainActivity.this, "更新信息", Toast.LENGTH_SHORT).show();
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
                        if (tv_speed.getText().toString().equals("")){  //speed为空
                            tv_speed        .setText(String.valueOf(information.getSpeed()));
                        }else {     //speed不为空
                            if (information.getSpeed() != 0){
                                tv_speed        .setText(String.valueOf(information.getSpeed()));
                            }
                        }

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
                        if (!information.getBaiduErrorCode().equals("")){
                            hint_gps_type   .setText(information.getBaiduErrorCode());
                        }
                        if (!information.getMacAdd().equals("")){
                            hint_deviceNo   .setText(information.getMacAdd());
                        }
                        is_End_of_Package.setText("0");

                        hint_indexNum   .setText("由1开始编号");
                        hint_longitude  .setText("GPS经度");
                        hint_latitude   .setText("GPS纬度");
                        hint_speed      .setText("单位为m/s");
                        hint_direction  .setText("0正北90正东-90正西180或-180正南");
                        if (!information.getTime().equals("")){
                            hint_timeNow    .setText(information.getTime());
                        }
                        hint_packageNum .setText("发送数据包的个数");

                    }
                    break;
                case MainActivity.UPDATA_LOG:
                    String strLog = intent.getExtras().getString(MainActivity.UPDATA_LOG);
                    log.setMovementMethod(new ScrollingMovementMethod());
                    log.append(strLog+"\n");
                    break;
                case MainActivity.SEND_MESSAGE:
                    /*Information information1 = (Information) getInformation();
                    String url = "http://118.24.19.160:8088/V2I/collect";
                    sendPost(url, information1);*/

                    /*Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                    intent1.setAction(DataPackageTool.THE_END_PACKAGE);
                    intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information);
                    startService(intent1);*/

                    Information information1 = (Information) getInformation();
                    if (getPackageSize().equals(MainActivity._40B)){
                        Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                        intent1.setAction(DataPackageTool._40B);
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1);
                        startService(intent1);
                    }else if (getPackageSize().equals(MainActivity._100B)){
                        Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                        intent1.setAction(DataPackageTool._100B);
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1);
                        startService(intent1);
                    }else if (getPackageSize().equals(MainActivity._500B)){
                        Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                        intent1.setAction(DataPackageTool._500B);
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1);
                        startService(intent1);
                    }else if (getPackageSize().equals(MainActivity._1KB)){
                        Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                        intent1.setAction(DataPackageTool._1KB);
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1);
                        startService(intent1);
                    }else {
                        Toast.makeText(MainActivity.this, "数据包大小不匹配", Toast.LENGTH_SHORT).show();
                    }


                    break;
                case MainActivity.UPDATA_SUCCESS:
                    String result = (String) intent.getStringExtra(Information.IOFMATION);
                    tv_context.setText(result);
                    log.setMovementMethod(new ScrollingMovementMethod());
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

    private  Information getInformation(){
        Information information = new Information();
        if (tv_deviceNo.getText()!= null && !tv_deviceNo.getText().toString().equals("")){
            String deviceNo         = tv_deviceNo.getText().toString();
            information.setDeviceNo(Integer.valueOf(deviceNo));
        }
        if (tv_indexNum.getText() != null && !tv_indexNum.getText().toString().equals("")){
            String indexNum         = tv_indexNum.getText().toString();
            information.setIndexNum(Integer.valueOf(indexNum));
        }
        if (tv_packageNum.getText() != null && !tv_packageNum.getText().toString().equals("")){
            String packageNum       = tv_packageNum.getText().toString();
            information.setPackageNum(Long.valueOf(packageNum));
        }
        if (hint_deviceNo.getText() != null && !hint_deviceNo.getText().toString().equals("")){
            String macAdd           = hint_deviceNo.getText().toString();
            information.setMacAdd(macAdd);
        }
        if (tv_speed.getText() != null && !tv_speed.getText().toString().equals("")){
            String speed            = tv_speed.getText().toString();
            information.setSpeed(Float.valueOf(speed));
        }
        if (tv_timeNow.getText() != null && !tv_timeNow.getText().toString().equals("")){
            String timeNow          = tv_timeNow.getText().toString();
            information.setTimeNow(Long.valueOf(timeNow));
        }
        if (tv_latitude.getText() != null && !tv_latitude.getText().toString().equals("")){
            String latitude         = tv_latitude.getText().toString();
            information.setLatitude(Double.valueOf(latitude));
        }
        if (tv_longitude.getText() != null && !tv_longitude.getText().toString().equals("")){
            String longitude        = tv_longitude.getText().toString();
            information.setLongitude(Double.valueOf(longitude));
        }
        if (tv_direction.getText() != null && !tv_direction.getText().toString().equals("")){
            String direction        = tv_direction.getText().toString();
            information.setDirection(Float.valueOf(direction));
        }
        if (gps_tpye.getText() != null && !gps_tpye.getText().toString().equals("")){
            String coord_type_input = gps_tpye.getText().toString();
            information.setCoord_type_input(coord_type_input);
            //information.setCoord_type_input(Information.BDO9);
        }
        if (!frequency.equals("")){
            information.setFrequency(frequency);
        }
        if (!packageSize.equals("")){
            information.setPackageSize(packageSize);
        }
        if (tv_deviceName.getText() != null && !tv_deviceName.getText().toString().equals("")){
            String packageName = tv_deviceName.getText().toString();
            information.setPackageName(Integer.valueOf(packageName));
        }
        return information;
    }

    /**
     *
     * @param url
     * @param information
     *//*
    private void sendPost(String url, Information information) {
        final String TAG = "sendPost";
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("deviceNo",String.valueOf(information.getDeviceNo()));
        params.addBodyParameter("indexNum",String.valueOf(information.getIndexNum()));
        params.addBodyParameter("packageNum",String.valueOf(information.getPackageNum()));
        params.addBodyParameter("macAdd",information.getMacAdd());
        params.addBodyParameter("speed",String.valueOf(information.getSpeed()));
        params.addBodyParameter("timeNow",String.valueOf(information.getTimeNow()));
        params.addBodyParameter("latitude",String.valueOf(information.getLatitude()));
        params.addBodyParameter("longitude",String.valueOf(information.getLongitude()));
        params.addBodyParameter("direction",String.valueOf((int) information.getDirection()));
        params.addBodyParameter("coord_type_input",information.getCoord_type_input());

        String urlog = "http://118.24.19.160:8088/V2I/collect"
                + "?deviceNo="+String.valueOf(information.getDeviceNo())
                + "&indexNum="+String.valueOf(information.getIndexNum())
                + "&packageNum="+String.valueOf(information.getPackageNum())
                + "&macAdd="+information.getMacAdd()
                + "&speed="+String.valueOf(information.getSpeed())
                + "&timeNow="+String.valueOf(information.getTimeNow())
                + "&latitude="+String.valueOf(information.getLatitude())
                + "&longitude="+String.valueOf(information.getLongitude())
                + "&direction="+String.valueOf((int) information.getDirection())
                + "&coord_type_input="+information.getCoord_type_input();
        log.setMovementMethod(new ScrollingMovementMethod());
        log.append(urlog + "\n");

        x.http().post(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess: "+result);
                log.setMovementMethod(new ScrollingMovementMethod());
                log.append("发送成功\n"+result+"\n");
                //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "onError: "+ex.toString());
                log.setMovementMethod(new ScrollingMovementMethod());
                log.append("发送失败\n"+ex.toString()+"\n");
                //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                Log.i(TAG, "onCancelled: "+cex.toString());
            }

            @Override
            public void onFinished() {
                Log.i(TAG, "onFinished: ");
            }

            @Override
            public boolean onCache(String result) {
                Log.i(TAG, "onCache: "+result);
                return false;
            }
        });

    }*/



}
