package org.fis.student.Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class RegisterControllerTest extends ApplicationTest {

    private RegisterController controller;

    @BeforeClass
    public static void setupClass(){
        CustomerService.setPath("src/test/resources/Customers.json");
        CustomerService.loadCustomers();
        AdminService.setPath("src/test/resources/Admins.json");
        AdminService.loadAdmins();
    }

    @Before
    public void setUp(){
        CustomerService.loadCustomers();
        AdminService.loadAdmins();

        controller = new RegisterController();
        controller.idField = new TextField();
        controller.passwordField = new PasswordField();
        controller.alertLabel = new Label();
        controller.role = new ChoiceBox();
    }

    @Test
    public void addNewCustomer(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
        CustomerService.loadCustomers();

        controller.idField.setText("user");
        controller.passwordField.setText("password");
        controller.role.setValue("Customer");

        controller.register();

        assertEquals("Added", 1, CustomerService.getC().size());
    }

    @Test
    public void addSameCustomerTwice(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
        CustomerService.loadCustomers();

        controller.idField.setText("user2");
        controller.passwordField.setText("password");
        controller.role.setValue("Customer");

        controller.register();
        controller.register();

        assertEquals("Customer already exists!", 2, CustomerService.getC().size());
    }

    @Test
    public void addNewAdmin(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
        AdminService.loadAdmins();

        controller.idField.setText("admin");
        controller.passwordField.setText("password");
        controller.role.setValue("Admin");

        controller.register();

        assertEquals("Added", 1, AdminService.getA().size());
    }

    @Test
    public void addSameAdminTwice(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
        AdminService.loadAdmins();

        controller.idField.setText("admin2");
        controller.passwordField.setText("password");
        controller.role.setValue("Admin");

        controller.register();
        controller.register();

        assertEquals("Admin already exists!", 2, AdminService.getA().size());
    }

    @AfterClass
    public static void finish(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
        AdminService.getA().clear();
        AdminService.writeAdmins();
    }
}
