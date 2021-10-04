package com.company.core;

import java.io.*;
import java.net.Socket;

public class ClientHandlerThread extends Thread {
    Socket client;
    InputStream inputStream;
    OutputStream outputStream;
    InputStreamReader inputStreamReader;
    OutputStreamWriter outputStreamWriter;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    boolean success = false;

    public ClientHandlerThread(Socket client) {
        this.client = client;
        System.out.println("Client thread started " + client);
    }

    @Override
    public void run() {
        try {
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            while (!success) {
                try {
                    String roomId = bufferedReader.readLine();
                    String name = bufferedReader.readLine();

                    if (roomId.length() == 0 || name.length() == 0) {
                        bufferedWriter.write("failed\n");
                        bufferedWriter.flush();
//                    this.interrupt();
                    } else {
                        bufferedWriter.write("success\n");
                        bufferedWriter.flush();

                        System.out.println("User: " + name + " join room: " + roomId);
                        success = true;
                        // create room
                    }


//                while (success) {
//                    if (client.isClosed()) {
//                        System.out.println("Client " + client + " disconnected");
//                        this.interrupt();
//                    }
//                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
