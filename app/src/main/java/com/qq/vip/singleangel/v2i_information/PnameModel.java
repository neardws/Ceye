package com.qq.vip.singleangel.v2i_information;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by singl on 2018/4/13.
 */
@Table(name = "pnameModel",database = PnameDatabase.class)
public class PnameModel extends BaseModel {
    @Column
    @PrimaryKey
    private int packageName;

    public int getPackageName() {
        return packageName;
    }

    public void setPackageName(int packageName) {
        this.packageName = packageName;
    }


}
