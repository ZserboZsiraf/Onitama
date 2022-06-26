package logic;

import cards.Card;

import java.io.Serializable;
import java.util.Objects;

/**
 * Egy bábut reprezentál
 */
public class Piece implements Serializable {
    /**
     * A bábu színe.
     */
    private COLOR col;
    /**
     * Hogy leütötték-e.
     */
    private boolean captured;

    /**
     * A bábu constructore
     * @param col Egy szín.
     */
    public Piece(COLOR col) {
        this.col = col;
        this.captured=false;
    }

    /**
     * Copy constructor
     * @param p A lemásolandó bábu.
     */
    public Piece(Piece p){
        this.col=p.col;
        this.captured=p.captured;
    }

    /**
     * A függvény visszadja, hogy az egyik mezőből a másikba a megadott kártyával léphet-e a bábu.
     * @param start A mező ahonnan lépni akarunk.
     * @param destination A mező ahová lépni akarunk.
     * @param chosenCard A kártya amivel lépünk.
     * @return true ha léphet, false ha nem.
     */
    public boolean canItMove(Field start, Field destination, Card chosenCard){
        //Ha bábu van a mezőn ahová lépni akar
        if(destination.getPiece()!=null){
            //akkor megnézzük, hogy ugyanolyan színűe, mert ha igen, akkor oda nem léphet!
            if(start.getPiece().getCol().equals(destination.getPiece().getCol())){
                return false;
            }
        }
        //Visszadjuk,hogy a potenciális mezők között benne van-e a lépés amit végre akarunk hajtani.
        return chosenCard.getAllMoves(start).contains(destination);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return captured == piece.captured && col == piece.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, captured);
    }

    public COLOR getCol() {
        return col;
    }

    public void setCol(COLOR col) {
        this.col = col;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

}
