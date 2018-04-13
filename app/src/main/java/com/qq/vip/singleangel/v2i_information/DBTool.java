package com.qq.vip.singleangel.v2i_information;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;

/**
 * DBTool 增删改查操作
 * Created by singl on 2018/4/13.
 */

public class DBTool extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DBTool(String name) {
        super(name);
    }
    public DBTool(){
        super("DBTool");
    }

    public static final String ACTION_ADD       = "ACTION_ADD";
    public static final String ACTION_DELETE    = "ACTION_DELETE";
    public static final String ACTION_SELETE    = "ACTION_SELETE";

    public static final String TABLE_NAME               = "TABLE_NAME";
    public static final String TABLE_INFORMATION        = "TABLE_INFORMATION";
    public static final String TABLE_PACKAGE_NAME       = "TABLE_PACKAGE_NAME";
    public static final String TABLE_CONTROL_MESSAGE    = "TABLE_CONTROL_MESSAGE";

    /*InformationModel*/
    public static final String INFORMATION                  = "INFORMATION";
    public static final String INFORMATION_SEND_IS_SUCCESS  = "INFORMATION_SEND_IS_SUCCESS";

    /*PnameModel*/
    public static final String PACKAGE_NAME         = "PACKAGE_NAME";
    public static final String PACKAGE_TIME         = "PACKAGE_TIME";
    public static final String PACKAGE_FREQUENCY    = "FREQUENCY";
    public static final String PACKAGE_SIZE         = "PACKAGE_SIZE";

    /*ControlModel*/
    public static final String CONTROL_ID       = "CONTROL_ID";
    public static final String TIME_RECEIVE     = "TIME_RECEIVE";
    public static final String TIME_SEND_BACK   = "TIME_SEND_BACK";

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        /**
         * 添加操作
         */
        if (action.equals(DBTool.ACTION_ADD)){
            String tableName = intent.getExtras().getString(DBTool.TABLE_NAME);
            if (tableName.equals(DBTool.TABLE_INFORMATION)){
                Information information = (Information) intent.getSerializableExtra(DBTool.INFORMATION);
                boolean isSuccess = (boolean) intent.getExtras().getBoolean(DBTool.INFORMATION_SEND_IS_SUCCESS);
                insertInformation(information,isSuccess);
            }else if (tableName.equals(DBTool.TABLE_PACKAGE_NAME)){
                int packageName         = intent.getExtras().getInt(DBTool.PACKAGE_NAME);
                insertPname(packageName);
            }else if (tableName.equals(DBTool.TABLE_CONTROL_MESSAGE)){
                int id = intent.getExtras().getInt(DBTool.CONTROL_ID);
                long timeReceive = intent.getExtras().getLong(DBTool.TIME_RECEIVE);
                long timeSendBack = intent.getExtras().getLong(DBTool.TIME_SEND_BACK);
                insertControl(id,timeReceive,timeSendBack);
            }
        }
        /**
         * 删除操作
         */
        else if (action.equals(DBTool.ACTION_DELETE)){

        }
        /**
         * 查询操作
         */
        else if (action.equals(DBTool.ACTION_SELETE)){

        }

    }

    /**
     * 插入information
     * @param information
     */
    private void insertInformation(Information information, boolean isSuccess){
        InformationModel informationModel = new InformationModel();
        int id = 0;
        if (information.getIsEndofPackage() == 0){
            id = (String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow())).hashCode();
            informationModel.setId(id);
        }else if (information.getIsEndofPackage() == 1){
            id = (String.valueOf(information.getDeviceNo())+String.valueOf(information.getTimeNow()+100)).hashCode();
            informationModel.setId(id);
        }
        informationModel.setPackageName(information.getPackageName());
        informationModel.setDeviceNo(information.getDeviceNo());
        informationModel.setGpsType(information.getCoord_type_input());
        informationModel.setLongitude(information.getLongitude());
        informationModel.setLatitude(information.getLatitude());
        informationModel.setSpeed(information.getSpeed());
        informationModel.setDirection(information.getDirection());
        informationModel.setIndexNum(information.getIndexNum());
        informationModel.setTimeNow(information.getTimeNow());
        informationModel.setFrequency(information.getFrequency());
        informationModel.setPackageSize(information.getPackageSize());
        informationModel.setIsEndofPackage(information.getIsEndofPackage());
        informationModel.setSuccess(isSuccess);
        informationModel.insert();
        sendLog("InformationModel 插入成功，ID="+id+"\n");
    }

    private void insertPname(int packageName){
        PnameModel pnameModel = new PnameModel();
        pnameModel.setPackageName(packageName);
        pnameModel.insert();
        sendLog("PnameModel 插入成功，PackageName="+packageName+"\n");
    }

    private void insertControl(int id, long timeReceive, long timeSendBack){
        ControlModel controlModel = new ControlModel();
        controlModel.setId(id);
        controlModel.setTimeReceive(timeReceive);
        controlModel.setTimeSendBack(timeSendBack);
        controlModel.insert();
        sendLog("ControlModel 插入成功，ID="+id+"\n");
    }


    private void sendLog(String log){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_LOG);
        intent.putExtra(MainActivity.UPDATA_LOG,log);
        sendBroadcast(intent);
    }
}
