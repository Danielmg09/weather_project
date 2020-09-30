package com.tecnara.weather.server;

import com.tecnara.weather.server.domain.openweather.Openweather;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();

    }
}
