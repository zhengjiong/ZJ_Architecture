package com.zj.example.arch.livedata.repository.api

import android.arch.lifecycle.LiveData
import com.zj.example.arch.livedata.repository.model.ApiResponse
import retrofit2.http.GET

/**
 *
 * CreateTime:2019/1/24  18:46
 * @author 郑炯
 * @version 1.0
 */
interface TestApi {

    //https://fuse-test.1919.cn/sys/init.do?addressLat=30.573834&addressLon=104.071303&apn=wifi&areaName=%E6%AD%A6%E4%BE%AF%E5%8C%BA&cityId=75&cityName=%E6%88%90%E9%83%BD%E5%B8%82&depotId=343651486535913474&deviceId=WcdsswvAkYoDAJpTy0LqWohE&lat=30.573834&locationAreaName=%E6%AD%A6%E4%BE%AF%E5%8C%BA&locationCityId=75&locationCityName=%E6%88%90%E9%83%BD%E5%B8%82&locationProvinceId=32&locationProvinceName=%E5%9B%9B%E5%B7%9D%E7%9C%81&locationType=baidu&lon=104.071303&os=androId&osVersion=8.0.0&partner=apk360&provinceId=32&provinceName=%E5%9B%9B%E5%B7%9D%E7%9C%81&sign=3688cd15f96feffe2f60985b7028a4a47219ac9e947cc4a18381eb40846aaa67&userAgent=Nexus+6P&version=6.5.0&warehouseDepotId=343651486783377411
    //https://fuse-test.1919.cn/sys/init.do?addressLat=30.573834&addressLon=104.071303&apn=wifi&areaName=武侯区&cityId=75&cityName=成都市&depotId=343651486535913474&deviceId=WcdsswvAkYoDAJpTy0LqWohE&lat=30.573834&locationAreaName=武侯区&locationCityId=75&locationCityName=成都市&locationProvinceId=32&locationProvinceName=四川省&locationType=baidu&lon=104.071303&os=androId&osVersion=8.0.0&partner=apk360&provinceId=32&provinceName=四川省&sign=3688cd15f96feffe2f60985b7028a4a47219ac9e947cc4a18381eb40846aaa67&userAgent=Nexus 6P&version=6.5.0&warehouseDepotId=343651486783377411
    //@GET("sys/init.do?addressLat=30.573834&addressLon=104.071303&apn=wifi&areaName=武侯区&cityId=75&cityName=成都市&depotId=343651486535913474&deviceId=WcdsswvAkYoDAJpTy0LqWohE&lat=30.573834&locationAreaName=武侯区&locationCityId=75&locationCityName=成都市&locationProvinceId=32&locationProvinceName=四川省&locationType=baidu&lon=104.071303&os=androId&osVersion=8.0.0&partner=apk360&provinceId=32&provinceName=四川省&sign=3688cd15f96feffe2f60985b7028a4a47219ac9e947cc4a18381eb40846aaa67&userAgent=Nexus 6P&version=6.5.0&warehouseDepotId=343651486783377411")
    @GET("sys/init.do")
    fun kuaiheInit(): LiveData<ApiResponse>
}