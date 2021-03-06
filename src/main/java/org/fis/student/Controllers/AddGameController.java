package org.fis.student.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.student.Exceptions.GameAlreadyExists;
import org.fis.student.Models.Game;
import org.fis.student.Services.GameService;

import java.util.ArrayList;

public class AddGameController {
    @FXML
    TextField nameField;
    @FXML
    TextField priceField;
    @FXML
    Label alertLabel;

    @FXML
    public void initialize(){
        alertLabel.setText("");
    }

    @FXML
    public void backButton(){
        try{
            Stage stage = (Stage) nameField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminPage.fxml"));
            stage.setTitle("Administration Page");
            stage.setScene(new Scene(root, 600, 600));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void addButton(){
        ArrayList<Game> g = GameService.getG();
        boolean flag = false;

        for(Game i:g){
            if(nameField.getText().equals(i.getName())){
                flag = true;
            }
        }

        try{
        if(flag){
            throw new GameAlreadyExists();
        }else{
            g.add(new Game(nameField.getText(),Integer.parseInt(priceField.getText())));
            GameService.writeGames();
            try{
                Stage stage=(Stage)nameField.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminPage.fxml"));
                stage.setTitle("Administration Page");
                stage.setScene(new Scene(root,600,600));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        }catch (Exception e){
            alertLabel.setText("Game already exists!");
        }
    }
}
