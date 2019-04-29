package com.example.umbrella.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.umbrella.R;

public class DetailsActivity extends AppCompatActivity {
    
    private static final String TAG = DetailsActivity.class.getSimpleName();

    public static final String ZIPCODE_REPLY = "com.example.umbrella.zipcode.REPLY";
    public static final String UNITS_REPLY = "com.example.umbrella.units.REPLY";
    
    EditText tvZipCode;
    Spinner spinner;
    ImageView ivArrow;
    static String zipCode, unit = "";
    static String system = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        try{
            getSupportActionBar().hide();
        }catch (NullPointerException ne){ne.printStackTrace();}
        
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        
        tvZipCode = findViewById(R.id.et_zipcode);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                    R.array.unit, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DetailsActivity.this, "" + spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(DetailsActivity.this, "" + tvZipCode.getText().toString(), Toast.LENGTH_SHORT).show();
                unit = spinner.getSelectedItem().toString();
                if(unit.equals("Celsius")){
                    unit = "metric";
                    system = "metric";
                }else{
                    unit = "imperial";
                    system = "imperial";
                }
                zipCode = tvZipCode.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //unit = spinner.getSelectedItem().toString();
        if(unit.equals("Celsius")){
            unit = "metric";
        }else{
            unit = "imperial";
        }

        //zipCode = tvZipCode.getText().toString();

        ivArrow = findViewById(R.id.iv_arrow);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "" + unit, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d(TAG, "onBackPressed: Inside onBackPressed!");
        Intent replyIntent = new Intent(); //this, MainActivity.class
        replyIntent.putExtra(ZIPCODE_REPLY, zipCode);
        replyIntent.putExtra(UNITS_REPLY, system);

        Log.d(TAG, "onBackPressed: " + "Unit: " + unit);

        setResult(RESULT_OK, replyIntent);

        Log.d(TAG, "onBackPressed: Ending DetailsActivity");

        //super.onBackPressed();

        finish();
    }
}
