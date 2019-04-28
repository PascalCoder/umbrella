package com.example.umbrella.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umbrella.R;
import com.example.umbrella.model.ForecastList;
import com.example.umbrella.presenter.CustomAdapter;
import com.example.umbrella.presenter.Presenter;
import com.example.umbrella.presenter.PresenterContract;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ViewContract{

    private static final String TAG = MainActivity.class.getSimpleName();

    //@BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    PresenterContract presenter;
    @BindView(R.id.iv_settings)
    ImageView ivSettings;

    TextView tvCity, tvTemp, tvCondition;

    private final static int ZIPCODE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            getSupportActionBar().hide();
        }catch (NullPointerException ne){ne.printStackTrace();}

        tvCity = findViewById(R.id.tv_city);
        tvTemp = findViewById(R.id.tv_temp);
        tvCondition = findViewById(R.id.tv_condition);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter = new Presenter();
        presenter.bindView(this);
        presenter.initializeRetrofit();
        presenter.getForecasts(Presenter.zipCode, Presenter.units); //Presenter.zipCode

        tvCity.setText(Presenter.city);
        //double temp = Double.parseDouble(Presenter.temp);
        //tvTemp.setText("" + (temp) + "°");
        tvCondition.setText(Presenter.condition);

        Log.d(TAG, "onCreate: " + Presenter.zipCode + " " + Presenter.units + " City: " + Presenter.city);

    }

    @OnClick(R.id.iv_settings)
    public void submit(View view){
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivityForResult(intent, ZIPCODE_REQUEST);
    }

    @Override
    public void addForecast(ForecastList dataSet) {
        recyclerView.setAdapter(new CustomAdapter(dataSet));
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
