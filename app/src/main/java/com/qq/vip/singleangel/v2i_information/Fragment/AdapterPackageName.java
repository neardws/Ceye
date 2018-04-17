package com.qq.vip.singleangel.v2i_information.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qq.vip.singleangel.v2i_information.DataBase.Model.PnameModel;
import com.qq.vip.singleangel.v2i_information.R;

import java.util.List;

/**
 * Created by singl on 2018/4/16.
 */

public class AdapterPackageName extends ArrayAdapter<PnameModel>{

    List<PnameModel> pnameModels;
    Context context;
    int resource;

    public AdapterPackageName(@NonNull Context context, @LayoutRes int resource, List<PnameModel> pnameModels) {
        super(context, resource, pnameModels);
        this.pnameModels = pnameModels;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public PnameModel getItem(int position) {
        return pnameModels.get(position);
    }

    @NonNull
    @Override
    @SuppressLint("ViewHolder")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView tv_no          = (TextView) row.findViewById(R.id.table_packageName_no);
        TextView tv_packagename = (TextView) row.findViewById(R.id.table_packageName_pname);

        PnameModel pnameModel = getItem(position);
        tv_no.setText(position);
        if (pnameModel != null) {
            tv_packagename.setText(pnameModel.getPackageName());
        }else {
            tv_packagename.setText("PName is Null.");
        }

        return row;
    }
}
