package com.qq.vip.singleangel.v2i_information.DataBase.Model;

import com.qq.vip.singleangel.v2i_information.DataBase.ControlDatabase;
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
     * 服务器接收到Information的时间戳
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
     * 服务器发送回来的时间戳
     */
    @Column
    private long timeSendBack;

    public long getTimeSendBack() {
        return timeSendBack;
    }

    public void setTimeSendBack(long timeSendBack) {
        this.timeSendBack = timeSendBack;
    }

    /**
     * 客户端接收到信息的时间戳
     * 未添加@Column标记
     */
    @Column
    private long timeMyReceive;

    public long getTimeMyReceive() {
        return timeMyReceive;
    }

    public void setTimeMyReceive(long timeMyReceive) {
        this.timeMyReceive = timeMyReceive;
    }
}
