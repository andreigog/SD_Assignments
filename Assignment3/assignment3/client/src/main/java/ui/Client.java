package ui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Application {

    public void start(Stage primaryStage) throws Exception {
        Socket serverSocket = initializeConnection();
        ObjectOutputStream outStream=null;
        ObjectInputStream inStream=null;
        if (serverSocket != null) {
            outStream = new ObjectOutputStream(serverSocket.getOutputStream());
            inStream = new ObjectInputStream(serverSocket.getInputStream());
            primaryStage.setTitle("Dead Poets Society");
            primaryStage.setResizable(false);
            primaryStage.setScene(Utils.createScene("/layout/Login.fxml", outStream,inStream,null));
            primaryStage.show();
        }else {
            System.out.println("Serverine deaderino we regretino");
        }
    }

    private Socket initializeConnection() {
        String hostName = "localhost";
        int portNumber = 6969;
        Socket serverSocket;
        try  {

            serverSocket = new Socket(hostName, portNumber);;

        } catch (IOException e) {
            e.printStackTrace();
            serverSocket = null;
        }
        return serverSocket;
    }


    public static void main(String args[]) {
        launch(args);
    }
}

