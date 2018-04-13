package com.qq.vip.singleangel.v2i_information

import java.io.Serializable

/**
 * Created by singl on 2018/3/23.
 */

class Information : Serializable {

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
    var deviceName: String? = null
    var deviceNo: Int = 0
    var macAdd: String? = null
    var coord_type_input: String? = null
    var indexNum: Int = 0
    var longitude: Double = 0.toDouble()
    var latitude: Double = 0.toDouble()
    var speed: Float = 0.toFloat()
    var direction: Float = 0.toFloat()
    var timeNow: Long = 0
    var time: String? = null
    var packageNum: Long = 0
    var baiduErrorCode: String? = null
    var frequency: String? = null
    var packageSize: String? = null
    var packageName: Int = 0
    var isEndofPackage: Int = 0

    constructor() {
        frequency = ""
        packageSize = ""
        packageName = 0
        isEndofPackage = 0
        deviceName = "Device"
        deviceNo = 0
        macAdd = ""
        coord_type_input = ""
        indexNum = 0
        longitude = 0.0
        latitude = 0.0
        speed = 0f
        direction = 0f
        timeNow = 0
        packageNum = 0
        time = ""
        baiduErrorCode = ""
    }

    constructor(frequency: String, packageSize: String, packageName: Int, isEndofPackage: Int,
                deviceName: String, deviceNo: Int, indexNum: Int, longitude: Double, latitude: Double,
                speed: Float, baiduErrorCode: String, coord_type_input: String, direction: Float,
                timeNow: Long, packageNum: Long, time: String, macAdd: String) {
        this.frequency = frequency
        this.packageSize = packageSize
        this.packageName = packageName
        this.isEndofPackage = isEndofPackage
        this.deviceName = deviceName
        this.deviceNo = deviceNo
        this.coord_type_input = coord_type_input
        this.indexNum = indexNum
        this.longitude = longitude
        this.latitude = latitude
        this.speed = speed
        this.direction = direction
        this.timeNow = timeNow
        this.packageNum = packageNum
        this.time = time
        this.macAdd = macAdd
        this.baiduErrorCode = baiduErrorCode
    }

    constructor(information: Information) {
        this.deviceNo = information.deviceNo
        this.indexNum = information.indexNum
        this.longitude = information.longitude
        this.latitude = information.latitude
        this.speed = information.speed
        this.direction = information.direction
        this.timeNow = information.timeNow
        this.packageNum = information.packageNum
        this.time = information.time
        this.coord_type_input = information.coord_type_input
        this.deviceName = information.deviceName
        this.baiduErrorCode = information.baiduErrorCode
        this.frequency = information.frequency
        this.packageSize = information.packageSize
        this.packageName = information.packageName
        this.isEndofPackage = information.isEndofPackage
    }

    override fun toString(): String {
        return ("DER" + "##" + deviceNo + "##"
                + "MAC" + "##" + macAdd + "##"
                + "IND" + "##" + indexNum + "##"
                + "LON" + "##" + longitude + "##"
                + "LAT" + "##" + latitude + "##"
                + "SPE" + "##" + speed + "##"
                + "DIR" + "##" + direction + "##"
                + "TIM" + "##" + timeNow + "##"
                + "PAC" + "##" + packageNum + "##")
    }

    companion object {

        val IOFMATION = "INFORMATION"
        /**
         * WGS84：为一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系
         * GCJ02：是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系
         * BD09：为百度坐标系，在GCJ02坐标系基础上再次加密。其中bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托米制坐标
         */
        val WGS84 = "wgs84"
        val GCJ = "gcj02"
        val BDO9 = "bd09"
    }

}
