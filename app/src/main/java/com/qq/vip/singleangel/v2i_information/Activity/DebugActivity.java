package com.qq.vip.singleangel.v2i_information.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.vip.singleangel.v2i_information.ClassDefined.DASummer;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.ControlModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.ControlModel_Table;
import com.qq.vip.singleangel.v2i_information.ClassDefined.DAObject;
import com.qq.vip.singleangel.v2i_information.Tool.DBTool;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.InformationModel;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.InformationModel_Table;
import com.qq.vip.singleangel.v2i_information.DataBase.Model.PnameModel;
import com.qq.vip.singleangel.v2i_information.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class DebugActivity extends AppCompatActivity {
    private Button btn_Read;
    private Button btn_Pname;
    private Button btn_Infor;
    private Button btn_Control;

    private Button btn_Delete;
    private Button btn_Search;
    private Button btn_DA;
    private TextView log;

    private int package_Name;

    private TextView tv_frequency;
    private TextView tv_packageSize;
    private TextView tv_delay;
    private TextView tv_double_delay;
    private TextView tv_packageLost;

    private Spinner spinnerPackageName;
    private ArrayAdapter<String> spinnerResource;

    private List<PnameModel> pnameModels;
    private List<InformationModel> informationModels;
    private List<ControlModel> controlModels;

    private List<InformationModel> pnameInfor;
    private List<ControlModel>     pnameControl;
    private List<DAObject>         daObjects;
    private List<DASummer>         daSummers;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        pnameModels = new ArrayList<>();
        informationModels = new ArrayList<>();
        controlModels = new ArrayList<>();

        btn_Read = (Button) findViewById(R.id.btn_read);
        btn_Pname = (Button) findViewById(R.id.btn_pname);
        btn_Control = (Button) findViewById(R.id.btn_control);
        btn_Infor = (Button) findViewById(R.id.btn_infor);

        btn_Delete = (Button) findViewById(R.id.btn_delete);
        btn_Search = (Button) findViewById(R.id.btn_search);
        btn_DA = (Button) findViewById(R.id.btn_aya);

        tv_frequency = (TextView) findViewById(R.id.context_frequency);
        tv_packageSize = (TextView) findViewById(R.id.context_package_size);
        tv_delay = (TextView) findViewById(R.id.context_delay);
        tv_double_delay = (TextView) findViewById(R.id.context_doubledelay);
        tv_packageLost = (TextView) findViewById(R.id.context_package_lost);

        //返回所有查询结果
        List<PnameModel> pnameModelList = new Select().from(PnameModel.class).queryList();
        List<String> pnamelist = new ArrayList<>();
        if (pnameModelList.isEmpty()){
            Toast.makeText(this, "pnameModelList is Empty!",Toast.LENGTH_SHORT).show();
        }
        for (PnameModel pnameModel:pnameModelList){
            pnamelist.add(String.valueOf(pnameModel.getPackageName()));
            //Toast.makeText(this, String.valueOf(pnameModel.getPackageName()),Toast.LENGTH_SHORT).show();
        }
        spinnerPackageName = (Spinner) findViewById(R.id.spin_package_name);

        String[] strings = pnamelist.toArray(new String[pnamelist.size()]);

        spinnerResource = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, strings);
        spinnerResource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPackageName.setAdapter(spinnerResource);
        spinnerPackageName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String packageName = (String) spinnerResource.getItem(position);
                package_Name = Integer.valueOf(packageName);

                if (packageName != null){

                    String frequency = getFrequency(package_Name);
                    tv_frequency.setText(frequency);

                    String packageSize = getPackageSize(package_Name);
                    tv_packageSize.setText(packageSize);

                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                    /*}*/
                }
            }
        });

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.setText("");
                List<DAObject> daObjects = getDaObjects(package_Name);
                long singleDelay = 0;
                long doubleDelay = 0;
                int success = 0;
                log.append("Size:"+ daObjects.size()+"\n");
                for (DAObject daObject: daObjects){
                    log.append("NO:"+daObject.getNo()+"   ID:"+daObject.getId()+"  IND:"+daObject.getIndex()+"  END:"+daObject.isEnd()+"  SUC:"+daObject.getSuccess()+"\n"
                            +"CST:"+daObject.getCst()+"   SRT:"+daObject.getSrt()+"\n"
                            +"SST:"+daObject.getSst()+"   CRT:"+daObject.getCrt()+"\n"
                            +"SingleDelay:"+daObject.getSingleDelay()+"ms"+"   DoubleDelay:"+daObject.getDoubleDelay()+"\n");
                    log.append("\n");
                    if (daObject.getSingleDelay() != 0){
                        if (singleDelay == 0){
                            singleDelay = daObject.getSingleDelay();
                        }else {
                            singleDelay = (long) (singleDelay+daObject.getSingleDelay())/2;
                        }
                    }
                    if (daObject.getDoubleDelay() != 0){
                        if (doubleDelay == 0){
                            doubleDelay = daObject.getDoubleDelay();
                        }else {
                            doubleDelay = (long)(doubleDelay+daObject.getDoubleDelay())/2;
                        }
                    }
                    success = success + daObject.getSuccess();
                }
                tv_delay.setText(String.valueOf(singleDelay)+"ms");
                tv_double_delay.setText(String.valueOf(doubleDelay)+"ms");
                float packageLost = (daObjects.size()-success)/daObjects.size();
                tv_packageLost.setText(String.valueOf(packageLost)+"%");

            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(DebugActivity.this, DBTool.class);
                deleteIntent.setAction(DBTool.ACTION_DELETE);
                deleteIntent.putExtra(DBTool.TABLE_NAME, DBTool.TABLE_ALL);
                deleteIntent.putExtra(DBTool.PACKAGE_NAME, package_Name);
                startService(deleteIntent);
                Toast.makeText(getApplicationContext(),"Delete Success.",Toast.LENGTH_SHORT).show();
            }
        });

        btn_DA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daSummers = getDaSummers();
                log.setText("");
                for (DASummer daSummer: daSummers){
                    log.append("No:"+daSummer.getNo()+"   PackageName:"+daSummer.getPackageName()+"\n"
                            +"SingleDelay:"+daSummer.getSingleDelay()+"  DoubleDelay:"+daSummer.getDoubleDelay()+"  Lost:"+daSummer.getPackageLost());
                    log.append("\n");
                }
            }
        });



    }

    private List<DASummer> getDaSummers(){
        List<DASummer> daSummers = new ArrayList<>();
        if (pnameModels.size() != 0){
            int i = 1;
            for (PnameModel pnameModel: pnameModels){
                List<DAObject> daObjects = getDaObjects(pnameModel.getPackageName());
                long singleDelay = 0;
                long doubleDelay = 0;
                int success = 0;
                log.append("Size:"+ daObjects.size()+"\n");
                for (DAObject daObject: daObjects){
                    log.append("NO:"+daObject.getNo()+"   ID:"+daObject.getId()+"  IND:"+daObject.getIndex()+"  END:"+daObject.isEnd()+"  SUC:"+daObject.getSuccess()+"\n"
                            +"CST:"+daObject.getCst()+"   SRT:"+daObject.getSrt()+"\n"
                            +"SST:"+daObject.getSst()+"   CRT:"+daObject.getCrt()+"\n"
                            +"SingleDelay:"+daObject.getSingleDelay()+"ms"+"   DoubleDelay:"+daObject.getDoubleDelay()+"\n");
                    log.append("\n");
                    if (daObject.getSingleDelay() != 0){
                        if (singleDelay == 0){
                            singleDelay = daObject.getSingleDelay();
                        }else {
                            singleDelay = (long) (singleDelay+daObject.getSingleDelay())/2;
                        }
                    }
                    if (daObject.getDoubleDelay() != 0){
                        if (doubleDelay == 0){
                            doubleDelay = daObject.getDoubleDelay();
                        }else {
                            doubleDelay = (long)(doubleDelay+daObject.getDoubleDelay())/2;
                        }
                    }
                    success = success + daObject.getSuccess();
                }
                float packageLost = (daObjects.size()-success)/daObjects.size();
                DASummer daSummer = new DASummer();
                daSummer.setNo(i);
                daSummer.setPackageName(pnameModel.getPackageName());
                daSummer.setSingleDelay(singleDelay);
                daSummer.setDoubleDelay(doubleDelay);
                daSummer.setPackageLost(packageLost);
            }
        }
        return daSummers;
    }

    private List<DAObject> getDaObjects(int package_Name){
        int i = 1;
        List<DAObject> daObjects = new ArrayList<DAObject>();
        pnameInfor = new Select().from(InformationModel.class)
                .where(InformationModel_Table.packageName.is(package_Name)).queryList();
        for (InformationModel informationModel: pnameInfor){
            DAObject daObject = new DAObject();
            int id = informationModel.getId();
            daObject.setId(id);
            daObject.setNo(i);
            if (informationModel.isSuccess()){
                daObject.setSuccess(1);
            }else {
                daObject.setSuccess(0);
            }
            daObject.setIndex(informationModel.getIndexNum());
            daObject.setEnd(informationModel.getIsEndofPackage());
            daObject.setCst(informationModel.getTimeNow());

            ControlModel controlModel = new Select().from(ControlModel.class)
                    .where(ControlModel_Table.id.is(id)).querySingle();
            if (controlModel != null){
                daObject.setSrt(controlModel.getTimeReceive());
                daObject.setSst(controlModel.getTimeSendBack());
                daObject.setCrt(controlModel.getTimeMyReceive());
            }
            if (daObject.getCst()!=0 && daObject.getSrt()!=0){
                long singleDelay = daObject.getSrt()-daObject.getCst();
                daObject.setSingleDelay(singleDelay);
            }
            if (daObject.getCrt()!=0 && daObject.getSst()!=0){
                long doubleDelay = daObject.getCrt()-daObject.getSst()+(daObject.getSrt()-daObject.getCst());
                daObject.setDoubleDelay(doubleDelay);
            }
            daObjects.add(daObject);
            i ++;
        }
        return daObjects;
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

    private String getFrequency(int packageName){
        String frequency = null;
        InformationModel informationModel = SQLite.select().from(InformationModel.class)
                .where(InformationModel_Table.packageName.is(packageName)).querySingle();
        if (informationModel != null){
            frequency = informationModel.getFrequency();
        }else {
            Toast.makeText(getApplicationContext(), "informationModel is null" ,Toast.LENGTH_SHORT).show();
        }
        return frequency;
    }

    private String getPackageSize(int packageName){
        String packageSize = null;
        InformationModel informationModel = SQLite.select().from(InformationModel.class)
                .where(InformationModel_Table.packageName.is(packageName)).querySingle();
        if (informationModel != null){
            packageSize = informationModel.getPackageSize();
        }else {
            Toast.makeText(getApplicationContext(), "informationModel is null" ,Toast.LENGTH_SHORT).show();
            return null;
        }
        return packageSize;
    }
}
