package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugs.list.fetchbugFromList;
import com.ClassroomWunderlist.database.taskUpdate.markComplete;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class bugsInList {

    public static VBox lists;

    public static BorderPane leftPanel;
    public static BorderPane rightPanel;
    public static BorderPane view;

    public BorderPane view(String companyName, String listName){

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
                "Sort by Priority",
                "Sort by not completed");
        myComboBox.setValue("Sort by Creation Date");

        BorderPane temp = new BorderPane(null,null,myComboBox,null,header);
        temp.setPadding(new Insets(20,30,20,0));

        TextField newBug = new TextField();

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
            else if (t1.equals("Sort by Priority"))
                fetching(companyName,listName, "ORDER BY priority asc");
            else
                fetching(companyName,listName, "ORDER BY checked asc");
        });

        fetching(companyName, listName, "ORDER BY timestamp asc");

        ScrollPane bugs = new ScrollPane(lists);
        bugs.setStyle("-fx-background-color: transparent");
        bugs.setFitToWidth(true);

        leftPanel = new BorderPane(bugs,new VBox(0,headerSection),null,null,null);
        leftPanel.setPadding(new Insets(10,10,30,10));

        rightPanel = new BorderPane();
        rightPanel.setPadding(new Insets(50,10,30,10));

        view = new BorderPane(leftPanel);
        view.setStyle("-fx-background-color: transparent");

        return view;
    }

    public void fetching(String companyName, String listName, String filter){
        String[][] bugsList = fetchbugFromList.fetchbugFromList(companyName, listName, filter);
        lists.getChildren().clear();
        if (bugsList[0][0].equals("SUCCESS")){
            for (int i=1; i<bugsList.length;++i )
                addList(companyName,listName,  bugsList[i][0], bugsList[i][1], bugsList[i][2], bugsList[i][3], bugsList[i][4]);
        }
    }

    public void addList(String companyName, String listName, String bugName, String assigneeEmailId, String deadline, String priority, String checked){

        CheckBox taskCheck = new CheckBox();
        if (checked.equals("true"))
            taskCheck.setSelected(true);
        taskCheck.setPadding(new Insets(10,0,10,10));
        taskCheck.setFont(new Font("Cambria", 15));

        taskCheck.selectedProperty().addListener((nodeValue,oldValue,newValue) ->{
            String status = markComplete.update(companyName,listName,bugName,newValue.toString());
            if (status.equals("success") || rightPanel.getCenter()!=null){
                rightPanelComments ob = new rightPanelComments();
                rightPanel.setCenter(ob.rightPanelComments(
                        companyName, listName, bugName, assigneeEmailId, deadline, priority, newValue.toString(), taskCheck.selectedProperty())
                );
            }
        });

        Label newBugs = new Label(bugName);
        newBugs.setPadding(new Insets(10,10,10,0));
        newBugs.setFont(new Font("Cambria", 15));
        newBugs.setTextFill(Color.web("#171717"));

        HBox hb = new HBox(10,taskCheck, newBugs);

        StackPane newBugsPane = new StackPane(hb);
        newBugsPane.setAlignment(Pos.BASELINE_LEFT);
        newBugsPane.setStyle("-fx-background-color: #f4f4ff");
        newBugsPane.setOnMouseEntered(e-> newBugsPane.setStyle("-fx-background-color: #e6f3f7"));
        newBugsPane.setOnMouseExited(e-> newBugsPane.setStyle("-fx-background-color: #f4f4ff"));
        newBugsPane.setCursor(Cursor.HAND);

        newBugsPane.setOnMouseClicked(e->{
            String latestCheck = taskCheck.isSelected() ? "true" : "false" ;
            rightPanelComments ob = new rightPanelComments();
            rightPanel.setCenter(ob.rightPanelComments(
                    companyName, listName, bugName, assigneeEmailId, deadline, priority, latestCheck, taskCheck.selectedProperty())
            );
            view.setRight(rightPanel);
        });
        lists.getChildren().add(newBugsPane);
    }

}
