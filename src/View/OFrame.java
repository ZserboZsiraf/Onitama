package View;


import logic.Game;

import javax.swing.*;

/**
 * A játék Frameje.
 */
public class OFrame extends JFrame {
    private PanelCont pw;

    public OFrame(Game game){
        super();
        pw=new PanelCont(game);
        this.add(pw);
        //Beállítom a nagyságát
        this.setSize(1400,1200);
        //Beállítom,hogy ha ki x-elem akkor kilépjen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Ne lehessen átállítani.
        this.setResizable(false);
        this.setTitle("Onitama");
        //Háttér beállítás.
        ImageIcon img = new ImageIcon("images\\Onitama.png");
        this.setIconImage(img.getImage());
        this.setVisible(true);
    }
    public PanelCont getPw() {
        return pw;
    }
}


