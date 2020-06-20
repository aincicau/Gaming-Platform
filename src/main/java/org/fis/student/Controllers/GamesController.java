package org.fis.student.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.CustomerService;
import org.fis.student.Services.GameService;

import javax.swing.table.TableCellEditor;
import javax.swing.text.TableView;
import java.util.ArrayList;

public class GamesController {
    @FXML
    private javafx.scene.control.TableView<Game> tableView;
    @FXML
    private javafx.scene.control.TableColumn<Game,String> nameColumn;
    @FXML
    private TableColumn<Game,Integer> priceColumn;

    @FXML
    public void initialize(){
        ArrayList<Customer> c = CustomerService.getC();
        Customer current = null;
        for(Customer i:c){
            if(i.isLogged()){
                current = i;
            }
        }

        tableView.setItems(FXCollections.observableArrayList(current.getGames()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game,Integer>("price"));
    }

    @FXML
    public void backButton(){
        try{
            Stage stage=(Stage)tableView.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ShopPage.fxml"));
            stage.setTitle("Shop Page");
            stage.setScene(new Scene(root,600,600));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
