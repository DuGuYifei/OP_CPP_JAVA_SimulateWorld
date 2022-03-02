package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/30 14:06
 * Description: Guarana     '#':
 *      Strength of the animal which ate guarana is permanently increased by 3.
 */

public class Guarana extends Plant{
    //constructor
    public Guarana(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Guarana(Guarana g) {
        super(g);
    }

    public Guarana (int X, int Y, World world) {
        setName('#');
        setStrength(0);
        setInitiative(0);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Collision(Organism attacker)
    {
        //default collision
        if (attacker.getStrength() >= this.getStrength())
        {
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;

            /**std::cout + "'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") increases 3 strength of attacker who eats it'" + (Grid(attacker.getPosition().getX(), attacker.getPosition().getY())).getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") increases 3 strength of attacker who eats it'" + attacker.getName() + "'(" + attacker.getPosition().getX() + "," + attacker.getPosition().getY() + ")\n");
            
            attacker.setPosition(this.getPosition());
            attacker.setStrength(attacker.getStrength() + 3);
        }
    }
}
