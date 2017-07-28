package com.ClassroomWunderlist.main.template;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class bugsInList {

    public static ScrollPane view(String companyName, String listName){

        BorderPane bugs = new BorderPane(new Label(companyName+"  "+listName));
        bugs.setStyle("-fx-background-color: transparent");

        ScrollPane view = new ScrollPane(bugs);
        view.setStyle("-fx-background-color: transparent");
        return view;
    }


}
