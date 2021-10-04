package com.example.quizapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class WaitingController extends MyController implements Initializable {
    @FXML
    public Label roomIdLabel;
    @FXML
    public Label n_playersLabel;

    @FXML
    private ListView<String> playerListView;

    String[] players = {"jane", "john", "ben", "bean", "lee"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerListView.getItems().addAll(players);
    }

    @Override
    public void setSocket(Socket client, BufferedReader reader, BufferedWriter writer) {
        super.setSocket(client, reader, writer);
    }

}
