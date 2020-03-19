package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SignUpWindow.SignUpWindowController;
import sample.logInWindow.LogInWindowController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends Application {
    public static String clientMessage;
    public static Socket socket;
    public static ObjectOutputStream os;
    public static ObjectInputStream is;
    public static boolean connected = false;

    public static void connect() {
        clientMessage = "";

        try {
            socket = new Socket(InetAddress.getLocalHost(), 2525);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (UnknownHostException var1) {
            connected = false;
            System.err.println("Address not available" + var1);
        } catch (IOException var2) {
            connected = false;
            System.err.println("I/О thread error" + var2);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.connect();


        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/logInWindow/LogInWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        LogInWindowController logInWindowController=(LogInWindowController) fxmlLoader.getController();

        logInWindowController.setStage(primaryStage);

        primaryStage.setTitle("Вход в систему");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/logInWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
