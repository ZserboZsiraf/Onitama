package tests;

import cards.Tiger;
import logic.COLOR;
import logic.Field;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
/**
 * Tiger kártyát tesztelő osztály.
 */
public class TigerTest {

    private Field f;
    private Piece p;
    @Before
    public void setUp() throws Exception {
        p=new Piece(COLOR.RED);
        f=new Field(p,2,2);
    }

    @Test
    public void moves() {
        ArrayList<Field> moves=new Tiger().getAllMoves(f);
        Assert.assertTrue(moves.contains(new Field(p,2,4)));
        Assert.assertTrue(moves.contains(new Field(p,2,1)));
    }
}