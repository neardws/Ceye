package com.qq.vip.singleangel.v2i_information;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by singl on 2018/4/12.
 */
@Table(database = CeyeDatabase.class)
public class InformationModel extends BaseModel {

    /**
     * 主键，用timeNow 和 deviceNo做hash
     * 可唯一确定某一个设备发送的一条信息
     */
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 是否发送成功
     */
    @Column
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     *  packageName 数据包名
     *  对首次发送数据包的时间戳进行哈希，可以确定单次发送的数据包
     */
    @Column
    private int packageName;

    public int getPackageName() {
        return packageName;
    }

    public void setPackageName(int packageName) {
        this.packageName = packageName;
    }

    /**
     * 设备信息
     * deviceNo      设备号，由设备MAC地址进行哈希得到，唯一确定一个设备
     * gpsType       GPS信号类型
     * longitude     经度
     * latitude      纬度
     * speed         速度
     * direction     方向
     */
    @Column
    private int deviceNo;

    public int getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Column
    private String gpsType;

    public String getGpsType() {
        return gpsType;
    }

    public void setGpsType(String gpsType) {
        this.gpsType = gpsType;
    }

    @Column
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Column
    private double latitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Column
    private float speed;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Column
    private float direction;

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    /**
     * 数据包信息
     * indexNum         数据包的序号
     * timeNow          发送时间戳
     * frequency        数据包发送频率
     * packageSize      数据包的大小
     * isEndofPackage   标志位，是否为最后一个包
     */
    @Column
    private int indexNum;

    public int getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }

    @Column
    private long timeNow;

    public long getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    @Column
    private String frequency;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Column
    private String packageSize;

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    @Column
    private int isEndofPackage;

    public int getIsEndofPackage() {
        return isEndofPackage;
    }

    public void setIsEndofPackage(int isEndofPackage) {
        this.isEndofPackage = isEndofPackage;
    }

}
