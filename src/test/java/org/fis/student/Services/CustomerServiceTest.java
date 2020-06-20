package org.fis.student.Services;

import org.fis.student.Controllers.CustomerController;
import org.fis.student.Models.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class CustomerServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass(){
        CustomerService.setPath("src/test/resources/Customers.json");
        CustomerService.loadCustomers();
    }

    @Before
    public void setUp(){
        CustomerService.setC(new ArrayList<>());
    }

    @Test
    public void loadWriteTest(){
        CustomerService.getC().add(new Customer("user","password",new ArrayList<>(),1000));

        CustomerService.writeCustomers();
        CustomerService.getC().clear();
        CustomerService.loadCustomers();

        assertEquals("Load/Write done", 1, CustomerService.getC().size());
    }

    @After
    public void clearCustomers(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
    }

    @Test
    public void credentialsTestShouldBeCorrect() {
        CustomerService.getC().add(new Customer("user", CustomerService.encodePassword("password"),new ArrayList<>(),1000));

        assertTrue("Correct", CustomerService.checkCredentials("user", CustomerService.encodePassword("password")));
    }

    @After
    public void clearCustomers2(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
    }

    @Test
    public void credentialsTestShouldBeIncorrect() {
        CustomerService.getC().add(new Customer("user", CustomerService.encodePassword("password"),new ArrayList<>(),1000));

        assertFalse("Correct", CustomerService.checkCredentials("admin", CustomerService.encodePassword("password")));
    }

    @After
    public void clearCustomers3(){
        CustomerService.getC().clear();
        CustomerService.writeCustomers();
    }
}
