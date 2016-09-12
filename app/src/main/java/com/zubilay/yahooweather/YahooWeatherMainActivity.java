package com.zubilay.yahooweather;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zubilay.yahooweather.model.GsonRequest;
import com.zubilay.yahooweather.model.MySingleton;
import com.zubilay.yahooweather.model.POJO.Astronomy;
import com.zubilay.yahooweather.model.POJO.Atmosphere;
import com.zubilay.yahooweather.model.POJO.Condition;
import com.zubilay.yahooweather.model.POJO.Forecast;
import com.zubilay.yahooweather.model.POJO.WeatherData;
import com.zubilay.yahooweather.model.POJO.Wind;
import com.zubilay.yahooweather.model.YahooWeatherConversionUtils;
import com.zubilay.yahooweather.model.YahooWeatherEndpointBuilder;
import java.util.List;

public class YahooWeatherMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView locationTextView;
    TextView temperatureTextView;
    TextView conditionsTextView;
    TextView humidityTextView;
    TextView pressureTextView;
    TextView visibilityTextView;
    TextView windSpeedTextView;
    TextView sunriseTextView;
    TextView sunsetTextView;

    ListView listView;
    ForecastArrayAdapter adapter;
    WeatherData wd;
    String[] cityNameArray;

    String defaultCityString = "nome, ak";
    YahooWeatherEndpointBuilder yahooWeatherEndpointBuilder;
    YahooWeatherConversionUtils yahooWeatherConversionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yahoo_weather_main);
        yahooWeatherEndpointBuilder = new YahooWeatherEndpointBuilder();
        yahooWeatherConversionUtils = new YahooWeatherConversionUtils();

        locationTextView = (TextView) findViewById(R.id.landingTextView);
        temperatureTextView = (TextView) findViewById(R.id.currentTemperatureTextView);
        conditionsTextView = (TextView) findViewById(R.id.currentConditionsTextView);
        pressureTextView = (TextView) findViewById(R.id.pressureTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        visibilityTextView = (TextView) findViewById(R.id.visibilityTextView);
        windSpeedTextView = (TextView) findViewById(R.id.windSpeedTextView);
        sunriseTextView = (TextView) findViewById(R.id.sunriseTextView);
        sunsetTextView = (TextView) findViewById(R.id.sunsetTextView);

        adapter = new ForecastArrayAdapter(this,0);
        listView = (ListView) findViewById(R.id.forecastListView);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        // The listener for when a user touches a row.
        // since the data is not large and not changing dynamically for forecasts
        // we will pass it along in an intent
        adapter.setForecastViewListener(new ForecastViewListener() {
            @Override
            public void onForecastViewTouched(View v, long position) {
                Log.d("YahooWeatherMainActivit", " id ="+position);
                List<Forecast> forecastList = wd.getQuery().getResults().getChannel().getItem().getForecast();
                Forecast forecast = forecastList.get((int)position);

                Intent myIntent = new Intent(v.getContext(), ForecastDetailActivity.class);
                myIntent.putExtra("HEADER", wd.getQuery().getResults().getChannel().getDescription());
                myIntent.putExtra("DAY", forecast.getDay());
                myIntent.putExtra("DATE", forecast.getDate());
                myIntent.putExtra("HIGH", forecast.getHigh());
                myIntent.putExtra("LOW", forecast.getLow());
                myIntent.putExtra("CONDITIONS", forecast.getText());
                startActivityForResult(myIntent, 0);
            }
        } );


        Resources res = getResources();
        cityNameArray = res.getStringArray(R.array.location_array);


        //spinner for the various towns
        // I suppose I could let the users enter their own choices, that would make injection attacks easier
        // In this case it's yahoos problem not mine, but still.

        // spinner
        Spinner spinner = (Spinner) findViewById(R.id.weatherSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        requestWeatherData(yahooWeatherEndpointBuilder.setCity(defaultCityString));
    }

    void requestWeatherData(String yahooAPIString)
    {

        GsonRequest jsObjRequest = new GsonRequest(yahooAPIString,WeatherData.class ,null, new Response.Listener<WeatherData>() {

            @Override
            public void onResponse(WeatherData response) {
                wd = response;
                updateDisplay();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // If we don't get anything we  won't  update the display.
            }
        });

            MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);



    }

    void updateDisplay()
    {
        locationTextView.setText( wd.getQuery().getResults().getChannel().getDescription());

        Condition condition = wd.getQuery().getResults().getChannel().getItem().getCondition();
        temperatureTextView.setText(condition.getTemp());
        conditionsTextView.setText(condition.getText());

        Atmosphere atmosphere = wd.getQuery().getResults().getChannel().getAtmosphere();
        String humidityString =  atmosphere.getHumidity() + "%";
        humidityTextView.setText(humidityString);
        pressureTextView.setText(atmosphere.getPressure());
        visibilityTextView.setText(atmosphere.getVisibility());

        Wind wind =  wd.getQuery().getResults().getChannel().getWind();
        String windText =   yahooWeatherConversionUtils.getDirectionAsString(wind.getDirection()) + " "  + wind.getSpeed();
        windSpeedTextView.setText(windText);



        Astronomy astronomy = wd.getQuery().getResults().getChannel().getAstronomy();
        sunriseTextView.setText(astronomy.getSunrise());
        sunsetTextView.setText(astronomy.getSunset());


        List<Forecast> forecast = wd.getQuery().getResults().getChannel().getItem().getForecast();
        adapter.setForecast(forecast);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        requestWeatherData(yahooWeatherEndpointBuilder.setCity(cityNameArray[i]));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
