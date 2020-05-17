package org.fis.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.Services.CustomerService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomerService.loadCustomers();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Choice.fxml"));
        primaryStage.setTitle("Choice");
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.show();
    }
}
