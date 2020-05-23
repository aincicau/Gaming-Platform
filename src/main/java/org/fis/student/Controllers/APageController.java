package org.fis.student.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.student.Models.Admin;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;
import org.fis.student.Services.GameService;

import java.util.ArrayList;

public class APageController {
    @FXML
    private Button logButton;
    @FXML
    private TableView<Game> tableView;
    @FXML
    private TableColumn<Game,String> nameColumn;
    @FXML
    private TableColumn<Game,Integer> priceColumn;

    private Admin current;

    @FXML
    public void initialize(){
        GameService.loadGames();
        tableView.setItems(FXCollections.observableArrayList(GameService.getG()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game,Integer>("price"));

        ArrayList<Admin> c = AdminService.getA();
        for(Admin i:c){
            if(i.isLogged()){
                current = i;
            }
        }
    }

    @FXML
    public void logOut(){
        try {
            current.setLogged(false);

            Stage stage = (Stage) logButton.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva, 600, 600));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void addButton(){
        try {
            Stage stage = (Stage) logButton.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("AddGame.fxml"));
            stage.setTitle("Add Game Screen");
            stage.setScene(new Scene(ceva, 600, 600));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void deleteButton(){
        Game x = tableView.getSelectionModel().getSelectedItem();

        ArrayList<Game> g = GameService.getG();

        for(Game i:g){
            if(i.equals(x)){
                x = i;
            }
        }

        g.remove(x);
        GameService.writeGames();

        this.initialize();
    }
}
