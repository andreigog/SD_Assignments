package ro.utcluj.student.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ro.utcluj.student.business.entities.UserEntity;

import java.io.IOException;

public class Utils {
    public static Scene createScene(String sceneLocation, UserEntity user) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(sceneLocation));
        if (sceneLocation.contains("Player"))
            loader.setController(new PlayerController(user));
        final Parent loaderParent = (Parent) loader.load();
        final Scene scene = new Scene(loaderParent);
        return scene;
    }
}
