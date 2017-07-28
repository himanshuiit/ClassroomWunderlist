package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugs.list.fetchbugFromList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class bugsInList {

    public static VBox lists;

    public static BorderPane leftPanel;
    public static BorderPane rightPanel;
    public static BorderPane view;

    public static BorderPane view(String companyName, String listName){

        lists = new VBox(15);

        Label header = new Label(listName);
        header.setPadding(new Insets(0));
        header.setFont(new Font("Cambria", 25));
        header.setTextFill(Color.web("#ededed"));

        ComboBox<String> myComboBox = new ComboBox<>();
        myComboBox.getItems().addAll(
                "Sort by Creation Date",
                "Sort Alphabetically",
                "Sort by Due Date",
                "Sort by Asignee",
                "Sort by Priority");
        myComboBox.setValue("Sort by Creation Date");

        BorderPane temp = new BorderPane(null,null,myComboBox,null,header);
        temp.setPadding(new Insets(20,30,20,0));
        BorderPane headerSection = new BorderPane(
                null,
                temp,
                null,
                null,
                null);


        myComboBox.valueProperty().addListener((ov, t, t1) -> {
            if (t1.equals("Sort by Creation Date"))
                fetching(companyName,listName, "ORDER BY timestamp asc");
            else if (t1.equals("Sort Alphabetically"))
                fetching(companyName,listName, "ORDER BY bugName asc");
            else if (t1.equals("Sort by Due Date"))
                fetching(companyName,listName, "ORDER BY deadline asc");
            else if (t1.equals("Sort by Asignee"))
                fetching(companyName,listName, "ORDER BY assigneeEmailId asc");
            else
                fetching(companyName,listName, "ORDER BY priority asc");
        });

        fetching(companyName, listName, "ORDER BY timestamp asc");

        ScrollPane bugs = new ScrollPane(lists);
        bugs.setStyle("-fx-background-color: transparent");
        bugs.setFitToWidth(true);

        leftPanel = new BorderPane(bugs,new VBox(headerSection),null,null,null);
        leftPanel.setPadding(new Insets(10,30,30,30));

        rightPanel = new BorderPane();
        rightPanel.setPadding(new Insets(10,30,30,30));

        view = new BorderPane(leftPanel);
        view.setStyle("-fx-background-color: transparent");

        return view;
    }

    public static void fetching(String companyName, String listName, String filter){
        String[][] bugsList = fetchbugFromList.fetchbugFromList(companyName, listName, filter);
        lists.getChildren().clear();
        if (bugsList[0][0].equals("SUCCESS")){
            for (int i=1; i<bugsList.length;++i )
                addList(bugsList[i][0]);
        }
    }

    public static void addList(String name){
        Label newBugs = new Label(name);
        newBugs.setPadding(new Insets(10));
        newBugs.setFont(new Font("Cambria", 15));
        newBugs.setTextFill(Color.web("#171717"));
        StackPane newBugsPane = new StackPane(newBugs);
        newBugsPane.setAlignment(Pos.BASELINE_LEFT);
        newBugsPane.setStyle("-fx-background-color: #f4f4ff");
        newBugsPane.setOnMouseEntered(e-> newBugsPane.setStyle("-fx-background-color: #e6f3f7"));
        newBugsPane.setOnMouseExited(e-> newBugsPane.setStyle("-fx-background-color: #f4f4ff"));
        newBugsPane.setCursor(Cursor.HAND);
        newBugsPane.setOnMouseClicked(e->{
            rightPanel.setCenter(rightPanelComments.rightPanelComments());
            view.setRight(rightPanel);
        });
        lists.getChildren().add(newBugsPane);
    }

}
