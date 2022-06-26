package logic;



/**
 * Ez az osztály összefogja azon függvényeket, amik megnézik, hogy vége-e a meccsnek.
 */
public class WinChecker {
    /**
     * Megnézi, hogy leütötték-e az ellenfél Mesterét.
     * @param move A megtörtént lépés.
     * @param opponetMaster Az ellenfél mestere.
     * @return True ha leütötték, false ha nem.
     */
    public boolean checkWin(Move move,COLOR opponetMaster){
        //Ha senkit nem ütöttek le akkor felesleges vizsgálni.
        if(move.getPieceCaptured()==null)return false;
        //Ha pedig valamelyik bábut letütöttök és az egy Master és az pont megegyezik az ellenfél mesterének színével, akkor nyertünk!
        return move.getPieceCaptured().getClass().equals(Master.class) && move.getPieceCaptured().getCol().equals(opponetMaster);
    }

    /**
     * Megnézi, hogy belépett-e a mesterünk az ellenfél szentélyébe.
     * @param b A tábla.
     * @param ourKing A mesterünk színe
     * @return true ha beléptünk, false ha nem.
     */
    public boolean checkShrine(Board b,COLOR ourKing){
        //Ha kék akkor a [2][0] pozícióban lévő mezőbe kell belépnie és akkor nyert.
        if(ourKing.equals(COLOR.BLUE)){
            if(b.getField(2,0).getPiece()==null)return false;
            return b.getField(2,0).getPiece().getClass().equals(Master.class)&&b.getField(2,0).getPiece().getCol().equals(COLOR.BLUE);
        }
        //Ha piros akkor a [2][4] pozícióban lévő mezőbe kell belépnie és akkor nyert.
        if(ourKing.equals(COLOR.RED)){
            if(b.getField(2,4).getPiece()==null)return false;
            return b.getField(2,4).getPiece().getClass().equals(Master.class)&&b.getField(2,4).getPiece().getCol().equals(COLOR.RED);
        }
        return false;
    }
}
