package sample.Root.RootAddTransactionWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Root.RootTransactionsWindow.RootTransactionsWindowController;

import java.io.IOException;

public class RootAddTransactionWindowController {

    private String username;
    public void setUsername(String username) {
        this.username=username;
    }

    private String companyname;
    public void setCompanyname(String companyname) {
        this.companyname=companyname;
    }

    private Stage primaryStage=null;
    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public String  message;

    @FXML
    private TextField vrField;

    @FXML
    private TextField rpField;

    @FXML
    private TextField r1Field;

    @FXML
    private TextField r2Field;

    @FXML
    private TextField r3Field;

    @FXML
    private TextField p1Field;

    @FXML
    private TextField p2Field;

    @FXML
    private TextField p3Field;

    @FXML
    private TextField ravgField;

    @FXML
    private TextField nameField;

    @FXML
    void addTransactionClick(ActionEvent event) {
        if (nameField.getText().equals(null) || r1Field.getText().equals(null) || r2Field.getText().equals(null) || r3Field.getText().equals(null) || p1Field.getText().equals(null) || p2Field.getText().equals(null) || p3Field.getText().equals(null) || ravgField.getText().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Все поля должны быть заполнены!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String name, vr, rp, r1, r2, r3, p1, p2, p3, ravg;
            name = nameField.getText();
            vr = vrField.getText();
            rp = rpField.getText();
            r1 = r1Field.getText();
            r2 = r2Field.getText();
            r3 = r3Field.getText();
            p1 = p1Field.getText();
            p2 = p2Field.getText();
            p3 = p3Field.getText();
            ravg = ravgField.getText();
            String clM="addTransaction," + name + "," + vr + "," + rp + "," + r1 + "," + r2 + "," + r3 + "," + p1 + "," + p2 + "," + p3 + "," + ravg + "," + companyname + "," + username;
            try {
                Main.os.writeObject(clM);
                //message=(String)Main.is.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            if (message.equals("success")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Успех!");
//                alert.setHeaderText(null);
//                alert.setContentText("Вы успешно добавили предприятие.");
//
//                alert.showAndWait();
//
//            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Вы успешно добавили предприятие.");

            alert.showAndWait();
        }

    }

    @FXML
    void goBackClick(ActionEvent event) {
        String companyName = companyname;
        primaryStage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("/sample/Root/RootTransactionsWindow/RootTransactionsWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RootTransactionsWindowController rootTransactionsWindowController = (RootTransactionsWindowController) fxmlLoader.getController();

        rootTransactionsWindowController.setup(primaryStage,username,companyname);
        rootTransactionsWindowController.setUsername(username);
        rootTransactionsWindowController.setCurrent_companyname(companyName);

        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Root/RootTransactionsWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

}

