package com.ClassroomWunderlist.main.template;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class assignedbugs {

    public static ScrollPane view(String assigneeEmailId){

        BorderPane bugs = new BorderPane(new Label(assigneeEmailId));
        bugs.setStyle("-fx-background-color: transparent");

        ScrollPane view = new ScrollPane(bugs);
        view.setStyle("-fx-background-color: transparent");
        return view;
    }

}
