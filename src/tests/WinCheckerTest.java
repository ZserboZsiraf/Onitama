package tests;

import cards.Boar;
import cards.Deck;
import logic.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WinChecker osztály tesztje
 */
public class WinCheckerTest {
    /**
     * Egy tábla.
     */
    private Board b;
    /**
     * Egy lépés.
     */
    private Move move;
    @Before
    public void setUp() throws Exception {
        //létrehozok egy új táblát.
        b=new Board();
        //beállítom,hogy [0][1]-es helyen legyen a kék Mester.
        b.getField(0,1).setPiece(b.getField(2,4).getPiece());
        //Inicializálom a move-ot
        move=new Move(new Player(COLOR.RED,new Deck()),b.getField(0,0),b.getField(0,1));
        //Beállítom a capturedpiecet.
        move.setPieceCaptured(new Master(COLOR.BLUE));
    }

    @Test
    public void checkWin() {
        //True-nak kell visszatérnie, mivel a kék király a lépés PieceCaptured változójában benne van!
        Assert.assertTrue(new WinChecker().checkWin(move,COLOR.BLUE));
    }

    @Test
    public void checkShrine() {
        //Beállítom, hogy a kék Shrineján legyen a piros Mester
        b.getField(2,4).setPiece(new Master(COLOR.RED));
        //True, mert a kék szentélyén van a piros Mester
        Assert.assertTrue(new WinChecker().checkShrine(b,COLOR.RED));
    }
}