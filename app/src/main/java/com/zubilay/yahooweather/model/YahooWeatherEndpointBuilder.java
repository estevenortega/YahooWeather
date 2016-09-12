package com.zubilay.yahooweather.model;

import java.net.URLEncoder;

/**
 * Created by Esteven on 9/12/2016.
 *
 * Call to manage the endpoint.
 * Takes care of adding in new cities and holds the default endpoint string which I have set to nome, ak
 */
public class YahooWeatherEndpointBuilder {

    String baseURLString = "https://query.yahooapis.com/";
    String restRoute = "v1/public/yql?";
    String queryBegin = "q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22";
    String queryEnd = "%22)&format=json&env=store://datatables.org/alltableswithkeys";

    // the original string we are breaking up
    String yahooApi = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2c%20ak%22)&format=json&env=store://datatables.org/alltableswithkeys";

    public String setCity(String city)
    {
        String returnString;
        try {
            city = URLEncoder.encode(city, "utf-8");
            returnString = baseURLString + restRoute + queryBegin + city + queryEnd;
        }
        catch (Exception e)
        {
            // looks like we could not encode the city
            // reeturn the default endpoint
            returnString = yahooApi;
        }

        return returnString;
    }

}
