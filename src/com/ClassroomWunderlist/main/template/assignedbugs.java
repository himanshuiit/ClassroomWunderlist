package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugs.assigned.fetchAssignedBugs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class assignedbugs {

    public static VBox lists;

    public static BorderPane leftPanel;
    public static BorderPane rightPanel;
    public static BorderPane view;

    public static BorderPane view(String companyName, String assigneeEmailId){

        Label header = new Label("Assigned to me");
        header.setPadding(new Insets(10));
        header.setFont(new Font("Cambria", 25));
        header.setTextFill(Color.web("#ededed"));
        StackPane headerPane = new StackPane(header);
        headerPane.setAlignment(Pos.BASELINE_LEFT);

        lists = new VBox(15);

        String[][] bugsList = fetchAssignedBugs.fetchAssignedBugs(companyName, assigneeEmailId);

        if (bugsList[0][0].equals("SUCCESS")){
            for (int i=1; i<bugsList.length;++i )
                addList(bugsList[i][0]);
        }

        ScrollPane bugs = new ScrollPane(lists);
        bugs.setStyle("-fx-background-color: transparent");
        bugs.setFitToWidth(true);

        leftPanel = new BorderPane(bugs,new VBox(headerPane),null,null,null);
        leftPanel.setPadding(new Insets(10,30,30,30));

        rightPanel = new BorderPane();
        rightPanel.setPadding(new Insets(10,30,30,30));

        view = new BorderPane(leftPanel);
        view.setStyle("-fx-background-color: transparent");

        return view;
    }

    public static void addList(String name){
        Label newBugs = new Label(name);
        newBugs.setPadding(new Insets(10));
        newBugs.setFont(new Font("Cambria", 15));
        newBugs.setTextFill(Color.web("#171717"));
        StackPane newBugsPane = new StackPane(newBugs);
        newBugsPane.setAlignment(Pos.BASELINE_LEFT);
        newBugsPane.setStyle("-fx-background-color: #f4f4ff");
        newBugsPane.setOnMouseEntered(e-> newBugsPane.setStyle("-fx-background-color: #dbdbe5"));
        newBugsPane.setOnMouseExited(e-> newBugsPane.setStyle("-fx-background-color: #f4f4ff"));
        newBugsPane.setCursor(Cursor.HAND);
        newBugsPane.setOnMouseClicked(e->{
            rightPanel.setCenter(rightPanelComments.rightPanelComments());
            view.setRight(rightPanel);
        });
        lists.getChildren().add(newBugsPane);
    }
}
