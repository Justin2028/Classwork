/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.tictactoePersonal;

import edu.govschool.modals.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 *
 * @author Glaedwyn
 */
public class TicTacToe extends Application {

    Stage stage;
    Scene game;
    Scene option;
    Button[][] board;
    Button size3;
    Button size4;
    Button size5;
    static int size;
    static int turn = 1;
    int[][] moves;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        size = SizeOptionMenu.show();
        board = new Button[size][size];
        moves = new int[size][size];
        VBox[] columns = new VBox[size];
        HBox row = new HBox(20);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button btn = new Button();
                btn.setText("   ");
                btn.setOnAction(e -> {
                    if (turn % 2 == 1) {
                        if (!btn.getText().equals("O")) {
                            btn.setText("X");
                            updateArray();
                            turn++;
                        }
                    } else {
                        if (!btn.getText().equals("X")) {
                            btn.setText("O");
                            updateArray();
                            turn++;
                        }
                    }
                });
                board[i][j] = btn;
            }
        }
        for (int i = 0; i < size; i++) {
            columns[i] = new VBox(20);
            for (int j = 0; j < size; j++) {
                columns[i].getChildren().add(board[i][j]);
            }
        }
        for (int i = 0; i < size; i++) {
            row.getChildren().add(columns[i]);
        }
        game = new Scene(row);
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(game);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void updateArray() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("X")) {
                    moves[i][j] = 6;
                }
                else if (board[i][j].getText().equals("O")) {
                    moves[i][j] = -6;
                }
                else
                {
                    moves[i][j] = -3;
                }
            }
        }
    }
}
