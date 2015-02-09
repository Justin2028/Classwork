/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.riskboard;

import edu.govschool.riskboard.modals.MessageBox;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 *
 * @author Glaedwyn
 */
public class Country {

    ArrayList<Country> borders;
    private int numTroops = 0;
    String owner;
    String name;
    String p1 = "Player 1";
    String p2 = "Player 2";
    static int turn = 0;
    static int p1remaining = 10;
    static int p2remaining = 10;
    
    public Country(String name) {
        this.name = name;
        borders = new ArrayList();
    }
    /**
     * displayName
     * This method is the default action kind of handling thing.
     * This method is what handles a button click, based on whose turn it is.
     * @param btn 
     */
    
    public void displayName(Button btn) {
        if (turn % 2 == 1) {
            //if unselected, add troops and set color based on which turn
            if (numTroops == 0) {
                turn++;
                owner = p1;
                numTroops++;
                setColor(btn);
            }
        } else {
            if (numTroops == 0) {
                turn++;
                owner = p2;
                numTroops++;
                setColor(btn);
            }
        }
        if (turn == 42) {
            //All countries are full, begin reinforcing
            MessageBox.show("All countries have been selected. Please begin reinforcing with ten armies each", "All countries selected");
            turn++;
        } 
        else if (turn > 42 && turn <= 62) {
            if (owner.equals(p1)) {
                if (p1remaining > 0) {
                    p1remaining--;
                    numTroops++;
                    turn++;
                }
            } else if (owner.equals(p2)) {
                if (p2remaining > 0) {
                    p2remaining--;
                    numTroops++;
                    turn++;
                }
            }
        }
        p1remaining = 31;
        p2remaining = 31;
        
    }

    public void setColor(Button btn) {
        if (turn % 2 == 1) {
            btn.setStyle("-fx-background-color: #FF1100");
        } else {
            btn.setStyle("-fx-background-color: #0011FF");
        }
    }

    /**
     * numTroops() Returns the number of troops on this country.
     *
     * @return numTroops the number of troops on this country
     */
    public int numTroops() {
        return numTroops;
    }

    public void updateTroopsBattle(int lost) {
        numTroops -= lost;
    }

    public boolean battleLost() {
        return numTroops == 0;
    }

    public void updateTroopsConquered(int num) {
        numTroops = num;
    }

    public int numDiceAllowed(boolean attacking) {
        int numDice = 0;
        if (attacking) {
            numDice = numTroops - 1;
            if (numDice > 3) {
                numDice = 3;
            }
            if (numDice == 0) {
                MessageBox.show("Insufficient Troops", "Error");
            }
        } else {
            numDice = numTroops;
            if (numDice > 2) {
                numDice = 2;
            }
        }
        return numDice;
    }
}
