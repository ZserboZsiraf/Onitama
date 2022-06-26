package View;

import logic.Game;
import logic.Matchup;

import javax.swing.*;
import java.awt.*;

/**
 * Beállítások menüpont, itt lehet beállítani a matchupot.
 */
public class Settings extends JPanel {
    private JButton back;
    public Settings(Game g) {
        this.setLayout(null);
        this.setBackground(Color.white);
        //Beállítom a gombot, amire ha rákattintunk akkor Player vs Player meccs lesz.
        JButton pvp = new JButton("Player vs Player");
        pvp.setFont(new Font("Times new Roman",Font.PLAIN,60));
        pvp.setBounds(300,125,600,100);
        pvp.addActionListener(e->g.setMatchup(Matchup.PlayerVsPlayer));
        //Beállítom a gombot, amire ha rákattintunk akkor Player vs RandomAI meccs lesz.
        JButton pvRai = new JButton("Player vs RandomAI");
        pvRai.setFont(new Font("Times new Roman",Font.PLAIN,60));
        pvRai.setBounds(300,275,600,100);
        pvRai.addActionListener(e->g.setMatchup(Matchup.PlayerVsRandom));
        //Beállítom a gombot, amire ha rákattintunk akkor Player vs MiniMaxAI meccs lesz.
        JButton pvAI = new JButton("Player vs MiniMaxAI");
        pvAI.setFont(new Font("Times new Roman",Font.PLAIN,60));
        pvAI.setBounds(300,425,600,100);
        pvAI.addActionListener(e->g.setMatchup(Matchup.PlayerVsAI));
        //Beállítom a gombot ami hatására vissza lehet lépni.
        back=new JButton("Go back to Menu");
        back.setFont(new Font("Times new Roman",Font.PLAIN,60));
        back.setBounds(300,575,600,100);
        back.addActionListener(e->this.setVisible(false));
        this.add(pvp);
        this.add(pvAI);
        this.add(pvRai);
        this.add(back);
    }

    public JButton getBack() {
        return back;
    }
}
