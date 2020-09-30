package com.tecnara.weather.server;

import com.tecnara.weather.server.domain.Coordinates;
import com.tecnara.weather.server.domain.openweather.Openweather;
import com.tecnara.weather.server.services.openweather.OpenWeatherServices;
import com.tecnara.weather.server.utils.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    ServerSocket serversocket;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public Server(){
        try {
            serversocket = new ServerSocket(3333);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void serverInit() {
        try {
            serversocket = new ServerSocket(3333);
            System.out.println("Listening...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void socketOpen() {

        try {
            socket = serversocket.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(String SendMsg) {
        try {
            dos.writeUTF(SendMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRequest() {
        try {
            String response = dis.readUTF();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (dis != null)
                dis.close();
            if (dos != null)
                dos.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Openweather getOpenweatherInfo(String coordinates){
        Coordinates coord = Utils.parseCoordinates(coordinates);
        Openweather openinfo = OpenWeatherServices.getCurrentMeteo(coord);
        return openinfo;
    }

    public void start(){
        while(true){
            System.out.println("Listening...");

            socketOpen();

            try {
                this.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String coordinates = getRequest();

            Openweather openinfo = getOpenweatherInfo(coordinates);

            sendResponse(openinfo.toMessage());

            close();

        }

    }

    public void retard(int time){
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
