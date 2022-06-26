package cards;

import logic.Field;
import logic.Piece;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Egy kártyát leíró osztály. Ebből fogom származtatni a többit.
 */
public interface  Card  {
    /**
     * Visszaadja, hogy a kártyával milyen pozíciókba léphetne a bábu.
     * @param f Egy mező.
     * @return Pozíciók ArrayList-e.
     */
    public abstract ArrayList<Field>getAllMoves(Field f);

    /**
     * Ha le akarok másolni egy kártyát akkor muszáj lesz másolatot készítenem.
     * @return A kártya másolata.
     */
    public abstract Card makeCopy();

}
