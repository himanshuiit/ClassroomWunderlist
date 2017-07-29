package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.bugs.list.fetchbugFromList;
import com.ClassroomWunderlist.database.taskUpdate.markComplete;
import com.ClassroomWunderlist.database.bugs.list.addNewBug;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bugsInList {

    public static VBox lists;

    public static BorderPane leftPanel;
    public static BorderPane rightPanel;
    public static BorderPane view;

    public String currentUserEmailId;

    public BorderPane view(String companyName, String listName, String emailId){

        currentUserEmailId = emailId;

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

        BorderPane comboPane = new BorderPane(null,null,myComboBox,null,header);
        comboPane.setPadding(new Insets(20,30,20,0));

        TextField newBug = new TextField();
        newBug.setPromptText("+ Add a to-do...");
        newBug.setPrefColumnCount(15);
        newBug.setFont(Font.font(18));
        newBug.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 1,1,1,1; -fx-text-inner-color: #fff;");
        StackPane searchPane = new StackPane(newBug);

        BorderPane headerSection = new BorderPane(
                null,
                comboPane,
                null,
                searchPane,
                null);
        headerSection.setPadding(new Insets(0,0,20,0));

        myComboBox.valueProperty().addListener((ov, t, t1) -> {
            switch (t1) {
                case "Sort by Creation Date":
                    fetching(companyName, listName, "ORDER BY timestamp asc");
                    break;
                case "Sort Alphabetically":
                    fetching(companyName, listName, "ORDER BY bugName asc");
                    break;
                case "Sort by Due Date":
                    fetching(companyName, listName, "ORDER BY deadline asc");
                    break;
                case "Sort by Asignee":
                    fetching(companyName, listName, "ORDER BY assigneeEmailId asc");
                    break;
                case "Sort by Priority":
                    fetching(companyName, listName, "ORDER BY priority asc");
                    break;
                default:
                    fetching(companyName, listName, "ORDER BY checked asc");
                    break;
            }
        });

        fetching(companyName, listName, "ORDER BY timestamp asc");

        ScrollPane bugs = new ScrollPane(lists);
        bugs.setStyle("-fx-background-color: transparent");
        bugs.setFitToWidth(true);
        bugs.setVvalue(1.0);
        bugs.vvalueProperty().bind(lists.heightProperty());

        leftPanel = new BorderPane(bugs,headerSection,null,null,null);
        leftPanel.setPadding(new Insets(10,10,30,10));

        rightPanel = new BorderPane();
        rightPanel.setPadding(new Insets(50,10,30,10));

        view = new BorderPane(leftPanel);
        view.setStyle("-fx-background-color: transparent");

        newBug.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (newPropertyValue)
                profile.scene.getAccelerators().put(
                    new KeyCodeCombination(KeyCode.ENTER),
                    () -> {
                        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                        String status = addNewBug.add(timeStamp,companyName,listName,newBug.getText());
                        if (status.equals("success"))
                            addList(companyName, listName, newBug.getText(), null, null, null, "false");
                    }
                );
            else
                profile.scene.getAccelerators().put(
                    new KeyCodeCombination(KeyCode.ENTER),
                    () ->  System.out.print("")
                );
        });

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
                    companyName, listName, bugName, assigneeEmailId, deadline, priority, newValue.toString(), taskCheck.selectedProperty(), currentUserEmailId)
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
                    companyName, listName, bugName, assigneeEmailId, deadline, priority, latestCheck, taskCheck.selectedProperty(), currentUserEmailId)
            );
            view.setRight(rightPanel);
        });
        lists.getChildren().add(newBugsPane);
    }

}
