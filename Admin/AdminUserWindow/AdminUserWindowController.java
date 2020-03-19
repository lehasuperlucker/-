package sample.Admin.AdminUserWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Admin.AdminAddUserWindow.AdminAddUserWindowController;
import sample.Admin.AdminMainWindow.AdminMainWindowController;
import sample.Main;
import sample.Root.RootUserWindow.User;

import java.io.IOException;

public class AdminUserWindowController {

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, String> idColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> accessColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    private ObservableList<User> fields = FXCollections.observableArrayList();
    @FXML
    private TextField login;

    private String username;
    public void setUsername(String username){
        this.username=username;
    }

    private String current_username = "";
    private User userField_current=null;

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

    @FXML
    void addNewUserClick(ActionEvent event) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/AdminAddUserWindow/AdminAddUserWindow/AddUserWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminAddUserWindowController adminaddUserWindowController=(AdminAddUserWindowController) fxmlLoader.getController();

        adminaddUserWindowController.setStage(primaryStage);
        adminaddUserWindowController.setUsername(username);



        primaryStage.setTitle("Добавление пользователя");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Admin/AdminAddUserWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    @FXML
    void blockUserClick(ActionEvent event) {
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

    @FXML
    void returnToMainMenuClick(ActionEvent event) {
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

}
