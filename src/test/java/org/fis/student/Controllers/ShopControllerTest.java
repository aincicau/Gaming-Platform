package org.fis.student.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.fis.student.Models.Customer;
import org.fis.student.Models.Game;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class ShopControllerTest extends ApplicationTest {
    private ShopController controller;

    @Before
    public void setUp(){
        controller = new ShopController();
        controller.logoutButton = new Button();
        controller.tableView = new TableView<>();
        controller.nameColumn = new TableColumn<>();
        controller.priceColumn = new TableColumn<>();
        controller.labelCredit = new Label();
        controller.current = new Customer();
        controller.alertLabel = new Label();
    }

    @Test
    public void buyGame(){
        Customer c = new Customer("user", "password", new ArrayList<Game>(), 1000);
        Game g = new Game("game",50);

        controller.current = c;
        controller.tableView.getSelectionModel().select(g);
        controller.labelCredit.setText(String.valueOf(1000));

        controller.purchaseGame();

        boolean flag = false;

        for(Game i:c.getGames()){
            if(i.getName().equals(g.getName())){
                flag = true;
            }
        }

        assertTrue("Bought",flag);
    }

    @Test
    public void insufficentCredit(){
        Customer c = new Customer("user", "password", new ArrayList<Game>(), 10);
        Game g = new Game("game",50);

        controller.current = c;
        controller.tableView.getSelectionModel().select(g);
        controller.labelCredit.setText(String.valueOf(10));

        controller.purchaseGame();

        boolean flag = false;

        for(Game i:c.getGames()){
            if(i.getName().equals(g.getName())){
                flag = true;
            }
        }

        assertFalse("Insufficient credit!",flag);
    }

    @Test
    public void alreadyBought(){
        Customer c = new Customer("user", "password", new ArrayList<Game>(), 1000);
        c.getGames().add(new Game("game",50));
        Game g = new Game("game",50);

        controller.current = c;
        controller.tableView.getSelectionModel().select(g);
        controller.labelCredit.setText(String.valueOf(1000));

        controller.purchaseGame();

        assertEquals("Game already bought!", 1, c.getGames().size());
    }
}
