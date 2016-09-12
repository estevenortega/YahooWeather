package com.zubilay.yahooweather;

import android.view.View;

/**
 * Created by Esteven on 9/10/2016.
 *
 * listener for use with the forecast array adapter cells
 */
public interface ForecastViewListener {
        void onForecastViewTouched(View v, long position);

    }
