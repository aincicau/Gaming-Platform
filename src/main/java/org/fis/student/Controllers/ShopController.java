package org.fis.student.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.student.Models.Game;
import org.fis.student.Services.GameService;
import javafx.collections.FXCollections;

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
    public void initialize(){
        tableView.setItems(FXCollections.observableArrayList(GameService.getG()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game,Integer>("price"));
    }

    @FXML
    public void logout() {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva, 600, 600));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
