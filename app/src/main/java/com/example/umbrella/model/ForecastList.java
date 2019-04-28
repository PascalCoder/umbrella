package com.example.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastList {

    @SerializedName("cnt")
    @Expose
    private Integer count;

    @SerializedName("list")
    @Expose
    private java.util.List<com.example.umbrella.model.List> list = null;

    @SerializedName("city")
    @Expose
    private City city;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
