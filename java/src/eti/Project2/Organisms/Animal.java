package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/27 21:31
 * Description: class for animal
 */

public class Animal extends Organism {
    //constructor
    public Animal(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Animal(Animal a) {
        super(a);
    }

    public Animal() {
    }

    //action randomly to neighbour
    @Override
    public void Action() {
        Random random = new Random();
        int direction = random.nextInt(8);
        Organism attacker = this;
        MapPosition defenderP = new MapPosition(-1, -1);
        switch (direction) {
            case 0:    //left
                defenderP = getPosition().Left(getWorld());
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
            case 4:    //left down
                defenderP = getPosition().LeftDown(getWorld());
                break;
            case 5:    //left up
                defenderP = getPosition().LeftUp(getWorld());
                break;
            case 6:    //right down
                defenderP = getPosition().RightDown(getWorld());
                break;
            case 7:    //right up
                defenderP = getPosition().RightUp(getWorld());
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

            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a baby." + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a baby.\n");

            //baby's collision
            attacker = new Animal(this);
            attacker.setPosition(new MapPosition(-1, -1));

        } else
        //if they are different animal
        {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ")" + std::endl;*/
            char defenderName = getWorld().organisms[defenderP.getY()][defenderP.getX()].getName();
            if(defenderName != '_')
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + defenderName + "'(" + defenderP.getX() + "," + defenderP.getY() + ")\n");
            else
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") move to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");

            getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
        }
        getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
    }
}
