package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.student.Exceptions.IncorrectCustomer;
import org.fis.student.Models.Customer;
import org.fis.student.Services.CustomerService;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerController {
    @FXML
    Label id;
    @FXML
    TextField fieldID;
    @FXML
    PasswordField fieldPass;

    @FXML
    public void butonBack()
    {
        try
        {
            Stage stage=(Stage)id.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(root,600,600));

        }catch(IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void butonLogin()
    {
        try {
            if (CustomerService.checkCredentials(fieldID.getText(), CustomerService.encodePassword(fieldPass.getText()))){
                try{
                    ArrayList<Customer> c = CustomerService.getC();
                    for(Customer i:c){
                        if(fieldID.getText().equals(i.getID())){
                            i.setLogged(true);
                        }
                    }

                    Stage stage=(Stage)id.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ShopPage.fxml"));
                    stage.setTitle("Shop Page");
                    stage.setScene(new Scene(root,600,600));
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            else
                throw new IncorrectCustomer();
        }catch (IncorrectCustomer e){
            System.out.println(e);
        }
    }
}
