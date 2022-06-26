package tests;

import logic.COLOR;
import logic.Field;
import logic.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Field class tesztjei.
 */
public class FieldTest {
    /**
     * Egy mező.
     */
    private Field f;
    @Before
    public void setUp() throws Exception {
        //Belerakok egy piros bábut, és a pozícióját [0][0]-ra állítom.
        f=new Field(new Piece(COLOR.RED),0,0);
    }

    @Test
    public void getPiece() {
        //Megnézem, hogy jó bábut ad-e vissza.
        Assert.assertTrue(new Piece(COLOR.RED).equals(f.getPiece()));
    }

    @Test
    public void setPiece() {
        //Megnéztem, hogy be tudok-e állítani bábut.
        f.setPiece(new Piece(COLOR.BLUE));
        Assert.assertTrue(new Piece(COLOR.BLUE).equals(f.getPiece()));
    }

    @Test
    public void getX() {
        //Megnézem, hogy jó X koordinátát ad-e vissza.
        Assert.assertEquals(0,f.getX());
    }

    @Test
    public void setX() {
        //Megnézem, hogy be tudom-e állítani az X koordinátát.
        f.setX(1);
        Assert.assertEquals(1,f.getX());
    }

    @Test
    public void getY() {
        //Megnézem, hogy jó Y koordinátát ad-e vissza.
        Assert.assertEquals(0,f.getY());
    }

    @Test
    public void setY() {
        //Megnézem, hogy be tudom-e állítani az Y koordinátát.
        f.setY(1);
        Assert.assertEquals(1,f.getY());
    }
}