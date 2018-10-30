package com.example.huzheyuan.weatherapp.util;

import android.text.TextUtils;

import com.example.huzheyuan.weatherapp.db.City;
import com.example.huzheyuan.weatherapp.db.County;
import com.example.huzheyuan.weatherapp.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 该工具类主要用于解析服务器返回的Json数据
     */
    public static boolean handleProvinceResponse(String reponse) {
        if(!TextUtils.isEmpty(reponse)){
            try{//解析返回的省级数据
                JSONArray allProvinces = new JSONArray(reponse);
                for(int i = 0;i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int provinceId) {
        if(!TextUtils.isEmpty(response)){//字符串处理的工具类TextUtils
            try {//解析返回的市级数据
                JSONArray allcities = new JSONArray(response);
                for(int i=0;i < allcities.length();i++){
                    City city = new City();
                    JSONObject cityObject = allcities.getJSONObject(i);
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
            }
        }
        return false;
    }

    public static boolean handleCountyResponse (String response,int cityId) {
        if(!TextUtils.isEmpty(response)){
            try {//解析返回的县级数据
                JSONArray allCounties = new JSONArray(response);
                for(int i = 0;i < allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCityid(cityId);
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_Id"));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
