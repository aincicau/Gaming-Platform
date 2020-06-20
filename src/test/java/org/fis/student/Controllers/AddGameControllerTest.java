package org.fis.student.Controllers;

import javafx.scene.control.TextField;
import org.fis.student.Models.Game;
import org.fis.student.Services.GameService;
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
    }

    @Test
    public void addGame(){
        controller.nameField.setText("new game");
        controller.priceField.setText("50");

        controller.addButton();

        assertEquals("Added", 2, GameService.getG().size());

        GameService.getG().clear();
        GameService.getG().add(new Game("name", 20));
        GameService.writeGames();
    }

    @Test
    public void addSameGameTwice(){
        controller.nameField.setText("new game");
        controller.priceField.setText("50");

        controller.addButton();
        controller.addButton();

        assertEquals("Added", 3, GameService.getG().size());

        GameService.getG().clear();
        GameService.getG().add(new Game("name", 20));
        GameService.writeGames();
    }
}
