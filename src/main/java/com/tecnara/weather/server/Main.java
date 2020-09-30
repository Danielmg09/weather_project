package com.tecnara.weather.server;

import com.tecnara.weather.server.domain.openweather.Openweather;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        while (true) {

            System.out.println("Listening...");
            server.socketOpen();

            String coordinates = server.getResponse();

            Openweather openinfo = server.jsonToClass(coordinates);

            server.sendRequest(openinfo.toMessage());

            server.close();

        }
    }
}
