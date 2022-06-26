package tests;

import cards.Boar;
import cards.Card;
import logic.COLOR;
import logic.Field;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Egy bábut tesztelő osztály.
 */
public class PieceTest {
    /**
     * Egy bábu.
     */
    private Piece p;
    /**
     * Egy kártya.
     */
    private Card c;
    /**
     * Ahonnan lépünk.
     */
    private Field startField;
    /**
     * Ahová.
     */
    private Field destField;
    @Before
    public void setUp() throws Exception {
        p=new Piece(COLOR.RED);
        c=new Boar();
        startField=new Field(p,0,0);
        destField=new Field(null,0,1);
    }

    @Test
    public void canItMove() {
        //Megnézem, hogy true-t ad vissza ha tényleg beléphet-e.
        Assert.assertTrue(p.canItMove(startField,destField,c));
        destField=new Field(new Piece(COLOR.RED),0,1);
        //False-t kell hogy visszaadjon ha egy ugyanolyan színű bábu van már ott.
        Assert.assertFalse(p.canItMove(startField,destField,c));
        destField=new Field(null,0,2);
        //False-t kell hogy visszaadjon, mert a Boar kártya nem teszi lehetővé,hogy odalépjen.
        Assert.assertFalse(p.canItMove(startField,destField,c));
    }
}