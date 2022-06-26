package cards;

import logic.COLOR;
import logic.Field;
import logic.Piece;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A Boar kártya.
 *
 *    ---------
 *   |X X X X X|
 *   |X X M X X| P=bábu, M=a lehetséges lépések.
 *   |X M P M X|
 *   |X X X X X|
 *   |X X X X X|
 *    ---------
 */
public class Boar implements Card, Serializable {
    @Override
    public ArrayList<Field> getAllMoves(Field f) {
        int mirror=f.getPiece().getCol().equals(COLOR.RED)?1:-1;
        ArrayList<Field>moves=new ArrayList<>();
        moves.add(new Field(f.getPiece(),f.getX(),f.getY()+1*mirror));
        moves.add(new Field(f.getPiece(),f.getX()-1*mirror,f.getY()));
        moves.add(new Field(f.getPiece(),f.getX()+1*mirror,f.getY()));
        return moves;
    }

    @Override
    public Card makeCopy() {
        return new Boar();
    }
}
