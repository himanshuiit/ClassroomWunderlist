package com.ClassroomWunderlist.main.functions;

//import com.ClassroomDBMS.database.signIn.deleteAccount;
//import com.ClassroomDBMS.database.signIn.userSignOut;
//import com.ClassroomDBMS.main.templates.Home.courseInfo;
//import com.ClassroomDBMS.main.templates.editProfile.*;
//import com.ClassroomDBMS.main.templates.search.peopleSearch;
//import com.ClassroomDBMS.main.templates.speakouts.notices;
//import com.ClassroomDBMS.main.templates.tutorialSubmission.submissions;
import com.ClassroomWunderlist.main.windows.home.main;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Optional;

public class profile {

    public static Label fullName;
    public static Label emailID;
    public static Label phoneNumbercollege;
    public static Label course;
    public static Label findPeople;
    public static Label speakOut;
    public static Label submission;

    public static Scene main(String[] profileDetails){
        BorderPane userOptions = new BorderPane();

        BorderPane optionDetails = new BorderPane();
        optionDetails.setStyle("-fx-background-color: #171717");
        optionDetails.setPrefWidth(220);

        BorderPane options = new BorderPane();
        VBox userData = new VBox(10);
        userData.setPadding(new Insets(0,0,20,0));
        userData.setAlignment(Pos.TOP_CENTER);

        Label userLOGO = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_SECRET,
                "",
                "100",
                "0",
                ContentDisplay.LEFT );
        userLOGO.setTextFill(Color.web("grey"));
        userLOGO.setPadding(new Insets(15,0,0,10));
        StackPane logo = new StackPane(userLOGO);
        logo.setStyle("-fx-background-color: #fff");

        fullName = new Label(profileDetails[1]);
        fullName.setFont(new Font("Cambria", 25));
        fullName.setTextFill(Color.web("#ededed"));

        emailID = new Label(profileDetails[2]);
        emailID.setFont(new Font("Cambria", 15));
        emailID.setTextFill(Color.web("#ededed"));

        phoneNumbercollege = new Label(profileDetails[3]+",  "+profileDetails[5]);
        phoneNumbercollege.setFont(new Font("Cambria", 15));
        phoneNumbercollege.setTextFill(Color.web("#ededed"));
        phoneNumbercollege.setWrapText(true);

        HBox profileSetting = new HBox(10);
        profileSetting.setAlignment(Pos.TOP_CENTER);

        Button editButton = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT,"Edit Profile");
        editButton.setCursor(Cursor.HAND);

        Button deleteButton = GlyphsDude.createIconButton(FontAwesomeIcon.CUT,"Delete Profile");
        deleteButton.setCursor(Cursor.HAND);

        profileSetting.getChildren().addAll(editButton,deleteButton);

        userData.getChildren().addAll(logo,fullName,emailID,phoneNumbercollege,profileSetting);
        userData.setStyle("-fx-border-color: #fff;-fx-border-width: 0 0 2 0;-fx-underline: true;");

        options.setTop(userData);

        VBox buttons = new VBox(15);

        course = GlyphsDude.createIconLabel( FontAwesomeIcon.USERS,
                "  Home",
                "20",
                "18",
                ContentDisplay.LEFT );
        course.setFont(new Font("Cambria", 20));
        course.setTextFill(Color.web("#171717"));
        course.setPadding(new Insets(10));
        StackPane coursePane = new StackPane(course);
        coursePane.setAlignment(Pos.BASELINE_LEFT);
        coursePane.setStyle("-fx-background-color: grey");
        coursePane.setCursor(Cursor.HAND);

        findPeople = GlyphsDude.createIconLabel( FontAwesomeIcon.SEARCH,
                "  Find Students",
                "20",
                "18",
                ContentDisplay.LEFT );
        findPeople.setFont(new Font("Cambria", 20));
        findPeople.setTextFill(Color.web("#171717"));
        findPeople.setPadding(new Insets(10));
        StackPane findPeoplePane = new StackPane(findPeople);
        findPeoplePane.setAlignment(Pos.BASELINE_LEFT);
        findPeoplePane.setStyle("-fx-background-color: grey");
        findPeoplePane.setCursor(Cursor.HAND);

        speakOut = GlyphsDude.createIconLabel( FontAwesomeIcon.WECHAT,
                "  SpeakOut",
                "20",
                "18",
                ContentDisplay.LEFT );
        speakOut.setFont(new Font("Cambria", 20));
        speakOut.setTextFill(Color.web("#171717"));
        speakOut.setPadding(new Insets(10));
        StackPane speakOutPane = new StackPane(speakOut);
        speakOutPane.setAlignment(Pos.BASELINE_LEFT);
        speakOutPane.setStyle("-fx-background-color: grey");
        speakOutPane.setCursor(Cursor.HAND);

        submission = GlyphsDude.createIconLabel( FontAwesomeIcon.UPLOAD,
                "  Submission",
                "20",
                "18",
                ContentDisplay.LEFT );
        submission.setFont(new Font("Cambria", 20));
        submission.setTextFill(Color.web("#171717"));
        submission.setPadding(new Insets(10));
        StackPane submissionPane = new StackPane(submission);
        submissionPane.setAlignment(Pos.BASELINE_LEFT);
        submissionPane.setStyle("-fx-background-color: grey");
        submissionPane.setCursor(Cursor.HAND);

        buttons.getChildren().addAll(coursePane, findPeoplePane, speakOutPane, submissionPane);
        options.setCenter(buttons);

        Label logout = GlyphsDude.createIconLabel( FontAwesomeIcon.SIGN_OUT,
                "  Log Out",
                "20",
                "18",
                ContentDisplay.LEFT );
        logout.setFont(new Font("Cambria", 20));
        logout.setTextFill(Color.web("#171717"));
        logout.setPadding(new Insets(10));
        StackPane logoutPane = new StackPane(logout);
        logoutPane.setAlignment(Pos.BASELINE_LEFT);
        logoutPane.setStyle("-fx-background-color: grey");
        logoutPane.setCursor(Cursor.HAND);

        options.setBottom(logoutPane);
        optionDetails.setCenter(options);

        BorderPane optionData = new BorderPane();

