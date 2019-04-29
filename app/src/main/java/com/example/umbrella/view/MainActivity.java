package com.example.umbrella.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    public final static int UPDATE_REQUEST = 1;

    public static final String EXTRA_MESSAGE = "com.example.umbrella.extra.MESSAGE";

    //@BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    PresenterContract presenter;
    //@BindView(R.id.iv_settings)
    ImageView ivSettings;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    TextView tvCity, tvTemp, tvCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            getSupportActionBar().hide();
        }catch (NullPointerException ne){ne.printStackTrace();}

        toolbar = findViewById(R.id.toolbar);
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
        tvCondition.setText(Presenter.condition);

        ivSettings = findViewById(R.id.iv_settings);
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails(ivSettings);
            }
        });

    }

    //@OnClick(R.id.iv_settings)
    public void updateDetails(View view){
        String message = "Testing message";
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, UPDATE_REQUEST);

        Log.d(TAG, "updateDetails: Details Activity started!");
    }

    @Override
    public void addForecast(ForecastList dataSet) {
        recyclerView.setAdapter(new CustomAdapter(dataSet));

        Log.d(TAG, "onCreate: " + Presenter.zipCode + " " + Presenter.units + " City: " + Presenter.city + "Temp: " + Presenter.temp);
        tvCity.setText(Presenter.city);
        double temp = Double.parseDouble(Presenter.temp);
        tvTemp.setText("" + Math.round(temp) + "°");
        tvCondition.setText(Presenter.condition);

        if(Double.parseDouble(Presenter.temp) < 60){
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSkyBlue));
        }else{
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        }
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: " + "Back to main before check!");
        if(requestCode == UPDATE_REQUEST){
            if(resultCode == RESULT_OK){
                Log.d(TAG, "onActivityResult: " + "Back to main!");

                String zipCode = data.getStringExtra(DetailsActivity.ZIPCODE_REPLY);
                String units = data.getStringExtra(DetailsActivity.UNITS_REPLY);

                Log.d(TAG, "onActivityResult: Details: " + zipCode + " " + units);

                //Toast.makeText(this, "" + zipCode + " " + units , Toast.LENGTH_SHORT).show();

                presenter.bindView(this);
                presenter.initializeRetrofit();
                presenter.getForecasts(zipCode, units);
            }
        }
    }
}
