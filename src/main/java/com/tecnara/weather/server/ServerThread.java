package com.tecnara.weather.server;

import com.tecnara.weather.server.domain.Coordinates;
import com.tecnara.weather.server.domain.openweather.Openweather;
import com.tecnara.weather.server.services.openweather.OpenWeatherServices;
import com.tecnara.weather.server.utils.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    ServerThread(Socket socket) {
        this.socket = socket;
        try {
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

    public Openweather getOpenweatherInfo(String coordinates) {
        Coordinates coord = Utils.parseCoordinates(coordinates);
        Openweather openinfo = OpenWeatherServices.getCurrentMeteo(coord);
        return openinfo;
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


    @Override
    public void run() {
        try {
            String coordinates = getRequest();
            Thread.sleep(5000);
            Openweather openinfo = getOpenweatherInfo(coordinates);
            sendResponse(openinfo.toMessage());
            close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}



