package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugComment.fetchbugsComment;
import com.ClassroomWunderlist.database.bugComment.addNewComment;

import javafx.beans.property.BooleanProperty;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class rightPanelComments {
    public static String[] months = {"","Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public static ScrollPane scroller;
    public static VBox fetchedComments;

    public static final Pattern VALID_STRING_REGEX = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);

    public BorderPane rightPanelComments(String companyName, String listName, String bugName, String assigneeEmailId, String deadline, String priority, String checked, BooleanProperty checkBox, String currentUserEmailId){

        CheckBox taskCheck = new CheckBox();
        if (checked.equals("true")){
            taskCheck.setSelected(true);
        }
        taskCheck.setPadding(new Insets(15,0,-5,5));
        taskCheck.setFont(new Font("Cambria", 15));
        taskCheck.selectedProperty().bindBidirectional(checkBox);       //Best line I ever wrote so far

        Label newBugs = new Label(bugName);
        newBugs.setWrapText(true);
        newBugs.setPadding(new Insets(5,5,5,0));
        newBugs.setFont(new Font("Cambria", 20));
        newBugs.setTextFill(Color.web("#ededed"));

        HBox hb = new HBox(10,taskCheck, newBugs);
        hb.setPadding(new Insets(25,0,0,0));

        StackPane bugHeaderPane = new StackPane(hb);
//        bugHeaderPane.setFitToWidth(true);
        bugHeaderPane.setPrefHeight(100);

        if(assigneeEmailId == null || assigneeEmailId.isEmpty())
            assigneeEmailId= "(none)";

        Label assignee = new Label("Assigned to "+assigneeEmailId);
        assignee.setPadding(new Insets(5));
        assignee.setFont(new Font("Cambria", 16));
        assignee.setTextFill(Color.web("#ededed"));

        if(deadline == null || deadline.isEmpty())
            deadline = "(not set)";
        else
            deadline = timeStampChangeFormat(deadline);

        Label milestone = new Label("Deadline: "+ deadline);
        milestone.setPadding(new Insets(5));
        milestone.setFont(new Font("Cambria", 16));
        milestone.setTextFill(Color.web("#ededed"));

        if(priority == null || priority.isEmpty())
            priority = "(not set)";

        Label priorityLabel = new Label("Priority: "+ priority);
        priorityLabel.setPadding(new Insets(5));
        priorityLabel.setFont(new Font("Cambria", 16));
        priorityLabel.setTextFill(Color.web("#ededed"));
        priorityLabel.setStyle(priorityColorLabel(priority));

        VBox vb = new VBox(10);
        vb.setPrefWidth(300);
        vb.getChildren().addAll(bugHeaderPane,assignee,milestone, priorityLabel);
        vb.setAlignment(Pos.BASELINE_LEFT);

        fetchedComments = fetchbugsComment.fetchbugsComment(companyName,listName,bugName,currentUserEmailId);

        scroller = new ScrollPane(fetchedComments);
        scroller.setPadding(new Insets(30,0,0,0));
        scroller.setMaxWidth(300);
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedComments.heightProperty());

        BorderPane mymessageCorner = new BorderPane();
        mymessageCorner.setPadding(new Insets(15,0,0,0));

        TextArea newComment = new TextArea();
        newComment.setPromptText("Enter your comment here");
        newComment.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newComment.setWrapText(true);
        newComment.setPrefHeight(50);
        newComment.setPrefWidth(270);

        Button send = new Button("Send");
        send.setPrefHeight(50);
        send.setFont(new Font("Cambria", 16));
        send.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent; ; -fx-border: 0");
        send.setTextFill(Color.web("#fff"));
        send.setCursor(Cursor.HAND);

        Label error = new Label("");
        error.setTextFill(Color.web("red"));

        send.setOnAction(e-> {
            if (!newComment.getText().equals("") && !validate(newComment.getText())){
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String status = addNewComment.add(timeStamp, companyName, listName, bugName, currentUserEmailId, newComment.getText());

                if (status=="success"){
                    BorderPane newmessage = comments.rightformatmessage(timeStamp, newComment.getText());
                    fetchedComments.getChildren().add(newmessage);
                    newComment.setText("");
                }
                else
                    error.setText(status);
            }
        });

        mymessageCorner.setLeft(newComment);
        mymessageCorner.setRight(send);
        mymessageCorner.setBottom(error);

        BorderPane panel = new BorderPane(scroller,vb,null,mymessageCorner,null);
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

        if (priority == null)
            return "-fx-background-color: transparent";
        else if (priority.equals("P1"))
            return "-fx-background-color: #84e184";
        else if (priority.equals("P2"))
            return "-fx-background-color: #8484e1";
        else if (priority.equals("P3"))
            return "-fx-background-color: #3232cd";
        else if (priority.equals("P4"))
            return "-fx-background-color: #dc6f6f";
        else if (priority.equals("P5"))
            return "-fx-background-color: #cd3232";
        else
            return "-fx-background-color: transparent";
    }

    public static boolean validate(String Str) {
        Matcher matcher = VALID_STRING_REGEX .matcher(Str);
        return matcher.find();
    }


}
