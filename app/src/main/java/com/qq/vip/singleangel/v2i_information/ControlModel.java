package com.qq.vip.singleangel.v2i_information;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by singl on 2018/4/13.
 */
@Table(name = "ControlModel",database = ControlDatabase.class)
public class ControlModel extends BaseModel {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 接收到Information的时间戳
     */
    @Column
    private long timeReceive;

    public long getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(long timeReceive) {
        this.timeReceive = timeReceive;
    }

    /**
     * 发送回来的时间戳
     */
    @Column
    private long timeSendBack;

    public long getTimeSendBack() {
        return timeSendBack;
    }

    public void setTimeSendBack(long timeSendBack) {
        this.timeSendBack = timeSendBack;
    }
}
