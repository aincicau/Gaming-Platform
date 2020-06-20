package org.fis.student.Services;

import org.fis.student.Models.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class GameServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass(){
        GameService.setPath("src/test/resources/Games.json");
        GameService.loadGames();
    }

    @Before
    public void setUp(){
        GameService.setG(new ArrayList<>());
    }

    @Test
    public void loadWriteTest(){
        GameService.getG().add(new Game("game",50));

        GameService.writeGames();
        GameService.getG().clear();
        GameService.loadGames();

        assertEquals("Load/write done", 1, GameService.getG().size());
    }

    @After
    public void clearGames(){
        GameService.getG().clear();
        GameService.writeGames();
    }
}
