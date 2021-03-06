package com.tecnara.weather.client;

import com.tecnara.weather.client.utils.Utils;

public class ClientThread extends Thread{
    @Override
    public void run() {
        Client client = new Client();

        //coordinates example
        String coordinates = "{\"lon\":13.5, \"lat\":13.3}";

        //Uncomment to ask coordinates
        //String coordinates = client.askCoordinates();


        if (Utils.checker(coordinates)){
            client.sendRequest(coordinates);
            System.out.println(client.getResponse());
        }else{
            System.out.println("This coordinates are not correct");
        }

        client.close();
    }
}
