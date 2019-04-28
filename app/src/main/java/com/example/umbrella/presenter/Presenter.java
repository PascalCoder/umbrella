package com.example.umbrella.presenter;

import android.util.Log;

import com.example.umbrella.model.Forecast;
import com.example.umbrella.model.ForecastList;
import com.example.umbrella.model.WeatherApi;
import com.example.umbrella.view.ViewContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter implements PresenterContract {

    private final String TAG = Presenter.class.getSimpleName();
    ViewContract viewContract;
    private WeatherApi api;
    private String BASE_URL = "http://api.openweathermap.org/";
    public static String zipCode = "55431";
    public static String units = "imperial";
    public static Forecast day1Forecast, day2Forecast, day3Forecast, day4Forecast, day5Forecast;
    public static String day1Date, day2Date, day3Date, day4Date, day5Date;
    public static String[] daysDate = new String[5];
    public static ForecastList[] forecastListArray = new ForecastList[5];
    public static ArrayList<com.example.umbrella.model.List> [] lists = new ArrayList[5];
    public static List<Forecast> days;
    public String _day;
    public String date;
    public String time;
    public static String city, condition;
    public static String temp;

    @Override
    public void bindView(ViewContract view) {
        this.viewContract = view;
    }

    @Override
    public void initializeRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        api = retrofit.create(WeatherApi.class);
    }

    @Override
    public void getForecasts(String zipCode, String units) {

        /*if(zipCode.equals("") || zipCode == null){zipCode = "55431";}
        if(units.equals("") || units == null){units = "imperial";}*/

        initializeArrayLists();

        api.getForecast(zipCode, units).enqueue(new Callback<ForecastList>() {
            @Override
            public void onResponse(Call<ForecastList> call, Response<ForecastList> response) {
                if(response.body() != null){
                    days = new ArrayList<>();
                    Forecast day = new Forecast();
                    day.setTemps(new ArrayList<>());
                    day.setTimes(new ArrayList<>());
                    viewContract.addForecast(response.body());
                    Log.d(TAG, "onResponse: " + response.body().getList().size());

                    day1Date = response.body().getList().get(0).getDateTime();
                    day1Date = day1Date.substring(0, day1Date.indexOf(" "));
                    daysDate[0] = day1Date;

                    city = response.body().getCity().getName();
                    temp = "" + response.body().getList().get(0).getMain().getTemp();
                    condition = response.body().getList().get(0).getWeather().get(0).getMain();

                    String tempDate = response.body().getList().get(0).getDateTime();
                    tempDate = tempDate.substring(0, tempDate.indexOf(" "));

                    int j = 1;
                    for(int i = 0; i < response.body().getList().size(); i++){
                        String date = response.body().getList().get(i).getDateTime();
                        date = date.substring(0, date.indexOf(" "));

                        if(tempDate.equals(date)){
                            continue;
                        }else{
                            if(j < daysDate.length) {
                                daysDate[j] = date;
                                Log.d(TAG, "onResponse: " + daysDate[j]);
                                j++;
                                //k++;
                            }else{break;}
                            tempDate = response.body().getList().get(i).getDateTime();
                            tempDate = tempDate.substring(0, tempDate.indexOf(" "));
                        }
                    }

                    int l = 0;
                    for(int i = 0; i < response.body().getList().size(); i++){
                        String date = response.body().getList().get(i).getDateTime();
                        date = date.substring(0, date.indexOf(" "));

                        if(daysDate[l].equals(date)){
                            lists[l].add(response.body().getList().get(i));
                            continue;
                        }else{
                            l++;
                            if(l < daysDate.length){
                                lists[l].add(response.body().getList().get(i));
                            }else{
                                break;
                            }

                        }
                    }
                    Log.d(TAG, "onResponse: " + "Lists: " + lists[0].size());

                }
                else{
                    viewContract.addForecast(new ForecastList());
                }
            }

            @Override
            public void onFailure(Call<ForecastList> call, Throwable t) {
                Log.d(TAG, "onFailure: Something went wrong!");
            }
        });
    }

    @Override
    public void onDestroy() {
        viewContract = null;
    }

    public void initializeArrayLists(){
        for(int i = 0; i < lists.length; i++){
            lists[i] = new ArrayList<>(8);
        }
    }
}
