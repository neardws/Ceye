# Ceye
V2I simulation, Baidu map location, Car trace
2018.4.17 版本 1.0
 > 功能实现
 > * 上传Baidu 定位GPS信息
 > * 包传输信息到本地数据库，本地进行数据分析

## 2018.4.9
### bug
* 1、时间同步问题，差值为负数
* 2、使用大小不同的图片作为数据包进行发送

## 百度定位SDK
public int getLocType ( )返回值：
* 61 ： GPS定位结果，GPS定位成功。
* 62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
* 63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
* 65 ： 定位缓存的结果。
* 66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
* 67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
* 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
* 161： 网络定位结果，网络定位定位成功。
* 162： 请求串密文解析失败。
* 167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
* 502： key参数错误，请按照说明文档重新申请KEY。
* 505： key不存在或者非法，请按照说明文档重新申请KEY。
* 601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。
* 602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
* 501～700：key验证失败，请按照说明文档重新申请KEY

## 2018.4.10
### bug fixed
#### 1、 时间同步
使用阿里云NTP服务器同步时间

## 2018.4.12
### 频率与数据包大小
#### 初步确定发送频率
* 100ms
* 200ms
* 500ms
* 1s
* 5s
* 10s
#### 初步确定数据包大小
* 40B
* 100B
* 500B
* 1KB
### bug fixed
* 1、使用TXT文本作为数据包进行发送
* 2、增加AboutMe Activity
* 3、添加DBFlow依赖, 将发送的数据在本地数据库进行存储

## DBFlow
> *DBFlow是一个基于AnnotationProcessing(注解处理器)的强大、健壮同时又简单的ORM框架。*
> *此框架设计为了速度、性能和可用性。消除了大量死板的数据库代码，取而代之的是强大而又简介的API接口。*
> *DBFlow使数据库的操作变得简洁而稳定，让您能够更专注地编写令人惊讶的APP。*

