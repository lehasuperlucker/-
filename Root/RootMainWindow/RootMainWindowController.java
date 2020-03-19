package sample.Root.RootMainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Root.RootCompanyWindow.RootCompanyWindowController;
import sample.Root.RootUserWindow.RootUserWindowController;
import sample.logInWindow.LogInWindowController;

import java.io.IOException;








public class RootMainWindowController {

    private String username;

    private Stage primaryStage=null;

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void setUsername(String username){
        this.username=username;
    }





    public void goToRootUserWindowClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Root/RootUserWindow/RootUserWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RootUserWindowController rootUserWindowController=(RootUserWindowController) fxmlLoader.getController();

        rootUserWindowController.setup(primaryStage);
        rootUserWindowController.setUsername(username);

        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Root/RootUserWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void goToRootCompanyClick(ActionEvent actionEvent) {
        primaryStage.hide();
        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Root/RootCompanyWindow/RootCompanyWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RootCompanyWindowController rootCompanyWindowController=(RootCompanyWindowController) fxmlLoader.getController();

        rootCompanyWindowController.setup(primaryStage);
        rootCompanyWindowController.setUsername(username);

        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Root/RootCompanyWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void returnToAuthClick(ActionEvent actionEvent) {
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

    public void goToTransactionRootWindowClick(ActionEvent actionEvent) {
    }
}
