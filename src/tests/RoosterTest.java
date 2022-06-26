package tests;

import cards.Rooster;
import logic.COLOR;
import logic.Field;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
/**
 * Rooster kártyát tesztelő osztály.
 */
public class RoosterTest {

    private Field f;
    private Piece p;
    @Before
    public void setUp() throws Exception {
        p=new Piece(COLOR.RED);
        f=new Field(p,2,2);
    }

    @Test
    public void moves() {
        ArrayList<Field> moves=new Rooster().getAllMoves(f);
        Assert.assertTrue(moves.contains(new Field(p,1,2)));
        Assert.assertTrue(moves.contains(new Field(p,1,1)));
        Assert.assertTrue(moves.contains(new Field(p,3,2)));
        Assert.assertTrue(moves.contains(new Field(p,3,3)));
    }
}