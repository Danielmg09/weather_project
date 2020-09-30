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

public class Server {

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

    public void sendRequest(String SendMsg) {
        try {
            dos.writeUTF(SendMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
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

    public Openweather jsonToClass (String coordinates){
        Coordinates coord = Utils.parseCoordinates(coordinates);
        Openweather openinfo = OpenWeatherServices.getCurrentMeteo(coord);
        return openinfo;
    }

    public void start(){
        while(true){
            try {
                socket = serversocket.accept();
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String coordinates = getResponse();
                System.out.println("Coordinates received");

                Coordinates coord = Utils.parseCoordinates(coordinates);
                Openweather openinfo = OpenWeatherServices.getCurrentMeteo(coord);

                sendRequest(openinfo.toMessage());
                close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
