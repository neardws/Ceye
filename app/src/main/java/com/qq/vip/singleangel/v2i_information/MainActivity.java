package com.qq.vip.singleangel.v2i_information

import android.app.ActivityManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkRequest
import android.net.Uri
import android.os.Bundle
import android.support.design.internal.TextScale
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.ObjectOutputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.TimeZone

import java.lang.System.`in`
import java.net.Proxy.Type.HTTP

class MainActivity : AppCompatActivity() {

    private var broadcastReceiver: BroadcastReceiver? = null
    private val intentFilter = IntentFilter()

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

    var frequency: String? = "100ms"
    var packageSize: String? = "40B"

    private var log: TextView? = null

    private var tv_deviceNo: TextView? = null
    private var tv_indexNum: TextView? = null
    private var tv_longitude: TextView? = null
    private var tv_latitude: TextView? = null
    private var tv_speed: TextView? = null
    private var tv_direction: TextView? = null
    private var tv_timeNow: TextView? = null
    private var tv_packageNum: TextView? = null
    private var tv_context: TextView? = null
    private var gps_tpye: TextView? = null
    private var hint_gps_type: TextView? = null

    private var hint_deviceNo: TextView? = null
    private var hint_indexNum: TextView? = null
    private var hint_longitude: TextView? = null
    private var hint_latitude: TextView? = null
    private var hint_speed: TextView? = null
    private var hint_direction: TextView? = null
    private var hint_timeNow: TextView? = null
    private var hint_packageNum: TextView? = null


    private var tv_deviceName: TextView? = null
    private var is_End_of_Package: TextView? = null
    /*    private EditText ed_deviceName;
    private EditText ed_frequency;*/
    private var spin_frequency: Spinner? = null
    private var arrayAdapter: ArrayAdapter<*>? = null

    private var spin_package_size: Spinner? = null
    private var arraySize: ArrayAdapter<*>? = null

