package org.fis.student.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.fis.student.Models.Admin;
import org.fis.student.Models.Game;
import org.fis.student.Services.GameService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class APageControllerTest extends ApplicationTest {
    private APageController controller;

    @BeforeClass
    public static void setupClass(){
        GameService.setPath("src/test/resources/Games.json");
        GameService.loadGames();
    }

    @Before
    public void setUp(){
        controller = new APageController();
        controller.logButton = new Button();
        controller.tableView = new TableView<>();
        controller.nameColumn = new TableColumn<>();
        controller.priceColumn = new TableColumn<>();
        controller.current = new Admin();
    }

    @Test
    public void logoutTest(){
        controller.current = new Admin("user", "password");
        controller.current.setLogged(true);

        controller.logOut();

        assertTrue("Logged out", !controller.current.isLogged());
    }

    @Test
    public void deleteGameTest(){
        controller.tableView.getSelectionModel().select(GameService.getG().get(0));

        controller.deleteButton();

        assertEquals("Deleted", 0, GameService.getG().size());

        GameService.getG().clear();
        GameService.getG().add(new Game("game",20));
        GameService.writeGames();
    }
}
