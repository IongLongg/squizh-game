package com.example.quizapp.controller;

import java.io.*;
import java.net.Socket;

public class MyController {
    private Socket client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void setSocket(Socket client, BufferedReader reader, BufferedWriter writer) {
        this.client = client;
        this.bufferedReader = reader;
        this.bufferedWriter = writer;
    }
}
