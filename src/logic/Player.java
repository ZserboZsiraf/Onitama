package logic;

import cards.Card;
import cards.Deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Egy játékost reprezentál.
 */
public  class Player implements Serializable {
    /**
     * A játékos színe.
     */
    private COLOR col;
    /**
     * A játékos kártyái.
     */
    private ArrayList<Card>cards;
    /**
     * A játékos kiválasztott kártyája.
     */
    private Card chosenCard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return col == player.col && Objects.equals(cards, player.cards) && Objects.equals(chosenCard, player.chosenCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, cards, chosenCard);
    }

    public Player(COLOR col, Deck d) {
        this.col = col;
        cards=new ArrayList<>(2);
        cards.add(d.getCard());
        cards.add(d.getCard());
        chosenCard=null;
    }

    public COLOR getCol() {
        return col;
    }

    public void setCol(COLOR col) {
        this.col = col;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }
}
