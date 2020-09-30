package com.tecnara.weather.client;

import com.tecnara.weather.client.utils.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;



    public Client(){
        try {
            this.socket = new Socket("localhost",3333);
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.dis = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String askCoordinates(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce latitude: ");
        float latitude = sc.nextFloat();
        System.out.println("Introduce longitude: ");
        float longitude = sc.nextFloat();
        return "{\"lon\":" + longitude + ", \"lat\":" + latitude + "}";

    }

    public void sendRequest(String SendMsg){
        try {
            dos.writeUTF(SendMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(){
        try {
            String response = dis.readUTF();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
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
}
