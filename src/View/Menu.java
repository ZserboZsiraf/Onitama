package View;

import javax.swing.*;
import java.awt.*;

/**
 * A főmenüért felelős JLabel.
 */
public class Menu extends JLabel {
    /**
     * Az új játék gomb.
     */
    private JButton newGame;
    /**
     * A betöltés gomb.
     */
    private JButton load;
    /**
     * A kilépés gomb.
     */
    private JButton exit;
    /**
     * A beállítások gomb.
     */
    private JButton settings;

    Menu(){
       //Beállítom a gombokat,meg a hátteret.
       newGame=new JButton("New Game");
       newGame.setBounds(650,220,400,100);
       newGame.setFont(new Font("Algerian", Font.PLAIN,60));
       load=new JButton("Load");
       load.setBounds(650,340,400,100);
       load.setFont(new Font("Algerian", Font.PLAIN,60));
       exit=new JButton("Exit");
       exit.setBounds(650,580,400,100);
        exit.setFont(new Font("Algerian", Font.PLAIN,60));
        settings=new JButton("Settings");
        settings.setBounds(650,460,400,100);
        settings.setFont(new Font("Algerian", Font.PLAIN,60));
        this.setLayout(null);
        this.add(newGame);
        this.add(load);
        this.add(exit);
        this.add(settings);
        this.setBackground(new Color(255,204,51));
        this.setOpaque(true);
        ImageIcon img=new ImageIcon("src\\images\\Onitama.png");
        this.setIcon(new ImageIcon(img.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT)));
        this.setVisible(true);
    }
    public JButton getSettings() {
        return settings;
    }
    public JButton getNewGame() {
        return newGame;
    }

    public void setNewGame(JButton newGame) {
        this.newGame = newGame;
    }

    public JButton getLoad() {
        return load;
    }

    public void setLoad(JButton load) {
        this.load = load;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }
}
