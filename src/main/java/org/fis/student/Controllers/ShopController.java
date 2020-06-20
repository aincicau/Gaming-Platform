package org.fis.student.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.student.Exceptions.GameAlreadyPurchased;
import org.fis.student.Exceptions.InsufficientCredits;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.CustomerService;
import org.fis.student.Services.GameService;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class ShopController {
    @FXML
    Button logoutButton;
    @FXML
    TableView<Game> tableView;
    @FXML
    TableColumn<Game,String> nameColumn;
    @FXML
    TableColumn<Game,Integer> priceColumn;
    @FXML
    Label labelCredit;

    Customer current;

    @FXML
    public void initialize(){
        tableView.setItems(FXCollections.observableArrayList(GameService.getG()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game,Integer>("price"));

        ArrayList<Customer> c = CustomerService.getC();
        for(Customer i:c){
            if(i.isLogged()){
                current = i;
            }
        }

        labelCredit.setText(String.valueOf(current.getCredit()));
    }

    @FXML
    public void logout() {
        try {
            current.setLogged(false);

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
        ArrayList<Game> currentUserGames = current.getGames();

        Game g = tableView.getSelectionModel().getSelectedItem();

        boolean flag = false;
        for(Game i:currentUserGames) {
            if (g.equals(i)){
                flag = true;
            }
        }

        try{
            if(!flag) {
                if(current.getCredit()-g.getPrice()>=0) {
                    current.setCredit(Integer.parseInt(labelCredit.getText()) - g.getPrice());
                    labelCredit.setText(String.valueOf(current.getCredit()));
                    currentUserGames.add(g);
                }
                else{
                    throw new InsufficientCredits();
                }
            }
            else{
                throw new GameAlreadyPurchased();
            }
        }catch (Exception e){
            System.out.println(e);
        }

        CustomerService.writeCustomers();
    }

    @FXML
    public void myGames(){
        try{
            Stage stage=(Stage)tableView.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("MyGames.fxml"));
            stage.setTitle("My Games");
            stage.setScene(new Scene(ceva,600,600));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
