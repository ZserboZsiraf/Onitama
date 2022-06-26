package tests;

import cards.Boar;
import cards.Deck;
import logic.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Game-t teszteli
 */
public class GameTest {
    private Game game;
    @Before
    public void setUp() throws Exception {
        Deck d=new Deck();
        game=new Game(new Player(COLOR.RED,d),new Player(COLOR.BLUE,d),d, Matchup.PlayerVsPlayer);
    }

    @Test
    public void hasItEnded() {
        //Beállítom,hogy kék nyert
        game.setGameStatus(Status.BLUEWON);
        //True-nak kell visszatérnie, mert nyert valaki és vége lett a meccsnek.
        Assert.assertTrue(game.hasItEnded());
    }

    @Test
    public void winCheck() {
        //Inicalizálok egy move-t
        Move m=new Move(new Player(COLOR.RED,new Deck()),new Field(null,0,0),new Field(null,1,1));
        //Beállítom, hogy a kék Mestert kapta el.
        m.setPieceCaptured(new Master(COLOR.BLUE));
        //A winChecknek be kell állítania, hogy a piros nyert.
        game.winCheck(m,COLOR.RED);
        //Megnézem, hogy tényleg beállította-e.
        Assert.assertTrue(game.getGameStatus().equals(Status.REDWON));
    }

    @Test
    public void playerMove(){
        //Inicializálok egy playert.
        Player player=new Player(COLOR.RED,new Deck());
        //Beállítom a kártyát amivel lép Boarnak.
        player.setChosenCard(new Boar());
        //Felfelé tudnia kell lépnie.
        Assert.assertTrue(game.playerMove(player,0,0,0,1));
    }
}