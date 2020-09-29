package com.tecnara.weather.server.domain;

public class Coordinates {

    private float lat;
    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
    @Override
    public boolean equals(Object object){
        if (object.getClass().equals(Coordinates.class)){
            Coordinates c = (Coordinates)object;
            if (this.lat == c.lat && this.lon == c.lon){
                return true;
            }
        }
        return false;
    }
}
