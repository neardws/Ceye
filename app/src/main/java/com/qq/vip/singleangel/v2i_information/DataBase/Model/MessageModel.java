package com.qq.vip.singleangel.v2i_information.DataBase.Model;

import com.qq.vip.singleangel.v2i_information.DataBase.MessageDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by singl on 2018/4/26.
 */
@Table(name = "MessageModel",database = MessageDatabase.class)
public class MessageModel {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 消息的内容
     */
    @Column
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
