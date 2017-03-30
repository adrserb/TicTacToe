package com.example.Xsi0;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //git test
    int playerSelectionRow;
    int playerSelectionCol;
    GameStatus currentState = GameStatus.PLAYING;
    Board board = new Board();
    AbstractPlayer player1 = new HumanPlayer(CellContent.X, board);
    AbstractPlayer player2;

    AbstractPlayer currentPlayer;

    public static void main(String[] args) {
        Main myMain = new Main();
        myMain.start();
        while(myMain.currentState == GameStatus.IDLE) {
            System.out.println("Play again?");
            String response;
            Scanner playAgainReader = new Scanner(System.in);
            response = playAgainReader.nextLine();
            System.out.println(response);
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
        System.out.println("Choose your opponent: 1 for human 2 for C1:");
        Scanner reader = new Scanner(System.in);
        int opponentType = reader.nextInt();
        if(opponentType == 1) {
            player2 = new HumanPlayer(CellContent.O, board);
        }
        else {
            player2 = new ComputerPlayerLevel1(CellContent.O, board);
        }

        currentPlayer = firstPlayer();
        while(currentState == GameStatus.PLAYING) {
            currentPlayer.playerMove();
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

    public AbstractPlayer firstPlayer () {
        Random randomGenerator = new Random();
        int xScore = randomGenerator.nextInt(10);
        int oScore = randomGenerator.nextInt(10);
        if (xScore>=oScore) {
            System.out.println("Player X goes first with score " + xScore);
            return player1;
        }
        else {
            System.out.println("Player O goes first with score " + oScore);
            return player2;
        }
    }


//    public void playerMove(CellContent symbol) {
//        //position choice
//            String playerSelection;
//            boolean isInputValid;
//            System.out.println("Player " + symbol + ":");
//            Scanner reader = new Scanner(System.in);
//            System.out.println("Enter position: ");
//
//        do {
//            playerSelection = reader.nextLine();
//            isInputValid = positionValidator(playerSelection);
//
//        }while(!isInputValid);
//        board.setCells(playerSelectionRow, playerSelectionCol, symbol);
//    }


    public void updateGameStatus(AbstractPlayer player) {
        if(board.isTie()) {
            currentState = GameStatus.DRAW;
        }
        if(board.wins(player)) {
            if (player.playerSymbol == CellContent.X)
                currentState = GameStatus.XWON;
            else
                currentState = GameStatus.OWON;
        }

        if (player.playerSymbol == CellContent.X)
            currentPlayer = player2;
        else currentPlayer = player1;
    }

//    public boolean positionValidator(String inputPosition) {
//        String pattern = "^[1-3]{2}$";
//        boolean isValid = inputPosition.matches(pattern);
////        boolean isCellEmpty = true;
//        if(!isValid) {
//            System.out.println("Invalid please retry: ");
//            return false;
//        }
//        else {
//            playerSelectionRow = Character.getNumericValue(inputPosition.charAt(0));
//            playerSelectionCol = Character.getNumericValue(inputPosition.charAt(1));
//            boolean isCellEmpty = board.isCellFree(playerSelectionRow-1, playerSelectionCol-1);
//            if(!isCellEmpty) {
//                System.out.println("Cell not empty: ");
//                return false;
//            }
//        }
//        return true;
//    }

}
