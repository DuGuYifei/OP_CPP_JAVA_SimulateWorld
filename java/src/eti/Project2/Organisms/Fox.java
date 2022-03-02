package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 13:31
 * Description: Fox     'f':
 *      Has good sense of smell:
 *      fox will never move to a cell occupied by a stronger organism.
 */

public class Fox extends Animal{
    //constructor

    public Fox(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Fox(Fox f) {
        super(f);
    }

    public Fox(int X, int Y, World world) {
        setName('f');
        setStrength(3);
        setInitiative(7);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action()	//fox never go to the grid that have stronger organism
    {
        boolean[] WASD = { false,false,false,false,false,false,false,false };
        int randomRange = 0;
        MapPosition defenderP = new MapPosition(-1, -1);

        defenderP = getPosition().Up(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getStrength() <= this.getStrength())
        {
            randomRange++;
            WASD[0] = true;
        }

        defenderP = getPosition().Left(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getStrength() <= this.getStrength())
        {
            randomRange++;
            WASD[1] = true;
        }

        defenderP = getPosition().Down(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getStrength() <= this.getStrength())
        {
            randomRange++;
            WASD[2] = true;
        }

        defenderP = getPosition().Right(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getStrength() <= this.getStrength())
        {
            randomRange++;
            WASD[3] = true;
        }

        defenderP = getPosition().LeftUp(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
            randomRange++;
            WASD[4] = true;
        }

        defenderP = getPosition().LeftDown(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
            randomRange++;
            WASD[5] = true;
        }

        defenderP = getPosition().RightUp(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
            randomRange++;
            WASD[6] = true;
        }

        defenderP = getPosition().RightDown(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
            randomRange++;
            WASD[7] = true;
        }

        //if there is place can move(otherwise doesn't move)
        if (randomRange > 0)
        {
            Random random=new Random();
            int direction = random.nextInt(randomRange);
            for (int i = 0; i < 8; i++)
            {
                if (WASD[i])
                {
                    direction--;
                }
                if (direction < 0)
                {
                    switch (i)
                    {
                        case 0:
                            defenderP = getPosition().Up(getWorld());
                            break;
                        case 1:
                            defenderP = getPosition().Left(getWorld());
                            break;
                        case 2:
                            defenderP = getPosition().Down(getWorld());
                            break;
                        case 3:
                            defenderP = getPosition().Right(getWorld());
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

                    /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") find safe place (" + defenderP.getX() + "," + defenderP.getY() + ") to move" + std::endl;*/
                    GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") find safe place (" + defenderP.getX() + "," + defenderP.getY() + ") to move\n");
                    
                    Organism attacker = this;

                    //if they are same animal
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == this.getName())
                    {
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
                        attacker = new Fox(this);
                        attacker.setPosition(new MapPosition(-1, -1));
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
                    }
				else
                    //if they are different animal
                    {
                        /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ")" + std::endl;*/
                        char defenderName = getWorld().organisms[defenderP.getY()][defenderP.getX()].getName();
                        if(defenderName != '_')
                            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + defenderName + "'(" + defenderP.getX() + "," + defenderP.getY() + ")\n");
                        else
                            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") move to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");

                        getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
                    }
                    break;
                }
            }
        }
        else {
            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") didn't find safe place tp move so stay there" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") didn't find safe place tp move so stay there");
        }
    }
}
