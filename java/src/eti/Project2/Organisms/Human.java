package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.awt.event.KeyEvent;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 18:33
 * Description: Human       'h':
 * Human moves in same way as animals,
 * but the direction of his movement corresponds to the arrow keys pressed by the player.
 * For example, if the player presses the left arrow key,
 * the human (in his turn) will move to the cell to the left of his initial position.
 * Human possesses a special ability
 * which can be activated by a separate key on the keyboard.
 * After activation, this ability affects the behavior of his collision() method
 * for next 5 rounds.
 * After that, the special ability is turned off and cannot be activated for the next 5 rounds.
 * Ability：    purification
 * Human destroys all animals and plants that are adjacent to his position.
 */

public class Human extends Animal {
    //constructor
    public Human(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Human(Human h) {
        super(h);
    }

    public Human(int X, int Y, World world) {
        setName('h');
        setStrength(5);
        setInitiative(4);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    private static int abilityRound = 0;            //the round number of human special ability

    private static int arrowKey = KeyEvent.VK_UP;                //the arrow key to control human

    //getters and setters
    public static int getAbilityRound() {
        return abilityRound;
    }

    public static void setAbilityRound(int abilityRound) {
        Human.abilityRound = abilityRound;
    }

    public static int getArrowKey() {
        return arrowKey;
    }

    public static void setArrowKey(int arrowKey) {
        Human.arrowKey = arrowKey;
    }

    @Override
    public void Action() {

        //static int abilityRound = 0;

        Organism attacker = this;
        MapPosition defenderP = new MapPosition(-1, -1);

        switch (Human.getArrowKey()) {
            case KeyEvent.VK_UP:
                defenderP = getPosition().Up(getWorld());   //key up
                break;
            case KeyEvent.VK_DOWN:
                defenderP = getPosition().Down(getWorld()); // key down
                break;
            case KeyEvent.VK_LEFT:
                defenderP = getPosition().Left(getWorld()); // key left
                break;
            case KeyEvent.VK_RIGHT:
                defenderP = getPosition().Right(getWorld());// key right
                break;
        }

        if (abilityRound == 0) {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + (Grid(defenderP.getX(), defenderP.getY())).getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ")" + std::endl;*/
            char defenderName = getWorld().organisms[defenderP.getY()][defenderP.getX()].getName();
            if (defenderName != '_') {
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + defenderName + "'(" + defenderP.getX() + "," + defenderP.getY() + ")\n");
            }else {
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") move to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");
            }

            getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
            getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
        }
        //ability activated
        else {
            /**std::cout + "	 -- human is tring to pure the grid around(ability remain " + abilityRound + " round)" + std::endl;*/
            GUI.getInformation().append("	 -- human is trying to pure the grid around(ability remain " + getAbilityRound() + " round)\n");

            setAbilityRound(getAbilityRound() - 1);

            MapPosition pureP = new MapPosition(-1, -1);    //which will be pured after movement

            //defenderP
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = this;
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
            setPosition(defenderP);

            //up
            pureP = defenderP.Up(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //down
            pureP = defenderP.Down(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //left
            pureP = defenderP.Left(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //right
            pureP = defenderP.Right(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //left up
            pureP = defenderP.LeftUp(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //left down
            pureP = defenderP.LeftDown(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //right up
            pureP = defenderP.RightUp(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());

            //right down
            pureP = defenderP.RightDown(getWorld());
            getWorld().organisms[pureP.getY()][pureP.getX()] = new Organism('_', -1, -1, pureP.getX(), pureP.getY(), getWorld());
        }
    }
}
