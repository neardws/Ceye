package com.qq.vip.singleangel.v2i_information.Tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.qq.vip.singleangel.v2i_information.ClassDefined.Information;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by singl on 2018/3/30.
 */

public class InfoTool {
    public static final String TAG = "InfoTool";

    private Information information;
    private WifiManager wifiManager;
    private SensorManager sensorManager;

    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    private Context context;
    /**
     * accelerometer    加速度传感器
     * magnetic_field   磁场传感器
     *
     */
    private Sensor accelerometer;
    private Sensor magnetic_field;

    private float[] accelerometerValues = new float[3];
    private float[] magneticFieldValues = new float[3];

    public InfoTool(Context context) {
        init(context);
    }

    /**
     * 初始化
     */
    @SuppressLint("WifiManagerLeak")
    private void init(Context context) {
        this.context = context;
        information = new Information();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        sensorManager = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        /**
         * 初始传感器
         */
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic_field = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(new MySensorEventListener(),
                accelerometer, Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new MySensorEventListener(),
                magnetic_field, Sensor.TYPE_MAGNETIC_FIELD);

    }

    /**
     * 注销服务
     */
    public void uninit(){
        sensorManager.unregisterListener(new MySensorEventListener());

        resetInfor();
    }

    public Information getInfo(){
        /**
         * 获取信息
         * 并通知Activity 进行显示
         */
        setInfor();
        getMac();                           //获得MAC地址
        getTimeNow();                       //获得时间
        calculateOrientation();             //获得方向
        return information;
    }

    private void resetInfor() {
        information = new Information();
    }


    private void setInfor() {
        information.setIndexNum(information.getIndexNum() + 1);
        information.setPackageNum(information.getPackageNum() + 1);
    }

    /**
     * 获取时间
     * 获取更精确时间，精确到毫秒
     */
    private void getTimeNow(){
        syncTime();
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String time = dff.format(new Date());
        information.setTime(time);
        //information.setTimeNow(System.currentTimeMillis()/1000);
        information.setTimeNow(System.currentTimeMillis());
    }

    /**
     * +
     * 获取时间
     * 获取更精确时间，精确到纳秒
     */
    private void getTimeNowNanoTime(){
        syncTime();
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String time = dff.format(new Date());
        information.setTime(time);
        //information.setTimeNow(System.currentTimeMillis()/1000);
        information.setTimeNow(System.nanoTime());
    }

    private void syncTime(){
        SntpClient sntpClient = new SntpClient();
        if (sntpClient.requestTime(SntpClient.ALI, SntpClient.TIMEOUT)){
            long time = sntpClient.getNtpTime();
            //SystemClock.setCurrentTimeMillis(time);//设置系统时间
            setCurrentTimeMillis(time);
        }else if (sntpClient.requestTime(SntpClient.SJTU, SntpClient.TIMEOUT)){
            long time = sntpClient.getNtpTime();
            //SystemClock.setCurrentTimeMillis(time);//设置系统时间
            setCurrentTimeMillis(time);
        }else {
            Log.d(TAG, "同步时间失败");
        }
    }

    /**
     * 设置当前的系统时间
     *
     * @param time
     * @return true表示设置成功, false表示设置失败
     */
    public boolean setCurrentTimeMillis(long time) {
        try {
            if (ShellUtils.checkRootPermission()) {
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                Date current = new Date(time);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd.HHmmssfff");
                String datetime = df.format(current);
                Process process = Runtime.getRuntime().exec("su");
                DataOutputStream os = new DataOutputStream(process.getOutputStream());
                //os.writeBytes("setprop persist.sys.timezone GMT\n");
                os.writeBytes("/system/bin/date -s " + datetime + "\n");
                os.writeBytes("clock -w\n");
                os.writeBytes("exit\n");
                os.flush();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }



    /**
     * 计算方向
     * 0为正北
     * 90为正东
     * -90为正西
     * 180或-180为正南
     * @return
     */
    private boolean calculateOrientation() {
        boolean isSuccess = true;
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null, accelerometerValues,
                magneticFieldValues);
        SensorManager.getOrientation(R, values);
        values[0] = (float) Math.toDegrees(values[0]);
        information.setDirection(values[0]);


        /*if (values[0] >= -5 && values[0] < 5) {
            azimuthAngle.setText("正北");
        } else if (values[0] >= 5 && values[0] < 85) {
            // Log.i(TAG, "东北");
            azimuthAngle.setText("东北");
        } else if (values[0] >= 85 && values[0] <= 95) {
            // Log.i(TAG, "正东");
            azimuthAngle.setText("正东");
        } else if (values[0] >= 95 && values[0] < 175) {
            // Log.i(TAG, "东南");
            azimuthAngle.setText("东南");
        } else if ((values[0] >= 175 && values[0] <= 180)
                || (values[0]) >= -180 && values[0] < -175) {
            // Log.i(TAG, "正南");
            azimuthAngle.setText("正南");
        } else if (values[0] >= -175 && values[0] < -95) {
            // Log.i(TAG, "西南");
            azimuthAngle.setText("西南");
        } else if (values[0] >= -95 && values[0] < -85) {
            // Log.i(TAG, "正西");
            azimuthAngle.setText("正西");
        } else if (values[0] >= -85 && values[0] < -5) {
            // Log.i(TAG, "西北");
            azimuthAngle.setText("西北");
        }*/
        return isSuccess;

    }

    private class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticFieldValues = event.values;
            }
            calculateOrientation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

    }


    /**
     * 获取MAC地址，并根据MAC地址的哈希值获得设备号
     */
    private void getMac(){
        String macAddress = null;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null &&
                marshmallowMacAddress.equals(wifiInfo.getMacAddress())){
            try {
                macAddress = getAdressMacByInterface();
                if (macAddress != null){

                }else {
                    macAddress = getAddressMacByFile(wifiManager);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            if (wifiInfo != null && wifiInfo.getMacAddress() != null){
                macAddress = wifiInfo.getMacAddress();
            }else {
                macAddress = marshmallowMacAddress;
            }
        }
        int deviceNo = macAddress.hashCode();
        information.setDeviceNo(deviceNo);
        information.setMacAdd(macAddress);
    }

    private static String getAdressMacByInterface(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:",b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();

        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();

        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }

}
