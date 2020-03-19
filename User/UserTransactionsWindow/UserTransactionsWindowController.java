package sample.User.UserTransactionsWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.Root.RootPieChart.RootPieChartController;
import sample.Root.RootTransactionsWindow.Transaction;
import sample.User.UserAddTransactionWindow.UserAddTransactionWindowController;
import sample.User.UserMainWindow.UserMainWindowController;
import sample.User.UserPieChart.UserPieChartController;

import java.io.IOException;

public class UserTransactionsWindowController {

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> transactionIdColumn;

    @FXML
    private TableColumn<Transaction, String> companyIdColumn;

    @FXML
    private TableColumn<Transaction, String> userIdColumn;

    @FXML
    private TableColumn<Transaction, String> nameColumn;

    @FXML
    private TableColumn<Transaction, String> riskColumn;

    @FXML
    private TableColumn<Transaction, String> deviationColumn;

    @FXML
    private TableColumn<Transaction, String> dispersionColumn;

    @FXML
    private TableColumn<Transaction, String> variationColumn;

    private ObservableList<Transaction> fields = FXCollections.observableArrayList();

    private String current_transname = "";
    private Transaction transactionCurrent_field=null;
    private String current_companyname;

    public void setCurrent_companyname(String name) {
        current_companyname=name;
    }

    private String username;

    public void setUsername(String username) {
        this.username=username;
    }

    private Stage primaryStage=null;
    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void setup(Stage primaryStage,String userName,String companyname) {
        setStage(primaryStage);
        String company_name=companyname;
        username=userName;

        String transactionId="";
        String companyId="";
        String userId="";
        String name="";
        String risk="";
        String deviation="";
        String dispersion="";
        String variation="";

        String clM="sendTransactionsInfo" + "," + company_name;
        try {
            Main.os.writeObject(clM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                transactionId=(String)Main.is.readObject();
                if (transactionId.equals("end"))
                    break;
                companyId=(String)Main.is.readObject();
                userId=(String)Main.is.readObject();
                name=(String)Main.is.readObject();
                risk=(String)Main.is.readObject();
                deviation=(String)Main.is.readObject();
                dispersion=(String)Main.is.readObject();
                variation=(String)Main.is.readObject();

                fields.add(new Transaction(transactionId,companyId,userId,name,risk,deviation,dispersion,variation));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionIdColumn"));
        companyIdColumn.setCellValueFactory(new PropertyValueFactory<>("companyIdColumn"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userIdColumn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameColumn"));
        riskColumn.setCellValueFactory(new PropertyValueFactory<>("riskColumn"));
        deviationColumn.setCellValueFactory(new PropertyValueFactory<>("deviationColumn"));
        dispersionColumn.setCellValueFactory(new PropertyValueFactory<>("dispersionColumn"));
        variationColumn.setCellValueFactory(new PropertyValueFactory<>("variationColumn"));

        transactionTable.setItems(fields);
        transactionTable.refresh();

        TableView.TableViewSelectionModel<Transaction> selectionModel = transactionTable.getSelectionModel();
        selectionModel.selectedIndexProperty().addListener((observableValue, number, t1) -> {
            Transaction transaction = transactionTable.getSelectionModel().getSelectedItem();
            current_transname = transaction.getNameColumn();
            transactionCurrent_field = transaction;
        });
    }

    @FXML
    void addNewTransactionClick(ActionEvent event) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/User/UserAddTransactionWindow/UserAddTransactionWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserAddTransactionWindowController userAddTransactionWindowController=(UserAddTransactionWindowController) fxmlLoader.getController();

        userAddTransactionWindowController.setStage(primaryStage);
        userAddTransactionWindowController.setCompanyname(current_companyname);
        userAddTransactionWindowController.setUsername(username);

        primaryStage.setTitle("Добавление предприятия");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/User/UserAddTransactionWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    @FXML
    void calcDeviationClick(ActionEvent event) {
        if (transactionCurrent_field==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String clM="calculateDeviation," + current_transname;
            try {
                Main.os.writeObject(clM);
                transactionCurrent_field.setDeviationColumn((String)Main.is.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            transactionTable.refresh();
        }
    }

    @FXML
    void calcDispersionClick(ActionEvent event) {
        if (transactionCurrent_field==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String clM="calculateDispersion," + current_transname;
            try {
                Main.os.writeObject(clM);
                transactionCurrent_field.setDispersionColumn((String)Main.is.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            transactionTable.refresh();
        }

    }

    @FXML
    void calcRiskClick(ActionEvent event) {
        if (transactionCurrent_field==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String clM="calculateRisk," + current_transname;
            try {
                Main.os.writeObject(clM);
                transactionCurrent_field.setRiskColumn((String)Main.is.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            transactionTable.refresh();
        }
    }

    @FXML
    void calcVariationClick(ActionEvent event) {
        if (transactionCurrent_field==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String clM="calculateVariation," + current_transname;
            try {
                Main.os.writeObject(clM);
                transactionCurrent_field.setVariationColumn((String)Main.is.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            transactionTable.refresh();
        }
    }


    @FXML
    void goBackClick(ActionEvent event) {
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

    public void saveToFileClick(ActionEvent actionEvent) {
        if (transactionCurrent_field==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else if(transactionCurrent_field.getRiskColumn().equals(null)||transactionCurrent_field.getDeviationColumn().equals(null)||transactionCurrent_field.getDispersionColumn().equals(null)||transactionCurrent_field.getVariationColumn().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала рассчитайте все показатели!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String clM="saveTransactionToFile," + current_transname;
            try {
                Main.os.writeObject(clM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showPieChart(ActionEvent actionEvent) {
        if (transactionCurrent_field==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите операцию из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            primaryStage.hide();
            FXMLLoader fxmlLoader = new FXMLLoader();

            Parent root = null;
            try {
                root = fxmlLoader.load(getClass().getResource("/sample/User/UserPieChart/UserPieChart.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            UserPieChartController userpieChartController = (UserPieChartController) fxmlLoader.getController();
            userpieChartController.setStage(primaryStage);
            userpieChartController.setup(primaryStage,current_transname,current_companyname);


            primaryStage.setTitle("");
            Scene scene = new Scene(root);
            //scene.getStylesheets().add("sample/Root/RootTransactionsWindow/style.css");
            primaryStage.setResizable(true);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();

        }
    }
}