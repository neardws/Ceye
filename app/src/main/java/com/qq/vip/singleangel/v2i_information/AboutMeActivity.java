package com.qq.vip.singleangel.v2i_information

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AboutMeActivity : AppCompatActivity() {
    private var tv_error_code: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)


        tv_error_code = findViewById(R.id.error_code) as TextView
        val error_code = ("61 ： GPS定位结果，GPS定位成功。\n"
                + "62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。\n"
                + "63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。\n"
                + "65 ： 定位缓存的结果。\n"
                + "66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。\n"
                + "67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。\n"
                + "68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。\n"
                + "161： 网络定位结果，网络定位定位成功。\n"
                + "162： 请求串密文解析失败。\n"
                + "167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。\n"
                + "502： key参数错误，请按照说明文档重新申请KEY。\n"
                + "505： key不存在或者非法，请按照说明文档重新申请KEY。\n"
                + "601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。\n"
                + "602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。\n"
                + "501～700：key验证失败，请按照说明文档重新申请KEY\n")
        tv_error_code!!.text = error_code
    }


}
