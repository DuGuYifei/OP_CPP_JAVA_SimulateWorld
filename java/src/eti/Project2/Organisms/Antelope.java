package eti.Project2.Organisms;

import eti.Project2.GUI;
import eti.Project2.MapPosition;
import eti.Project2.World;

import java.util.Random;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/29 23:04
 * Description: Antelope    'a'
 * Has wider range of movement - 2 fields instead of 1.
 * Has 50% chance to escape from fight. In such case it moves to a free neighboring cell.
 */

public class Antelope extends Animal {
    //constructor
    public Antelope(char n, int s, int i, int X, int Y, World w) {
        super(n, s, i, X, Y, w);
    }

    public Antelope(Antelope a) {
        super(a);
    }

    public Antelope(int X, int Y, World world) {
        setName('a');
        setStrength(4);
        setInitiative(4);
        setPosition(new MapPosition(X, Y));
        setWorld(world);
    }

    @Override
    public void Action() {
        //used to be free grid that can be escape
        Organism attacker = this;

        getWorld().organisms[getPosition().getY()][getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());

        Random random = new Random();
        MapPosition defenderP = new MapPosition(getPosition().getX(), getPosition().getY());

        for (int moveTimes = 0; moveTimes < 2; moveTimes++) {
            int direction = random.nextInt(8);

            switch (direction) {
                case 0:    //left up
                    defenderP = defenderP.LeftUp(getWorld());
                    break;
                case 1:    //left down
                    defenderP = defenderP.LeftDown(getWorld());
                    break;
                case 2:    //right up
                    defenderP = defenderP.RightUp(getWorld());
                    break;
                case 3:    //right down
                    defenderP = defenderP.RightDown(getWorld());
                    break;
                case 4:    //left
                    defenderP = defenderP.Left(getWorld());
                    break;
                case 5:    //up
                    defenderP = defenderP.Up(getWorld());
                    break;
                case 6:    //down
                    defenderP = defenderP.Down(getWorld());
                    break;
                case 7:    //right
                    defenderP = defenderP.Right(getWorld());
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

            /**std::cout + "'" + getName() + "'(" + getPosition().X + "," + getPosition().Y + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].
             getName() + "'(" + defenderP.X + "," + defenderP.Y + ") has a baby." + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") and '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() + "'(" + defenderP.getX() + "," + defenderP.getY() + ") has a baby.\n");
            
            //baby's collision
            attacker = new Antelope(this);
            attacker.setPosition(new MapPosition(-1, -1));
            getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);

            getWorld().organisms[getPosition().getY()][getPosition().getX()] = this;
        } else {
            /**std::cout + "'" + getName() + "'(" + getPosition().X + "," + getPosition().Y + ") attack '" + getWorld().organisms[defenderP.getY()][defenderP.getX()].
             getName() + "'(" + defenderP.X + "," + defenderP.Y + ")" + std::endl;*/
            char defenderName = getWorld().organisms[defenderP.getY()][defenderP.getX()].getName();

            if(defenderName != '_')
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") attack '" + defenderName + "'(" + defenderP.getX() + "," + defenderP.getY() + ")\n");
            else
                GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") move to (" + defenderP.getX() + "," + defenderP.getY() + ")\n");

            //they are different animal(including free cell)
            //if not free cell, 50% chance to escape
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'w' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 's' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'f' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 't' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'a' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'h' ||
                    getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == 'c') {
                int escape = random.nextInt(2);
                //escape: find a free cell
                if (escape == 0) {
                    /**std::cout + "	 -- antelope escape from fight" + std::endl;*/
                    GUI.getInformation().append("	 -- antelope escape from fight");
                    
                    boolean[] WASD = {false, false, false, false, false, false, false, false};
                    int randomRange = 0;
                    MapPosition defenderFreeP = new MapPosition(-1, -1);
                    defenderFreeP = defenderP.Up(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[0] = true;
                    }

                    defenderFreeP = defenderP.Left(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[1] = true;
                    }

                    defenderFreeP = defenderP.Down(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[2] = true;
                    }

                    defenderFreeP = defenderP.Right(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[3] = true;
                    }

                    defenderFreeP = defenderP.LeftUp(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[4] = true;
                    }

                    defenderFreeP = defenderP.LeftDown(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[5] = true;
                    }

                    defenderFreeP = defenderP.RightUp(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[6] = true;
                    }

                    defenderFreeP = defenderP.RightDown(getWorld());
                    if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                        randomRange++;
                        WASD[7] = true;
                    }

                    //if have free cell
                    if (randomRange > 0) {
                        int direction = random.nextInt(randomRange);
                        for (int i = 0; i < 8; i++) {
                            if (WASD[i]) {
                                direction--;
                            }
                            if (direction < 0) {
                                switch (i) {
                                    case 0:
                                        defenderP = defenderP.Up(getWorld());
                                        break;
                                    case 1:
                                        defenderP = defenderP.Left(getWorld());
                                        break;
                                    case 2:
                                        defenderP = defenderP.Down(getWorld());
                                        break;
                                    case 3:
                                        defenderP = defenderP.Right(getWorld());
                                        break;
                                    case 4:
                                        defenderP = defenderP.LeftUp(getWorld());
                                        break;
                                    case 5:
                                        defenderP = defenderP.LeftDown(getWorld());
                                        break;
                                    case 6:
                                        defenderP = defenderP.RightUp(getWorld());
                                        break;
                                    case 7:
                                        defenderP = defenderP.RightDown(getWorld());
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    return;
                }
            }
            getWorld().organisms[defenderP.getY()][defenderP.getX()].Collision(attacker);
        }
    }

    @Override
    public void Collision(Organism attacker) {
        //used to be free grid that can be escape
        //if not the baby
        if(attacker.getPosition().getX()!=-1)
            getWorld().organisms[attacker.getPosition().getY()][attacker.getPosition().getX()] = new Organism('_', -1, -1, getPosition().getX(), getPosition().getY(), getWorld());

        Random random = new Random();
        int escape = random.nextInt(2);
        //escape: find a free cell
        if (escape == 0) {
            /**std::cout + "'" + getName() + "'(" + getPosition().X + "," + getPosition().Y + ") escape from fight" + std::endl;*/
            GUI.getInformation().append("'" + getName() + "'(" + getPosition().getX() + "," + getPosition().getY() + ") escape from fight\n");
            
            boolean[] WASD = {false, false, false, false, false, false, false, false};
            int randomRange = 0;
            MapPosition defenderP = new MapPosition(-1, -1);

            defenderP = getPosition().Up(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                randomRange++;
                WASD[0] = true;
            }

            defenderP = getPosition().Left(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                randomRange++;
                WASD[1] = true;
            }

            defenderP = getPosition().Down(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
                randomRange++;
                WASD[2] = true;
            }

            defenderP = getPosition().Right(getWorld());
            if (getWorld().organisms[defenderP.getY()][defenderP.getX()].getName() == '_') {
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

            if (randomRange > 0) {
                int direction = random.nextInt(randomRange);
                for (int i = 0; i < 8; i++) {
                    if (WASD[i]) {
                        direction--;
                    }
                    if (direction < 0) {
                        switch (i) {
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

                        getWorld().organisms[defenderP.getY()][defenderP.getX()] = this;                            //let this move to new position
                        getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;                //move attacker to here

                        attacker.setPosition(this.getPosition());
                        getWorld().organisms[defenderP.getY()][defenderP.getX()].
                                setPosition(defenderP);        //set the position as new position

                        return;
                    }
                }
            }
        }
        //not escape
        //default collision
        if (attacker.getStrength() >= this.getStrength()) {
            getWorld().organisms[getPosition().getY()][getPosition().getX()] = attacker;

            //if = -1, it is nullorgan or baby of two same animal
            if (attacker.getPosition().getX() == -1)
                attacker.setActed(true);                //same strength, attacker win, so baby win(even if there is something let this be stronger, attacker will not pass code to here)

            attacker.setPosition(this.getPosition());
        }
    }
}
