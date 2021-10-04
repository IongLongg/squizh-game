package com.example.quizapp.controller;

import com.example.quizapp.Main;
import com.example.quizapp.constant.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController extends MyController implements Initializable {
    @FXML
    private TextField roomIdText;
    @FXML
    private TextField nameText;
    @FXML
    private Button joinButton;
    @FXML
    private Label alertText;

    private boolean isJoined = false;

    Socket client;
    InputStream inputStream;
    OutputStream outputStream;
    InputStreamReader inputStreamReader;
    OutputStreamWriter outputStreamWriter;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InetAddress address;
        try {
            address = InetAddress.getByName(Constant.SERVER_IP);
            client = new Socket(address, Constant.SERVER_PORT);
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            alertText.setVisible(false);
        } catch (Exception ex) {
            alertText.setVisible(true);
            alertText.setText("Cannot connect to server");
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void joinRoom() {
        String roomId = roomIdText.getText();
        String name = nameText.getText();

        try {
            bufferedWriter.write(roomId+"\n");
            bufferedWriter.flush();
            bufferedWriter.write(name+"\n");
            bufferedWriter.flush();

            String response = bufferedReader.readLine();
            System.out.println("Response: " + response);
            if (response.equals("success")) {
                isJoined = true;
                System.out.println("User: " + name + " join room " + roomId);

                Main m = new Main();
                m.switchScene("waiting-view.fxml");
            } else if (response.equals("failed")) {
                System.out.println("Join failed");
                alertText.setVisible(true);
                alertText.setText("Fields are required");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}