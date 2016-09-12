package com.zubilay.yahooweather.model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteven on 9/9/2016.
 *
 * A class to hold the data I get from the yahoo API
 * This class will also handel the rest requests and data parsing.
 */


//@Generated("org.jsonschema2pojo")
public class WeatherData {

   @SerializedName("query")
   @Expose
    private Query query;

    /**
     *
     * @return
     * The query
     */
    public Query getQuery() {
        return query;
    }

    /**
     *
     * @param query
     * The query
     */
    public void setQuery(Query query) {
        this.query = query;
    }

}

