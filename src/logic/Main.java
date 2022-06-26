package logic;

import View.OFrame;
import cards.Deck;


public class Main {
    public static void main(String[] args) {
        Deck d=new Deck();
        Player p1=new Player(COLOR.RED,d);
        Player p2=new Player(COLOR.BLUE,d);
        OFrame os=new OFrame(new Game(p1,p2,d,Matchup.PlayerVsPlayer));
    }
}
