package com.zubilay.yahooweather.model;

/**
 * Created by Esteven on 9/12/2016.
 *
 * Class for holding various conversions we may need to do to the data before we can use it.
 */
public class YahooWeatherConversionUtils {

    String[] dirArray = {"N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW"};


    public String getDirectionAsString(String direction)
    {
        Double index = ((Integer.valueOf(direction) / 22.5 ) + 0.5);
        return  dirArray[index.intValue()];
    }
    public String getDirectionAsString(int direction)
    {
        Double index = ((direction / 22.5 ) + 0.5);
        return  dirArray[index.intValue()];
    }

}
