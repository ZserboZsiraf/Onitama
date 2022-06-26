package logic;

import java.util.Objects;

/**
 * Egy lépést reprezentál.
 */
public class Move {
    /**
     * A játékos aki lépett.
     */
    private Player player;
    /**
     * A mező ahonnan lépni akar.
     */
    private Field startField;
    /**
     * A mező ahová lépni akar.
     */
    private Field destinationField;
    /**
     * A bábu amit mozgat.
     */
    private Piece pieceMoved;
    /**
     * A bábu amit leütött.
     */
    private Piece pieceCaptured;

    public Move(Player player, Field startField, Field destinationField) {
        this.player = player;
        this.startField = startField;
        this.destinationField = destinationField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(player, move.player) && Objects.equals(startField, move.startField) && Objects.equals(destinationField, move.destinationField) && Objects.equals(pieceMoved, move.pieceMoved) && Objects.equals(pieceCaptured, move.pieceCaptured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, startField, destinationField, pieceMoved, pieceCaptured);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Field getStartField() {
        return startField;
    }

    public void setStartField(Field startField) {
        this.startField = startField;
    }

    public Field getDestinationField() {
        return destinationField;
    }

    public void setDestinationField(Field destinationField) {
        this.destinationField = destinationField;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public void setPieceMoved(Piece pieceMoved) {
        this.pieceMoved = pieceMoved;
    }

    public Piece getPieceCaptured() {
        return pieceCaptured;
    }

    public void setPieceCaptured(Piece pieceCaptured) {
        this.pieceCaptured = pieceCaptured;
    }
}