    private//information.setCoord_type_input(Information.BDO9);
    val information: Information
        get() {
            val information = Information()
            if (tv_deviceNo!!.text != null && tv_deviceNo!!.text.toString() != "") {
                val deviceNo = tv_deviceNo!!.text.toString()
                information.deviceNo = Integer.valueOf(deviceNo)
            }
            if (tv_indexNum!!.text != null && tv_indexNum!!.text.toString() != "") {
                val indexNum = tv_indexNum!!.text.toString()
                information.indexNum = Integer.valueOf(indexNum)
            }
            if (tv_packageNum!!.text != null && tv_packageNum!!.text.toString() != "") {
                val packageNum = tv_packageNum!!.text.toString()
                information.packageNum = java.lang.Long.valueOf(packageNum)
            }
            if (hint_deviceNo!!.text != null && hint_deviceNo!!.text.toString() != "") {
                val macAdd = hint_deviceNo!!.text.toString()
                information.macAdd = macAdd
            }
            if (tv_speed!!.text != null && tv_speed!!.text.toString() != "") {
                val speed = tv_speed!!.text.toString()
                information.speed = java.lang.Float.valueOf(speed)
            }
            if (tv_timeNow!!.text != null && tv_timeNow!!.text.toString() != "") {
                val timeNow = tv_timeNow!!.text.toString()
                information.timeNow = java.lang.Long.valueOf(timeNow)
            }
            if (tv_latitude!!.text != null && tv_latitude!!.text.toString() != "") {
                val latitude = tv_latitude!!.text.toString()
                information.latitude = java.lang.Double.valueOf(latitude)
            }
            if (tv_longitude!!.text != null && tv_longitude!!.text.toString() != "") {
                val longitude = tv_longitude!!.text.toString()
                information.longitude = java.lang.Double.valueOf(longitude)
            }
            if (tv_direction!!.text != null && tv_direction!!.text.toString() != "") {
                val direction = tv_direction!!.text.toString()
                information.direction = java.lang.Float.valueOf(direction)
            }
            if (gps_tpye!!.text != null && gps_tpye!!.text.toString() != "") {
                val coord_type_input = gps_tpye!!.text.toString()
                information.coord_type_input = coord_type_input
            }
            if (frequency != "") {
                information.frequency = frequency
            }
            if (packageSize != "") {
                information.packageSize = packageSize
            }
            if (tv_deviceName!!.text != null && tv_deviceName!!.text.toString() != "") {
                val packageName = tv_deviceName!!.text.toString()
                information.packageName = Integer.valueOf(packageName)
            }
            return information
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 对xutils3.5进行初始化
         */
        x.Ext.init(application)
        x.Ext.setDebug(true)

        intentFilter.addAction(MainActivity.UPDATA_UI)
        intentFilter.addAction(MainActivity.UPDATA_SUCCESS)
        intentFilter.addAction(MainActivity.UPDATA_FAILED)
        intentFilter.addAction(MainActivity.START_SEND)
        intentFilter.addAction(MainActivity.STOP_SEND)
        intentFilter.addAction(MainActivity.SEND_MESSAGE)
        intentFilter.addAction(MainActivity.UPDATA_LOG)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        log = findViewById(R.id.log) as TextView
        log!!.movementMethod = ScrollingMovementMethod()

        tv_deviceNo = findViewById(R.id.con_deviceNo) as TextView
        tv_indexNum = findViewById(R.id.con_indexNum) as TextView
        tv_longitude = findViewById(R.id.con_longitude) as TextView
        tv_latitude = findViewById(R.id.con_latitude) as TextView
        tv_speed = findViewById(R.id.con_speed) as TextView
        tv_direction = findViewById(R.id.con_direction) as TextView
        tv_timeNow = findViewById(R.id.con_timeNow) as TextView
        tv_packageNum = findViewById(R.id.con_packageNum) as TextView
        tv_context = findViewById(R.id.context) as TextView
        gps_tpye = findViewById(R.id.gps_type) as TextView
        hint_gps_type = findViewById(R.id.hint_gps_type) as TextView

        hint_deviceNo = findViewById(R.id.hint_deviceNo) as TextView
        hint_indexNum = findViewById(R.id.hint_indexNum) as TextView
        hint_longitude = findViewById(R.id.hint_longitude) as TextView
        hint_latitude = findViewById(R.id.hint_latitude) as TextView
        hint_speed = findViewById(R.id.hint_speed) as TextView
        hint_direction = findViewById(R.id.hint_direction) as TextView
        hint_timeNow = findViewById(R.id.hint_timeNow) as TextView
        hint_packageNum = findViewById(R.id.hint_packageNum) as TextView

        tv_deviceName = findViewById(R.id.tv_deviceName) as TextView
        is_End_of_Package = findViewById(R.id.isEndofPackage) as TextView

        /*        ed_deviceName = (EditText) findViewById(R.id.edit_deviceName);
        ed_frequency = (EditText) findViewById(R.id.ed_frequency);*/
        spin_frequency = findViewById(R.id.spin_frequency) as Spinner
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.frequency,
                android.R.layout.simple_spinner_item)
        arrayAdapter!!.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item)
        spin_frequency!!.adapter = arrayAdapter
        spin_frequency!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val freq = arrayAdapter!!.getItem(position) as String
                if (freq != null) {
                    frequency = freq
                    Toast.makeText(applicationContext, "发送频率$freq  任务开始后请勿更改.", Toast.LENGTH_SHORT).show()
                } else {

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        spin_package_size = findViewById(R.id.spin_package_size) as Spinner
        arraySize = ArrayAdapter.createFromResource(this, R.array.package_size,
                android.R.layout.simple_spinner_item)
        spin_package_size!!.adapter = arraySize
        spin_package_size!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val size = arraySize!!.getItem(position) as String
                if (size != null) {
                    packageSize = size
                    Toast.makeText(applicationContext, "数据包大小$size  任务开始后请勿更改.", Toast.LENGTH_SHORT).show()
                } else {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setImageResource(R.drawable.ic_send_black_24dp)
        fab.setOnClickListener { view ->
            if (!isServiceRunning(applicationContext, "com.qq.vip.singleangel.v2i_information.GetInfo")) {
                log!!.text = ""
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
                val nowTime = System.currentTimeMillis()
                val timeHash = nowTime.toString().hashCode()
                tv_deviceName!!.text = timeHash.toString()

                val intent = Intent(this@MainActivity, GetInfo::class.java)
                intent.putExtra(GetInfo.FREQUENCY, frequency)
                startService(intent)
                fab.setImageResource(R.drawable.ic_clear_black_24dp)
                log!!.movementMethod = ScrollingMovementMethod()
                log!!.append("已开启服务\n")
                Snackbar.make(view, "已开启服务", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            } else {
                val intent = Intent(this@MainActivity, GetInfo::class.java)
                stopService(intent)
                fab.setImageResource(R.drawable.ic_send_black_24dp)
                log!!.movementMethod = ScrollingMovementMethod()

                /**
                 * 发送最后一个包
                 */

                /**
                 * 发送最后一个包
                 */
                is_End_of_Package!!.text = "1"
                val information = information
                information.setIsEndofPackage(1)
                information.isEndofPackage = 1
                val intent1 = Intent(this@MainActivity, DataPackageTool::class.java)
                intent1.action = DataPackageTool.THE_END_PACKAGE
                intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information)
                startService(intent1)

                log!!.append("已关闭服务\n")
                Snackbar.make(view, "已关闭服务", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        }

    }


    override fun onResume() {
        super.onResume()
        broadcastReceiver = ReactionBroadcast()
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_aboutme) {
            val intent = Intent(this@MainActivity, AboutMeActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * 接收更新UI的广播
     */
    inner class ReactionBroadcast : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            when (action) {
                MainActivity.UPDATA_UI -> {
                    //Toast.makeText(MainActivity.this, "更新信息", Toast.LENGTH_SHORT).show();
                    val information = intent.getSerializableExtra(Information.IOFMATION) as Information

                    if (information != null) {
                        if (information.indexNum != 0) {
                            tv_indexNum!!.text = information.indexNum.toString()
                        }
                        if (information.longitude != 0.0) {
                            tv_longitude!!.text = information.longitude.toString()
                        }
                        if (information.latitude != 0.0) {
                            tv_latitude!!.text = information.latitude.toString()
                        }
                        if (tv_speed!!.text.toString() == "") {  //speed为空
                            tv_speed!!.text = information.speed.toString()
                        } else {     //speed不为空
                            if (information.speed != 0f) {
                                tv_speed!!.text = information.speed.toString()
                            }
                        }

                        if (information.direction != 0f) {
                            tv_direction!!.text = information.direction.toString()
                        }
                        if (information.timeNow != 0L) {
                            tv_timeNow!!.text = information.timeNow.toString()
                        }
                        if (information.packageNum != 0L) {
                            tv_packageNum!!.text = information.packageNum.toString()
                        }

                        tv_context!!.text = information.toString()
                        if (information.deviceNo != 0) {
                            tv_deviceNo!!.text = information.deviceNo.toString()
                        }

                        if (information.coord_type_input != "") {
                            gps_tpye!!.text = information.coord_type_input
                        }
                        if (information.baiduErrorCode != "") {
                            hint_gps_type!!.text = information.baiduErrorCode
                        }
                        if (information.macAdd != "") {
                            hint_deviceNo!!.text = information.macAdd
                        }
                        is_End_of_Package!!.text = "0"

                        hint_indexNum!!.text = "由1开始编号"
                        hint_longitude!!.text = "GPS经度"
                        hint_latitude!!.text = "GPS纬度"
                        hint_speed!!.text = "单位为m/s"
                        hint_direction!!.text = "0正北90正东-90正西180或-180正南"
                        if (information.time != "") {
                            hint_timeNow!!.text = information.time
                        }
                        hint_packageNum!!.text = "发送数据包的个数"

                    }
                }
                MainActivity.UPDATA_LOG -> {
                    val strLog = intent.extras.getString(MainActivity.UPDATA_LOG)
                    log!!.movementMethod = ScrollingMovementMethod()
                    log!!.append(strLog!! + "\n")
                }
                MainActivity.SEND_MESSAGE -> {
                    /*Information information1 = (Information) getInformation();
                    String url = "http://118.24.19.160:8088/V2I/collect";
                    sendPost(url, information1);*/

                    /*Intent intent1 = new Intent(MainActivity.this, DataPackageTool.class);
                    intent1.setAction(DataPackageTool.THE_END_PACKAGE);
                    intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information);
                    startService(intent1);*/

                    val information1 = information
                    if (packageSize == MainActivity._40B) {
                        val intent1 = Intent(this@MainActivity, DataPackageTool::class.java)
                        intent1.action = DataPackageTool._40B
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1)
                        startService(intent1)
                    } else if (packageSize == MainActivity._100B) {
                        val intent1 = Intent(this@MainActivity, DataPackageTool::class.java)
                        intent1.action = DataPackageTool._100B
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1)
                        startService(intent1)
                    } else if (packageSize == MainActivity._500B) {
                        val intent1 = Intent(this@MainActivity, DataPackageTool::class.java)
                        intent1.action = DataPackageTool._500B
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1)
                        startService(intent1)
                    } else if (packageSize == MainActivity._1KB) {
                        val intent1 = Intent(this@MainActivity, DataPackageTool::class.java)
                        intent1.action = DataPackageTool._1KB
                        intent1.putExtra(DataPackageTool.PACKAGE_SIZE, information1)
                        startService(intent1)
                    } else {
                        Toast.makeText(this@MainActivity, "数据包大小不匹配", Toast.LENGTH_SHORT).show()
                    }
                }
                MainActivity.UPDATA_SUCCESS -> {
                    val result = intent.getStringExtra(Information.IOFMATION) as String
                    tv_context!!.text = result
                    log!!.movementMethod = ScrollingMovementMethod()
                    log!!.append(result)
                }
                MainActivity.UPDATA_FAILED -> Toast.makeText(this@MainActivity, "发送信息失败", Toast.LENGTH_SHORT).show()
                else -> {
                }
            }
        }
    }

    companion object {

        val UPDATA_UI = "UPDATA_UI"
        val UPDATA_LOG = "UPDATA_LOG"
        val UPDATA_SUCCESS = "UPDATA_SUCCESS"
        val UPDATA_FAILED = "UPDATA_FAILED"
        val SEND_MESSAGE = "SEND_MESSAGE"
        val START_SEND = "START_SEND"
        val STOP_SEND = "STOP_SEND"

        val _40B = "40B"
        val _100B = "100B"
        val _500B = "500B"
        val _1KB = "1KB"

        /**
         * 查看是否有服务正在运行
         * @param context
         * @param serviceName
         * @return
         */
        fun isServiceRunning(context: Context, serviceName: String): Boolean {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningServiceInfos = am.getRunningServices(200)
            if (runningServiceInfos.size <= 0) {
                return false
            }
            for (serviceInfo in runningServiceInfos) {
                if (serviceInfo.service.className == serviceName) {
                    return true
                }
            }
            return false
        }
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
