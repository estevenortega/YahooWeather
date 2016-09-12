package com.zubilay.yahooweather;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zubilay.yahooweather.model.POJO.Forecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esteven on 9/10/2016.
 *
 * ArrayAdapter class for displaying forecasts
 */
public class ForecastArrayAdapter extends ArrayAdapter <Object> {

    List<Forecast> forecastList ;
    Activity parentContext;
    ForecastViewListener forecastViewListener;

    public ForecastArrayAdapter(Activity context, int resource) {
        super(context, resource);
        parentContext = context;
        forecastList = new ArrayList<Forecast>(); // make an empty
    }

    void setForecast(List<Forecast> forecastList )
    {
        this.forecastList = forecastList;
    }




    public int getCount() {
        return forecastList.size();
    }

    public Object getItem(int position) {
        return forecastList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        // For now we only have one item type
        int returnVal = 0;
        return returnVal;
    }
    public void setForecastViewListener(ForecastViewListener forecastViewListener)
    {
        this.forecastViewListener = forecastViewListener;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //inflate the custom view layout
        View forecastView = LayoutInflater.from(parentContext).inflate(R.layout.forcast_cell_layout,null);
        Forecast forecast = forecastList.get(position);

        TextView dayView = (TextView) forecastView.findViewById(R.id.forecastDayTextView);
        TextView dateView = (TextView) forecastView.findViewById(R.id.forecastDateTextView);
        TextView highView = (TextView) forecastView.findViewById(R.id.forecastHighTextView);
        TextView lowView = (TextView) forecastView.findViewById(R.id.foreCastLowTextView);

        dayView.setText(forecast.getDay());
        dateView.setText(forecast.getDate());
        highView.setText(forecast.getHigh());
        lowView.setText(forecast.getLow());

        final int fPosition = position;
        forecastView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(forecastViewListener != null)
                {
                    forecastViewListener.onForecastViewTouched(v,  fPosition);

                }
            }
        });

        return forecastView;
    }
}
