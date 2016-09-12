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
        forecastList = new ArrayList<>(); // make an empty
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
        return 0;
    }
    public void setForecastViewListener(ForecastViewListener forecastViewListener)
    {
        this.forecastViewListener = forecastViewListener;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ForecastCellView forecastView;

        // check to see if we already have a ForecastView, if not create one.
        if(convertView != null && convertView instanceof ForecastCellView)
        {
            forecastView = (ForecastCellView)convertView;
        }
        else {

            forecastView = new ForecastCellView(parentContext);
        }
        Forecast forecast = forecastList.get(position);
        forecastView.setDay(forecast.getDay());
        forecastView.setDate(forecast.getDate());
        forecastView.setHigh(forecast.getHigh());
        forecastView.setLow(forecast.getLow());


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
