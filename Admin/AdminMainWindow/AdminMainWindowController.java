package sample.Admin.AdminMainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Admin.AdminCompanyWindow.AdminCompanyWindowController;
import sample.Admin.AdminUserWindow.AdminUserWindowController;
import sample.logInWindow.LogInWindowController;

import java.io.IOException;

public class AdminMainWindowController {

    private String username;

    private Stage primaryStage=null;

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void setUsername(String username){
        this.username=username;
    }

    @FXML
    void goToAdminUserWindowClick(ActionEvent event) {
        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Admin/AdminUserWindow/AdminUserWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminUserWindowController adminUserWindowController=(AdminUserWindowController) fxmlLoader.getController();

        adminUserWindowController.setup(primaryStage);
        adminUserWindowController.setUsername(username);

        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Admin/AdminUserWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    @FXML
    void goToAdminCompanyClick(ActionEvent event) {
        primaryStage.hide();
        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Admin/AdminCompanyWindow/AdminCompanyWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminCompanyWindowController adminCompanyWindowController=(AdminCompanyWindowController) fxmlLoader.getController();

        adminCompanyWindowController.setup(primaryStage);
        adminCompanyWindowController.setUsername(username);


        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Admin/AdminCompanyWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    @FXML
    void returnToAuthClick(ActionEvent event) {
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
}





