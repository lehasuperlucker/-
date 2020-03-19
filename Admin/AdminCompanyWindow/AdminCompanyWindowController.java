package sample.Admin.AdminCompanyWindow;

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
import sample.Admin.AdminAddCompanyWindow.AdminAddCompanyWindowController;
import sample.Admin.AdminMainWindow.AdminMainWindowController;
import sample.Admin.AdminTransactionsWindow.AdminTransactionsWindowController;
import sample.Main;
import sample.Root.RootCompanyWindow.Company;

import java.io.IOException;

public class AdminCompanyWindowController {

    @FXML
    private TableView<Company> tableCompany;
    @FXML
    private TableColumn<Company,String> userIdColumn;
    @FXML
    private TableColumn<Company,String> companyIdColumn;
    @FXML
    private TableColumn<Company,String> nameColumn;
    @FXML
    private TableColumn<Company,String> formColumn;
    @FXML
    private TableColumn<Company,String> districtColumn;
    @FXML
    private TableColumn<Company,String> regionColumn;
    @FXML
    private TableColumn<Company,String> employeeColumn;
    @FXML
    private TableColumn<Company,String> ownerColumn;
    @FXML
    private TableColumn<Company,String> yearColumn;
    @FXML
    private TableColumn<Company,String> addressColumn;
    @FXML
    private TableColumn<Company,String> mailColumn;
    @FXML
    private TableColumn<Company,String> siteColumn;

    private ObservableList<Company> fields = FXCollections.observableArrayList();

    private String current_name = "";
    private Company companyField_current=null;
    private String username;

    public void setUsername(String username) {
        this.username=username;
    }

    private Stage primaryStage=null;

    public void setStage(Stage stage) {
        primaryStage= stage;
    }

    public void setup(Stage primaryStage) {
        setStage(primaryStage);

        String userId="";
        String companyId="";
        String name="";
        String form="";
        String district="";
        String region="";
        String employee="";
        String owner="";
        String foundation_year="";
        String address="";
        String mail="";
        String site="";

        try {
            Main.os.writeObject("sendCompanyInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                userId=(String)Main.is.readObject();
                if (userId.equals("end"))
                    break;
                companyId=(String)Main.is.readObject();
                name=(String)Main.is.readObject();
                form=(String)Main.is.readObject();
                district=(String)Main.is.readObject();
                region=(String)Main.is.readObject();
                employee=(String)Main.is.readObject();
                owner=(String)Main.is.readObject();
                foundation_year=(String)Main.is.readObject();
                address=(String)Main.is.readObject();
                mail=(String)Main.is.readObject();
                site=(String)Main.is.readObject();

                fields.add(new Company(userId,companyId,name,form,district,region,employee,owner,foundation_year,address,mail,site));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        userIdColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("userIdColumn"));
        companyIdColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("companyIdColumn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("nameColumn"));
        formColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("formColumn"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("districtColumn"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("regionColumn"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("employeeColumn"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("ownerColumn"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("yearColumn"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("addressColumn"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("mailColumn"));
        siteColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("siteColumn"));

        tableCompany.setItems(fields);
        tableCompany.refresh();

        TableView.TableViewSelectionModel<Company> selectionModel = tableCompany.getSelectionModel();
        selectionModel.selectedIndexProperty().addListener((observableValue, number, t1) -> {
            Company company = tableCompany.getSelectionModel().getSelectedItem();
            current_name = company.getNameColumn();
            companyField_current = company;
        });
    }

    public void goBackClick(ActionEvent actionEvent) {
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

    public void addNewCompanyClick(ActionEvent actionEvent) {
        primaryStage.hide();

        FXMLLoader fxmlLoader=new FXMLLoader();

        Parent root=null;
        try {
            root=fxmlLoader.load(getClass().getResource("/sample/Admin/AdminAddCompanyWindow/AdminAddCompanyWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminAddCompanyWindowController adminaddCompanyWindowController=(AdminAddCompanyWindowController) fxmlLoader.getController();

        adminaddCompanyWindowController.setStage(primaryStage);

        adminaddCompanyWindowController.setUsername(username);

        primaryStage.setTitle("Добавление предприятия");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Admin/AdminAddCompanyWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void deleteSelectedCompanyClick(ActionEvent actionEvent) {
        String companyName=current_name;
        if (companyField_current==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите предприятие из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else
        {
            fields.remove(companyField_current);
            String clM="deleteCompany" + "," + companyName;
            try {
                Main.os.writeObject(clM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tableCompany.refresh();
    }


    public void showTransactionsClick(ActionEvent actionEvent) {
        if (companyField_current==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Сначала выберите предприятие из таблицы!");

            alert.showAndWait();
            alert.hide();
        }
        else {
            String companyName = current_name;
            primaryStage.hide();
            FXMLLoader fxmlLoader = new FXMLLoader();

            Parent root = null;
            try {
                root = fxmlLoader.load(getClass().getResource("/sample/Admin/AdminTransactionsWindow/AdminTransactionsWindow.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            AdminTransactionsWindowController adminTransactionsWindowController = (AdminTransactionsWindowController) fxmlLoader.getController();

            adminTransactionsWindowController.setup(primaryStage,username,companyName);
            adminTransactionsWindowController.setUsername(username);
            adminTransactionsWindowController.setCurrent_companyname(companyName);

            primaryStage.setTitle("");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("sample/Admin/AdminTransactionsWindow/style.css");
            primaryStage.setResizable(true);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();
    }
}}
