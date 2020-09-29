package com.tecnara.weather.server.utils;

import com.google.gson.Gson;
import com.tecnara.weather.server.domain.Coordinates;

public class Utils {

    public static Coordinates parseCoordinates (String coordinates){
        Gson gson = new Gson();
        Coordinates coord = gson.fromJson(coordinates,Coordinates.class);
        return coord;
    }
}
