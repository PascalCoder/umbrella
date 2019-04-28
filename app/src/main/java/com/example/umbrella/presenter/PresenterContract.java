package com.example.umbrella.presenter;

import com.example.umbrella.view.ViewContract;

public interface PresenterContract {

    void bindView(ViewContract view);
    void initializeRetrofit();
    void getForecasts(String zipCode, String units);
    void onDestroy();
}
