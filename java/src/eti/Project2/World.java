package eti.Project2;

import eti.Project2.Organisms.*;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/26 23:07
 * Description: class of world. store all organisms in world.
 */

public class World {
    private int sizeX;
    private int sizeY;

    public Organism[][] organisms;      //position is (a,b), then its pointer is b*sizeX+a.

    //getters
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    //setters
    public void setSizeX(int X) {
        sizeX = X;
    }

    public void setSizeY(int Y) {
        sizeY = Y;
    }

    //constructor and destructor
    public World() {
        sizeX = 0;
        sizeY = 0;
    }

    public World(int X, int Y) {
        sizeX = X;
        sizeY = Y;
        organisms = new Organism[Y][X];
        for (int i = 0; i < Y; i++)
            for (int j = 0; j < X; j++)
                organisms[i][j] = new Organism('_', -1, -1, j, i, this);
    }


    //other functions
    public void Save(String fileName, int mode) {
        try {
            //open file
            File file = new File(fileName);
            file.createNewFile();

            //output parameter of world
            PrintWriter pw = new PrintWriter(file);
            pw.println(sizeX + " " + sizeY + " " + mode);

            //out put all organisms
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    pw.print(organisms[i][j].getName() + " ");
                    pw.print(organisms[i][j].getAge() + " ");
                    pw.print(organisms[i][j].getStrength() + " ");
                    pw.print(organisms[i][j].getInitiative() + " ");
                    pw.print(organisms[i][j].getPosition().getX() + " ");
                    pw.println(organisms[i][j].getPosition().getY());
                }
            }
            //close file
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public int Load(String fileName) {
        int mode = -1;
        try {
            //open file
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            //scan size of world
            setSizeX(scanner.nextInt());
            setSizeY(scanner.nextInt());

            mode = scanner.nextInt();

            organisms = new Organism[sizeY][sizeX];
            char name;
            int x, y, age, strength, initiative;
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    name = scanner.next().charAt(0);
                    age = scanner.nextInt();
                    strength = scanner.nextInt();
                    initiative = scanner.nextInt();
                    x = scanner.nextInt();
                    y = scanner.nextInt();

                    switch (name) {
                        case '_':
                            organisms[i][j] = new Organism('_', -1, -1, x, y, this);
                            break;
                        case 'w':
                            organisms[i][j] = new Animal('w', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 's':
                            organisms[i][j] = new Animal('s', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 'f':
                            organisms[i][j] = new Fox('f', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 't':
                            organisms[i][j] = new Turtle('t', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 'a':
                            organisms[i][j] = new Antelope('a', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 'c':
                            organisms[i][j] = new CyberSheep('c', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case '!':
                            organisms[i][j] = new Plant('!', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case '@':
                            organisms[i][j] = new SowThistle('@', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case '#':
                            organisms[i][j] = new Guarana('#', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case '$':
                            organisms[i][j] = new Belladonna('$', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case '%':
                            organisms[i][j] = new Hogweed('%', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                        case 'h':
                            organisms[i][j] = new Human('h', strength, initiative, x, y, this);
                            organisms[i][j].setAge(age);
                            break;
                    }
                }
            }
            //close file
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return mode;
    }
}