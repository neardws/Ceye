package com.qq.vip.singleangel.v2i_information;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by singl on 2018/3/25.
 */

public class GetInfo extends Service {
    private LocationManager locationManager;
    private Information information;
    private Context context;
    public static final String FREQUENCY        = "FREQUENCY";

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
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        setGPS(information);
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
        locationManager.removeUpdates(new MyLocationListener());
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

    private class MyLocationListener implements LocationListener {

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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 8, new MyLocationListener());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 8, new MyLocationListener());


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
}
