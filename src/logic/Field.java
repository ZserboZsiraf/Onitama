package logic;

import java.io.Serializable;
import java.util.Objects;

/**
 * A tábla egy mezőjét reprezentálja.
 */
public class Field implements Serializable {
    /**
     * Egy bábu a mezőn
     */
    private Piece piece;
    /**
     * A mező elheyezkedése a vízszintes tengelyen
     */
    private int x;
    /**
     * A mező elhelyezkedése függőleges tengelyen.
     */
    private int y;

    /**
     * Field constructora.
     * @param piece A bábu ami rajta lesz.
     * @param x Az elhelyezkedése vízszintesen.
     * @param y Az elhelyezkedése függőlegesen.
     */
    public Field(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        //Úgy akartam összehasonlítani, hogy ne számítson a benne lévő piece, bár ez lehet hülyeség így.
        return x == field.x && y == field.y /*&& Objects.equals(piece, field.piece)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, x, y);
    }

    /**
     * Copy constructor
     * @param f Az átmásolandó mező.
     */
    public Field(Field f){
        this.piece=new Piece(f.getPiece());
        this.x=f.x;
        this.y=f.y;
    }
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
