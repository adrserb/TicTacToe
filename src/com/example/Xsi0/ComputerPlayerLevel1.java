package com.example.Xsi0;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by adrians on 29.03.2017.
 */
public class ComputerPlayerLevel1 extends AbstractPlayer{

    public ComputerPlayerLevel1(CellContent playerSymbol, Board board) {
        super(playerSymbol, board);
    }

    @Override
    public void playerMove() {
        String computerSelectedPosition = computerPositionSelector(board.availablePositions);
        System.out.println("Computer " + playerSymbol+ ":");
        System.out.println("Enter position: ");
        System.out.println(computerSelectedPosition);
        board.getIntPositions(computerSelectedPosition);
        board.setCells(board.currentRow, board.currentCol, playerSymbol);

    }

    private String computerPositionSelector(ArrayList list) {
        Random randomPositionSelector = new Random();
        int position = randomPositionSelector.nextInt(list.size());
        return board.availablePositions.get(position);
    }

}
