package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 15:57
 * Description: SowThistle      '@':
 *      Performs 3 attempts at spreading in each turn
 */

public class SowThistle extends Plant{
    //constructor
    public SowThistle(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public SowThistle(SowThistle s) {
        super(s);
    }

    public SowThistle(int X, int Y, World world) {
        setName('@');
        setStrength(0);
        setInitiative(0);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action()
    {
        for (int times = 0; times < 3; times++)
        {
            boolean[] WASD = { false,false,false,false, false, false, false, false };
            int randomRange = 0;
            MapPosition defenderP=new MapPosition(-1, -1);

            defenderP = getPosition().Up(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_')
            {
                randomRange++;
                WASD[0] = true;
            }

            defenderP = getPosition().Left(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_')
            {
                randomRange++;
                WASD[1] = true;
            }

            defenderP = getPosition().Down(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_')
            {
                randomRange++;
                WASD[2] = true;
            }

            defenderP = getPosition().Right(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_')
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

                        /**std::cout << "'" << getName() << "'(" << getPosition().getX() << "," << getPosition().getY() << ") is tring to spread to (" << defenderP.getX() << "," << defenderP.getY() << ")" << std::endl;*/
                        GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") is trying to spread to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");

                        getWorld().organisms[defenderP.getY()][defenderP.getX()] = new SowThistle(this);		//add the same plant in it
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].setAge(0);
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].setActed(true);
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].setPosition(defenderP);	//set the position as current position
                        return;
                    }
                }
            }
        }
    }
}
