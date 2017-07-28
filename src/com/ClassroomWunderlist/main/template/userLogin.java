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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userLogin {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public String[] status;
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    public BorderPane userLogin(){

        BorderPane loginPane = new BorderPane();
        loginPane.setPadding(new Insets(20));

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(20));

        TextField companyName = new TextField();
        companyName.setPromptText("Company Name");
        companyName.setFont(Font.font(15));
        companyName.setPrefHeight(30);
        companyName.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");
        companyName.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                loginPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField emailId = new TextField();
        emailId.setPromptText("Email address");
        emailId.setFont(Font.font(15));
        emailId.setPrefHeight(30);
        emailId.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setFont(Font.font(15));
        password.setPrefHeight(30);
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        Label error = new Label();
        error.setTextFill(Color.web("red"));

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
            else if (emailId.getText().isEmpty())
                error.setText("Username or EmailId can't be empty");
            else if (!validate(emailId.getText()))
                error.setText("Email ID incorrect");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else{
                status = dbLoginCheck.dbLoginCheck(companyName.getText(),emailId.getText(),password.getText());
                if (status[0].equals("success")){
                    main.window.setScene(profile.main(companyName.getText(), status[1], emailId.getText()));
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

        vb.getChildren().addAll(companyName, emailId, password, error, loginRow);
        loginPane.setCenter(vb);
        loginPane.setMinHeight(400);

        return loginPane;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
