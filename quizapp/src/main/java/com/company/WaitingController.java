package com.company;

import com.company.model.ClientRequest;
import com.company.model.Topic;
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
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WaitingController implements Initializable {
    DataInputStream dis;
    ArrayList<Topic> topicList;

    @FXML
    public Label roomIdLabel;

    @FXML
    private ListView<String> topicTitleListView;

    @FXML
    private Button joinRoomBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;

        try {
            String roomListJson = dis.readUTF();
            System.out.println(roomListJson);
            Gson gson = new Gson();
            Type typeObject = new TypeToken<ArrayList<Topic>>(){}.getType();
            topicList = gson.fromJson(roomListJson, typeObject);
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
        String userId = "1234";
        ClientRequest clientRequest = new ClientRequest(1, selectedTopicId.toString() + "," + userId);
    }
}
