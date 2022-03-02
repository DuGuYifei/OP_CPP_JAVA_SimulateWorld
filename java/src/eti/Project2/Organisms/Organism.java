package eti.Project2.Organisms;

import eti.Project2.MapPosition;
import eti.Project2.World;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/26 22:18
 * Description: animals and plants
 * WOLF        'w'
 * SHEEP       's'
 * FOX         'f'
 * TURTLE      't'
 * ANTELOPE    'a'
 * CYBERSHEEP  'c'
 * GRASS       '!'
 * SOWTHISTLE  '@'
 * GUARANA     '#'
 * BELLADONNA  '$'
 * HOGWEED     '%'
 * HUMAN       'h'
 * nullOrgan   '_'
 */

public class Organism {
    private boolean acted = true;

    private int age = 0;
    private char name;

    private int strength;
    private int initiative;
    private MapPosition position;            //coordinate in the world
    private World world;

    //getters and setters
    public boolean getActed() {
        return acted;
    }

    public void setActed(boolean acted) {
        this.acted = acted;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public MapPosition getPosition() {
        return position;
    }

    public void setPosition(MapPosition position) {
        this.position = position;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    //constructor
    public Organism(char n, int s, int i, int X, int Y, World w) {
        name = n;
        strength = s;
        initiative = i;
        position = new MapPosition(X, Y);
        world = w;
        if (n == '_')
            setActed(true);
    }

    public Organism(Organism o) {
        setName(o.getName());
        setStrength(o.getStrength());
        setInitiative(o.getInitiative());
        setPosition(o.getPosition());
        setWorld(o.getWorld());
        setActed(true);
        setAge(0);
    }

    public Organism() {
    }

    ;

    //other functions
    public void Action() {
    }

    public void Collision(Organism attacker) {
        //default collision
        if (attacker.getStrength() >= this.getStrength()) {
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;

            if (attacker.getPosition().getX() == -1)
                attacker.setActed(true);                //same strength, attacker win, so baby win(even if there is something let this be stronger, attacker will not pass code to here)

            attacker.setPosition(getPosition());
        }
    }

    public void Draw(JButton b) {
        //std::cout << name <<" ";
        char name = getName();
        ImageIcon img;

        switch (name) {
            case 'a':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/antelope.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;

            case '$':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/belladonna.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 'c':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/cybersheep.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 'f':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/fox.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case '!':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/grass.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case '#':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/guarana.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case '%':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/hogweed.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 'h':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/human.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 's':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/sheep.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case '@':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/sowthistle.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 't':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/turtle.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case 'w':
                img = (new ImageIcon(Objects.requireNonNull(Organism.class.getResource("PNGImage/wolf.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                b.setIcon(img);
                break;
            case '_':
                b.setIcon(null);
                break;

        }
    }
}