> >[**Github**](https://github.com/Raizlabs/DBFlow)
> >[**中文文档**](https://link.jianshu.com/?t=https://yumenokanata.gitbooks.io/dbflow-tutorials/content/index.html)
> >[**英文文档**](https://legacy.gitbook.com/book/agrosner/dbflow/details)

## 2018.4.13
### 预期计划
* 读取GPS数据、可视化
* 从服务器接收数据，在本机统计时延、丢包率等
* 将JAVA项目迁移为Kotlin
* 现在代码越来越多，分组进行管理

### bug fixed
* 添加三张表 *InfomationModel* 、*PnameModel* 、*ControlModel*

```java

@Table(name = "informationModel",database = InformationDatabase.class)
public class InformationModel extends BaseModel {

    /**
     * 主键，用timeNow 和 deviceNo做hash
     * 可唯一确定某一个设备发送的一条信息
     */
    @PrimaryKey
    private int id;

    /**
     * 是否发送成功
     */
    @Column
    private boolean isSuccess;

    /**
     *  packageName 数据包名
     *  对首次发送数据包的时间戳进行哈希，可以确定单次发送的数据包
     */
    @Column
    private int packageName;

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

    @Column
    private String gpsType;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private float speed;

    @Column
    private float direction;

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

    @Column
    private long timeNow;

    @Column
    private String frequency;

    @Column
    private String packageSize;

    @Column
    private int isEndofPackage;

}

```

```java

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

```

```java

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

```

* 三张表的插入操作已实现

### 实际测试
安卓客户端使用Packet Capture捕获发送的数据包，实际上使用XUtils.post方法发送的40字节数据包为3KB

```groovy

POST /V2I/collect HTTP/1.1
If-Modified-Since: Fri, 13 Apr 2018 05:42:00 GMT
Content-Type: multipart/form-data; boundary=--------7da3d815208100x1.c40e2ac227d58p15
Content-Length: 2439
User-Agent: Dalvik/2.1.0 (Linux; U; Android 8.0.0; ONEPLUS A5010 Build/OPR1.170623.032)
Host: 118.24.19.160:8088
Connection: Keep-Alive
Accept-Encoding: gzip

----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="file"; filename="file40b"
Content-Type: application/octet-stream

IND##indexNum##LON##longitude##LAT##lati
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="deviceNo"
Content-Type: text/plain; charset=UTF-8

112161931
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="indexNum"
Content-Type: text/plain; charset=UTF-8

20
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="packageNum"
Content-Type: text/plain; charset=UTF-8

20
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="macAdd"
Content-Type: text/plain; charset=UTF-8

94:65:2D:3F:E7:20
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="speed"
Content-Type: text/plain; charset=UTF-8

0.0
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="timeNow"
Content-Type: text/plain; charset=UTF-8

1523598119736
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="latitude"
Content-Type: text/plain; charset=UTF-8

29.571414
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="longitude"
Content-Type: text/plain; charset=UTF-8

106.47717
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="direction"
Content-Type: text/plain; charset=UTF-8

76
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="coord_type_input"
Content-Type: text/plain; charset=UTF-8

bd09ll
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="frequency"
Content-Type: text/plain; charset=UTF-8

1s
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="packageSize"
Content-Type: text/plain; charset=UTF-8

40B
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="packageName"
Content-Type: text/plain; charset=UTF-8

-1191700514
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="isEndofPackage"
Content-Type: text/plain; charset=UTF-8

1
----------7da3d815208100x1.c40e2ac227d58p15
Content-Disposition: form-data; name="id"
Content-Type: text/plain; charset=UTF-8

-1052558956
----------7da3d815208100x1.c40e2ac227d58p15--

```

## 2018.4.14_第二次实验计划
### 实验时间
------------------
2018.4.14 周六下午，预计实验一个小时
### 实验地点
------------------
重庆市沙坪坝区虎峰山，山路，4G信号或许不稳定

### 实验设备
-------------------
#### 主要实验设备
* 一加5T
 > * 安卓版本 8.0
 > * 通信网络 中国移动4G
* 魅族手机
 > * 安卓版本 5.0
 > * 通信网络 中国移动4G
#### 对照实验设备
* 曼昆GPS追踪器
### 具体实验步骤
------------------
##### 实验分组
* 第一组

| 频率   |数据包大小  |实验时间 | 数据包数目 | 消耗流量 |
| :-----: | :-----:   | :----: | :----: | :----: |
| 100ms | 40B      |   3min |  1800   |     70KB |
| 200ms | 40B      |   3min |   900   |     35KB |
| 500ms | 40B      |   3min |   360   |     14KB |
|  1s   | 40B      |   3min |   180   |      7KB |
|  5s   | 40B      |   3min |    36   |    1400B |
|  10s  | 40B      |   3min |    18   |     720B |

* 第二组

| 频率   |数据包大小  |实验时间 | 数据包数目 | 消耗流量 |
| :-----: | :-----:   | :----: | :----: | :----: |
| 100ms | 500B     |   3min |  1800   |    878KB |
| 200ms | 500B     |   3min |   900   |    439KB |
| 500ms | 500B     |   3min |   360   |    175KB |
|  1s   | 500B     |   3min |   180   |     87KB |
|  5s   | 500B     |   3min |    36   |     17KB |
|  10s  | 500B     |   3min |    18   |      8KB |

* 第三组

| 频率   |数据包大小  |实验时间 | 数据包数目 | 消耗流量 |
| :-----: | :-----:   | :----: | :----: | :----: |
| 100ms | 40B      |   3min | 1800 |   70KB |
| 100ms | 100B     |   3min | 1800 |  175KB |
| 100ms | 500B     |   3min | 1800 |  878KB |
| 100ms | 1KB      |   3min | 1800 | 1800KB |

* 第四组

| 频率   |数据包大小  |实验时间 | 数据包数目 | 消耗流量 |
| :-----: | :-----:   | :----: | :----: | :----: |
| 500ms | 40B      |   3min | 360 |   14KB |
| 500ms | 100B     |   3min | 360 |   35KB |
| 500ms | 500B     |   3min | 360 |  175KB |
| 500ms | 1KB      |   3min | 360 |  360KB |

##### 实验流程
* 实验花费时间
> * 第一组 18min
> * 第二组 18min
> * 第三组 12min
> * 第四组 12min
> 总计需要大约1h

* 说明
> * 第一组与第二组分别将数据包大小固定为40Byte与500Byte，更改发送频率
> * 组内进行发送频率对时延、丢包率影响的实验，第一组与第二组互为对照组

> * 第三组与第四组分别将发送频率固定为100ms与500ms，更改发送的数据包大小
> * 组内进行数据包大小对时延、丢包率影响的实验，第三组与第四组互为对照组

> * 对采集的GPS轨迹信息进行分析，与对照实验设备曼昆GPS追踪器进行比较

* 服务器端统计数据

> * 时延
    > 时延获取通过接收与发送时间戳的差值，精确到ms

> * 丢包率
    > 每组实验中单次实验中实际获取到的数据包数目/理论上发送的数据包数目

### 预期实验结果
----------------------

对采集的数据进行分析，拟合曲线，得到影响时延、丢包率的关键因素

> * 时延
    > 预期时延小于100ms

> * 丢包率
    > 预期丢包率小于1%

## 2018.4.16
### Debug
> * 读取数据库后发现 *CRT* （客户端接收时间）都为空

```java

public final class ControlModel_Table extends ModelAdapter<ControlModel> {
  /**
   * Primary Key */
  public static final Property<Integer> id = new Property<Integer>(ControlModel.class, "id");

  public static final Property<Long> timeReceive = new Property<Long>(ControlModel.class, "timeReceive");

  public static final Property<Long> timeSendBack = new Property<Long>(ControlModel.class, "timeSendBack");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,timeReceive,timeSendBack};

```

> 发现ControlModel已更改，但是ControlModel_Table并为更改，故没有存储timeMyReceive时间，发现真实错误原因为为添加@Column标记

```java

    /**
     * 客户端接收到信息的时间戳
     * 未添加@Column标记
     */

    private long timeMyReceive;

    public long getTimeMyReceive() {
        return timeMyReceive;
    }

    public void setTimeMyReceive(long timeMyReceive) {
        this.timeMyReceive = timeMyReceive;
    }

```

### 2018.4.17
#### 版本发布
release 1.0

#### 预期实现功能
* UI更新
* 增加百度鹰眼SDK

### 2018.4.25
#### 添加功能
* 百度云推送
> [百度云推送官方文档](http://push.baidu.com/doc/guide/index)





