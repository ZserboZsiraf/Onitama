package View;


import cards.Deck;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Felelős a különböző Menüpontok közötti váltásért.
 */
public class PanelCont extends JPanel {
    /**
     * A menü.
     */
    private Menu m;
    /**
     * A játék grafikája.
     */
    private BoardView bw;
    /**
     * A játék.
     */
    private Game game;
    PanelCont(Game g){
        this.game=g;
        //Kell egy CardLayout, hogy gombnyomásra váltsak a különböző JPanelek/JLabelek között.
        CardLayout cl=new CardLayout();
        this.setLayout(cl);
        this.setBackground(Color.white);
        m=new Menu();
        Settings set = new Settings(g);
        bw=new BoardView(game);
        this.add(m,"1");
        this.add(bw,"2");
        this.add(set,"3");
        cl.show(this,"1");
        //A New Game gomb létrehoz egy új játékot.
        m.getNewGame().addActionListener(e->{
            Deck d=new Deck();
            Player h1=new Player(COLOR.RED,d);
            Player h2=new Player(COLOR.BLUE,d);
            cl.show(this,"2");
            game=new Game(h1,h2,d,g.getMatchup());
            this.remove(bw);
            bw=new BoardView(game);
            this.add(bw,"2");
            cl.show(this,"2");
            bw.repaint();
        });
        //Az Exit gombbal kilépünk.
        m.getExit().addActionListener(e->{
            JComponent comp=(JComponent) e.getSource();
            Window win=SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
        //A Settings gombbal belépünk a settings menübe.
        m.getSettings().addActionListener(e-> cl.show(this,"3"));
        //A Load gombbal betöltjük a boardot és aztán ugyanaz történne mintha a New Game-re kattintottunk volna.
        m.getLoad().addActionListener(e->{
            try {
                ObjectInputStream is=new ObjectInputStream(new FileInputStream("save.txt"));
                try {
                    game=(Game) is.readObject();
                } catch (ClassNotFoundException err) {
                    err.printStackTrace();
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
            this.remove(bw);
            bw=new BoardView(game);
            this.add(bw,"2");
            cl.show(this,"2");
        });

    }

    public Menu getM() {
        return m;
    }

    public void setBw(BoardView bw) {
        this.bw = bw;
    }

    public BoardView getBw() {
        return bw;
    }
}
