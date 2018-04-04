package ro.utcluj.student.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Utils {
    public static Scene createScene(String sceneLocation, Integer userId) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TennisTournament.class.getResource(sceneLocation));
        if (sceneLocation.contains("Player"))
            loader.setController(new PlayerController(userId));
        final Parent loaderParent = (Parent) loader.load();
        final Scene scene = new Scene(loaderParent);
        return scene;
    }
}
