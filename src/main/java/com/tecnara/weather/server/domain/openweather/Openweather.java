package com.tecnara.weather.server.domain.openweather;

import java.util.List;

public class Openweather {

    private String name;
    private List<Weather> weather;
    private Wind wind;
    private Main main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String toMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("Location: ");
        sb.append(this.name).append("\nMain weather: ").append(weather.get(0).getMain());
        sb.append(", ").append(this.weather.get(0).getDescription());
        sb.append("\nTemperature: ").append(this.main.getTemp());
        sb.append(",\nHumidity: ").append(this.main.getHumidity());
        sb.append("\nWind speed: ").append(this.wind.getSpeed());
        return sb.toString();
    }
}
