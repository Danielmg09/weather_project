package com.tecnara.weather.client;

import com.tecnara.weather.client.utils.Utils;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        String coordinates = client.askCoordinates();

        if (Utils.checker(coordinates)){
            client.sendRequest(coordinates);
            System.out.println(client.getResponse());
        }else{
            System.out.println("This coordinates are not correct");
        }

        client.close();
    }
}
