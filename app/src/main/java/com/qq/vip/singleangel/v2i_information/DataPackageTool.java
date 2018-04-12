package com.qq.vip.singleangel.v2i_information;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by singl on 2018/4/10.
 */

public class DataPackageTool extends IntentService{
    public static final String TAG = "DATA_PACKAGE_TOOL";

    public static final String PACKAGE_SIZE = "PACKAGE_SIZE";
    public static final String url = "http://118.24.19.160:8088/V2I/collect";

    public static final String THE_END_PACKAGE = "THE_END_PACKAGE";

    public static final String _40B     = "40B";
    public static final String _100B    = "100B";
    public static final String _500B    = "500B";
    public static final String _1KB     = "1024B";


    public static final String URL_40B   = "file:///android_asset/40B.txt";
    public static final String URL_100B  = "file:///android_asset/100B.txt";
    public static final String URL_500B  = "file:///android_asset/500B.txt";
    public static final String URL_1KB   = "file:///android_asset/1KB.txt";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DataPackageTool(String name) {
        super(name);
    }

    public DataPackageTool(){
        super(TAG);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String packSize = null;
        if (intent != null) {
            packSize = intent.getAction();
            Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
            if (packSize.equals(DataPackageTool._40B)){
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
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
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                params.addBodyParameter("file", new File(URL_40B), null);

                String urlog = "http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo="          +String.valueOf(information.getDeviceNo())
                        + "&indexNum="          +String.valueOf(information.getIndexNum())
                        + "&packageNum="        +String.valueOf(information.getPackageNum())
                        + "&macAdd="            +information.getMacAdd()
                        + "&speed="             +String.valueOf(information.getSpeed())
                        + "&timeNow="           +String.valueOf(information.getTimeNow())
                        + "&latitude="          +String.valueOf(information.getLatitude())
                        + "&longitude="         +String.valueOf(information.getLongitude())
                        + "&direction="         +String.valueOf((int) information.getDirection())
                        + "&coord_type_input="  +information.getCoord_type_input()
                        + "&frequency="         +information.getFrequency()
                        + "&packageSize="       +information.getPackageSize()
                        + "&packageName="       +String.valueOf(information.getPackageName())
                        + "&isEndofPackage="    +String.valueOf(information.getIsEndofPackage());
                sendLog(urlog);

                x.http().post(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: "+result);
                        sendLog("发送成功\n"+result+"\n");
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: "+ex.toString());
                        sendLog("发送失败\n"+ex.toString()+"\n");
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


            }else if (packSize.equals(DataPackageTool._100B)){
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
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
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                params.addBodyParameter("file", new File(URL_100B), null);

                String urlog = "http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo="          +String.valueOf(information.getDeviceNo())
                        + "&indexNum="          +String.valueOf(information.getIndexNum())
                        + "&packageNum="        +String.valueOf(information.getPackageNum())
                        + "&macAdd="            +information.getMacAdd()
                        + "&speed="             +String.valueOf(information.getSpeed())
                        + "&timeNow="           +String.valueOf(information.getTimeNow())
                        + "&latitude="          +String.valueOf(information.getLatitude())
                        + "&longitude="         +String.valueOf(information.getLongitude())
                        + "&direction="         +String.valueOf((int) information.getDirection())
                        + "&coord_type_input="  +information.getCoord_type_input()
                        + "&frequency="         +information.getFrequency()
                        + "&packageSize="       +information.getPackageSize()
                        + "&packageName="       +String.valueOf(information.getPackageName())
                        + "&isEndofPackage="    +String.valueOf(information.getIsEndofPackage());
                sendLog(urlog);
                x.http().post(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: "+result);
                        sendLog("发送成功\n"+result+"\n");
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: "+ex.toString());
                        sendLog("发送失败\n"+ex.toString()+"\n");
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

            }else if (packSize.equals(DataPackageTool._500B)){
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
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
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                params.addBodyParameter("file", new File(URL_500B), null);

                String urlog = "http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo="          +String.valueOf(information.getDeviceNo())
                        + "&indexNum="          +String.valueOf(information.getIndexNum())
                        + "&packageNum="        +String.valueOf(information.getPackageNum())
                        + "&macAdd="            +information.getMacAdd()
                        + "&speed="             +String.valueOf(information.getSpeed())
                        + "&timeNow="           +String.valueOf(information.getTimeNow())
                        + "&latitude="          +String.valueOf(information.getLatitude())
                        + "&longitude="         +String.valueOf(information.getLongitude())
                        + "&direction="         +String.valueOf((int) information.getDirection())
                        + "&coord_type_input="  +information.getCoord_type_input()
                        + "&frequency="         +information.getFrequency()
                        + "&packageSize="       +information.getPackageSize()
                        + "&packageName="       +String.valueOf(information.getPackageName())
                        + "&isEndofPackage="    +String.valueOf(information.getIsEndofPackage());
                sendLog(urlog);

                x.http().post(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: "+result);
                        sendLog("发送成功\n"+result+"\n");
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: "+ex.toString());
                        sendLog("发送失败\n"+ex.toString()+"\n");
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

            }else if (packSize.equals(DataPackageTool._1KB)){
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
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
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                params.addBodyParameter("file", new File(URL_1KB), null);

                String urlog = "http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo="          +String.valueOf(information.getDeviceNo())
                        + "&indexNum="          +String.valueOf(information.getIndexNum())
                        + "&packageNum="        +String.valueOf(information.getPackageNum())
                        + "&macAdd="            +information.getMacAdd()
                        + "&speed="             +String.valueOf(information.getSpeed())
                        + "&timeNow="           +String.valueOf(information.getTimeNow())
                        + "&latitude="          +String.valueOf(information.getLatitude())
                        + "&longitude="         +String.valueOf(information.getLongitude())
                        + "&direction="         +String.valueOf((int) information.getDirection())
                        + "&coord_type_input="  +information.getCoord_type_input()
                        + "&frequency="         +information.getFrequency()
                        + "&packageSize="       +information.getPackageSize()
                        + "&packageName="       +String.valueOf(information.getPackageName())
                        + "&isEndofPackage="    +String.valueOf(information.getIsEndofPackage());
                sendLog(urlog);

                x.http().post(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: "+result);
                        sendLog("发送成功\n"+result+"\n");
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: "+ex.toString());
                        sendLog("发送失败\n"+ex.toString()+"\n");
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

            }else if (packSize.equals(DataPackageTool.THE_END_PACKAGE)){
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
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
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String urlog = "http://118.24.19.160:8088/V2I/collect"
                        + "?deviceNo="          +String.valueOf(information.getDeviceNo())
                        + "&indexNum="          +String.valueOf(information.getIndexNum())
                        + "&packageNum="        +String.valueOf(information.getPackageNum())
                        + "&macAdd="            +information.getMacAdd()
                        + "&speed="             +String.valueOf(information.getSpeed())
                        + "&timeNow="           +String.valueOf(information.getTimeNow())
                        + "&latitude="          +String.valueOf(information.getLatitude())
                        + "&longitude="         +String.valueOf(information.getLongitude())
                        + "&direction="         +String.valueOf((int) information.getDirection())
                        + "&coord_type_input="  +information.getCoord_type_input()
                        + "&frequency="         +information.getFrequency()
                        + "&packageSize="       +information.getPackageSize()
                        + "&packageName="       +String.valueOf(information.getPackageName())
                        + "&isEndofPackage="    +String.valueOf(information.getIsEndofPackage());
                sendLog(urlog);

                x.http().post(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: "+result);
                        sendLog("发送成功\n"+result+"\n");
                        //Toast.makeText(MainActivity.this, "发送信息成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: "+ex.toString());
                        sendLog("发送失败\n"+ex.toString()+"\n");
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
            }else {

            }

        }


    }

    private void sendLog(String log){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_LOG);
        intent.putExtra(MainActivity.UPDATA_LOG,log);
        sendBroadcast(intent);
    }
}
