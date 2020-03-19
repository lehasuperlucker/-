package sample.SignUpWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.logInWindow.LogInWindowController;

import java.io.IOException;

public class SignUpWindowController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button SignUpButton;

    private Stage primaryStage=null;

//    @FXML
//    void initialize() {
//        SignUpButton.setOnAction(event -> {
//            String username=loginField.getText();
//            String password=passwordField.getText();
//            String clM="checkUserInDB"+username;
//
//            try {
//                Main.os.writeObject(clM);
//                String message=(String)Main.is.readObject();
//                if (message.equals("success")) {
//                    String msg="addUser," + username + "," + password;
//                    try {
//                        Main.os.writeObject(msg);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    returnToSignIn();
//                }
//                else if (message.equals("fail")) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ошибка");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Неверно введены пароль или логин.");
//
//                    alert.showAndWait();
//
//                    loginField.clear();
//                    passwordField.clear();
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        });
//    }

    private void returnToSignIn() {
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

    public void setStage(Stage stage) {
        primaryStage= stage;
    }


    public void signUpClick(ActionEvent actionEvent) {
        String username=loginField.getText();
        String password=passwordField.getText();
        String clM="checkUserInDB,"+username;

        try {
            Main.os.writeObject(clM);
            String message=(String)Main.is.readObject();
            if (message.equals("success")) {
                String msg="addUser," + username + "," + password;
                try {
                    Main.os.writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                returnToSignIn();
            }
            else if (message.equals("fail")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такой пользователь уже существует.");

                alert.showAndWait();

                loginField.clear();
                passwordField.clear();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
