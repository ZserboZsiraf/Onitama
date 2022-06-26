package tests;

import cards.Cobra;
import logic.COLOR;
import logic.Field;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
/**
 * Cobra kártyát tesztelő osztály.
 */
public class CobraTest {

    private Field f;
    private Piece p;
    @Before
    public void setUp() throws Exception {
        p=new Piece(COLOR.RED);
        f=new Field(p,2,2);
    }

    @Test
    public void moves() {
        ArrayList<Field> moves=new Cobra().getAllMoves(f);
        Assert.assertTrue(moves.contains(new Field(p,3,3)));
        Assert.assertTrue(moves.contains(new Field(p,1,2)));
        Assert.assertTrue(moves.contains(new Field(p,3,1)));
    }
}