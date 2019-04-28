package com.example.umbrella.model;

import java.util.List;

public class Forecast {

    private String city;

    private String temp;

    private String condition;

    private String day;

    private List<String> times = null;

    private List<Double> temps = null;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<Double> getTemps() {
        return temps;
    }

    public void setTemps(List<Double> temps) {
        this.temps = temps;
    }
}
