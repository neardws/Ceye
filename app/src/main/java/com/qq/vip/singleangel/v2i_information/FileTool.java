package com.qq.vip.singleangel.v2i_information;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by singl on 2018/4/13.
 */

public class FileTool {
    private Context context;
    private String packageName;
    public static final String TAG = "FileTool";

    public static final String _40B_FILE_URL = "/mnt/sdcard/file40b";
    public static final String _100B_FILE_URL = "/mnt/sdcard/file100b";
    public static final String _500B_FILE_URL = "/mnt/sdcard/file500b";
    public static final String _1KB_FILE_URL = "/mnt/sdcard/file1024b";

    private static final String str_40B  = "IND##indexNum##LON##longitude##LAT##lati";

    private static final String str_100B = "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "#longitude##LAT##latie##";

    private static final String str_500B = "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "#longitude##LAT##latie##"+
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "#longitude##LAT##latie##";
    private static final String str_1KB  = "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
                                           "#longitude##LAT##latie##"+
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "#longitude##LAT##latie##"+
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "IND##indexNum##LON##longitude##LAT##latiIND##indexNum##LON##longitude##LAT##lati##longitude##LAT##la" +
            "#longitude##LAT##latie##";

    public FileTool(Context context){
        this.context = context;
        this.packageName = context.getPackageName();
    }

    /**
     * 初始化创建文件
     */
    public void init(){
        creatFile(_40B_FILE_URL);
        creatFile(_100B_FILE_URL);
        creatFile(_500B_FILE_URL);
        creatFile(_1KB_FILE_URL);
    }

    private void creatFile(String fileUrl){
        File file = new File(fileUrl);
        if (!file.exists()){
            boolean isSuccess = false;
            try {
                isSuccess = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context,"Create File failed."+e+"  "+packageName,Toast.LENGTH_LONG).show();
            }
            if (isSuccess){
                Log.d(TAG, "Create File Success");
                Toast.makeText(context,"Create File Success.",Toast.LENGTH_SHORT).show();
                sendLog("Create File Success.");
            }else {
                Log.d(TAG, "Create File Failed.");
                Toast.makeText(context,"Create File Failed.",Toast.LENGTH_SHORT).show();
                sendLog("Create File Failed.");
            }

            try {
                OutputStream outputStream = new FileOutputStream(file);
                if (fileUrl.equals(_40B_FILE_URL)){
                    outputStream.write(str_40B.getBytes());
                    sendLog("Write 40B_File Success.");
                    Toast.makeText(context,"Write 40B_File Success.",Toast.LENGTH_SHORT).show();
                }else if (fileUrl.equals(_100B_FILE_URL)){
                    outputStream.write(str_100B.getBytes());
                    sendLog("Write 100B_File Success.");
                    Toast.makeText(context,"Write 100B_File Success.",Toast.LENGTH_SHORT).show();
                }else if (fileUrl.equals(_500B_FILE_URL)){
                    outputStream.write(str_500B.getBytes());
                    sendLog("Write 500B_File Success.");
                    Toast.makeText(context,"Write 500B_File Success.",Toast.LENGTH_SHORT).show();
                }else if (fileUrl.equals(_1KB_FILE_URL)){
                    outputStream.write(str_1KB.getBytes());
                    sendLog("Write 1024B_File Success.");
                    Toast.makeText(context,"Write 1024B_File Success.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Write File 参数不匹配",Toast.LENGTH_SHORT).show();
                }
                outputStream.close();

            }catch (FileNotFoundException e){

            }catch (IOException e){

            }

        }
    }

    private void sendLog(String log){
        Intent intent = new Intent();
        intent.setAction(MainActivity.UPDATA_LOG);
        intent.putExtra(MainActivity.UPDATA_LOG,log);
        context.sendBroadcast(intent);
    }


}
