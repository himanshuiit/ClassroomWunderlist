package com.ClassroomWunderlist.main.functions;

import com.ClassroomWunderlist.database.signIn.userSignOut;
import com.ClassroomWunderlist.main.windows.home.main;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Label fullName;
    public static Label company;
    public static Label assignedToMe;
    public static Label createView;

    public static Scene main(String companyName, String emailId){
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        BorderPane profilePane = new BorderPane();

        BorderPane options = new BorderPane();
        options.setPrefWidth(220);

        VBox user = new VBox(10);
        user.setPadding(new Insets(10));
        user.setAlignment(Pos.TOP_CENTER);

        fullName = new Label("Shubham Madheysia");
        fullName.setFont(new Font("Cambria", 20));
        fullName.setTextFill(Color.web("#ededed"));

        company = GlyphsDude.createIconLabel( FontAwesomeIcon.BANK,
                companyName,
                "15",
                "12",
                ContentDisplay.LEFT );
        company.setFont(new Font("Cambria", 12));
        company.setTextFill(Color.web("#ededed"));

        user.getChildren().addAll(fullName, company);
        options.setTop(user);

        assignedToMe = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_PLUS,
                "  Assigned to me",
                "20",
                "18",
                ContentDisplay.LEFT );
        assignedToMe.setFont(new Font("Cambria", 20));
        assignedToMe.setTextFill(Color.web("#171717"));
        assignedToMe.setPadding(new Insets(10));
        StackPane assigned = new StackPane(assignedToMe);
        assigned.setAlignment(Pos.BASELINE_LEFT);
        assigned.setStyle("-fx-background-color: #f4f4ff");
        assigned.setCursor(Cursor.HAND);

        TextField searchlists = new TextField();
        searchlists.setPromptText("Search by keyword");
        searchlists.setPrefColumnCount(15);
        searchlists.setFont(Font.font(18));
        searchlists.setStyle("-fx-background-color: transparent; -fx-text-inner-color: #fff;");
//        searchNotice.textProperty().addListener((observable, oldValue, newValue) -> {
//            fetchedMessages.getChildren().clear();
//            fetchedMessages.getChildren().add(keywordSearch.keywordSearch(emailId,newValue));
//        });
        StackPane searchPane = new StackPane(searchlists);
        searchPane.setPadding(new Insets(0,5,-10,5));

        VBox lists = new VBox(15);
        ScrollPane scrollerList = new ScrollPane(lists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(lists.heightProperty());

        searchlists.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                scrollerList.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });


        VBox allLists = new VBox(15);

        createView = GlyphsDude.createIconLabel( FontAwesomeIcon.PLUS,
                "  Create View",
                "20",
                "18",
                ContentDisplay.LEFT );
        createView.setFont(new Font("Cambria", 20));
        createView.setTextFill(Color.web("#171717"));
        createView.setPadding(new Insets(10));
        StackPane createViewPane = new StackPane(createView);
        createViewPane.setAlignment(Pos.BASELINE_LEFT);
        createViewPane.setStyle("-fx-background-color: transparent");
        createViewPane.setCursor(Cursor.HAND);

        allLists.getChildren().addAll(assigned, searchPane, scrollerList, createViewPane);

        options.setCenter(allLists);

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
        logoutPane.setStyle("-fx-background-color: #f4f4ff");
        logoutPane.setCursor(Cursor.HAND);

        options.setBottom(logoutPane);

        BorderPane optionData = new BorderPane();

        createViewPane.setOnMouseClicked(e-> {

            Label newList = new Label("Testing");
            newList.setFont(new Font("Cambria", 20));
            newList.setTextFill(Color.web("#171717"));
            newList.setPadding(new Insets(10));
            StackPane newListPane = new StackPane(newList);
            newListPane.setAlignment(Pos.BASELINE_LEFT);
            newListPane.setStyle("-fx-background-color: #f4f4ff");
            newListPane.setCursor(Cursor.HAND);

            lists.getChildren().add(newListPane);
        });

//        submissionPane.setOnMouseClicked(e-> {
//            optionData.setTop(submissions.tutorials(emailID.getText()));
//            toggleTextColors("#171717","#171717","#171717","red");
//            submissions.tutorials.requestFocus();
//        });

        logoutPane.setOnMouseClicked(e-> {
            userSignOut.userSignOut();
            main.window.setScene(loginHome.homeView());
        });

        profilePane.setLeft(options);
        profilePane.setCenter(optionData);

        Scene scene = new Scene(profilePane,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        optionData.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        options.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return scene;
    }

    public static void toggleTextColors(String courseColor, String findColor, String speakoutColor, String submissionColor)
    {
//        course.setTextFill(Color.web(courseColor));
    }
}
