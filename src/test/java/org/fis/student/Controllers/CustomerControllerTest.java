package org.fis.student.Controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.student.Models.Customer;
import org.fis.student.Services.CustomerService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class CustomerControllerTest extends ApplicationTest {
    public static final String TEST_USER = "id1";
    public static final String TEST_PASSWORD = "pass1";
    public static final String WRONG_USER = "user";
    public static final String WRONG_PASSWORD = "pass";

    private CustomerController controller;

    @BeforeClass
    public static void setupClass(){
        CustomerService.setPath("src/main/resources/Customers.json");
        CustomerService.loadCustomers();
    }

    @Before
    public void setUp(){
        controller = new CustomerController();
        controller.fieldID = new TextField();
        controller.fieldPass = new PasswordField();
    }

    @Test
    public void testCorrectUser(){
        controller.fieldID.setText(TEST_USER);
        controller.fieldPass.setText(TEST_PASSWORD);

        boolean flag = false;
        ArrayList<Customer> customers = CustomerService.getC();

        controller.butonLogin();

        for(Customer i:customers){
            if(i.isLogged()){
                flag = true;
            }
        }

        assertTrue("Login done successfully!",flag);
    }

    @Test
    public void testWrongUser(){
        controller.fieldID.setText(WRONG_USER);
        controller.fieldPass.setText(WRONG_PASSWORD);

        boolean flag = false;
        ArrayList<Customer> customers = CustomerService.getC();

        controller.butonLogin();

        for(Customer i:customers){
            if(i.isLogged()){
                flag = true;
            }
        }

        assertTrue("Login failed!",flag);
    }
}
