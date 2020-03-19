package sample.User.UserPieChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import sample.Main;
import sample.User.UserTransactionsWindow.UserTransactionsWindowController;

import java.io.IOException;

public class UserPieChartController {

    @FXML
    private PieChart pieChart;

    private String transname;

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



    public void setup(Stage primaryStage,String transname,String companyname){
        this.companyname=companyname;
        this.primaryStage=primaryStage;
        String clm="sendPieChartData," + transname;
        double risk = 0,dev = 0,dis = 0;
        try {
            Main.os.writeObject(clm);
            String data=(String)Main.is.readObject();
            String[] msg=data.split(",");
            risk=Double.parseDouble(msg[0]);
            dev=Double.parseDouble(msg[1]);
            dis=Double.parseDouble(msg[2]);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Уровень риска",risk),
                    new PieChart.Data("Ср.кв.отклонение",dev),
                    new PieChart.Data("Дисперсия",dis)
            );
        pieChart.setData(pieChartData);
    }

    public void goBackClick(ActionEvent actionEvent) {
        String companyName = companyname;
        primaryStage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("/sample/User/UserTransactionsWindow/UserTransactionsWindow.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserTransactionsWindowController userTransactionsWindowController = (UserTransactionsWindowController) fxmlLoader.getController();

        userTransactionsWindowController.setup(primaryStage,username,companyname);
        userTransactionsWindowController.setUsername(username);
        userTransactionsWindowController.setCurrent_companyname(companyName);

        primaryStage.setTitle("");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/User/UserTransactionsWindow/style.css");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }
}
