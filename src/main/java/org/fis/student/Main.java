package org.fis.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;
import org.fis.student.Services.GameService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomerService.setPath("src/main/resources/Customers.json");
        CustomerService.loadCustomers();
        AdminService.setPath("src/main/resources/Admins.json");
        AdminService.loadAdmins();
        GameService.setPath("src/main/resources/Games.json");
        GameService.loadGames();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
        primaryStage.setTitle("Choice");
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.show();
    }
}
