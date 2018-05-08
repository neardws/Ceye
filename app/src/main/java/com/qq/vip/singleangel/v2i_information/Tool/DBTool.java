package com.qq.vip.singleangel.v2i_information.Tool;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.qq.vip.singleangel.v2i_information.Activity.MainActivity;
import com.qq.vip.singleangel.v2i_information.ClassDefined.Information;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.ControlModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.ControlModel_Table;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.InformationModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.InformationModel_Table;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.MessageModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.PnameModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.PnameModel_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

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
    public static final String TABLE_MESSAGE            = "TABLE_MESSAGE";
    public static final String TABLE_INFORMATION        = "TABLE_INFORMATION";
    public static final String TABLE_PACKAGE_NAME       = "TABLE_PACKAGE_NAME";
    public static final String TABLE_CONTROL_MESSAGE    = "TABLE_CONTROL_MESSAGE";
    public static final String TABLE_ALL                = "TABLE_ALL";

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
    public static final String TIME_MY_RECEIVE  = "TIME_MY_RECEIVE";

    /*MessageModel*/
    public static final String MESSAGE_ID   = "MESSAGE_ID";
    public static final String MESSAGE_CONTEXT = "MESSAGE_CONTEXT";

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
                long timeMyReceive = intent.getExtras().getLong(DBTool.TIME_MY_RECEIVE);
                insertControl(id,timeReceive,timeSendBack,timeMyReceive);
            }else if (tableName.equals(DBTool.TABLE_MESSAGE)){
                String id = intent.getExtras().getString(DBTool.MESSAGE_ID);
                String context = intent.getExtras().getString(DBTool.MESSAGE_CONTEXT);
                long timeSendBack = intent.getExtras().getLong(DBTool.TIME_SEND_BACK);
                long timeMyReceive = intent.getExtras().getLong(DBTool.TIME_MY_RECEIVE);
                insertMessage(id,context,timeSendBack,timeMyReceive);
            }else {

            }
        }
        /**
         * 删除操作
         */
        else if (action.equals(DBTool.ACTION_DELETE)){
            String tableName = intent.getExtras().getString(DBTool.TABLE_NAME);
            if (tableName.equals(DBTool.TABLE_PACKAGE_NAME)){
                List<PnameModel> pnameModelList = SQLite.select().from(PnameModel.class).queryList();
                for (PnameModel pnameModel:pnameModelList){
                    pnameModel.delete();
                }
            }else if (tableName.equals(DBTool.TABLE_INFORMATION)){
                List<InformationModel> informationModelList = SQLite.select().from(InformationModel.class).queryList();
                for (InformationModel informationModel:informationModelList){
                    informationModel.delete();
                }
            }else if (tableName.equals(DBTool.TABLE_CONTROL_MESSAGE)){
                List<ControlModel> controlModelList = SQLite.select().from(ControlModel.class).queryList();
                for (ControlModel controlModel:controlModelList){
                    controlModel.delete();
                }
            } else if (tableName.equals(DBTool.TABLE_ALL)) {
                int packageName = intent.getExtras().getInt(DBTool.PACKAGE_NAME);
                /**
                 * 先删除Pname
                 */
                PnameModel pnameModel = new Select().from(PnameModel.class)
                        .where(PnameModel_Table.packageName.is(packageName)).querySingle();
                if (pnameModel != null) {
                    pnameModel.delete();
                }
                /**
                 * 删除Infor和Control
                 */
                List<InformationModel> informationModels = new Select().from(InformationModel.class)
                        .where(InformationModel_Table.packageName.is(packageName)).queryList();
                for (InformationModel informationModel: informationModels){
                    int id = informationModel.getId();
                    ControlModel controlModel = new Select().from(ControlModel.class)
                            .where(ControlModel_Table.id.is(id)).querySingle();
                    if (controlModel != null){
                        controlModel.delete();
                    }
                    informationModel.delete();
                }
                sendLog("删除了Pname:"+packageName+"所有表信息。");
            }
        }
        /**
         * 查询操作
         */
        else if (action.equals(DBTool.ACTION_SELETE)){
            String tableName = intent.getExtras().getString(DBTool.TABLE_NAME);
            if (tableName.equals(DBTool.TABLE_PACKAGE_NAME)){
                //返回所有查询结果
                List<PnameModel> pnameModelList = new Select().from(PnameModel.class).queryList();

            }else if (tableName.equals(DBTool.TABLE_INFORMATION)){

            }else if (tableName.equals(DBTool.TABLE_CONTROL_MESSAGE)){

            }
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

    private void insertControl(int id, long timeReceive, long timeSendBack, long timeMyReceive){
        ControlModel controlModel = new ControlModel();
        controlModel.setId(id);
        controlModel.setTimeReceive(timeReceive);
        controlModel.setTimeSendBack(timeSendBack);
        controlModel.setTimeMyReceive(timeMyReceive);
        controlModel.insert();
        sendLog("ControlModel 插入成功，ID="+id+",   Time my receive "+controlModel.getTimeMyReceive()+"\n");
    }

    private void insertMessage(String id, String context, long timeSendBack, long timeMyReceive){
        MessageModel messageModel = new MessageModel();
        messageModel.setId(id);
        messageModel.setContext(context);
        messageModel.setTimeSendBack(timeSendBack);
        messageModel.setTimeMyReceive(timeMyReceive);
        messageModel.insert();
        sendLog("MessageModel 插入成功，ID="+id+",   Time my receive "+timeMyReceive+"\n");
    }

    private void sendLog(String log){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_LOG);
        intent.putExtra(MainActivity.UPDATA_LOG,log);
        sendBroadcast(intent);
    }
}
