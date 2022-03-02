package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 13:06
 * Description: CyberSheep  'c'
 * Its main goal is the extermination of Sosnowsky's hogweed.
 * It always moves towards the closes hogweed and tries to eat it.
 * If there are no Sosnowsky's hogweeds, it behaves like a normal sheep.
 * Eats Sosnowsky's hogweed.
 */

public class CyberSheep extends Animal {
    //constructor
    public CyberSheep(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public CyberSheep(CyberSheep c) {
        super(c);
    }

    public CyberSheep(int X, int Y, World world) {
        setName('c');
        setStrength(11);
        setInitiative(4);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action() {
        MapPosition hogweedP = new MapPosition(-1, -1);
        int shortest = getWorld().getSizeX() + getWorld().getSizeY();
        for (int i = 0; i < getWorld().getSizeX(); i++) {
            for (int j = 0; j < getWorld().getSizeY(); j++) {
                if (getWorld().organisms[j][i].getName() == '%') {
                    int d = Math.abs(i - this.getPosition().getX()) + Math.abs(j - this.getPosition().getY());
                    if (d < shortest) {
                        shortest = d;
                        hogweedP = new MapPosition(i, j);

                        /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") find '" + (Grid(hogweedP.getX(),hogweedP.getY())).getName() + "'(" + hogweedP.getX() + "," + hogweedP.getY() + ")" + std::endl;*/
                        GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") find '" + getWorld().organisms[hogweedP.getY()][hogweedP.getX()].getName() + "'(" + hogweedP.getX() + "," + hogweedP.getY() + ")\n");
                    }
                }
            }
        }

        MapPosition defenderP = new MapPosition(-1, -1);
        Organism attacker = this;

        //if there is hogweed
        if (hogweedP.getX() != -1) {
            if (hogweedP.getX() > this.getPosition().getX())
                defenderP = getPosition().Right(getWorld());
            else if (hogweedP.getX() < this.getPosition().getX())
                defenderP = getPosition().Left(getWorld());
            if (hogweedP.getY() < this.getPosition().getY())
                defenderP = getPosition().Up(getWorld());
            else if (hogweedP.getY() > this.getPosition().getY())
                defenderP = getPosition().Down(getWorld());
        }

        //no hogweed
        else
        {
            Random random = new Random();
            int direction = random.nextInt(8);

            switch (direction) {
                case 0:    //left
                    defenderP = getPosition().Left(getWorld());
                    //this.SetPosition(getPosition().Left(getWorld()));
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
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a cyber baby.\n");


            //baby's collision
            attacker = new CyberSheep(this);
            attacker.setPosition(new MapPosition(-1, -1));
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
        }
        getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
    }
}
