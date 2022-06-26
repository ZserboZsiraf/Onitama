package logic;

import cards.Card;
import cards.Deck;

import java.io.*;

/**
 * Ez a class reprezentálja a játékot, irányítja a játékosok lépéseit, irányítja a játék menetét.
 */
public class Game implements Serializable {
    /**
     * A piros játékos.
     */
    private Player red;
    /**
     * A kék játékos.
     */
    private Player blue;
    /**
     * A játéktábla
     */
    private Board table;
    /**
     * A játékos aki éppen léphet.
     */
    private Player currentPlayer;
    /**
     * A játék állása. Piros/Kék nyert vagy még nincs eldőlve.
     */
    private Status gameStatus;
    //private LinkedList<Move>moves; el akartam tárolni a lépéseket,hogy visszanézhető legyen, de már nem volt időm rá.
    /**
     * A külső kártya.
     */
    private Card outerCard;
    /**
     * A Matchup, ami lehet Játékos Játéko ellen, Játékos RandomAI ellen, meg lehet Játékos MinMaxAI ellen.
     */
    private Matchup matchup;

    /**
     * A Game construktora
     * @param red A piros játékos
     * @param blue A kék játékos
     * @param d A kártyapakli amiből kisorsoljuk a külső kártyát.
     * @param matchup Az alapértelmezett matchup.
     */
    public Game(Player red, Player blue, Deck d,Matchup matchup){
        this.red=red;
        this.blue=blue;
        this.currentPlayer=red;
        this.table=new Board();
        // Sorsolok egy random kártyát a pakliból.
        this.outerCard=d.getCard();

        gameStatus=Status.NOTYETDETERMINED;
        this.matchup=matchup;
    }

    /**
     * Ezzel a függvénnyel megnézem a gameStatus alapján, hogy nyert-e valaki.
     * @return true ha vége van, false ha nincs.
     */
    public boolean hasItEnded(){
        return gameStatus==Status.REDWON||gameStatus==Status.BLUEWON;
    }

    /**
     * Megnézem szín szerint és a lépés alapján, hogy nyert-e az adott színű játékos.
     * @param m A lépés ami végbement.
     * @param c Az a szín amire vizsgálni akarjuk a győzelmet.
     */
    public void winCheck(Move m,COLOR c){
        //Ha piros
        if(c.equals(COLOR.RED)){
            //Megnézem, hogy a lépés hatására nem ütöttem le a kék Mestert
            //és megnézem azt is, hogy nem lépett-e be az én Mesterem az ellenfél szentélyébe.
            if(new WinChecker().checkWin(m, COLOR.BLUE)||new WinChecker().checkShrine(table,COLOR.RED)){
                setGameStatus(Status.REDWON);
            }
        }
        //Ha kék
        else{
            //Megnézem, hogy a lépés hatására nem ütöttem le a piros Mestert
            //és megnézem azt is, hogy nem lépett-e be az én Mesterem az ellenfél szentélyébe.
            if(new WinChecker().checkWin(m,COLOR.RED)||new WinChecker().checkShrine(table,COLOR.BLUE)){
                setGameStatus(Status.BLUEWON);
            }
        }
    }

    /**
     * Léptet egy játékost
     * @param player A játékos akit léptetni akarunk.
     * @param startX A vízszintes koordinátája annak a pozíciónak ahonnan lépni akar.
     * @param startY A függőleges koordinátája annak a pozíciónak ahonnan lépni akar.
     * @param destX  A vízszintes koordinátája annak a pozíciónak ahová lépni akar.
     * @param destY  A függőleges koordinátája annak a pozíciónak ahová lépni akar.
     * @return true ha végbement a lépés, false ha nem
     */
    public boolean playerMove(Player player,int startX,int startY,int destX,int destY){
        //Kiszámítjuk a mezőt ahonnan lépni akar.
        Field start=table.getField(startX,startY);
        //Kiszámítjuk a mezőt ahová lépni akar.
        Field dest=table.getField(destX,destY);
        //Létrehozzuk a lépést
        Move move=new Move(player,start,dest);
        // és megpróbálunk lépni.
        return this.makeMove(move,player);
    }

    /**
     * Ebben a függvényben megy ténylegesen végbe a lépés ha megfelel a feltételeknek.
     * @param move A lépés amit végre akarunk hajtani.
     * @param player A játékos aki végreakarja hajtani a lépést.
     * @return true ha sikeres a lépés,false ha nem.
     */
    private boolean makeMove(Move move,Player player){
        //Először megnézzük, hogy ahonnan lépni akar, ott van-e egyáltalán bábu.
        Piece chosenPiece=move.getStartField().getPiece();
        //Ha nincs akkor false-t adunk vissza,mert a semmivel nem léphet.
        if(chosenPiece==null)return false;
        //Ha van bábu, akkor fontos az, hogy ugyanolyan színű legyen, mint a játékos.
        else if(chosenPiece.getCol()!=player.getCol()){
            return false;
        }
        //Ha a lépés benne van a választott kártya alapján kilistázott valid lépésekben, akkor mehetünk csak tovább.
        else if(!chosenPiece.canItMove(move.getStartField(),move.getDestinationField(),player.getChosenCard()))return false;
        // Lekérjük azt a piecet, ami ott van ahová lépni akarunk.
        Piece destPiece=move.getDestinationField().getPiece();
        //Ha ott van valami, akkor azt leütöttük.
        if(destPiece!=null){
            destPiece.setCaptured(true);
            move.setPieceCaptured(destPiece);
        }
        //moves.add(move);
        //Beállítjuk a bábunkat a célmezőre.
        move.getDestinationField().setPiece(move.getStartField().getPiece());
        //A startmezőt pedig átállítjuk üresre.
        move.getStartField().setPiece(null);
        //Megnézzük, hogy ezzel nyert-e a játékos.
        winCheck(move,player.getCol());
        //Egyébként pedig jön a következő játékos.
        if(currentPlayer.getCol().equals(COLOR.RED)){
            currentPlayer=blue;
        }
        else{
            currentPlayer=red;
        }
        //Fontos,hogy a kártyákat is megcseréljük, mert ugye folyamatosan rotálódnak.
        //A külső kártya bekerül a játékoshoz aki lépett.
        player.getCards().add(outerCard.makeCopy());
        //A külső kártya az a kártya lesz, amit felhasznált.
        outerCard=(player.getChosenCard().makeCopy());
        //És ezután a felhasznált kártyát pedig eltávolítjuk tőle.
        player.getCards().remove(player.getChosenCard());
        return true;
    }

    /**
     * Ez a függvény lementi az adott játékhelyzetet. Szerializálom az objektumot.
     */
    public void save(){
        try {
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("save.txt"));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Player getRed() {
        return red;
    }

    public void setRed(Player red) {
        this.red = red;
    }

    public Player getBlue() {
        return blue;
    }

    public void setBlue(Player blue) {
        this.blue = blue;
    }

    public Board getTable() {
        return table;
    }

    public void setTable(Board table) {
        this.table = table;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Status getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Status gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Card getOuterCard() {
        return outerCard;
    }

    public void setOuterCard(Card outerCard) {
        this.outerCard = outerCard;
    }

    public Matchup getMatchup() {
        return matchup;
    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }
}
