package tests;

import cards.Card;
import cards.Deck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {
    private Deck d;
    @Before
    public void setUp() throws Exception {
        d=new Deck();
    }

    @Test
    public void getRandomCard() {
        Card c=d.getCard();
        Assert.assertFalse(d.getCards().contains(c));
        for(int i=0;i<15;i++){
            d.getCard();
        }
        Assert.assertEquals(0,d.getCards().size());
    }
}