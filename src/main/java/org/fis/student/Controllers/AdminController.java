package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.student.Exceptions.IncorrectAdmin;
import org.fis.student.Models.Admin;
import org.fis.student.Services.AdminService;

import java.io.IOException;
import java.util.ArrayList;

public class AdminController {
    @FXML
    Label id;
    @FXML
    TextField fieldID;
    @FXML
    PasswordField fieldPass;
    @FXML
    Label alertLabel;

    @FXML
    public void initialize(){
        alertLabel.setText("");
    }

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
    public void butonLogin(){
        try {
            if (AdminService.checkCredentials(fieldID.getText(), AdminService.encodePassword(fieldPass.getText()))) {
                try{
                    ArrayList<Admin> ad = AdminService.getA();
                    for(Admin i:ad){
                        if(fieldID.getText().equals(i.getUsername())){
                            i.setLogged(true);
                        }
                    }

                    Stage stage=(Stage)id.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminPage.fxml"));
                    stage.setTitle("Administration Page");
                    stage.setScene(new Scene(root,600,600));
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            else
                throw new IncorrectAdmin();
        }catch (IncorrectAdmin e){
            alertLabel.setText("Incorrect data!");
        }
    }
}

