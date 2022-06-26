package tests;

import logic.Board;
import logic.COLOR;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Board-ot tesztelő osztály.
 */
public class BoardTest {
    /**
     * Egy tábla.
     */
    private Board b;
    @Before
    public void setUp() throws Exception {
        b=new Board();
    }

    @Test
    public void getField() {
        //Megnézem,hogy a koordinátákat jól beállította-e és eközben még a getfieldet is tesztelem.
        Assert.assertEquals(0,b.getField(0,0).getX());
        //Megnézem,hogy a bábu is belekerül-e
        Assert.assertTrue(b.getField(0,0).getPiece().equals(new Piece(COLOR.RED)));
    }
}