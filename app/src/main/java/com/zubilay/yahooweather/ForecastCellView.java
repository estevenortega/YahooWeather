package com.zubilay.yahooweather;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Esteven on 9/12/2016.
 *
 * Custom cell for the ForecastArrayAdapter
 */
public class ForecastCellView extends LinearLayout {

    TextView dayView;
    TextView dateView;
    TextView highView;
    TextView lowView;



    public ForecastCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ForecastCellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public ForecastCellView(Context context) {
        super(context);
        init(context);


    }

   private void init(Context context)
   {
       inflate(context,R.layout.forcast_cell_layout, this);
       dayView = (TextView) findViewById(R.id.forecastDayTextView);
       dateView = (TextView) findViewById(R.id.forecastDateTextView);
       highView = (TextView) findViewById(R.id.forecastHighTextView);
       lowView = (TextView)  findViewById(R.id.foreCastLowTextView);



   }

    public void setDay(String day)
    {
        dayView.setText(day);
    }
    public void setDate(String date)
    {

        dateView.setText(date);
    }
    public void setHigh(String high)
    {

        highView.setText(high);
    }
    public void setLow(String low)
    {

        lowView.setText(low);
    }


}
