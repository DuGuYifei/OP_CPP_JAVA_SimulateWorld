package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 12:59
 * Description: Belladonna  '$'
 *      Kills any animal which eats it.
 */

public class Belladonna extends Plant{
    //constructor
    public Belladonna(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Belladonna(Belladonna b) {
        super(b);
    }

    public Belladonna(int X, int Y, World world){
        setName('$');
        setStrength(99);
        setInitiative(0);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Collision(Organism attacker)
    {
        //who eats this will die(so this will be eaten)
        /**std::cout + "'" + getName() + "' kills attacker who eats it'" + (Grid(attacker.getPosition().getX(), attacker.getPosition().getY())).getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")" + std::endl;*/
        GUI.getInformation().append("'" + getName() + "' kills attacker who eats it'" + attacker.getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")\n");
        
        getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());
    }
}
