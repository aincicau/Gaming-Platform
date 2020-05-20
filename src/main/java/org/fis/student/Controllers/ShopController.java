package org.fis.student.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.CustomerService;
import org.fis.student.Services.GameService;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class ShopController {
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<Game> tableView;
    @FXML
    private TableColumn<Game,String> nameColumn;
    @FXML
    private TableColumn<Game,Integer> priceColumn;
    @FXML
    private Label labelCredit;

    @FXML
    public void initialize(){
        tableView.setItems(FXCollections.observableArrayList(GameService.getG()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game,Integer>("price"));

        ArrayList<Customer> c = CustomerService.getC();
        for(Customer i:c){
            if(i.isLogged()){
                labelCredit.setText(String.valueOf(i.getCredit()));
            }
        }
    }

    @FXML
    public void logout() {
        try {
            ArrayList<Customer> c = CustomerService.getC();
            for(Customer i:c){
                if(i.isLogged()){
                    i.setLogged(false);
                }
            }

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva, 600, 600));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void purchaseGame(){

    }
}
