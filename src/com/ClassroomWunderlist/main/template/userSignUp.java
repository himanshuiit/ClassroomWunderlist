package com.ClassroomWunderlist.main.template;

import com.ClassroomWunderlist.database.signIn.dbSignUp;
import com.ClassroomWunderlist.main.functions.profile;
import com.ClassroomWunderlist.main.windows.home.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userSignUp {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public String status ;

    public BorderPane userSignUp(){
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        BorderPane signUpPane = new BorderPane();
        signUpPane.setPadding(new Insets(20,20,0,20));

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(20,20,20,20));

        TextField companyName = new TextField();
        companyName.setPromptText("Company Name");
        companyName.setFont(Font.font(15));
        companyName.setPrefHeight(30);
        companyName.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");
        companyName.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                signUpPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField fullName = new TextField();
        fullName.setFont(Font.font(15));
        fullName.setPromptText("Full Name");
        fullName.setPrefHeight(30);
        fullName.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        TextField email = new TextField();
        email.setFont(Font.font(15));
        email.setPromptText("Email Id");
        email.setPrefHeight(30);
        email.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        PasswordField password = new PasswordField();
        password.setFont(Font.font(15));
        password.setPromptText("password");
        password.setPrefHeight(30);
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setFont(Font.font(15));
        confirmPassword.setPromptText("Confirm password");
        confirmPassword.setPrefHeight(30);
        confirmPassword.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        Label error = new Label();
        error.setTextFill(Color.web("red"));

        HBox signUpRow = new HBox();
        Button signUpButton = new Button("SignUp");
        signUpButton.setFont(new Font("Cambria", 18));
        signUpButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        signUpButton.setTextFill(Color.web("#fff"));
        signUpRow.getChildren().addAll(signUpButton);
        signUpRow.setAlignment(Pos.BASELINE_CENTER);

        signUpButton.setOnAction(e-> {
            if (companyName.getText().isEmpty())
                error.setText("Company Name can't be empty");
            else if (fullName.getText().isEmpty())
                error.setText("Full Name can't be empty");
            else if (email.getText().isEmpty())
                error.setText("Email Id can't be empty");
            else if (!validate(email.getText()))
                error.setText("Email ID incorrect");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else if (confirmPassword.getText().isEmpty())
                error.setText("ConfirmPassword can't be empty");
            else if (!password.getText().equals(confirmPassword.getText()))
                error.setText("Password and confirm password don't match");
            else{
                status = dbSignUp.userSignUp(companyName.getText(), fullName.getText(),email.getText(),password.getText());
                if (status=="success") {
                    main.window.setScene(profile.main(companyName.getText(), email.getText()));
                }
                else
                    error.setText(status);
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

        vb.getChildren().addAll(companyName, fullName, email, password, confirmPassword, error, signUpRow);
        signUpPane.setCenter(vb);
        signUpPane.setMinHeight(400);

        return signUpPane;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
