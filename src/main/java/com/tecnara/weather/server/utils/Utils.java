package com.tecnara.weather.server.utils;

import com.google.gson.Gson;
import com.tecnara.weather.server.domain.Coordinates;

public class Utils {

    public static Coordinates parseCoordinates (String coordinates){
        Gson gson = new Gson();
        Coordinates coord = gson.fromJson(coordinates,Coordinates.class);
        return coord;
    }

    public static float fahrenheitToCelsius(float fahrenheit){
        float temperature = ((fahrenheit - 32.0f)/1.8f);
        return temperature;
    }

    public static float kelvinToCelsius (float kelvin){
        return (kelvin - 273.15f);
    }
}
