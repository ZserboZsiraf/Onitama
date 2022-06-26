package logic;

/**
 * Ez a class reprezentálja a Mester bábut.
 */
/*Megjegyzés:
    Igazából sokat gondolkodtam,hogy van-e értelme egyáltalán ennek létrehozni így egy külön osztályt,
    mert kb semmi különbség nincs közte meg a Piece között. Lehet jobb lett volna ha a Piece-ben veszek fel egy boolt :( .*/
public class Master extends Piece{
    public Master(COLOR col) {
        super(col);
    }

    public Master(Piece p) {
        super(p);
    }
}
