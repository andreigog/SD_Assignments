package ro.utcluj.student.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TennisTournament extends Application {

    public void start(Stage primaryStage) throws Exception {
        //primaryStage.setWidth(800);
        //primaryStage.setHeight(600);
        primaryStage.setTitle("Tennis Tournament");
        primaryStage.setResizable(false);
        primaryStage.setScene(Utils.createScene("/layout/Login.fxml",null ));
        primaryStage.show();
    }


    public static void main(String args[]) {
        launch(args);
    }
}

