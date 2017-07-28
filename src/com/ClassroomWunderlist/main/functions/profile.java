package com.ClassroomWunderlist.main.functions;

import com.ClassroomWunderlist.database.lists.keywordSearch;
import com.ClassroomWunderlist.database.signIn.userSignOut;
import com.ClassroomWunderlist.main.windows.createNewList.newList;
import com.ClassroomWunderlist.main.windows.home.main;
import com.ClassroomWunderlist.database.lists.fetchLatest;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Label fullName;
    public static Label company;
    public static Label assignedToMe;
    public static VBox lists;

    public static Label createView;


    public static Scene main(String companyName, String completeName, String emailId){
        BorderPane profilePane = new BorderPane();

        BorderPane options = new BorderPane();
        options.setPrefWidth(220);

        VBox user = new VBox(10);
        user.setPadding(new Insets(10));
        user.setAlignment(Pos.TOP_CENTER);

        fullName = new Label(completeName);
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
        StackPane searchPane = new StackPane(searchlists);
        searchPane.setPadding(new Insets(0,5,-10,5));

        lists = new VBox(15);

        String[][] listFetc = fetchLatest.fetchlatest(companyName);
        if (listFetc[0][0].equals("SUCCESS")){
            for (int i=1; i<listFetc.length;++i)
                addlist(listFetc[i][1]);
        }

        ScrollPane scrollerList = new ScrollPane(lists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(lists.heightProperty());

        searchlists.textProperty().addListener((observable, oldValue, newValue) -> {
            lists.getChildren().clear();
            String[][] searchedlist = keywordSearch.keywordSearch(companyName,searchlists.getText());
            if (searchedlist[0][0].equals("SUCCESS")){
                for (int i=1; i<searchedlist.length;++i)
                    addlist(searchedlist[i][1]);
            }
        });

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
        createViewPane.setOnMouseClicked(e-> addlist(newList.newList(companyName)) );

        VBox allLists = new VBox(15);
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

    public static void addlist(String name){
        if (!name.isEmpty()){
            Label newList = new Label(name);
            newList.setPadding(new Insets(10));
            newList.setFont(new Font("Cambria", 15));
            newList.setTextFill(Color.web("#171717"));
            StackPane newListPane = new StackPane(newList);
            newListPane.setAlignment(Pos.BASELINE_LEFT);
            newListPane.setStyle("-fx-background-color: #f4f4ff");
            newListPane.setOnMouseEntered(e-> newListPane.setStyle("-fx-background-color: #dbdbe5"));
            newListPane.setOnMouseExited(e-> newListPane.setStyle("-fx-background-color: #f4f4ff"));
            newListPane.setCursor(Cursor.HAND);
            lists.getChildren().add(newListPane);
        }
    }
}
