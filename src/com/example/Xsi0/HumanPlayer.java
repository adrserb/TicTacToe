package com.example.Xsi0;

import java.util.Scanner;

/**
 * Created by adrians on 29.03.2017.
 */
public class HumanPlayer extends AbstractPlayer{
    public HumanPlayer(CellContent playerSymbol, Board board) {
        super(playerSymbol, board);
    }

    @Override
    public void playerMove() {
        //position choice
        String playerSelection;
        boolean isInputValid;
        System.out.println("Player " + playerSymbol+ ":");
        Scanner reader1 = new Scanner(System.in);
        System.out.println("Enter position: ");

        do {
            playerSelection = reader1.nextLine();
            isInputValid = board.positionValidator(playerSelection);

        }while(!isInputValid);
        board.setCells(board.currentRow, board.currentCol, playerSymbol);
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
