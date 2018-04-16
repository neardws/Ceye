package com.qq.vip.singleangel.v2i_information;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class DebugActivity extends AppCompatActivity {
    private Button btn_Read;
    private Button btn_Pname;
    private Button btn_Infor;
    private Button btn_Control;
    private TextView log;

    private List<PnameModel> pnameModels;
    private List<InformationModel> informationModels;
    private List<ControlModel> controlModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        pnameModels = new ArrayList<>();
        informationModels = new ArrayList<>();
        controlModels = new ArrayList<>();

        btn_Read = (Button) findViewById(R.id.btn_read);
        btn_Pname = (Button) findViewById(R.id.btn_pname);
        btn_Control = (Button) findViewById(R.id.btn_control);
        btn_Infor = (Button) findViewById(R.id.btn_infor);

        log = (TextView) findViewById(R.id.debug_log);
        log.setMovementMethod(new ScrollingMovementMethod());

        btn_Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadDBAsyncTask dbAsyncTask = new ReadDBAsyncTask();
                dbAsyncTask.execute();
            }
        });

        btn_Pname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1;
                log.setText("");
                log.append("Size:"+ pnameModels.size()+"\n");
                for (PnameModel pnameModel: pnameModels){
                    log.append(String.valueOf(i)+"   "+String.valueOf(pnameModel.getPackageName())+"\n");
                    i ++;
                }
            }
        });

        btn_Infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1;
                log.setText("");
                log.append("Size:"+ informationModels.size()+"\n");
                for (InformationModel informationModel: informationModels){
                    log.setMovementMethod(new ScrollingMovementMethod());
                    log.append(String.valueOf(i)+ "  ID:"+ String.valueOf(informationModel.getId()) +"   " +"isSuccess:"+String.valueOf(informationModel.isSuccess())   +"\n"
                            +"Package Name:"    +String.valueOf(informationModel.getPackageName())  +"   " +"Device No:"+String.valueOf(informationModel.getDeviceNo()) +"\n"
                            +"GPS Type:"        +informationModel.getGpsType()                      +"   " +"Longitude:"       +String.valueOf(informationModel.getLongitude())    +"  " +"Latitude:"        +String.valueOf(informationModel.getLatitude())     +"\n"
                            +"Speed:"           +String.valueOf(informationModel.getSpeed())        +"   " +"Direction:"       +String.valueOf(informationModel.getDirection())    +"\n"
                            +"IndexNum:"        +String.valueOf(informationModel.getIndexNum())     +"   " +"TimeNow:"         +String.valueOf(informationModel.getTimeNow())      +"\n"
                            +"Frequency:"       +informationModel.getFrequency()                    +"   " +"PackageSize"      +informationModel.getPackageSize()                  +"  " +"isEnd:"           +String.valueOf(informationModel.getIsEndofPackage())+"\n");
                    log.append("\n");
                    i ++;
                }
            }
        });

        btn_Control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1;
                log.setText("");
                log.append("Size:"+ controlModels.size()+"\n");
                for (ControlModel controlModel: controlModels){
                    /*if (controlModel.getTimeMyReceive() != 0){*/
                        log.setMovementMethod(new ScrollingMovementMethod());
                        log.append(String.valueOf(i)+"  ID:"+String.valueOf(controlModel.getId())+"\n"
                                +"SRT:"+String.valueOf(controlModel.getTimeReceive())+"\n"
                                +"SST:"+String.valueOf(controlModel.getTimeSendBack())+"\n"
                                +"CRT:"+String.valueOf(controlModel.getTimeMyReceive())+"\n");
                        log.append("\n");
                        i ++;
                 /*   }*/
                }
            }
        });

    }

    private class ReadDBAsyncTask extends AsyncTask{
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"正在读取数据库...",Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            pnameModels = new Select().from(PnameModel.class).queryList();
            informationModels = new Select().from(InformationModel.class).queryList();
            controlModels = new Select().from(ControlModel.class).queryList();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(getApplicationContext(),"读取数据库完成.",Toast.LENGTH_SHORT).show();
        }
    }
}
