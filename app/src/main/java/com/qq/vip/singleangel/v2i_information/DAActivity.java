package com.qq.vip.singleangel.v2i_information;

import android.service.notification.Condition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class DAActivity extends AppCompatActivity {

    private Spinner spinnerPackageName;
    private ArrayAdapter<String> spinnerResource;

    private TextView tv_delay;
    private TextView tv_package_lost;

    private TextView tv_frequency;
    private TextView tv_package_size;

    private ListView listViewItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);

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

        tv_delay        = (TextView) findViewById(R.id.context_delay);
        tv_frequency    = (TextView) findViewById(R.id.context_frequency);
        tv_package_lost = (TextView) findViewById(R.id.context_package_lost);
        tv_package_size = (TextView) findViewById(R.id.context_package_size);

        spinnerPackageName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String packageName = (String) spinnerResource.getItem(position);
                if (packageName != null){
                    int intPackageName = Integer.valueOf(packageName);

                    String frequency = getFrequency(intPackageName);
                    tv_frequency.setText(frequency);

                    String packageSize = getPackageSize(intPackageName);
                    tv_package_size.setText(packageSize);

                    String avarageDelay = getDelay(intPackageName);
                    if (avarageDelay == null){

                    }else {
                        tv_delay.setText(avarageDelay);
                    }

                    //Toast.makeText(getApplicationContext(), "数据包哈希值为" + packageName ,Toast.LENGTH_SHORT).show();
                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private ArrayList<DAObject> getDAOjects (int packageName){
        ArrayList<DAObject> daObjects = new ArrayList<>();
        int i = 1;
        List<InformationModel> informationModels = SQLite.select().from(InformationModel.class)
                .where(InformationModel_Table.packageName.is(packageName)).queryList();
        if (informationModels.size()==0){
            Toast.makeText(getApplicationContext(), "informationModel is null" ,Toast.LENGTH_SHORT).show();
            return null;
        }else {
            for (InformationModel informationModel: informationModels){
                int id = informationModel.getId();
                ControlModel controlModel = SQLite.select().from(ControlModel.class)
                        .where(ControlModel_Table.id.is(id)).querySingle();
                DAObject daObject = new DAObject();
                daObject.setId(i++);
                daObject.setNo(id);
                daObject.setIndex(informationModel.getIndexNum());
                daObject.setEnd(informationModel.getIsEndofPackage());
                daObject.setCst(informationModel.getTimeNow());
                daObject.setCrt(controlModel.getTimeMyReceive());
                daObject.setSrt(controlModel.getTimeReceive());
                daObject.setSst(controlModel.getTimeSendBack());
                daObject.setDelay();
                daObjects.add(daObject);
            }
            return daObjects;
        }

    }

    private String getDelay(int packageName){
        ArrayList<DAObject> daObjects = getDAOjects(packageName);
        if (daObjects==null){
            return "error,no data.";
        }
        long delay = 0;
        int i = 0;
        for (DAObject daObject:daObjects){
            if (daObject.getDelay() != 0){
                delay = delay + daObject.getDelay();
                i = i + 1;
            }
        }
        long avarageDelay = (long) delay / i;
        String strDelay = avarageDelay + "ms";
        return strDelay;
    }

}
