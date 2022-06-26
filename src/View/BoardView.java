package View;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * A játék vizuálisan ebben az osztályban valósul meg.
 */
public class BoardView extends JPanel implements MouseMotionListener, MouseListener {
    /**
     * Jelzi ha mozgatunk egy bábut.
     */
    private boolean moving=false;
    /**
     * Ahonnan mozgatjuk a bábut.
     */
    private Field startField;
    /**
     * A piros első kártyájának a gombja.
     */
    private JButton redFirstCardButton;
    /**
     * A piros 2. kártyájának a gombja.
     */
    private JButton redSecondCardButton;
    /**
     * A kék első kártyájának a gombja.
     */
    private JButton blueFirstCardButton;
    /**
     * A kék 2. kártyájának a gombja.
     */
    private JButton blueSecondCardButton;
    /**
     * A külső kártya Labele.
     */
    private JLabel outerCardLabel;
    /**
     * A meccs állásának labele.
     */
    private JLabel MatchState;
    /**
     * A kurzor vízszintes pozíciója.
     */
    private int cX;
    /**
     * A kurzor függőleges pozíciója.
     */
    private int cY;
    /**
     * A játék amit módosítgatunk a lépések szerint.
     */
    private Game game;

    /**
     * A BoardView constructora.
     * @param g Egy Game.
     */
    public BoardView(Game g) {
        this.game=g;
        //Beállítom a piros első kártyájának a gombját.
        redFirstCardButton=new JButton();
        redFirstCardButton.setBounds(800,0,200,200);
        redFirstCardButton.addActionListener(e->{
            //Ha piros a játékos állítsa be, hogy az éppen lépő játékosnak a kiválasztott kártyája a piros első kártyája legyen.
            if(game.getCurrentPlayer().getCol().equals(COLOR.RED)){
                game.getCurrentPlayer().setChosenCard(game.getRed().getCards().get(0));
            }
        });
        this.add(redFirstCardButton);
        //Beállítom a piros második kártyájának a gombját.
        redSecondCardButton=new JButton();
        redSecondCardButton.setBounds(1100,0,200,200);
        redSecondCardButton.addActionListener(e->{
            //Ha piros a játékos állítsa be, hogy az éppen lépő játékosnak a kiválasztott kártyája a piros második kártyája legyen.
            if(game.getCurrentPlayer().getCol().equals(COLOR.RED)){
                game.getCurrentPlayer().setChosenCard(game.getRed().getCards().get(1));
            }
        });
        this.add(redSecondCardButton);
        //Beállítom a kék első kártyájának a gombját.
        blueFirstCardButton=new JButton();
        blueFirstCardButton.setBounds(800,225,200,200);
        blueFirstCardButton.addActionListener(e->{
            //Ha kék a játékos állítsa be, hogy az éppen lépő játékosnak a kiválasztott kártyája a kék első kártyája legyen.
            if(game.getCurrentPlayer().getCol().equals(COLOR.BLUE)){
                game.getCurrentPlayer().setChosenCard(game.getBlue().getCards().get(0));
            }
        });
        this.add(blueFirstCardButton);
        //Beállítom a kék második kártyájának a gombját.
        blueSecondCardButton=new JButton();
        blueSecondCardButton.setBounds(1100,225,200,200);
        blueSecondCardButton.addActionListener(e->{
            //Ha kék a játékos állítsa be, hogy az éppen lépő játékosnak a kiválasztott kártyája a kék második kártyája legyen.
            if(game.getCurrentPlayer().getCol().equals(COLOR.BLUE)){
                game.getCurrentPlayer().setChosenCard(game.getBlue().getCards().get(1));
            }
        });
        this.add(blueSecondCardButton);
        //Beállítom a külső kártya JLabelét.
        outerCardLabel=new JLabel();
        outerCardLabel.setBounds(800,450,200,200);
        this.add(outerCardLabel);
        //Beállítom a kártyák képeit.
        cardPicChange();
        //Beállítom az exit gombot, ha rákattintunk, visszatérünk a menübe.
        JButton exit=new JButton("Exit");
        exit.addActionListener(e->this.setVisible(false));
        exit.setBounds(0,625,200,100);
        exit.setFont(new Font("Algerian",Font.PLAIN,40));
        this.add(exit);

        this.setLayout(null);
        //Beállítom a JLabelt ami kiírja,hogy piros kártyái:
        JLabel p1cards=new JLabel("red's cards:");
        p1cards.setFont(new Font("Algerian", Font.PLAIN,27));
        p1cards.setBounds(600,0,200,200);
        this.add(p1cards);
        //Beállítom a JLabelt ami kiírja,hogy kék kártyái:
        JLabel p2cards=new JLabel("blue's cards:");
        p2cards.setFont(new Font("Algerian", Font.PLAIN,27));
        p2cards.setBounds(600,225,200,200);
        this.add(p2cards);
        //Beállítom a JLabelt ami kiírja,hogy külső kártya:
        JLabel outerCardLabel=new JLabel("outer card:");
        outerCardLabel.setFont(new Font("Algerian", Font.PLAIN,27));
        outerCardLabel.setBounds(600,450,200,200);
        this.add(outerCardLabel);
        //Beállítom a save gombot
        JButton save = new JButton("Save");
        save.setBounds(0,515,200,100);
        save.addActionListener(e->game.save());
        save.setFont(new Font("Algerian",Font.PLAIN,40));
        this.add(save);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setBounds(1,1,500,500);
        //Beállítom a MatchState labelt, ami még nem fog semmit se kiírni.
        MatchState=new JLabel();
        MatchState.setFont(new Font("Algerian", Font.PLAIN,30));
        MatchState.setBounds(300,600,500,100);
        this.add(MatchState);
    }

