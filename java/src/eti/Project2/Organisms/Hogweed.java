package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 14:12
 * Description: Hogweed     '%':
 *      Kills every animal in its immediate neighborhood except cyber-sheep.
 *      Kills any animal which eats it, apart from cyber-sheep.
 */

public class Hogweed extends Plant{
    //constructor
    public Hogweed(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Hogweed(Hogweed h) {
        super(h);
    }

    public Hogweed(int X, int Y, World world) {
        setName('%');
        setStrength(10);
        setInitiative(0);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action()
    {
        MapPosition defenderP = new MapPosition(-1, -1);

        /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") is trying to killing animal around" + std::endl;*/
        GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") is killing animal around\n");
        
        //kill WASD animal around(so it didn't kill the plant)
        defenderP = getPosition().Up(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
            getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
            getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
            getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
            getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
            getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().Left(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().Down(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().Right(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().LeftUp(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().LeftDown(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().RightUp(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }

        defenderP = getPosition().RightDown(getWorld());
        if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h')
        {
            getWorld().organisms[defenderP.getY()][defenderP.getX()] = new Organism('_', -1, -1, defenderP.getX(), defenderP.getY(), getWorld());
        }
    }

    @Override
    public void Collision(Organism attacker)
    {
        if (attacker.getName() != 'c')
        {
            //who eats this will die(so this will be eaten)
            /**std::cout + "'" + getName() + "' kills attacker who eats it'" + (Grid(attacker.getPosition().getX(), attacker.getPosition().getY())).getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")" + std::endl;*/
            GUI.getInformation().append( "'" + getName() + "' kills attacker who eats it'" + attacker.getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")\n");

            getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
        }
        else
        {
            /**std::cout + "'" + getName() + "' is eaten by cybersheep" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "' is eaten by cyber sheep\n");

            attacker.setPosition(this.getPosition());
            attacker.setPosition(this.getPosition());
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;
        }
    }
}
