package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 17:11
 * Description: Turtle      't':
 * Has 75% chance to stay in the same place.
 * Reflects attacks of animal with strength less than 5.
 * Attacker will return to the previous cell.
 */

public class Turtle extends Animal {
    //constructor
    public Turtle(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Turtle(Turtle t) {
        super(t);
    }

    public Turtle(int X, int Y, World world) {
        setName('t');
        setStrength(4);
        setInitiative(4);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action() {
        //%75 stay
        Random random = new Random();
        int stayOrMove = random.nextInt(100);
        if (stayOrMove < 75) {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") didn't move" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") didn't move\n");
            return;
        }

        //%25 move
        int direction = random.nextInt(4);
        Organism attacker = this;
        MapPosition defenderP = new MapPosition(-1, -1);
        switch (direction) {
            case 0:    //left
                defenderP = getPosition().Left(getWorld());
                //this.setPosition(getPosition().Left(getWorld()));
                break;
            case 1:    //right
                defenderP = getPosition().Right(getWorld());
                break;
            case 2:    //up
                defenderP = getPosition().Up(getWorld());
                break;
            case 3:    //down
                defenderP = getPosition().Down(getWorld());
                break;
            case 4:
                defenderP = getPosition().LeftUp(getWorld());
                break;
            case 5:
                defenderP = getPosition().LeftDown(getWorld());
                break;
            case 6:
                defenderP = getPosition().RightUp(getWorld());
                break;
            case 7:
                defenderP = getPosition().RightDown(getWorld());
                break;
        }

        //if they are same animal
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == this.getName()) {
            //baby is born next to them
            MapPosition babyPosition =
                    defenderP.getY() >= this.getPosition().getY() ?
                            new MapPosition(defenderP.getX(), defenderP.getY() + 1) :
                            new MapPosition(this.getPosition().getX(), this.getPosition().getY() + 1);

            if (babyPosition.getY() >= getWorld().getSizeY())
                babyPosition.setY(0);

            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + (Grid(defenderP.getX(), defenderP.getY())).getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a baby." + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a baby.\n");

            //baby's collision
            attacker = new Turtle(this);
            attacker.setPosition(new MapPosition(-1, -1));
            getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
        } else
        //if they are different animal
        {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + (Grid(defenderP.getX(), defenderP.getY())).getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ")" + std::endl;*/
            char defenderName = getWorld().organisms[defenderP.getY()][defenderP.getX()].getName();
            if(defenderName != '_')
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + defenderName + "'(" + defenderP.getX() + "," + defenderP.getY() + ")\n");
            else
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") move to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");

            getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
            getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
        }
    }

    @Override
    public void Collision(Organism attacker) {
        //default collision
        if (attacker.getStrength() >= 5) {
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;

            attacker.setPosition(this.getPosition());
        }
        //less than 5, let it go back(nothing to do)
        else {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") reflect attack" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") reflect attack\n");

            if (attacker.getPosition().getX() != -1) {
                getWorld().organisms[attacker.getPosition().getY()][attacker.getPosition().getX()] = attacker;
            }
        }
    }
}
