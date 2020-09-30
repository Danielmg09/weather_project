package com.tecnara.weather.server;

import com.tecnara.weather.server.domain.openweather.Openweather;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        server.startserver();

    }
}
