package com.qq.vip.singleangel.v2i_information;

import android.content.Context;
import android.content.Intent;

import com.qq.vip.singleangel.v2i_information.Activity.MainActivity;
import com.qq.vip.singleangel.v2i_information.ClassDefined.Information;
import com.qq.vip.singleangel.v2i_information.Tool.InfoTool;

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
        long sleepTime = (long) frequency*100;
        while (!stop){
            Information infor = infoTool.getInfo();

            updateUI(infor);
            sendMessage();
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

    private void sendMessage(){
        Intent intent = new Intent();
        intent.setAction(MainActivity.SEND_MESSAGE);
        context.sendBroadcast(intent);
    }



}
