package org.fis.student.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fis.student.Services.GameService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AddGameControllerTest extends ApplicationTest {
    private AddGameController controller;

    @BeforeClass
    public static void setupClass(){
        GameService.setPath("src/test/resources/Games.json");
        GameService.loadGames();
    }

    @Before
    public void setUp(){
        controller = new AddGameController();
        controller.nameField = new TextField();
        controller.priceField = new TextField();
        controller.alertLabel = new Label();
    }

    @Test
    public void addGame(){
        controller.nameField.setText("new game");
        controller.priceField.setText("50");

        controller.addButton();

        assertEquals("Added", 1, GameService.getG().size());
    }
    @After
    public void afterAddedSingleGame(){
        GameService.getG().clear();
        GameService.writeGames();
    }

    @Test
    public void addSameGameTwice(){
        controller.nameField.setText("new game");
        controller.priceField.setText("50");

        controller.addButton();
        controller.addButton();

        assertEquals("Second time not added", 1, GameService.getG().size());
    }

    @After
    public void afterAddedGameTwice(){
        GameService.getG().clear();
        GameService.writeGames();
    }
}
