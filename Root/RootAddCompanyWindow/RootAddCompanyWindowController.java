package sample.Root.RootAddCompanyWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Root.RootCompanyWindow.RootCompanyWindowController;

import java.io.IOException;

public class RootAddCompanyWindowController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField formField;

    @FXML
    private TextField districtField;

    @FXML
    private TextField regionField;

    @FXML
    private TextField emplField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField websiteField;

    public String message;

    private Stage primaryStage=null;

    private String username;


    public void setUsername(String username){
        this.username=username;
    }

    @FXML
    void addCompanyClick(ActionEvent event) {
        String name,form, district, region, employee_number, owner, foundation_year, address, e_mail, site, userName;
        name=nameField.getText();
        form=formField.getText();
        district=districtField.getText();
        region=regionField.getText();
        employee_number=emplField.getText();
        owner=ownerField.getText();
        foundation_year=yearField.getText();
        address=addressField.getText();
        e_mail=mailField.getText();
        site=websiteField.getText();
        userName=username;
        String clientMessage="addCompany," + name + "," + form + "," + district + "," + region + "," + employee_number + "," + owner + "," + foundation_year + "," + address + "," + e_mail + "," + site + "," + userName;
        try {
            Main.os.writeObject(clientMessage);
            message=(String)Main.is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (message.equals("success")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Вы добавили предприятие.");

            alert.showAndWait();

        }
    }

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void returnToRootWindow(ActionEvent actionEvent) {
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
}
