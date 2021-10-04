package com.company.core;

import com.company.constant.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private ServerSocket serverSocket;

    public ServerListenerThread(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        System.out.println("Server is running...");
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandlerThread clientHandlerThread = new ClientHandlerThread(socket);
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket error");
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    //TODO: handle exception
                }
            }
        }
    }
}
