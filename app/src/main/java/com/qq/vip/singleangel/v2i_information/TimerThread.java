package com.qq.vip.singleangel.v2i_information

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat

/**
 * Created by singl on 2018/3/30.
 */

class TimerThread : Thread {
    private val context: Context
    private val information: Information
    /**
     * 多少秒来获取信息
     */
    var frequency = 1

    private val infoTool: InfoTool

    private var stop = false

    fun stopThread() {
        infoTool.uninit()
        this.stop = true
    }

    constructor() {

    }

    constructor(context: Context, frequency: Int) {
        information = Information()
        this.context = context
        frequency = frequency
        infoTool = InfoTool(context)
    }

    override fun run() {
        super.run()
        stop = false
        val sleepTime = frequency.toLong() * 100
        while (!stop) {
            val infor = infoTool.info

            updateUI(infor)
            sendMessage()
            try {
                Thread.sleep(sleepTime)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    private fun updateUI(information: Information?) {
        val intent = Intent()
        intent.action = MainActivity.UPDATA_UI
        intent.putExtra(Information.IOFMATION, information)
        context.sendBroadcast(intent)
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.action = MainActivity.SEND_MESSAGE
        context.sendBroadcast(intent)
    }


}
