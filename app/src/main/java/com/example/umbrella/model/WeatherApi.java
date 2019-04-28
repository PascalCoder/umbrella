package com.example.umbrella.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/forecast?APPID=4052514f0c50d212da8d03116d6f2560")//zip=55431&units=imperial&
    Call<ForecastList> getForecast(@Query("zip") String zipCode, @Query("units") String units);
}
