package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Writer;
import ui.Controllers.AdminController;
import ui.Controllers.LoginController;
import ui.Controllers.ReaderController;
import ui.Controllers.WriterController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Utils {
    public static Scene createScene(String sceneLocation, ObjectOutputStream outStream, ObjectInputStream inStream, Writer writer) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource(sceneLocation));
        if (sceneLocation.contains("Login")){
            loader.setController(new LoginController(outStream,inStream));
        }
        if (sceneLocation.contains("Admin")){
            loader.setController(new AdminController(outStream,inStream));
        }
        if (sceneLocation.contains("Writer")){
            loader.setController(new WriterController(outStream,inStream,writer));
        }
        if (sceneLocation.contains("Reader")){
            loader.setController(new ReaderController(outStream,inStream));
        }
        Parent loaderParent = (Parent) loader.load();
        Scene scene = new Scene(loaderParent);
        return scene;
    }
}
