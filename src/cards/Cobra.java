package cards;

import logic.COLOR;
import logic.Field;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A Cobra kártya.
 *
 *    ---------
 *   |X X X X X|
 *   |X X X M X| P=bábu, M=a lehetséges lépések.
 *   |X M P X X|
 *   |X X X M X|
 *   |X X X X X|
 *    ---------
 */
public class Cobra implements Card , Serializable {
    @Override
    public ArrayList<Field> getAllMoves(Field f) {
        int mirror=f.getPiece().getCol().equals(COLOR.RED)?1:-1;
        ArrayList<Field>moves=new ArrayList<>();
        moves.add(new Field(f.getPiece(),f.getX()-1*mirror,f.getY()));
        moves.add(new Field(f.getPiece(),f.getX()+1*mirror,f.getY()+1*mirror));
        moves.add(new Field(f.getPiece(),f.getX()+1*mirror,f.getY()-1*mirror));
        return moves;
    }
    @Override
    public Card makeCopy() {
        return new Cobra();
    }
}
