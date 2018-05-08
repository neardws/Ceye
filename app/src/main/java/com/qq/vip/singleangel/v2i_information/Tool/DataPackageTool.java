package com.qq.vip.singleangel.v2i_information.Tool;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.qq.vip.singleangel.v2i_information.Activity.MainActivity;
import com.qq.vip.singleangel.v2i_information.ClassDefined.Information;

import org.json.JSONException;
import org.json.JSONObject;
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

    public static final String CHANNEL_ID = "channel_id";
    public static final String _40B     = "40B";
    public static final String _100B    = "100B";
    public static final String _500B    = "500B";
    public static final String _1KB     = "1024B";

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
            if (packSize.equals(DataPackageTool._40B)){
                Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
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
                params.addBodyParameter("coord_type_input","bd09ll");
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String id = null;
                if (information.getIsEndofPackage() == 0){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }else if (information.getIsEndofPackage() == 1){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }

                params.addBodyParameter("file", new File(FileTool._40B_FILE_URL), null);

                String urlog = url
                        + "?packageId="+id
                        + "&deviceNo="          +String.valueOf(information.getDeviceNo())
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

                sendPost(params,information);

            }else if (packSize.equals(DataPackageTool._100B)){
                Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
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
                params.addBodyParameter("coord_type_input","bd09ll");
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String id = null;
                if (information.getIsEndofPackage() == 0){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }else if (information.getIsEndofPackage() == 1){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }
                params.addBodyParameter("file", new File(FileTool._100B_FILE_URL), null);

                String urlog = url
                        + "?packageId="+id
                        + "&deviceNo="          +String.valueOf(information.getDeviceNo())
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

                sendPost(params,information);

            }else if (packSize.equals(DataPackageTool._500B)){
                Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
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
                params.addBodyParameter("coord_type_input","bd09ll");
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String id = null;
                if (information.getIsEndofPackage() == 0){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }else if (information.getIsEndofPackage() == 1){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }
                params.addBodyParameter("file", new File(FileTool._500B_FILE_URL), null);

                String urlog = url
                        + "?packageId="+id
                        + "&deviceNo="          +String.valueOf(information.getDeviceNo())
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

                sendPost(params,information);

            }else if (packSize.equals(DataPackageTool._1KB)){
                Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
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
                params.addBodyParameter("coord_type_input","bd09ll");
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String id = null;
                if (information.getIsEndofPackage() == 0){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }else if (information.getIsEndofPackage() == 1){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }

                params.addBodyParameter("file", new File(FileTool._1KB_FILE_URL), null);

                String urlog = url
                        + "?packageId="+id
                        + "&deviceNo="          +String.valueOf(information.getDeviceNo())
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

                sendPost(params,information);

            }else if (packSize.equals(DataPackageTool.THE_END_PACKAGE)){
                Information information = (Information) intent.getSerializableExtra(DataPackageTool.PACKAGE_SIZE);
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
                params.addBodyParameter("coord_type_input","bd09ll");
                params.addBodyParameter("frequency",information.getFrequency());
                params.addBodyParameter("packageSize",information.getPackageSize());
                params.addBodyParameter("packageName",String.valueOf(information.getPackageName()));
                params.addBodyParameter("isEndofPackage",String.valueOf(information.getIsEndofPackage()));
                String id = null;
                if (information.getIsEndofPackage() == 0){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }else if (information.getIsEndofPackage() == 1){
                    id = String.valueOf((String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode());
                    params.addBodyParameter("packageId",String.valueOf(id));
                }


                params.addBodyParameter("file", new File(FileTool._40B_FILE_URL), null);

                String urlog = url
                        + "?packageId="+id
                        + "&deviceNo="          +String.valueOf(information.getDeviceNo())
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
                sendPost(params,information);

            }else if (packSize.equals(DataPackageTool.CHANNEL_ID)){
                String channelID = intent.getExtras().getString(DataPackageTool.CHANNEL_ID);
                String url = "http://118.24.19.160:8088/V2I/RegChID";
                RequestParams params = new RequestParams(url);
                params.setCancelFast(true);  //可被立即停止
                params.addBodyParameter("deviceNo",channelID);
                //sendLog("Send channel id :"+ String.valueOf(information.getDeviceNo()));
                sendPost(params,channelID);
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

    private void InsertInfo(Information information, boolean isSuccess){
        Intent inforIntent = new Intent(DataPackageTool.this, DBTool.class);
        inforIntent.setAction(DBTool.ACTION_ADD);
        inforIntent.putExtra(DBTool.TABLE_NAME, DBTool.TABLE_INFORMATION);
        inforIntent.putExtra(DBTool.INFORMATION, information);
        inforIntent.putExtra(DBTool.INFORMATION_SEND_IS_SUCCESS, isSuccess);
        startService(inforIntent);
    }

    private void InsertControl(int packageId, long timeReceive, long timeSendBack, long nowTimw){
        Intent dbIntent = new Intent(DataPackageTool.this, DBTool.class);
        dbIntent.setAction(DBTool.ACTION_ADD);
        dbIntent.putExtra(DBTool.TABLE_NAME,DBTool.TABLE_CONTROL_MESSAGE);
        dbIntent.putExtra(DBTool.CONTROL_ID,packageId);
        dbIntent.putExtra(DBTool.TIME_RECEIVE,timeReceive);
        dbIntent.putExtra(DBTool.TIME_SEND_BACK,timeSendBack);
        dbIntent.putExtra(DBTool.TIME_MY_RECEIVE,nowTimw);
        startService(dbIntent);
    }

    private void sendPost(RequestParams params, final Information information){
        x.http().post(params, new Callback.CacheCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                InsertInfo(information,true);
                try {
                    int packageId = (int) result.getInt("packageId");
                    long timeReceive = (long) result.getLong("recvtimeStamp");
                    long timeSendBack = (long) result.getLong("backtimeStamp");
                    long nowTime = System.currentTimeMillis();
                    sendLog("发送成功\n"+"JSON  packageId="+packageId+
                            "   backtimeStamp="+ timeSendBack+
                            "   recvtimeStamp="+ timeReceive+"\n");
                    InsertControl(packageId,timeReceive,timeSendBack, nowTime);
                } catch (JSONException e) {
                    e.printStackTrace();
                    sendLog("JSONException\n"+e.toString()+"\n");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                sendLog("发送失败\n"+ex.toString()+"\n");
                InsertInfo(information,false);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(JSONObject result) {
                return false;
            }
        });
    }

    private void sendPost(RequestParams params, final String channelID){
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                sendLog("Send channel id :"+channelID+"发送成功"+result+"\n"+"\n");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                sendLog("发送失败\n"+ex.toString()+"\n");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }



}
