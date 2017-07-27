package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.logIn.dbLoginCheck;
import com.ClassroomWunderlist.main.functions.profile;
import com.ClassroomWunderlist.main.windows.home.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class userLogin {

    public String[] status;
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    public BorderPane userLogin(){

        BorderPane loginPane = new BorderPane();
        loginPane.setPadding(new Insets(20));

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(20));

        TextField companyName = new TextField();
        companyName.setPromptText("Company Name");
        companyName.setPrefHeight(35);
        companyName.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");
        companyName.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                loginPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField username = new TextField();
        username.setPromptText("full name or email address");
        username.setPrefHeight(35);
        username.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setPrefHeight(35);
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        Label error = new Label();
        error.setTextFill(Color.web("red"));

        vb.getChildren().addAll(companyName, username,password, error);

        loginPane.setCenter(vb);

        HBox loginRow = new HBox();
        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Cambria", 18));
        loginButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        loginButton.setTextFill(Color.web("#fff"));
        loginRow.getChildren().addAll(loginButton);
        loginRow.setAlignment(Pos.BASELINE_CENTER);

        loginButton.setOnAction(e-> {
            e.consume();
            if (companyName.getText().isEmpty())
                error.setText("Company Name can't be empty");
            else if (username.getText().isEmpty())
                error.setText("Username or EmailId can't be empty");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else{
                status = dbLoginCheck.dbLoginCheck(username.getText(),password.getText());
                if (status[0]=="success"){
                    main.window.setScene(profile.main(status));
                }
                else
                    error.setText("Incorrect Username / Email Id or password !");
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee-> {
                error.setTextFill(Color.web("red"));
                error.setText("");
            });
            new Thread(sleeper).start();
        });

        loginPane.setBottom(loginRow);

        return loginPane;
    }
}
