package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugComment.fetchbugsComment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class rightPanelComments {
    public static String[] months = {"","Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public static BorderPane bugComment;
    public static ScrollPane scroller;
    public static VBox fetchedComments;

    public static BorderPane rightPanelComments(String companyName, String listName, String bugName, String assigneeEmailId, String deadline, String priority, String checked){

        Label bugHeader = new Label(bugName);
        bugHeader.setWrapText(true);
        bugHeader.setPadding(new Insets(5));
        bugHeader.setFont(new Font("Cambria", 20));
        bugHeader.setTextFill(Color.web("#ededed"));
        ScrollPane bugHeaderPane = new ScrollPane(bugHeader);
        bugHeaderPane.setFitToWidth(true);
        bugHeaderPane.setPrefHeight(100);

        Label assignee = new Label("Assigned to "+assigneeEmailId);
        assignee.setPadding(new Insets(5));
        assignee.setFont(new Font("Cambria", 16));
        assignee.setTextFill(Color.web("#ededed"));

        Label milestone = new Label("Deadline: "+timeStampChangeFormat(deadline));
        milestone.setPadding(new Insets(5));
        milestone.setFont(new Font("Cambria", 16));
        milestone.setTextFill(Color.web("#ededed"));

        Label priorityLabel = new Label("Priority: "+ priority);
        priorityLabel.setPadding(new Insets(5));
        priorityLabel.setFont(new Font("Cambria", 16));
        priorityLabel.setTextFill(Color.web("#ededed"));
        priorityLabel.setStyle(priorityColorLabel(priority));

        VBox vb = new VBox(10);
        vb.setPrefWidth(300);
        vb.getChildren().addAll(bugHeaderPane,assignee,milestone, priorityLabel);
        vb.setAlignment(Pos.BASELINE_LEFT);

        fetchedComments = fetchbugsComment.fetchbugFromList(companyName,listName,bugName,assigneeEmailId);

        scroller = new ScrollPane(fetchedComments);
        scroller.setPadding(new Insets(30,0,0,0));
        scroller.setMaxWidth(300);
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedComments.heightProperty());

        BorderPane panel = new BorderPane(scroller,vb,null,null,null);
        return panel;
    }

    public static String timeStampChangeFormat(String orignal){
        String newTime;
        String[] timearray = orignal.split("\\.");

        if(Integer.parseInt(timearray[3])>12)
            newTime = (Integer.parseInt(timearray[3])-12) + ":" +timearray[4]+ "pm, ";
        else
            newTime = timearray[3] + ":" +timearray[4]+ "am, ";

        newTime = newTime + timearray[2] +" "+ months[Integer.parseInt(timearray[1])] +"'"+ timearray[0].substring(2);

        return newTime;
    }

    public static String priorityColorLabel(String priority) {
        if (priority.equals("P1"))
            return "-fx-background-color: #84e184";
        else if (priority.equals("P2"))
            return "-fx-background-color: #8484e1";
        else if (priority.equals("P3"))
            return "-fx-background-color: #3232cd";
        else if (priority.equals("P4"))
            return "-fx-background-color: #dc6f6f";
        else
            return "-fx-background-color: #cd3232";
    }

}
