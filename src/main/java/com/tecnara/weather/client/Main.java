package com.tecnara.weather.client;

import com.tecnara.weather.client.utils.Utils;

public class Main {
    public static void main(String[] args) {
        //Without threads
        /*
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
        */



        ClientThread c1 = new ClientThread();
        ClientThread c2 = new ClientThread();
        ClientThread c3 = new ClientThread();

        c1.start();
        c2.start();
        c3.start();
    }
}
