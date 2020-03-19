package sample.Root.RootUserWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Root.RootAddUserWindow.AddUserWindowController;
import sample.Main;
import sample.Root.RootMainWindow.RootMainWindowController;

import java.io.IOException;

public class RootUserWindowController {

    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User,String> idColumn;
    @FXML
    private TableColumn<User,String> usernameColumn;
    @FXML
    private TableColumn<User,String> roleColumn;
    @FXML
    private TableColumn<User,String> accessColumn;

    private ObservableList<User> fields = FXCollections.observableArrayList();


    private String current_username = "";
    private User userField_current=null;
    private String username;

    public void setUsername(String username){
        this.username=username;
    }

    private Stage primaryStage=null;

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void setup(Stage primaryStage){

        setStage(primaryStage);

        String userID = "";
        String username = "";
        String role = "";
        String access="";

        try {
            Main.os.writeObject("sendUserInfo");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                userID=(String)Main.is.readObject();
                if (userID.equals("end"))
                    break;
                username=(String)Main.is.readObject();
                role=(String)Main.is.readObject();
                access=(String)Main.is.readObject();
                fields.add(new User(userID,username,role,access));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<User,String>("idColumn"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("usernameColumn"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("roleColumn"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<User,String>("accessColumn"));

        tableUsers.setItems(fields);
        tableUsers.refresh();

        TableView.TableViewSelectionModel<User> selectionModel = tableUsers.getSelectionModel();
        selectionModel.selectedIndexProperty().addListener((observableValue, number, t1) -> {
            User user = tableUsers.getSelectionModel().getSelectedItem();
            current_username = user.getUsernameColumn();
            userField_current = user;
        });

    }


    public void deleteUserClick(ActionEvent actionEvent) {
        String username=current_username;

        if (username.equals("root"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нельзя удалить менеджера!");

            alert.showAndWait();
            alert.hide();
        }
        else if(username.equals(null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите пользователя из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            fields.remove(userField_current);
            String clM="deleteUser" + "," + username;
            try {
                Main.os.writeObject(clM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tableUsers.refresh();
        current_username="";

    }

    public void makeAdminClick(ActionEvent actionEvent) {
        String username=current_username;

        if (userField_current.getRoleColumn().equals("root"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нельзя изменить роль менеджера!");

            alert.showAndWait();
            alert.hide();
        }
        else if (userField_current.getRoleColumn().equals("admin"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Этот юзер уже является админом!");

            alert.showAndWait();
            alert.hide();
        }
        else
        {
            userField_current.setRoleColumn("admin");
            tableUsers.refresh();
            String clm="makeAdmin" + "," + username;
            try {
                Main.os.writeObject(clm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addNewUserClick(ActionEvent actionEvent) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Root/RootAddUserWindow/AddUserWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddUserWindowController addUserWindowController=(AddUserWindowController) fxmlLoader.getController();

        addUserWindowController.setStage(primaryStage);
        addUserWindowController.setUsername(username);



        primaryStage.setTitle("Добавление пользователя");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Root/RootAddUserWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void returnToMainMenuClick(ActionEvent actionEvent) {
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

    public void blockUserClick(ActionEvent actionEvent) {
        String username=current_username;
        if (userField_current.getAccessColumn().equals("разрешен")) {
            userField_current.setAccessColumn("закрыт");
            tableUsers.refresh();
            String clM="blockUser" + "," + username;
            try {
                Main.os.writeObject(clM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (userField_current.getAccessColumn().equals("закрыт")) {
            userField_current.setAccessColumn("разрешен");
            tableUsers.refresh();
            String clM="unblockUser" + "," + username;
            try {
                Main.os.writeObject(clM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
