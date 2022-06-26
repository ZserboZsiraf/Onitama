package cards;

import java.util.ArrayList;
import java.util.Random;

/***
 * Az összes kártyát tartalmazó kártyapakli.
 */
public class Deck {
    private ArrayList<Card>cards;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Deck(){
        cards=new ArrayList<>(16);
        cards.add(new Boar());
        cards.add(new Cobra());
        cards.add(new Crab());
        cards.add(new Crane());
        cards.add(new Dragon());
        cards.add(new Eel());
        cards.add(new Elephant());
        cards.add(new Frog());
        cards.add(new Goose());
        cards.add(new Horse());
        cards.add(new Mantis());
        cards.add(new Monkey());
        cards.add(new Ox());
        cards.add(new Rabbit());
        cards.add(new Rooster());
        cards.add(new Tiger());
    }

    /**
     * Véletlenszerűen kisorsol egy kártyját és visszadja.
     * @return Egy Card, amit véletlen választunk.
     */
    public Card getCard(){
       return cards.remove(new Random().nextInt(cards.size()));
    }
}
