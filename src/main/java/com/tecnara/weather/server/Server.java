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

    ServerSocket serversocket = null;
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;


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


    public static void main(String[] args) {

        Server server = new Server();
        server.serverInit();

        while (true) {
            server.socketOpen();

            String coordinates = server.getResponse();
            System.out.println("Coordinates received");

            Coordinates coord = Utils.parseCoordinates(coordinates);
            Openweather openinfo = OpenWeatherServices.getCurrentMeteo(coord);

            server.sendRequest(openinfo.toMessage());

            server.close();


        }




    }
}