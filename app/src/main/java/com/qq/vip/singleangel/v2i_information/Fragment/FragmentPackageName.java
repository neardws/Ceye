package com.qq.vip.singleangel.v2i_information.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qq.vip.singleangel.v2i_information.R;

/**
 * Created by singl on 2018/4/16.
 */

public class FragmentPackageName extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public FragmentPackageName(){

    }

    public static FragmentPackageName newInstance(int sectionNumber) {
        FragmentPackageName fragment = new FragmentPackageName();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_package_name, container, false);
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                loadPname(rootView);
            }
        }).start();*/
        loadPname(rootView);
        return rootView;

    }

    private void loadPname(View rootView){
        ListView listView = (ListView) rootView.findViewById(R.id.listView_table_packageName);
       // List<PnameModel> pnameModelList = new Select().from(PnameModel.class).queryList();
        /*PnameModel pnameModel = new PnameModel();
        pnameModel.setPackageName(1000002442);
        List<PnameModel> pnameModelList = new ArrayList<>();
        pnameModelList.add(pnameModel);
        AdapterPackageName adapterPackageName = new AdapterPackageName(getContext(),R.layout.item_table_package_name, pnameModelList);
        listView.setAdapter(adapterPackageName);*/
    }



}
