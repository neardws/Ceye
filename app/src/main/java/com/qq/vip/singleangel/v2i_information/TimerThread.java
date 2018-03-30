package com.qq.vip.singleangel.v2i_information;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;

/**
 * Created by singl on 2018/3/30.
 */

public class TimerThread extends Thread{
    private Context context;
    private Information information;
    /**
     * 多少秒来获取信息
     */
    private int frequency = 1;

    private InfoTool infoTool;

    private boolean stop = false;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void stopThread(){
        infoTool.uninit();
        this.stop = true;
    }

    public TimerThread(){

    }

    public TimerThread(Context context, int frequency){
        information = new Information();
        this.context = context;
        setFrequency(frequency);
        infoTool = new InfoTool(context);
    }

    @Override
    public void run() {
        super.run();
        stop = false;
        long sleepTime = (long) frequency*1000;
        while (!stop){
            Information infor = infoTool.getInfo();
            infor.setLongitude(information.getLongitude());
            infor.setLatitude(information.getLatitude());
            infor.setSpeed(information.getSpeed());
            infor.setCoord_type_input(Information.WGS84);
            //ooper.loop();
            updateUI(infor);
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI(Information information){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_UI);
        intent.putExtra(Information.IOFMATION, information);
        context.sendBroadcast(intent);
    }



}
