package com.company;

import com.company.constant.RequestCode;
import com.company.model.ClientRequest;
import com.company.model.Topic;
import com.company.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WaitingController implements Initializable {
    DataInputStream dis;
    DataOutputStream dos;
    ArrayList<Topic> topicList;
    User currentUser;
    Gson gson = new Gson();

    @FXML
    public Label userIdLabel;

    @FXML
    public Label userNameLabel;

    @FXML
    private ListView<String> topicTitleListView;

    @FXML
    private Button joinRoomBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDataStream(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        try {
            // display user info
            Type typeUser = new TypeToken<User>(){}.getType();
            String jsonUser = dis.readUTF();
            currentUser = gson.fromJson(jsonUser, typeUser);
            System.out.println("Current User: " + currentUser.toString());
            userIdLabel.setText("ID: " + currentUser.getUserId());
            userNameLabel.setText("Display Name: " + currentUser.getUserName());

            // display room list
            String roomListJson = dis.readUTF();
            System.out.println(roomListJson);
            Type typeTopicList = new TypeToken<ArrayList<Topic>>(){}.getType();
            topicList = gson.fromJson(roomListJson, typeTopicList);
            List<String> topicNames = new ArrayList<>();
            topicList.forEach(topic -> {
                topicNames.add(topic.getTopicName());
            });
            topicTitleListView.getItems().addAll(topicNames);
            topicTitleListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void joinRoom(ActionEvent event) {
        Integer selectedIndex = topicTitleListView.getSelectionModel().getSelectedIndex();
        Integer selectedTopicId = topicList.get(selectedIndex).getTopicId();
        ClientRequest request = new ClientRequest(RequestCode.USER_JOIN_ROOM, currentUser.getUserId() + "," + selectedTopicId.toString());
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        try {
            dos.writeUTF(jsonRequest);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
