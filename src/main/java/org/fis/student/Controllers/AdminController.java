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

public class AdminController {
    @FXML
    private Label id;
    @FXML
    private TextField fieldID;
    @FXML
    private PasswordField fieldPass;

    @FXML
    public void butonBack()
    {
        try
        {
            Stage stage=(Stage)id.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
            stage.setTitle("Choice");
            stage.setScene(new Scene(ceva,600,600));

        }catch(IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void butonLogin(){
        try {
            if (AdminService.checkCredentials(fieldID.getText(), AdminService.encodePassword(fieldPass.getText())))
                System.out.println("te duce la admin page");
            else
                throw new IncorrectAdmin();
        }catch (IncorrectAdmin e){
            System.out.println(e);
        }
    }
}

