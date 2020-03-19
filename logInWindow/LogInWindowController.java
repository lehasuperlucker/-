package sample.logInWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Admin.AdminMainWindow.AdminMainWindowController;
import sample.Main;
import sample.Root.RootMainWindow.RootMainWindowController;
import sample.SignUpWindow.SignUpWindowController;
import sample.User.UserMainWindow.UserMainWindowController;

import java.io.IOException;

public class LogInWindowController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;


    public String message;

    private Stage primaryStage=null;





    private void goToAdminWindow(String username) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Admin/AdminMainWindow/AdminMainWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminMainWindowController adminMainWindowController=(AdminMainWindowController) fxmlLoader.getController();

        adminMainWindowController.setStage(primaryStage);
        adminMainWindowController.setUsername(username);

        primaryStage.setTitle("Меню администратора");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Admin/AdminMainWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }


    private void goToUserWindow(String username) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/User/UserMainWindow/UserMainWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserMainWindowController userMainWindowController=(UserMainWindowController) fxmlLoader.getController();

        userMainWindowController.setup(primaryStage);

        userMainWindowController.setUsername(username);

        primaryStage.setTitle("Меню пользователя");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/User/UserMainWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    private void goToRootMainWindow(String username) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Root/RootMainWindow/RootMainWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RootMainWindowController rootMainWindowController=(RootMainWindowController) fxmlLoader.getController();

        rootMainWindowController.setStage(primaryStage);
        rootMainWindowController.setUsername(username);


        primaryStage.setTitle("Меню менеджера");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Root/RootMainWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }



    public void setStage(Stage stage) {
        primaryStage= stage;
    }


    public void clickSignIn(ActionEvent actionEvent) {
        String loginText=loginField.getText();
        String passwordText=passwordField.getText();
        String clientMessage="checkLoginUser," + loginText + "," + passwordText;
        try {
            Main.os.writeObject(clientMessage);
            message=(String)Main.is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (message.equals("successUser")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Вы вошли как пользователь.");

            alert.showAndWait();

            loginField.clear();
            passwordField.clear();

            goToUserWindow(loginText);

        }
        else if (message.equals("successAdmin")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Вы вошли как администратор.");

            alert.showAndWait();

            loginField.clear();
            passwordField.clear();

            goToAdminWindow(loginText);

        }
        else if (message.equals("successRoot")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Вы вошли как главный администратор.");

            alert.showAndWait();

            loginField.clear();
            passwordField.clear();

            goToRootMainWindow(loginText);
        }
        else if (message.equals("fail")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неверно введены пароль или логин.");

            alert.showAndWait();

            loginField.clear();
            passwordField.clear();
        }
        else if(message.equals("blocked")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Эта учетная запись заблокирована администратором.");

            alert.showAndWait();

            loginField.clear();
            passwordField.clear();
        }
    }

    public void clickSignUp(ActionEvent actionEvent) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/SignUpWindow/SignUpWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SignUpWindowController signUpWindowController=(SignUpWindowController) fxmlLoader.getController();

        signUpWindowController.setStage(primaryStage);

        primaryStage.setTitle("Вход в систему");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/SignUpWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
