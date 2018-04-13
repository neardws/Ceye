package com.qq.vip.singleangel.v2i_information

import android.app.IntentService
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.util.Log

import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

import java.io.File

/**
 * Created by singl on 2018/4/10.
 */

class DataPackageTool : IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    constructor(name: String) : super(name) {}

    constructor() : super(TAG) {}


    override fun onHandleIntent(intent: Intent?) {
        var packSize: String? = null
        if (intent != null) {
            packSize = intent.action
            val information = intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE) as Information
            if (packSize == DataPackageTool._40B) {
                val params = RequestParams(url)
                params.isCancelFast = true  //可被立即停止
                params.addBodyParameter("deviceNo", information.deviceNo.toString())
                params.addBodyParameter("indexNum", information.indexNum.toString())
                params.addBodyParameter("packageNum", information.packageNum.toString())
                params.addBodyParameter("macAdd", information.macAdd)
                params.addBodyParameter("speed", information.speed.toString())
                params.addBodyParameter("timeNow", information.timeNow.toString())
                params.addBodyParameter("latitude", information.latitude.toString())
                params.addBodyParameter("longitude", information.longitude.toString())
                params.addBodyParameter("direction", information.direction.toInt().toString())
                params.addBodyParameter("coord_type_input", information.coord_type_input)
                params.addBodyParameter("frequency", information.frequency)
                params.addBodyParameter("packageSize", information.packageSize)
                params.addBodyParameter("packageName", information.packageName.toString())
                params.addBodyParameter("isEndofPackage", information.isEndofPackage.toString())
                params.addBodyParameter("file", File(URL_40B), null)

                val urlog = ("http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo=" + information.deviceNo.toString()
                        + "&indexNum=" + information.indexNum.toString()
                        + "&packageNum=" + information.packageNum.toString()
                        + "&macAdd=" + information.macAdd
                        + "&speed=" + information.speed.toString()
                        + "&timeNow=" + information.timeNow.toString()
                        + "&latitude=" + information.latitude.toString()
                        + "&longitude=" + information.longitude.toString()
                        + "&direction=" + information.direction.toInt().toString()
                        + "&coord_type_input=" + information.coord_type_input
                        + "&frequency=" + information.frequency
                        + "&packageSize=" + information.packageSize
                        + "&packageName=" + information.packageName.toString()
                        + "&isEndofPackage=" + information.isEndofPackage.toString())
                sendLog(urlog)

                x.http().post(params, object : Callback.CacheCallback<String> {

                    override fun onSuccess(result: String) {
                        Log.i(TAG, "onSuccess: " + result)
                        sendLog("发送成功\n" + result + "\n")
                        insertInformationSuccess(information)
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(ex: Throwable, isOnCallback: Boolean) {
                        Log.i(TAG, "onError: " + ex.toString())
                        sendLog("发送失败\n" + ex.toString() + "\n")
                        insertInformationFailed(information)
                        //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                    override fun onCancelled(cex: Callback.CancelledException) {
                        Log.i(TAG, "onCancelled: " + cex.toString())
                    }

                    override fun onFinished() {
                        Log.i(TAG, "onFinished: ")
                    }

                    override fun onCache(result: String): Boolean {
                        Log.i(TAG, "onCache: " + result)
                        return false
                    }
                })


            } else if (packSize == DataPackageTool._100B) {
                val params = RequestParams(url)
                params.isCancelFast = true  //可被立即停止
                params.addBodyParameter("deviceNo", information.deviceNo.toString())
                params.addBodyParameter("indexNum", information.indexNum.toString())
                params.addBodyParameter("packageNum", information.packageNum.toString())
                params.addBodyParameter("macAdd", information.macAdd)
                params.addBodyParameter("speed", information.speed.toString())
                params.addBodyParameter("timeNow", information.timeNow.toString())
                params.addBodyParameter("latitude", information.latitude.toString())
                params.addBodyParameter("longitude", information.longitude.toString())
                params.addBodyParameter("direction", information.direction.toInt().toString())
                params.addBodyParameter("coord_type_input", information.coord_type_input)
                params.addBodyParameter("frequency", information.frequency)
                params.addBodyParameter("packageSize", information.packageSize)
                params.addBodyParameter("packageName", information.packageName.toString())
                params.addBodyParameter("isEndofPackage", information.isEndofPackage.toString())
                params.addBodyParameter("file", File(URL_100B), null)

                val urlog = ("http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo=" + information.deviceNo.toString()
                        + "&indexNum=" + information.indexNum.toString()
                        + "&packageNum=" + information.packageNum.toString()
                        + "&macAdd=" + information.macAdd
                        + "&speed=" + information.speed.toString()
                        + "&timeNow=" + information.timeNow.toString()
                        + "&latitude=" + information.latitude.toString()
                        + "&longitude=" + information.longitude.toString()
                        + "&direction=" + information.direction.toInt().toString()
                        + "&coord_type_input=" + information.coord_type_input
                        + "&frequency=" + information.frequency
                        + "&packageSize=" + information.packageSize
                        + "&packageName=" + information.packageName.toString()
                        + "&isEndofPackage=" + information.isEndofPackage.toString())
                sendLog(urlog)
                x.http().post(params, object : Callback.CacheCallback<String> {

                    override fun onSuccess(result: String) {
                        Log.i(TAG, "onSuccess: " + result)
                        sendLog("发送成功\n" + result + "\n")
                        insertInformationSuccess(information)
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(ex: Throwable, isOnCallback: Boolean) {
                        Log.i(TAG, "onError: " + ex.toString())
                        sendLog("发送失败\n" + ex.toString() + "\n")
                        insertInformationFailed(information)
                        //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                    override fun onCancelled(cex: Callback.CancelledException) {
                        Log.i(TAG, "onCancelled: " + cex.toString())
                    }

                    override fun onFinished() {
                        Log.i(TAG, "onFinished: ")
                    }

                    override fun onCache(result: String): Boolean {
                        Log.i(TAG, "onCache: " + result)
                        return false
                    }
                })

            } else if (packSize == DataPackageTool._500B) {
                val params = RequestParams(url)
                params.isCancelFast = true  //可被立即停止
                params.addBodyParameter("deviceNo", information.deviceNo.toString())
                params.addBodyParameter("indexNum", information.indexNum.toString())
                params.addBodyParameter("packageNum", information.packageNum.toString())
                params.addBodyParameter("macAdd", information.macAdd)
                params.addBodyParameter("speed", information.speed.toString())
                params.addBodyParameter("timeNow", information.timeNow.toString())
                params.addBodyParameter("latitude", information.latitude.toString())
                params.addBodyParameter("longitude", information.longitude.toString())
                params.addBodyParameter("direction", information.direction.toInt().toString())
                params.addBodyParameter("coord_type_input", information.coord_type_input)
                params.addBodyParameter("frequency", information.frequency)
                params.addBodyParameter("packageSize", information.packageSize)
                params.addBodyParameter("packageName", information.packageName.toString())
                params.addBodyParameter("isEndofPackage", information.isEndofPackage.toString())
                params.addBodyParameter("file", File(URL_500B), null)

                val urlog = ("http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo=" + information.deviceNo.toString()
                        + "&indexNum=" + information.indexNum.toString()
                        + "&packageNum=" + information.packageNum.toString()
                        + "&macAdd=" + information.macAdd
                        + "&speed=" + information.speed.toString()
                        + "&timeNow=" + information.timeNow.toString()
                        + "&latitude=" + information.latitude.toString()
                        + "&longitude=" + information.longitude.toString()
                        + "&direction=" + information.direction.toInt().toString()
                        + "&coord_type_input=" + information.coord_type_input
                        + "&frequency=" + information.frequency
                        + "&packageSize=" + information.packageSize
                        + "&packageName=" + information.packageName.toString()
                        + "&isEndofPackage=" + information.isEndofPackage.toString())
                sendLog(urlog)

                x.http().post(params, object : Callback.CacheCallback<String> {

                    override fun onSuccess(result: String) {
                        Log.i(TAG, "onSuccess: " + result)
                        sendLog("发送成功\n" + result + "\n")
                        insertInformationSuccess(information)
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(ex: Throwable, isOnCallback: Boolean) {
                        Log.i(TAG, "onError: " + ex.toString())
                        sendLog("发送失败\n" + ex.toString() + "\n")
                        insertInformationFailed(information)
                        //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                    override fun onCancelled(cex: Callback.CancelledException) {
                        Log.i(TAG, "onCancelled: " + cex.toString())
                    }

                    override fun onFinished() {
                        Log.i(TAG, "onFinished: ")
                    }

                    override fun onCache(result: String): Boolean {
                        Log.i(TAG, "onCache: " + result)
                        return false
                    }
                })

            } else if (packSize == DataPackageTool._1KB) {
                val params = RequestParams(url)
                params.isCancelFast = true  //可被立即停止
                params.addBodyParameter("deviceNo", information.deviceNo.toString())
                params.addBodyParameter("indexNum", information.indexNum.toString())
                params.addBodyParameter("packageNum", information.packageNum.toString())
                params.addBodyParameter("macAdd", information.macAdd)
                params.addBodyParameter("speed", information.speed.toString())
                params.addBodyParameter("timeNow", information.timeNow.toString())
                params.addBodyParameter("latitude", information.latitude.toString())
                params.addBodyParameter("longitude", information.longitude.toString())
                params.addBodyParameter("direction", information.direction.toInt().toString())
                params.addBodyParameter("coord_type_input", information.coord_type_input)
                params.addBodyParameter("frequency", information.frequency)
                params.addBodyParameter("packageSize", information.packageSize)
                params.addBodyParameter("packageName", information.packageName.toString())
                params.addBodyParameter("isEndofPackage", information.isEndofPackage.toString())
                params.addBodyParameter("file", File(URL_1KB), null)

                val urlog = ("http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo=" + information.deviceNo.toString()
                        + "&indexNum=" + information.indexNum.toString()
                        + "&packageNum=" + information.packageNum.toString()
                        + "&macAdd=" + information.macAdd
                        + "&speed=" + information.speed.toString()
                        + "&timeNow=" + information.timeNow.toString()
                        + "&latitude=" + information.latitude.toString()
                        + "&longitude=" + information.longitude.toString()
                        + "&direction=" + information.direction.toInt().toString()
                        + "&coord_type_input=" + information.coord_type_input
                        + "&frequency=" + information.frequency
                        + "&packageSize=" + information.packageSize
                        + "&packageName=" + information.packageName.toString()
                        + "&isEndofPackage=" + information.isEndofPackage.toString())
                sendLog(urlog)

                x.http().post(params, object : Callback.CacheCallback<String> {

                    override fun onSuccess(result: String) {
                        Log.i(TAG, "onSuccess: " + result)
                        sendLog("发送成功\n" + result + "\n")
                        insertInformationSuccess(information)
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(ex: Throwable, isOnCallback: Boolean) {
                        Log.i(TAG, "onError: " + ex.toString())
                        sendLog("发送失败\n" + ex.toString() + "\n")
                        insertInformationFailed(information)
                        //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                    override fun onCancelled(cex: Callback.CancelledException) {
                        Log.i(TAG, "onCancelled: " + cex.toString())
                    }

                    override fun onFinished() {
                        Log.i(TAG, "onFinished: ")
                    }

                    override fun onCache(result: String): Boolean {
                        Log.i(TAG, "onCache: " + result)
                        return false
                    }
                })

            } else if (packSize == DataPackageTool.THE_END_PACKAGE) {
                val params = RequestParams(url)
                params.isCancelFast = true  //可被立即停止
                params.addBodyParameter("deviceNo", information.deviceNo.toString())
                params.addBodyParameter("indexNum", information.indexNum.toString())
                params.addBodyParameter("packageNum", information.packageNum.toString())
                params.addBodyParameter("macAdd", information.macAdd)
                params.addBodyParameter("speed", information.speed.toString())
                params.addBodyParameter("timeNow", information.timeNow.toString())
                params.addBodyParameter("latitude", information.latitude.toString())
                params.addBodyParameter("longitude", information.longitude.toString())
                params.addBodyParameter("direction", information.direction.toInt().toString())
                params.addBodyParameter("coord_type_input", information.coord_type_input)
                params.addBodyParameter("frequency", information.frequency)
                params.addBodyParameter("packageSize", information.packageSize)
                params.addBodyParameter("packageName", information.packageName.toString())
                params.addBodyParameter("isEndofPackage", information.isEndofPackage.toString())
                val urlog = ("http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo=" + information.deviceNo.toString()
                        + "&indexNum=" + information.indexNum.toString()
                        + "&packageNum=" + information.packageNum.toString()
                        + "&macAdd=" + information.macAdd
                        + "&speed=" + information.speed.toString()
                        + "&timeNow=" + information.timeNow.toString()
                        + "&latitude=" + information.latitude.toString()
                        + "&longitude=" + information.longitude.toString()
                        + "&direction=" + information.direction.toInt().toString()
                        + "&coord_type_input=" + information.coord_type_input
                        + "&frequency=" + information.frequency
                        + "&packageSize=" + information.packageSize
                        + "&packageName=" + information.packageName.toString()
                        + "&isEndofPackage=" + information.isEndofPackage.toString())
                sendLog(urlog)

                x.http().post(params, object : Callback.CacheCallback<String> {

                    override fun onSuccess(result: String) {
                        Log.i(TAG, "onSuccess: " + result)
                        sendLog("发送成功\n" + result + "\n")
                        insertInformationSuccess(information)
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(ex: Throwable, isOnCallback: Boolean) {
                        Log.i(TAG, "onError: " + ex.toString())
                        sendLog("发送失败\n" + ex.toString() + "\n")
                        insertInformationFailed(information)
                        //Toast.makeText(MainActivity.this, "发送信息失败"+ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                    override fun onCancelled(cex: Callback.CancelledException) {
                        Log.i(TAG, "onCancelled: " + cex.toString())
                    }

                    override fun onFinished() {
                        Log.i(TAG, "onFinished: ")
                    }

                    override fun onCache(result: String): Boolean {
                        Log.i(TAG, "onCache: " + result)
                        return false
                    }
                })
            } else {

            }

        }


    }

    private fun sendLog(log: String) {
        val intent = Intent()
        intent.action = MainActivity.UPDATA_LOG
        intent.putExtra(MainActivity.UPDATA_LOG, log)
        sendBroadcast(intent)
    }

    private fun insertInformationSuccess(information: Information) {
        val informationModel = InformationModel()
        if (information.isEndofPackage == 0) {
            val id = (information.deviceNo.toString() + information.timeNow.toString()).hashCode()
            informationModel.id = id
        } else if (information.isEndofPackage == 1) {
            val id = (information.deviceNo.toString() + (information.timeNow + 100).toString()).hashCode()
            informationModel.id = id
        }
        informationModel.packageName = information.packageName
        informationModel.deviceNo = information.deviceNo
        informationModel.gpsType = information.coord_type_input
        informationModel.longitude = information.longitude
        informationModel.latitude = information.latitude
        informationModel.speed = information.speed
        informationModel.direction = information.direction
        informationModel.indexNum = information.indexNum
        informationModel.timeNow = information.timeNow
        informationModel.frequency = information.frequency
        informationModel.packageSize = information.packageSize
        informationModel.isEndofPackage = information.isEndofPackage
        informationModel.isSuccess = true
        informationModel.insert()
    }

    private fun insertInformationFailed(information: Information) {
        val informationModel = InformationModel()
        if (information.isEndofPackage == 0) {
            val id = (information.deviceNo.toString() + information.timeNow.toString()).hashCode()
            informationModel.id = id
        } else if (information.isEndofPackage == 1) {
            val id = (information.deviceNo.toString() + (information.timeNow + 100).toString()).hashCode()
            informationModel.id = id
        }
        informationModel.packageName = information.packageName
        informationModel.deviceNo = information.deviceNo
        informationModel.gpsType = information.coord_type_input
        informationModel.longitude = information.longitude
        informationModel.latitude = information.latitude
        informationModel.speed = information.speed
        informationModel.direction = information.direction
        informationModel.indexNum = information.indexNum
        informationModel.timeNow = information.timeNow
        informationModel.frequency = information.frequency
        informationModel.packageSize = information.packageSize
        informationModel.isEndofPackage = information.isEndofPackage
        informationModel.isSuccess = false
        informationModel.insert()
    }

    companion object {
        val TAG = "DATA_PACKAGE_TOOL"

        val PACKAGE_SIZE = "PACKAGE_SIZE"
        val url = "http://118.24.19.160:8088/V2I/collect"

        val THE_END_PACKAGE = "THE_END_PACKAGE"

        val _40B = "40B"
        val _100B = "100B"
        val _500B = "500B"
        val _1KB = "1024B"


        val URL_40B = "file:///android_asset/40B.txt"
        val URL_100B = "file:///android_asset/100B.txt"
        val URL_500B = "file:///android_asset/500B.txt"
        val URL_1KB = "file:///android_asset/1KB.txt"
    }
}
