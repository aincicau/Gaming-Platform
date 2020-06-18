package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.student.Exceptions.UserAlreadyExists;
import org.fis.student.Models.Admin;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {
    @FXML
    private TextField idField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Label alertLabel;

    public void initialize(){
        role.getItems().addAll("Customer", "Admin");
        role.setValue("Customer");
        alertLabel.setText("");
    }

    public void backButton(){
        try
        {
            Stage stage=(Stage)role.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva,600,600));

        }catch(IOException e) {
            alertLabel.setText("User already exists!");
            System.out.println(e);
        }
    }

    public void register(){
        String id = idField.getText();
        String pass = passwordField.getText();

        if(role.getValue().equals("Customer")){
            try {
                for (Customer i : CustomerService.getC()) {
                    if (i.getID().equals(id))
                        throw new UserAlreadyExists();
                }

                CustomerService.getC().add(new Customer(id, CustomerService.encodePassword(pass), new ArrayList<Game>(), 1000));
                CustomerService.writeCustomers();

                try
                {
                    Stage stage=(Stage)idField.getScene().getWindow();
                    Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("LoginCustomer.fxml"));
                    stage.setTitle("Login Customer");
                    stage.setScene(new Scene(ceva,600,600));

                }catch(IOException e) {
                    System.out.println(e);
                }

            }catch(Exception e){
                alertLabel.setText("User already exists!");
                System.out.println(e);
            }
        }else{
            try {
                for (Admin i : AdminService.getA()) {
                    if (i.getUsername().equals(id))
                        throw new UserAlreadyExists();
                }

                AdminService.getA().add(new Admin(id, AdminService.encodePassword(pass)));
                AdminService.writeAdmins();

                try
                {
                    Stage stage=(Stage)idField.getScene().getWindow();
                    Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("LoginAdmin.fxml"));
                    stage.setTitle("Login Administrator");
                    stage.setScene(new Scene(ceva,600,600));

                }catch(IOException e) {
                    System.out.println(e);
                }

            }catch (Exception e){
                alertLabel.setText("User already exists!");
                System.out.println(e);
            }
        }
    }
}
