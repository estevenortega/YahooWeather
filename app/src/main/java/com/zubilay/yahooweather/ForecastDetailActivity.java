package com.zubilay.yahooweather;
/**
 * Created by Esteven on 9/10/2016.
 *
 * Activity for the details of the forecasts
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ForecastDetailActivity extends AppCompatActivity {

    TextView headerTextView;
    TextView dayTextView;
    TextView dateTextView;
    TextView highTextView;
    TextView lowTextView;
    TextView conditionsTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);

        headerTextView = (TextView) findViewById(R.id.forecastDetailHeaderTextView);
        dayTextView = (TextView) findViewById(R.id.forecastDetailDayTextView);
        dateTextView = (TextView) findViewById(R.id.forecastDetailDateTextView);
        highTextView = (TextView) findViewById(R.id.forecastDetailHighTextView);
        lowTextView = (TextView) findViewById(R.id.forecastDetailLowTextView);
        conditionsTextView = (TextView) findViewById(R.id.forecastConditionsTextView);


        Intent intent = getIntent();
        headerTextView.setText( intent.getStringExtra("HEADER"));
        dayTextView.setText( intent.getStringExtra("DAY"));
        dateTextView.setText( intent.getStringExtra("DATE"));
        highTextView.setText( intent.getStringExtra("HIGH"));
        lowTextView.setText( intent.getStringExtra("LOW"));
        conditionsTextView.setText( intent.getStringExtra("CONDITIONS"));


    }
}
