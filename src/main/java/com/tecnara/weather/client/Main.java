package com.tecnara.weather.client;

import com.tecnara.weather.client.utils.Utils;

public class Main {
    public static void main(String[] args) {
        ClientThread c1 = new ClientThread();
        ClientThread c2 = new ClientThread();
        ClientThread c3 = new ClientThread();
        c1.start();
        c2.start();
        c3.start();
    }
}
