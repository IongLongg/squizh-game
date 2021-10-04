package com.company;

import com.company.constant.Constant;
import com.company.core.ServerListenerThread;

import java.io.IOException;

public class QuizServer {

    public static void main(String[] args) {
        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(Constant.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverListenerThread.start();
    }
}

