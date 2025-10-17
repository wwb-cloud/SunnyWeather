package com.sunnyweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location

class WeatherViewModel : ViewModel() {
    // 存储位置信息的 LiveData
    private val locationLiveData = MutableLiveData<Location>()

    // 存储经纬度和地名的变量
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    // 直接使用 switchMap 扩展函数转换 LiveData
    val weatherLiveData = locationLiveData.switchMap { location ->
        // 当 locationLiveData 变化时，自动触发 Repository 的刷新操作
        Repository.refreshWeather(location.lng, location.lat)
    }

    // 对外提供的刷新天气方法
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}