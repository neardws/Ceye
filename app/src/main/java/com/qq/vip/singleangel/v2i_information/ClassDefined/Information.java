package com.qq.vip.singleangel.v2i_information.ClassDefined;

import java.io.Serializable;

/**
 * Created by singl on 2018/3/23.
 */

public class Information implements Serializable{

    public static final String IOFMATION = "INFORMATION";
   /**
    *   WGS84：为一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系
    *   GCJ02：是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系
    *   BD09：为百度坐标系，在GCJ02坐标系基础上再次加密。其中bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托米制坐标
    *   */
    public static final String WGS84     = "wgs84";
    public static final String GCJ       = "gcj02";
    public static final String BDO9      = "bd09";

    /**
     * frequency            发送的频率
     * packageSize          发送数据包的大小
     * packageName          对此次开始发送时间进行哈希
     * isEndofPackage       标志位，是否为最后一个包，默认为0
     * deviceName           设备名字
     * deviceNo             设备号
     * macAdd               MAC地址
     * coord_type_input     坐标类型
     * indexNum             序号
     * longitude            经度
     * latitude             纬度
     * speed                速度
     * direction            方向
     * timeNow              时间戳
     * packageNum           总的数据包的数目
     */
    private String      deviceName;
    private String      deviceNo;
    private String      macAdd;
    private String      coord_type_input;
    private int         indexNum;
    private double      longitude;
    private double      latitude;
    private float       speed;
    private float       direction;
    private long        timeNow;
    private String      time;
    private long        packageNum;
    private String      baiduErrorCode;
    private String      frequency;
    private String      packageSize;
    private int         packageName;
    private int         isEndofPackage;
    public Information(){
        frequency           = "";
        packageSize         = "";
        packageName         = 0;
        isEndofPackage      = 0;
        deviceName          = "Device";
        deviceNo            = "";
        macAdd              = "";
        coord_type_input    = "";
        indexNum            = 0;
        longitude           = 0;
        latitude            = 0;
        speed               = 0;
        direction           = 0;
        timeNow             = 0;
        packageNum          = 0;
        time                = "";
        baiduErrorCode      = "";
    }

    public Information(String frequency, String packageSize, int packageName, int isEndofPackage,
                        String deviceName, String deviceNo, int indexNum, double longitude, double latitude,
                        float speed, String baiduErrorCode, String coord_type_input, float direction,
                        long timeNow, long packageNum, String time, String macAdd){
        this.frequency        = frequency;
        this.packageSize      = packageSize;
        this.packageName      = packageName;
        this.isEndofPackage   = isEndofPackage;
        this.deviceName       = deviceName;
        this.deviceNo         = deviceNo;
        this.coord_type_input = coord_type_input;
        this.indexNum         = indexNum;
        this.longitude        = longitude;
        this.latitude         = latitude;
        this.speed            = speed;
        this.direction        = direction;
        this.timeNow          = timeNow;
        this.packageNum       = packageNum;
        this.time             = time;
        this.macAdd           = macAdd;
        this.baiduErrorCode   = baiduErrorCode;
    }

    public Information(Information information){
        this.setDeviceNo(information.getDeviceNo());
        this.setIndexNum(information.getIndexNum());
        this.setLongitude(information.getLongitude());
        this.setLatitude(information.getLatitude());
        this.setSpeed(information.getSpeed());
        this.setDirection(information.getDirection());
        this.setTimeNow(information.getTimeNow());
        this.setPackageNum(information.getPackageNum());
        this.setTime(information.getTime());
        this.setCoord_type_input(information.getCoord_type_input());
        this.setDeviceName(information.getDeviceName());
        this.setBaiduErrorCode(information.getBaiduErrorCode());
        this.setFrequency(information.getFrequency());
        this.setPackageSize(information.getPackageSize());
        this.setPackageName(information.getPackageName());
        this.setIsEndofPackage(information.getIsEndofPackage());
    }

    public void setPackageName(int packageName) {
        this.packageName = packageName;
    }

    public void setIsEndofPackage(int isEndofPackage) {
        this.isEndofPackage = isEndofPackage;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setBaiduErrorCode(String baiduErrorCode){
        this.baiduErrorCode = baiduErrorCode;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setCoord_type_input(String coord_type_input) {
        this.coord_type_input = coord_type_input;
    }

    public void setMacAdd(String macAdd) {
        this.macAdd = macAdd;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    public void setPackageNum(long packageNum) {
        this.packageNum = packageNum;
    }

    public int getIndexNum() {
        return indexNum;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDirection() {
        return direction;
    }

    public long getTimeNow() {
        return timeNow;
    }

    public long getPackageNum() {
        return packageNum;
    }

    public String getTime() {
        return time;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public String getMacAdd() {
        return macAdd;
    }

    public String getCoord_type_input() {
        return coord_type_input;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBaiduErrorCode() {
        return baiduErrorCode;
    }

    public int getIsEndofPackage() {
        return isEndofPackage;
    }

    public int getPackageName() {
        return packageName;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getPackageSize() {
        return packageSize;
    }

    @Override
    public String toString(){
        String string =  "DER"  +"##"+  deviceNo    +"##"
                        +"MAC"  +"##"+  macAdd      +"##"
                        +"IND"  +"##"+  indexNum    +"##"
                        +"LON"  +"##"+  longitude   +"##"
                        +"LAT"  +"##"+  latitude    +"##"
                        +"SPE"  +"##"+  speed       +"##"
                        +"DIR"  +"##"+  direction   +"##"
                        +"TIM"  +"##"+  timeNow     +"##"
                        +"PAC"  +"##"+  packageNum  +"##";
        return string;
    }

}
