package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ShopController {
    @FXML
    private Button logoutButton;

    @FXML
    public void logout(){
        try{
            Stage stage=(Stage)logoutButton.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva,600,600));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
