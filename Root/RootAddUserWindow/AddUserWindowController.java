package sample.Root.RootAddUserWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Root.RootUserWindow.RootUserWindowController;

import java.io.IOException;

public class AddUserWindowController {

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    private String username;
    public void setUsername(String username) {
        this.username=username;
    }

    private Stage primaryStage=null;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void addUserClick(ActionEvent event) {
        String username;
        String password;
        username=loginField.getText();
        password=passwordField.getText();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Вы успешно добавили пользователя.");

                alert.showAndWait();

                loginField.clear();
                passwordField.clear();
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

    @FXML
    void goBackClick(ActionEvent event) {
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

}