package logic;

import cards.Card;

import java.util.ArrayList;

/**
 * MiniMax osztály, megmondja egy játékosnak, hogy mi a legjobb lépése.
 * Az osztályban a MiniMax algoritmust használtam alfa-béta vágással.
 */
// Nem túl okos.
public class MinMax{
    /**
     * A mező ami tartalmazza a bábut, ahonnan indul a lépés.
     */
    private Field bestStart;
    /**
     * A célmező, ahová lépni akarunk.
     */
    private Field bestDest;
    /**
     * Az ellenfél kártyái, mert ugye azt tudnia kell,ha rekurzívan akarom cserélgetni a kártyákat.
     */
    private ArrayList<Card>opponentCards;
    /**
     * A játékos akinek nézni akarjuk a legjobb lépését.
     */
    private Player p;
    /**
     * A külső kártya.
     */
    private Card outerCard;
    public MinMax(Player p,ArrayList<Card>opponentCards,Card outerCard) {
        this.p=p;
        this.opponentCards=new ArrayList<>();
        this.opponentCards.add(opponentCards.get(0).makeCopy());
        this.opponentCards.add(opponentCards.get(1).makeCopy());
        this.outerCard=outerCard.makeCopy();
    }

    /**
     * Beállítja a legjobb lépés paramétereit.
     * A lényege a függvénynek,hogy lépek egyet a táblán és azt odaadom egy rekurzív hívásba,
     * ahol megnézi a gép, hogy mik a lépései az ellenfélnek, és így adogatják egymásnak a hívásokat,
     * míg a depth nem lesz egyenlő nullával. Ezután kiértékelődnek a boardok.
     * Mivel a gép a minimalizáló játékos, ezért neki az a legjobb ha a tábla minél nagyobb negatív szám, ezért azt a
     * lépést fogja választani, ami a legkisebb.
     * @param table a tábla amin lépünk
     */
    public void miniMax(Board table){
        //Miközben jönnek a rekuzív hívások cserélgetnem kell a kártyákat, ezért lemásolom őket.
        ArrayList<Card>ourCardsCopy=new ArrayList<>(2);
        ourCardsCopy.add(p.getCards().get(0).makeCopy());
        ourCardsCopy.add(p.getCards().get(1).makeCopy());
        //Beállítok egy nagy értéket, aminél úgyis lesz kisebb.
        int bestScore=10000;
        //Végigmegyek a táblán.
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                //Ha a mezőn van valami
                if(table.getField(x,y).getPiece()!=null){
                    // és az kék
                    if(table.getField(x,y).getPiece().getCol().equals(COLOR.BLUE)){
                        for(int i=0;i<2;i++){
                            //akkor megnézem, hogy milyen lépéseket biztosít az első és a második kártyája.
                            ArrayList<Field>possibleMoves=p.getCards().get(i).getAllMoves(table.getField(x,y));
                            //Aztán végigmegyek az összes lépésen.
                            for(Field f:possibleMoves){
                                //Létrehozok egy új Boardot ami megegyezik az eredetivel, és ezen fogom mozgatni a bábukat.
                                Board temp=new Board(table);
                                if(f.getX()<5&&f.getY()<5&&f.getX()>-1&&f.getY()>-1) {
                                    //Ha a lépés szabályos
                                    if(table.getField(x,y).getPiece().canItMove(table.getField(x,y),table.getField(f.getX(),f.getY()),p.getCards().get(i))){
                                        //mozgatom a bábut.
                                        temp.getField(f.getX(),f.getY()).setPiece(f.getPiece());
                                        temp.getField(x,y).setPiece(null);
                                        //Meghívom az alphaBetaMax függvényt, amivel elindul a minimax.
                                        int score=alphaBetaMax((int) Double.NEGATIVE_INFINITY,(int) Double.POSITIVE_INFINITY,3,temp,p.getCards().get(i).makeCopy(),opponentCards,ourCardsCopy);
                                        //Ha a score nagyobb, mint a bestScore, akkor beállítja a tulajdonságokat.
                                        if(score<bestScore){
                                            bestScore=score;
                                            bestStart=table.getField(x,y);
                                            bestDest=table.getField(f.getX(),f.getY());
                                            p.setChosenCard(p.getCards().get(i));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Ez a függvény megvalósítja a miniMax algoritmust és az alfa-béta vágást a maximalizáló játékos szemszögéből.
     * Az alfa-béta vágás lényege az lenne, hogyha már van egy jobb opciója az adott játékosnak, akkor nem pazarlunk el felesleges erőforrást arra,
     * hogy lemenjünk egy olyan ágon, amit a játékos amúgy se választana már.
     * @param alpha Az alpha értéke.
     * @param beta A beta értéke.
     * @param depth A mélység ameddig megyünk.
     * @param node A tábla, amin már létünk.
     * @param chosenCard A kártya amivel léptünk.
     * @param opponentCards Az ellenfél kártyái.
     * @param ourCards A mi kártyáink.
     * @return A lépés pontozása.
     */
    public int alphaBetaMax(int alpha,int beta,int depth,Board node,Card chosenCard,ArrayList<Card>opponentCards,ArrayList<Card>ourCards){
        //Ha a depth==0 akkor ki kell értékelni a táblát.
        if(depth==0) return evaluate(node);
        //Mivel a maximalizáló játékos jön (aki a gép szemszögéből mi az igazi játékos vagyunk)ezért ő lépett. Ki kell cserélnem a kártyáját.
        //Hozzáadom a gép kártyáihoz a külsőkártyát.
        ourCards.add(outerCard.makeCopy());
        //A külsőkártyá az a kártya lesz ami lépett.
        outerCard=chosenCard.makeCopy();
        //És a kártyái közül kiveszem azt a kártyát amivel lépett.
        ourCards.remove(chosenCard);
        //Itt nagyjából ugyanazt csinálom mint az alap minimax függvényben, annyi különbséggel,hogy a piros bábukat mozgatom(mert azok az ellenfél bábui).
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                if(node.getField(x,y).getPiece()!=null){
                    if(node.getField(x,y).getPiece().getCol().equals(COLOR.RED)){
                        for(int i=0;i<2;i++){
                            ArrayList<Field>possibleMoves=opponentCards.get(i).getAllMoves(node.getField(x,y));
                            for(Field f:possibleMoves){
                                Board temp=new Board(node);
                                if(f.getX()<5&&f.getY()<5&&f.getX()>-1&&f.getY()>-1){
                                    if(node.getField(x,y).getPiece().canItMove(node.getField(x,y), node.getField(f.getX(),f.getY()), opponentCards.get(i))){
                                        temp.getField(f.getX(),f.getY()).setPiece(f.getPiece());
                                        temp.getField(x,y).setPiece(null);
                                        int score=alphaBetaMin(alpha,beta,depth-1,temp,opponentCards.get(i).makeCopy(),opponentCards,ourCards);
                                        //Ha a
                                        if(score>=beta){
                                            return beta;
                                        }
                                        if(score>alpha){
                                            alpha=score;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return alpha;
    }
    /**
     * Ez a függvény megvalósítja a miniMax algoritmust és az alfa-béta vágást a minimalizáló játékos szemszögéből.
     * Az alfa-béta vágás lényege az lenne, hogyha már van egy jobb opciója az adott játékosnak, akkor nem pazarlunk el felesleges erőforrást arra,
     * hogy lemenjünk egy olyan ágon, amit a játékos amúgy se választana már.
     * @param alpha Az alpha értéke.
     * @param beta A beta értéke.
     * @param depth A mélység ameddig megyünk.
     * @param node A tábla, amin már létünk.
     * @param chosenCard A kártya amivel léptünk.
     * @param opponentCards Az ellenfél kártyái.
     * @param ourCards A mi kártyáink.
     * @return A lépés pontozása.
     */
    public int alphaBetaMin(int alpha, int beta, int depth, Board node, Card chosenCard, ArrayList<Card>opponentCards, ArrayList<Card>ourCards){
        if(depth==0)return evaluate(node);
        opponentCards.add(outerCard.makeCopy());
        outerCard=chosenCard.makeCopy();
        opponentCards.remove(chosenCard);
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                if(node.getField(x,y).getPiece()!=null){
                    if(node.getField(x,y).getPiece().getCol().equals(COLOR.BLUE)){
                        for(int i=0;i<2;i++){
                            ArrayList<Field>possibleMoves=ourCards.get(i).getAllMoves(node.getField(x,y));
                            for(Field f:possibleMoves){
                                Board temp=new Board(node);
                                if(f.getX()<5&&f.getY()<5&&f.getX()>-1&&f.getY()>-1){
                                    if(node.getField(x,y).getPiece().canItMove(node.getField(x,y),node.getField(f.getX(),f.getY()),ourCards.get(i))){
                                        temp.getField(f.getX(),f.getY()).setPiece(f.getPiece());
                                        temp.getField(x,y).setPiece(null);
                                        int score=alphaBetaMax(alpha,beta,depth-1,temp,ourCards.get(i).makeCopy(),opponentCards,ourCards);
                                        if(score<=alpha){
                                            return alpha;
                                        }
                                        if(score<beta){
                                            beta=score;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        return beta;
    }

    /**
     * Kiértékel egy táblaállást.
     * @param node egy tábla
     * @return a pontok
     */
    public int evaluate(Board node){
        int sum=0;
        int[][]positions={
                {0,4,8,4,0},
                {4,8,12,8,4},
                {8,12,16,12,8},
                {4,8,12,8,4},
                {0,4,8,4,0}
        };
        if(node.getField(2,0).getPiece()!=null) {
            if(node.getField(2,0).getPiece().getClass().equals(Master.class)&&node.getField(2,0).getPiece().getCol().equals(COLOR.BLUE))
                sum -= 2000;
        }
        if(node.getField(2,4).getPiece()!=null) {
            if(node.getField(2,4).getPiece().getClass().equals(Master.class)&&node.getField(2,4).getPiece().getCol().equals(COLOR.RED))sum+= 2000;
        }
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                if(node.getField(x,y).getPiece()!=null){
                    if(node.getField(x,y).getPiece().getClass().equals(Master.class)){
                        if(node.getField(x,y).getPiece().getCol().equals(COLOR.RED)){
                            sum+=1000;
                        }
                        if(node.getField(x,y).getPiece().getCol().equals(COLOR.BLUE)){
                            sum -= 1000;
                        }
                    }
                    else if(node.getField(x,y).getPiece().getCol().equals(COLOR.RED)){
                        sum+=50;
                        sum+=positions[x][y];
                    }
                    else if(node.getField(x,y).getPiece().getCol().equals(COLOR.BLUE)){
                        sum-=50;
                        sum-=positions[x][y];
                    }
                }
            }
        }
        return sum;
    }

    public Field getBestStart() {
        return bestStart;
    }

    public void setBestStart(Field bestStart) {
        this.bestStart = bestStart;
    }

    public Field getBestDest() {
        return bestDest;
    }

    public void setBestDest(Field bestDest) {
        this.bestDest = bestDest;
    }

    public ArrayList<Card> getOpponentCards() {
        return opponentCards;
    }

    public void setOpponentCards(ArrayList<Card> opponentCards) {
        this.opponentCards = opponentCards;
    }

    public Card getOuterCard() {
        return outerCard;
    }

    public void setOuterCard(Card outerCard) {
        this.outerCard = outerCard;
    }
}