    //Ez a függvény kicsit durva lett.
    /**
     * Overrideoltam a paintComponent metódust, ezzel rajzolom ki a pályát, attól függően,hogy mozgatunk-e bábut.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D=(Graphics2D) g;
        //Az egész hátteret beszínezem fehérre.
        g2D.setColor(Color.white);
        g2D.fillRect(0,0,1400,1200);
        g2D.setColor(Color.black);
        //Ha nem mozgunk.
        if(!moving){
            //Végigmegyek a pályán.
            for(int x=0;x<5;x++){
                for(int y=0;y<5;y++){
                    //Kirajzoljuk a négyzeteket, a szentélyeket sárgával.
                    g2D.setColor(Color.WHITE);
                    g2D.fillRect(x*100+1,Math.abs(y-4)*100+1,100,100);
                    if(x==2&&(y==0||y==4)){
                        g2D.setColor(Color.ORANGE);
                        g2D.fillRect(x*100+1,Math.abs(y-4)*100+1,100,100);
                    }
                    g2D.setColor(Color.BLACK);
                    g2D.drawRect(x*100+1,Math.abs(y-4)*100+1,100,100);
                    //Ha a mezőben van bábu.
                    if(game.getTable().getField(x,y).getPiece()!=null){
                        //És az Master
                        DrawPieces(g2D, x, y);

                    }
                }
            }
        }
        //Ha mozgunk akkor konkrétan ugyanezt csinálom, csak nem rajzolom be a mezőjébe azt a bábut amit mozgatok.
        else{
            for(int x=0;x<5;x++){
                for(int y=0;y<5;y++){
                    g2D.setColor(Color.black);
                    g2D.drawRect(x*100+1,Math.abs(y-4)*100+1,100,100);
                    g2D.setColor(Color.white);
                    g2D.fillRect(x*100+5,Math.abs(y-4)*100+1,100,100);
                    if(x==2&&(y==0||y==4)){
                        g2D.setColor(Color.ORANGE);
                        g2D.fillRect(x*100+1,Math.abs(y-4)*100+1,100,100);
                    }
                    g2D.setColor(Color.black);
                    if(game.getTable().getField(x,y).getPiece()!=null){
                        if(game.getTable().getField(x,y).getPiece()!=startField.getPiece()){
                            DrawPieces(g2D, x, y);
                        }

                    }
                }
            }
            for(int x=0;x<5;x++){
                for(int y=0;y<5;y++) {
                    g2D.setColor(Color.black);
                    g2D.drawRect(x * 100 + 1, Math.abs(y - 4) * 100 + 1, 100, 100);
                }
            }
            //A bábut amit mozgatok a kurzorhoz fogom kirajzolni aszerint,hogy milyen fajta.
            if(startField.getPiece() instanceof Master&&startField.getPiece().getCol().equals(COLOR.RED)){
                    g2D.setColor(Color.white);
                    g2D.setColor(Color.black);
                    g2D.drawOval(cX-45,cY-45,90,90);
                    g2D.setColor(Color.red);
                    g2D.fillOval(cX-45,cY-45,90,90);
                    g2D.setColor(Color.black);
                }
            else if(startField.getPiece() instanceof Master&&startField.getPiece().getCol().equals(COLOR.BLUE)){
                g2D.setColor(Color.white);
                g2D.setColor(Color.black);
                g2D.drawOval(cX-45,cY-45,90,90);
                g2D.setColor(Color.blue);
                g2D.fillOval(cX-45,cY-45,90,90);
                g2D.setColor(Color.black);
            }
            else if(startField.getPiece() != null &&startField.getPiece().getCol().equals(COLOR.BLUE)){
                g2D.setColor(Color.white);
                g2D.setColor(Color.black);
                g2D.drawOval(cX-37,cY-37,75,75);
                g2D.setColor(Color.blue);
                g2D.fillOval(cX-37,cY-37,75,75);
                g2D.setColor(Color.black);
            }
            else if(startField.getPiece() != null &&startField.getPiece().getCol().equals(COLOR.RED)){
                g2D.setColor(Color.white);
                g2D.setColor(Color.black);
                g2D.drawOval(cX-37,cY-37,75,75);
                g2D.setColor(Color.red);
                g2D.fillOval(cX-37,cY-37,75,75);
                g2D.setColor(Color.black);
            }

        }
    }

    private void DrawPieces(Graphics2D g2D, int x, int y) {
        if(game.getTable().getField(x,y).getPiece().getClass().equals(Master.class)){
            if(game.getTable().getField(x,y).getPiece().getCol().equals(COLOR.RED)){
                g2D.drawOval(x*100+5,Math.abs(y-4)*100+5,90,90);
                g2D.setColor(Color.red);
                g2D.fillOval(x*100+5,Math.abs(y-4)*100+5,90,90);
                g2D.setColor(Color.black);
            }
            else{
                g2D.drawOval(x*100+5,Math.abs(y-4)*100+5,90,90);
                g2D.setColor(Color.blue);
                g2D.fillOval(x*100+5,Math.abs(y-4)*100+5,90,90);
                g2D.setColor(Color.black);
            }
        }
        else{
            if(game.getTable().getField(x,y).getPiece().getCol().equals(COLOR.RED)){
                g2D.drawOval(x*100+12,Math.abs(y-4)*100+12,75,75);
                g2D.setColor(Color.red);
                g2D.fillOval(x*100+12,Math.abs(y-4)*100+12,75,75);
                g2D.setColor(Color.black);
            }
            else{
                g2D.drawOval(x*100+12,Math.abs(y-4)*100+12,75,75);
                g2D.setColor(Color.blue);
                g2D.fillOval(x*100+12,Math.abs(y-4)*100+12,75,75);
                g2D.setColor(Color.black);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int startX=e.getX()/100;
        int startY=Math.abs(e.getY()/100-4);
        if(startX >= 0 && startX <= 4 && startY <= 4){
           startField= game.getTable().getField(startX,startY);
        }
    }

    /**
     * Egérlenyomással ki lehet jelölni,hogy melyik bábuval akarunk mozogni. Itt történik ennek a logikája.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(game.getTable().getField(e.getX()/100,Math.abs(e.getY()/100-4))!=null){
            if(game.getTable().getField(e.getX()/100,Math.abs(e.getY()/100-4)).getPiece()!=null){
                moving=true;
                startField= game.getTable().getField(e.getX()/100,Math.abs(e.getY()/100-4));
            }
        }
    }

    /**
     * Az egér felengedésével akár léptünk is egyet. Ennek a logikája itt történik.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        moving=false;
        //A hasmoved azért kell, mert nem tudjuk,hogy az adott játékos lépett-e.
        boolean hasmoved=false;
        //Ha még nincs vége a játéknak.
        if(game.getGameStatus().equals(Status.NOTYETDETERMINED)){
            //És ahová lépni akarunk a pályánk belül van.
            if(e.getX()/100>=0&&e.getX()/100<5&&Math.abs(e.getY()/100-4)<5){
                /**
                 * Ahová mozgatjuk.
                 */
                Field destField = game.getTable().getField(e.getX() / 100, Math.abs(e.getY() / 100 - 4));
                //És választott a játékos kártyát.
                if(game.getCurrentPlayer().getChosenCard()!=null){
                    //És ha saját kártyát választott.
                    if(game.getCurrentPlayer().getCards().contains(game.getCurrentPlayer().getChosenCard())){
                        //Akkor a hasmoved legyen egyenlő azzal,hogy lépett-e.
                        hasmoved=game.playerMove(game.getCurrentPlayer(),startField.getX(),startField.getY(), destField.getX(), destField.getY());
                    }
                    //Megváltoztatjuk a kártyák képeit.
                    cardPicChange();
                }
            }
            //Ha ezzel a lépéssel nyert volna a piros ( a piros lép először), akkor a MatchState JLabel írja ki,hogy a piros nyert.
            if(game.getGameStatus().equals(Status.REDWON)){
                MatchState.setText("Vége a meccsnek,Piros nyert!");
            }
            //Ha PlayervsRandom matchup van akkor meg kell nézni,hogy lépett-e a játékos.
            if(game.getMatchup().equals(Matchup.PlayerVsRandom)&&hasmoved){
                //Ha igen akkor random sorsolok a gépnek értékeket, ameddig nem sikerül lépnie.
                Random r=new Random();
                game.getCurrentPlayer().setChosenCard(game.getCurrentPlayer().getCards().get(r.nextInt(2)));
                while(!game.playerMove(game.getCurrentPlayer(),r.nextInt(5),r.nextInt(5),r.nextInt(5),r.nextInt(5))){
                    game.getCurrentPlayer().setChosenCard(game.getCurrentPlayer().getCards().get(r.nextInt(2)));
                }
                cardPicChange();
            }
            this.repaint();
            //Ha PlayervsMiniMaxAI van és lépett a játékos
            if(game.getMatchup().equals(Matchup.PlayerVsAI)&&hasmoved&&game.getGameStatus().equals(Status.NOTYETDETERMINED)){
                //Akkor a minMax class segítségével lekérem a legjobb lépését a kéknek és lépek.
                MinMax minMax=new MinMax(game.getCurrentPlayer(),game.getRed().getCards(), game.getOuterCard());
                minMax.miniMax(game.getTable());
                game.playerMove(game.getCurrentPlayer(),minMax.getBestStart().getX(),minMax.getBestStart().getY(),minMax.getBestDest().getX(),minMax.getBestDest().getY());
                cardPicChange();
            }
            //Ha kék nyert akkor ki kell írni,hogy kék nyert.
            if(game.getGameStatus().equals(Status.BLUEWON)){
                MatchState.setText("Vége a meccsnek,Kék nyert!");
            }
        }
        this.repaint();
    }

    /**
     * A kártyák képeinek a beállításáért felelős.
     */
    private void cardPicChange(){
        ImageIcon imgC=new CardsImages().getImage(game.getRed().getCards().get(0).getClass());
        Image img=imgC.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        redFirstCardButton.setIcon(new ImageIcon(img));

        imgC=new CardsImages().getImage(game.getRed().getCards().get(1).getClass());
        img=imgC.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        redSecondCardButton.setIcon(new ImageIcon(img));

        imgC=new CardsImages().getImage(game.getBlue().getCards().get(0).getClass());
        img=imgC.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        blueFirstCardButton.setIcon(new ImageIcon(img));

        imgC=new CardsImages().getImage(game.getBlue().getCards().get(1).getClass());
        img=imgC.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        blueSecondCardButton.setIcon(new ImageIcon(img));

        imgC=new CardsImages().getImage(game.getOuterCard().getClass());
        img=imgC.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        outerCardLabel.setIcon(new ImageIcon(img));
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Ebben a függvényben játszódik le a logika, amikor mozgatunk egy bábut.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(moving){
            //Ha ténylegesen mozgatunk egy bábut.
            if(startField.getPiece()!=null){
                //Újrarajzoljuk a JPanelt.
                    this.repaint();
                    //Lementjük az egér koordinátáit.
                    cX=e.getX();
                    cY=e.getY();
                }
            }
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
