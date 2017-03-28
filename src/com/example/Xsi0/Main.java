package com.example.Xsi0;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //git test
    int playerSelectionRow;
    int playerSelectionCol;
    GameStatus currentState = GameStatus.PLAYING;
    Board board = new Board();
    CellContent currentPlayer = firstPlayer();

    public static void main(String[] args) {
        Main myMain = new Main();
        myMain.start();
        while(myMain.currentState == GameStatus.IDLE) {
            System.out.println("Play again?");
            String response;
            Scanner reader = new Scanner(System.in);
            response = reader.nextLine();
            if(response.equalsIgnoreCase("yes")) {
                myMain.currentState = GameStatus.PLAYING;
                myMain.board.reInit();
                myMain.start();
            }
            else {
                System.out.println("END");
                break;
            }
        }
    }

    public void start() {
        while(currentState == GameStatus.PLAYING) {
            playerMove(currentPlayer);
            updateGameStatus(currentPlayer);
            board.paint();
        }
        switch (currentState) {
            case DRAW:
                System.out.println("Tie");
                currentState = GameStatus.IDLE;
                break;
            case OWON:
                System.out.println("Player O won");
                currentState = GameStatus.IDLE;
                break;
            case XWON:
                System.out.println("Player X won");
                currentState = GameStatus.IDLE;
                break;
            case PLAYING:
                System.out.println("error somewhere");
                break;
        }
    }

    public CellContent firstPlayer () {
        Random randomGenerator = new Random();
        int xScore = randomGenerator.nextInt(10);
        int oScore = randomGenerator.nextInt(10);
        if (xScore>=oScore) {
            System.out.println("Player X goes first with score " + xScore);
            return CellContent.X;
        }
        else {
            System.out.println("Player O goes first with score " + oScore);
            return CellContent.O;
        }
    }


    public void playerMove(CellContent symbol) {
        //position choice
            String playerSelection;
            boolean isInputValid;
            System.out.println("Player " + symbol + ":");
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter position: ");

        do {
            playerSelection = reader.nextLine();
            isInputValid = positionValidator(playerSelection);

        }while(!isInputValid);
        board.setCells(playerSelectionRow, playerSelectionCol, symbol);
    }


    public void updateGameStatus(CellContent symbol) {
        if(board.isTie()) {
            currentState = GameStatus.DRAW;
        }
        if(board.wins(symbol)) {
            if (symbol == CellContent.X)
                currentState = GameStatus.XWON;
            else
                currentState = GameStatus.OWON;
        }

        if (symbol == CellContent.X)
            currentPlayer = CellContent.O;
        else currentPlayer = CellContent.X;
    }

    public boolean positionValidator(String inputPosition) {
        String pattern = "^[1-3]{2}$";
        boolean isValid = inputPosition.matches(pattern);
//        boolean isCellEmpty = true;
        if(!isValid) {
            System.out.println("Invalid please retry: ");
            return false;
        }
        else {
            playerSelectionRow = Character.getNumericValue(inputPosition.charAt(0));
            playerSelectionCol = Character.getNumericValue(inputPosition.charAt(1));
            boolean isCellEmpty = board.isCellFree(playerSelectionRow-1, playerSelectionCol-1);
            if(!isCellEmpty) {
                System.out.println("Cell not empty: ");
                return false;
            }
        }
        return true;
    }

}
