package com.example.umbrella.view;

import com.example.umbrella.model.ForecastList;

public interface ViewContract {

    void addForecast(ForecastList dataSet);
    void onError(String errorMessage);

}
