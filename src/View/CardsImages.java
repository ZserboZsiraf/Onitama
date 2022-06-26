package View;

import cards.*;

import javax.swing.*;
import java.util.HashMap;

/**
 * Eltárol egy HashMapet, ahol a kártya típusa alapján megkaphatjuk a kártyához tartozó képet.
 */
public class CardsImages {
    private HashMap<Class, ImageIcon>images;

    public CardsImages(){
        images=new HashMap<>();
        images.put(Boar.class,new ImageIcon("src\\images\\Boar.png"));
        images.put(Cobra.class,new ImageIcon("src\\images\\Cobra.png"));
        images.put(Crab.class,new ImageIcon("src\\images\\Crab.png"));
        images.put(Crane.class,new ImageIcon("src\\images\\Crane.png"));
        images.put(Dragon.class,new ImageIcon("src\\images\\Dragon.png"));
        images.put(Eel.class,new ImageIcon("src\\images\\Eel.png"));
        images.put(Elephant.class,new ImageIcon("src\\images\\Elephant.png"));
        images.put(Frog.class,new ImageIcon("src\\images\\Frog.png"));
        images.put(Goose.class,new ImageIcon("src\\images\\Goose.png"));
        images.put(Horse.class,new ImageIcon("src\\images\\Horse.png"));
        images.put(Mantis.class,new ImageIcon("src\\images\\Mantis.png"));
        images.put(Monkey.class,new ImageIcon("src\\images\\Monkey.png"));
        images.put(Ox.class,new ImageIcon("src\\images\\Ox.png"));
        images.put(Rabbit.class,new ImageIcon("src\\images\\Rabbit.png"));
        images.put(Rooster.class,new ImageIcon("src\\images\\Rooster.png"));
        images.put(Tiger.class,new ImageIcon("src\\images\\Tiger.png"));
    }
    public ImageIcon getImage(Class className){
        return images.get(className);
    }
}
