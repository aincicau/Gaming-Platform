package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.student.Models.Admin;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {
    @FXML
    private TextField idField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox role;

    public void initialize(){
        role.getItems().addAll("Customer", "Admin");
        role.setValue("Customer");
    }

    public void backButton(){
        try
        {
            Stage stage=(Stage)role.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva,600,600));

        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public void register(){
        String id = idField.getText();
        String pass = passwordField.getText();

        if(role.getValue().equals("Customer")){
            CustomerService.getC().add(new Customer(id, CustomerService.encodePassword(pass), new ArrayList<Game>(), 1000));
            CustomerService.writeCustomers();
        }else{
            AdminService.getA().add(new Admin(id, AdminService.encodePassword(pass)));
            AdminService.writeAdmins();
        }
    }
}
