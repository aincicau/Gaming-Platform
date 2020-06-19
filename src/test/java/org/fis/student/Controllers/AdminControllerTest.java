package org.fis.student.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.student.Models.Admin;
import org.fis.student.Models.Customer;
import org.fis.student.Services.AdminService;
import org.fis.student.Services.CustomerService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AdminControllerTest extends ApplicationTest {
    public static final String TEST_USER = "id1";
    public static final String TEST_PASSWORD = "pass1";
    public static final String WRONG_USER = "user";
    public static final String WRONG_PASSWORD = "pass";

    private AdminController controller;

    @BeforeClass
    public static void setupClass(){
        AdminService.setPath("src/main/resources/Customers.json");
        AdminService.loadAdmins();
    }

    @Before
    public void setUp(){
        controller = new AdminController();
        controller.fieldID = new TextField();
        controller.fieldPass = new PasswordField();
        controller.id = new Label();
    }

    @Test
    public void testCorrectUser(){
        controller.fieldID.setText(TEST_USER);
        controller.fieldPass.setText(TEST_PASSWORD);

        boolean flag = false;
        ArrayList<Admin> admins = AdminService.getA();

        controller.butonLogin();

        for(Admin i:admins){
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
        ArrayList<Admin> admins = AdminService.getA();

        controller.butonLogin();

        for(Admin i:admins){
            if(i.isLogged()){
                flag = true;
            }
        }

        assertTrue("Login failed!",flag);
    }
}
