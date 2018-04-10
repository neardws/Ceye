package com.qq.vip.singleangel.v2i_information;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by singl on 2018/3/25.
 */

public class GetInfo extends Service {
    private LocationManager locationManager;
    private Information information;
    private Context context;
    public static final String FREQUENCY        = "FREQUENCY";

    public LocationClient mLocationClient = null;
    private BaiduLocationListener myListener = new BaiduLocationListener();

    TimerThread timerThread1;
    TimerThread timerThread3;
    TimerThread timerThread5;
    TimerThread timerThread10;
    TimerThread timerThread15;
    TimerThread timerThread20;
    TimerThread timerThread25;
    TimerThread timerThread30;
    TimerThread timerThread35;
    TimerThread timerThread40;
    TimerThread timerThread45;
    TimerThread timerThread50;
    TimerThread timerThread55;
    TimerThread timerThread60;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        information = new Information();
        /** 使用手机GPS定位
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        setGPS(information);
        */
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false


        option.setWifiCacheTimeOut(5*60*1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false


        mLocationClient.setLocOption(option);
        mLocationClient.start();
        Toast.makeText(getApplicationContext(), "百度定位服务开始运行", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_LOG);
        intent.putExtra(MainActivity.UPDATA_LOG,"百度定位服务开始运行");
        sendBroadcast(intent);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Context context = getApplicationContext();
        super.onStart(intent, startId);
        if (intent != null){
            if (isThreadRun()){
                Toast.makeText(context, "还用线程正在运行，请按停止按钮结束进程。", Toast.LENGTH_LONG).show();
            }else {

                int frequency = intent.getExtras().getInt(GetInfo.FREQUENCY);
                switch (frequency){
                    case 1:
                        timerThread1 = new TimerThread(context, 1);
                        timerThread1.start();
                        break;
                    case 3:
                        timerThread3 = new TimerThread(context, 3);
                        timerThread3.start();
                        break;
                    case 5:
                        timerThread5 = new TimerThread(context, 5);
                        timerThread5.start();
                        break;
                    case 10:
                        timerThread10 = new TimerThread(context, 10);
                        timerThread10.start();
                        break;
                    case 15:
                        timerThread15 = new TimerThread(context, 15);
                        timerThread15.start();
                        break;
                    case 20:
                        timerThread20 = new TimerThread(context, 20);
                        timerThread20.start();
                        break;
                    case 25:
                        timerThread25 = new TimerThread(context, 25);
                        timerThread25.start();
                        break;
                    case 30:
                        timerThread30 = new TimerThread(context, 30);
                        timerThread30.start();
                        break;
                    case 35:
                        timerThread35 = new TimerThread(context, 35);
                        timerThread35.start();
                        break;
                    case 40:
                        timerThread40 = new TimerThread(context, 40);
                        timerThread40.start();
                        break;
                    case 45:
                        timerThread45 = new TimerThread(context, 45);
                        timerThread45.start();
                        break;
                    case 50:
                        timerThread50 = new TimerThread(context, 50);
                        timerThread50.start();
                        break;
                    case 55:
                        timerThread55 = new TimerThread(context, 55);
                        timerThread55.start();
                        break;
                    case 60:
                        timerThread60 = new TimerThread(context, 60);
                        timerThread60.start();
                        break;
                    default:
                        timerThread5 = new TimerThread(context, 5);
                        timerThread5.start();
                        break;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //locationManager.removeUpdates(new MobileGPSLocationListener());
        if (mLocationClient.isStarted()){
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient.stop();
            Toast.makeText(getApplicationContext(), "百度定位服务已关闭", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(MainActivity.UPDATA_LOG);
            intent.putExtra(MainActivity.UPDATA_LOG,"百度定位服务已关闭");
            sendBroadcast(intent);
        }
       // mLocationClient.stop();

        if (isThreadRun()){
            if (timerThread1 != null){
                timerThread1.stopThread();
            }else if (timerThread3 != null){
                timerThread3.stopThread();
            }else if (timerThread5 != null){
                timerThread5.stopThread();
            }else if (timerThread10 != null){
                timerThread10.stopThread();
            }else if (timerThread15 != null){
                timerThread15.stopThread();
            }else if (timerThread20 != null){
                timerThread20.stopThread();
            }else if (timerThread25 != null){
                timerThread25.stopThread();
            }else if (timerThread30 != null){
                timerThread30.stopThread();
            }else if (timerThread35 != null){
                timerThread35.stopThread();
            }else if (timerThread40 != null){
                timerThread40.stopThread();
            }else if (timerThread45 != null){
                timerThread45.stopThread();
            }else if (timerThread50 != null){
                timerThread50.stopThread();
            }else if (timerThread55 != null){
                timerThread55.stopThread();
            }else if (timerThread60 != null){
                timerThread60.stopThread();
            }
        }else {
            //do nothing
        }
    }


    /**
     * 是否有线程正在运行
     * @return
     */
    private boolean isThreadRun(){
        if ( (timerThread1 != null && timerThread1.isAlive())
                || (timerThread3  != null &&  timerThread3.isAlive() )
                || (timerThread5  != null &&  timerThread5.isAlive() )
                || (timerThread10 != null && timerThread10.isAlive() )
                || (timerThread15 != null && timerThread15.isAlive() )
                || (timerThread20 != null && timerThread20.isAlive() )
                || (timerThread25 != null && timerThread25.isAlive() )
                || (timerThread30 != null && timerThread30.isAlive() )
                || (timerThread35 != null && timerThread35.isAlive() )
                || (timerThread40 != null && timerThread40.isAlive() )
                || (timerThread45 != null && timerThread45.isAlive() )
                || (timerThread50 != null && timerThread50.isAlive() )
                || (timerThread55 != null && timerThread55.isAlive() )
                || (timerThread60 != null && timerThread60.isAlive() ) ){
            return true;
        }else {
            return false;
        }
    }

    private class MobileGPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            information.setLongitude(location.getLongitude());
            information.setLatitude(location.getLatitude());
            information.setSpeed(location.getSpeed());
            updateUI(information);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(null);
            information.setLongitude(location.getLongitude());
            information.setLatitude(location.getLatitude());
            information.setSpeed(location.getSpeed());
            updateUI(information);
        }

        @Override
        public void onProviderDisabled(String provider) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(null);
            information.setLongitude(location.getLongitude());
            information.setLatitude(location.getLatitude());
            information.setSpeed(location.getSpeed());
            updateUI(information);
        }
    }

    /**
     *  使用机身GPS和网络位置
     *  设置经纬度
     * @return
     */
    private boolean setGPS(Information information) {
        boolean isSuccess = false;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return isSuccess;
        }
        if (locationManager != null) {
            information.setCoord_type_input(Information.WGS84);
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                information.setLongitude(location.getLongitude());
                information.setLatitude(location.getLatitude());
                information.setSpeed(location.getSpeed());
                isSuccess = true;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    information.setLongitude(location.getLongitude());
                    information.setLatitude(location.getLatitude());
                    information.setSpeed(location.getSpeed());
                    isSuccess = true;
                } else {
                    information.setLongitude(0.0110);
                    information.setLatitude(0.0110);
                }
            }
            updateUI(information);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return false;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 8, new MobileGPSLocationListener());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 8, new MobileGPSLocationListener());


        }else {
            isSuccess = false;
        }


        return isSuccess;
    }

    private void updateUI(Information information){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_UI);
        intent.putExtra(Information.IOFMATION, information);
        context.sendBroadcast(intent);
    }

    /**
     * 使用百度定位SDK
     */

    public class BaiduLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            location.setRadius(0.000000000000f);
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float speed = location.getSpeed();
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f

            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            //information.setCoord_type_input(Information.BDO9);
            information.setLatitude(latitude);
            information.setLongitude(longitude);
            information.setSpeed(speed);
            information.setCoord_type_input(Information.BDO9);
            information.setBaiduErrorCode(String.valueOf(errorCode));
           // information.setCoord_type_input(String.valueOf(errorCode));
            updateUI(information);
        }
    }
}
