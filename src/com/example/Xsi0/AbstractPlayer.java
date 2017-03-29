package com.example.Xsi0;

/**
 * Created by adrians on 29.03.2017.
 */
public abstract class AbstractPlayer {
    CellContent playerSymbol;
    Board board;

    public AbstractPlayer(CellContent playerSymbol, Board board) {
        this.playerSymbol = playerSymbol;
        this.board = board;
    }

    public CellContent getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(CellContent playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public abstract void playerMove();
}
