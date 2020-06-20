package org.fis.student.Services;

import org.fis.student.Controllers.AdminController;
import org.fis.student.Models.Admin;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class AdminServiceTest extends ApplicationTest {

    @BeforeClass
    public static void setupClass(){
        AdminService.setPath("src/test/resources/Admins.json");
        AdminService.loadAdmins();
    }

    @Before
    public void setUp(){
        AdminService.setA(new ArrayList<>());
    }

    @Test
    public void loadWriteTest(){
        AdminService.getA().add(new Admin("admin","pass"));

        AdminService.writeAdmins();
        AdminService.getA().clear();
        AdminService.loadAdmins();

        assertEquals("Writing done!", 1, AdminService.getA().size());
    }

    @After
    public void clearAdmins(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
    }

    @Test
    public void credentialsTestShouldBeCorrect(){
        AdminService.getA().add(new Admin("admin", AdminService.encodePassword("password")));

        assertTrue("Correct Data", AdminService.checkCredentials("admin", AdminService.encodePassword("password")));
    }

    @After
    public void clearAdmins2(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
    }

    @Test
    public void credentialsTestShouldBeIncorrect(){
        AdminService.getA().add(new Admin("user", AdminService.encodePassword("password")));

        assertFalse("Incorrect data", AdminService.checkCredentials("admin", AdminService.encodePassword("password")));
    }

    @After
    public void clearAdmins3(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
    }
}