//        optionData.setTop(courseInfo.TAinfo());
//        toggleTextColors("red", "#171717","#171717","#171717");
//
//        editButton.setOnAction(e-> {
//            e.consume();
//            updateUserDetails ob = new updateUserDetails();
//            optionData.setTop(ob.updateUserDetails());
//            toggleTextColors("#171717", "#171717","#171717","#171717");
//        });
//
//        deleteButton.setOnAction(e-> {
//            toggleTextColors("#171717", "#171717","#171717","#171717");
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Profile Delete Confirmation");
//            alert.setHeaderText("All your user details will be lost. Still your announcements could be seen");
//            alert.setContentText("Are you ok with this?");
//
//            try{
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK)
//                    deleteAccount.deleteAccount(emailID.getText());
//                    main.window.setScene(loginHome.homeView());
//            }
//            catch (Exception exe){
//                exe.getMessage();
//            }
//        });
//
//        coursePane.setOnMouseClicked(e-> {
//            optionData.setTop(courseInfo.TAinfo());
//            toggleTextColors("red", "#171717","#171717","#171717");
//        });
//
//        peopleSearch ob = new peopleSearch();
//        findPeoplePane.setOnMouseClicked(e-> {
//            optionData.setTop(ob.peoplesearch());
//            toggleTextColors("#171717", "red","#171717","#171717");
//        });
//
//        speakOutPane.setOnMouseClicked(e-> {
//            optionData.setTop(notices.notices(emailID.getText()));
//            toggleTextColors("#171717","#171717","red","#171717");
//            notices.messages.requestFocus();
//        });
//
//        submissionPane.setOnMouseClicked(e-> {
//            optionData.setTop(submissions.tutorials(emailID.getText()));
//            toggleTextColors("#171717","#171717","#171717","red");
//            submissions.tutorials.requestFocus();
//        });
//
//        logoutPane.setOnMouseClicked(e-> {
//            userSignOut.userSignOut(emailID.getText());
//            main.window.setScene(loginHome.homeView());
//        });

        userOptions.setLeft(optionDetails);
        userOptions.setCenter(optionData);

        Scene scene = new Scene(userOptions,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        optionData.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        optionDetails.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return scene;
    }

    public static void toggleTextColors(String courseColor, String findColor, String speakoutColor, String submissionColor)
    {
        course.setTextFill(Color.web(courseColor));
        findPeople.setTextFill(Color.web(findColor));
        speakOut.setTextFill(Color.web(speakoutColor));
        submission.setTextFill(Color.web(submissionColor));
    }
}
