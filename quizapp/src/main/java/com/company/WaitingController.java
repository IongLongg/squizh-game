package com.company;

import com.company.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WaitingController implements Initializable {
    DataInputStream dis;
    @FXML
    public Label roomIdLabel;
    @FXML
    public Label n_playersLabel;

    @FXML
    private ListView<String> quizTitleListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;

        try {
            System.out.println(dis.readUTF());
            Type typeObject = new TypeToken<>
            Gson gson = new Gson();
;            //
//            List<Question> quizList = (List<Question>) dis.readObject();
//            quizList.forEach((quiz) -> quizTitleListView.getItems().add(quiz.getTitle()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRoom(String roomId) {
        roomIdLabel.setText("RoomID " + roomId);
    }
}
