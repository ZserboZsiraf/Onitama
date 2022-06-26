package logic;

import java.io.Serializable;

/**
 *  A játéktáblát jelképezi.
 */
public class Board implements Serializable {
    /**
     *  A játék mezői.
     */
    private Field[][] fields;

    /**
     * Az alap konstruktor, inicializálja a táblát.
     */
    public Board(){
        initBoard();
    }

    /**
     * Copy constructor
     * @param b egy tábla
     */
    public Board(Board b){
        initBoard();
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                if(b.getField(x,y).getPiece()!=null){
                    fields[x][y]=new Field(b.getField(x,y));
                }
            }
        }
    }

    /**
     * Inicializálja a táblát.
     */
    private void initBoard(){
        //Mátrix inicializálás.
        fields=new Field[5][5];
        //Végigmegyek a mezőkön, mindegyiknél beállítom a pozícióját és, hogy milyen bábu van rajta, ha egyáltalán van.
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                //[2][0]-ba kerül be a piros Mester.
                if(x==2&&y==0)fields[x][y]=new Field(new Master(COLOR.RED),x,y);
                //[2][4]-be kerül be a kék Mester.
                else if(x==2&&y==4)fields[x][y]=new Field(new Master(COLOR.BLUE),x,y);
                //A legalsó sorba mennek a biros bábuk.
                else if(y==0)fields[x][y]=new Field(new Piece(COLOR.RED),x,y);
                //A legfelsőbe a kékek.
                else if(y==4)fields[x][y]=new Field(new Piece(COLOR.BLUE),x,y);
                //Egyébként pedig null.
                else fields[x][y]=new Field(null,x,y);
            }
        }
    }

    /**
     * Visszaad egy adott pozíciójú mezőt.
     * @param x A mező elhelyezkedése a vízszintes tengelyen.
     * @param y A mező elhelyezkedése a függőleges tengelyen.
     * @return A fields mátrixnak(ami tartalmazza az összes mezőt) [x][y]. mezője.
     */
    public Field getField(int x,int y){
        //Ha pályán kívül van null-t adok vissza.
        if(x<0||x>4||y<0||y>4)return null;
        return fields[x][y];
    }
}
