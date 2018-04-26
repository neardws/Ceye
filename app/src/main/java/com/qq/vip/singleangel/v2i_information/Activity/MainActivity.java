package com.qq.vip.singleangel.v2i_information.Activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.qq.vip.singleangel.v2i_information.Tool.DBTool;
import com.qq.vip.singleangel.v2i_information.Tool.DataPackageTool;
import com.qq.vip.singleangel.v2i_information.Tool.FileTool;
import com.qq.vip.singleangel.v2i_information.GetInfo;
import com.qq.vip.singleangel.v2i_information.ClassDefined.Information;
import com.qq.vip.singleangel.v2i_information.R;

import org.xutils.x;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATA_UI        = "UPDATA_UI";
    public static final String UPDATA_LOG       = "UPDATA_LOG";
    public static final String UPDATA_SUCCESS   = "UPDATA_SUCCESS";
    public static final String UPDATA_FAILED    = "UPDATA_FAILED";
    public static final String SEND_MESSAGE     = "SEND_MESSAGE";
    public static final String START_SEND       = "START_SEND";
    public static final String STOP_SEND        = "STOP_SEND";

    /**
     * 百度Push
     */
    public static final String TYPE_ON_BIND                     = "type_on_bind";
    public static final String TYPE_ON_MESSAGE                  = "type_on_message";
    public static final String TYPE_ON_NotificationArrived      = "type_onNotificationArrived";
    public static final String TYPE_ON_NotificationClicked      = "type_onNotificationClicked";
    public static final String TYPE_ON_SET_TAGS                 = "type_on_set_tags";
    public static final String TYPE_ON_DEL_TAGS                 = "type_on_del_tags";
    public static final String TYPE_ON_LIST_TAGS                = "type_on_list_tags";
    public static final String TYPE_ON_UNBIND                   = "type_on_unbind";


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
         * 创建文件
         */
        final FileTool fileTool = new FileTool(getApplicationContext());
        fileTool.init();

        /**
         * 添加百度云推送
         */
       // PushSettings.enableDebugMode(getApplicationContext(), true);
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"GQCEClqBN0kluKXSVTPdrXZ0HX5b3VFS");

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

        /**
         *百度云推送
         */
        intentFilter.addAction(MainActivity.TYPE_ON_BIND);
        intentFilter.addAction(MainActivity.TYPE_ON_MESSAGE);

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

                    long nowTime = System.currentTimeMillis();
                    int timeHash = String.valueOf(nowTime).hashCode();
                    tv_deviceName.setText(String.valueOf(timeHash));

                    /**
                     * 结束发送包，将deviceName 保存到数据库
                     */
                    InsertPname(timeHash);

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
                    information.setIndexNum(information.getIndexNum()+1);
                    information.setIsEndofPackage(1);
                    Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                    intent1.setAction(DataPackageTool.THE_END_PACKAGE);
                    intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information);
                    startService(intent1);

                    log.append("已关闭服务\n");
                    Snackbar.make(view, "已关闭服务", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    fileTool.writeLog(log.getText().toString());
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
        }else if (id == R.id.action_da){
            /*Intent intent = new Intent(MainActivity.this, DAActivity.class);
            startActivity(intent);*/
            Intent intent = new Intent(MainActivity.this, DebugActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_delete){
            /*Intent pname = new Intent(MainActivity.this,DBTool.class);
            pname.setAction(DBTool.ACTION_DELETE);
            pname.putExtra(DBTool.TABLE_NAME,DBTool.TABLE_PACKAGE_NAME);
            startService(pname);
            Intent infor = new Intent(MainActivity.this,DBTool.class);
            pname.setAction(DBTool.ACTION_DELETE);
            pname.putExtra(DBTool.TABLE_NAME,DBTool.TABLE_INFORMATION);
            startService(infor);
            Intent control = new Intent(MainActivity.this,DBTool.class);
            pname.setAction(DBTool.ACTION_DELETE);
            pname.putExtra(DBTool.TABLE_NAME,DBTool.TABLE_CONTROL_MESSAGE);
            startService(control);
            Toast.makeText(getApplicationContext(),"初始化成功。",Toast.LENGTH_SHORT).show();*/
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
                        if (information.getDeviceNo() != ""){
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
                        /*log.append("时间戳："+information.getTimeNow()+"   时间："+information.getTime()+"\n");*/
                    }
                    break;
                case MainActivity.UPDATA_LOG:
                    String strLog = intent.getExtras().getString(MainActivity.UPDATA_LOG);
                    log.setMovementMethod(new ScrollingMovementMethod());
                    log.append(strLog+"\n");
                    break;
                case MainActivity.SEND_MESSAGE:

                    Information information1 = (Information) getInformation();
                    log.append("时间戳："+information1.getTimeNow()+"   时间："+information1.getTime()+"\n");
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
                case MainActivity.TYPE_ON_BIND:
                    String channelId = intent.getExtras().getString(MainActivity.TYPE_ON_BIND);
                    tv_deviceNo.setText(channelId);
                    /**
                     * 向服务器上传ChannelID
                     */
                    Information channelInformation = new Information();
                   channelInformation.setDeviceNo(channelId);

                    Intent channelIntent = new Intent(MainActivity.this, DataPackageTool.class);
                    channelIntent.setAction(DataPackageTool.CHANNEL_ID);
                    channelIntent.putExtra(DataPackageTool.PACKAGE_SIZE, channelInformation);
                    startService(channelIntent);
                    //Toast.makeText(context, channelId, Toast.LENGTH_SHORT).show();
                    //log.append("MainActivity.TYPE_ON_BIND"+channelId);
                    break;
                case MainActivity.TYPE_ON_MESSAGE:
                    String message = intent.getExtras().getString(MainActivity.TYPE_ON_MESSAGE);
                    /**
                     * 对 message 进行处理，存入数据库
                     */

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
            information.setDeviceNo(deviceNo);
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
        /*if (gps_tpye.getText() != null && !gps_tpye.getText().toString().equals("")){
            String coord_type_input = gps_tpye.getText().toString();
            information.setCoord_type_input(coord_type_input);
            //information.setCoord_type_input(Information.BDO9);
        }*/
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
        information.setCoord_type_input("bd09");
        return information;
    }

    private void InsertPname(int packageName){
        Intent pnameIntent = new Intent(MainActivity.this, DBTool.class);
        pnameIntent.setAction(DBTool.ACTION_ADD);
        pnameIntent.putExtra(DBTool.TABLE_NAME, DBTool.TABLE_PACKAGE_NAME);
        pnameIntent.putExtra(DBTool.PACKAGE_NAME, packageName);
        startService(pnameIntent);
    }

}
