package eti.Project2;

import eti.Project2.Organisms.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/31 12:59
 * Description: GUI of the game
 */

public class GUI implements KeyListener {
    private static final JTextArea information = new JTextArea("Information:\n", 44, 30);
    private World world;
    private JFrame frame;
    private JPanel virtualWorldP;
    private JButton[][] gridButton;
    private HexagonButton[][] hexButton;
    private Organism[] actionSort;
    private Comparator cmp = new MyComparator();
    private int mode;

    //getters
    public static JTextArea getInformation() {
        return information;
    }

    public GUI() {
        {
            //frame
            frame = new JFrame("VirtualWorld    s188026    Yifei Liu");
            frame.setSize(1200, 800);
            frame.setLayout(new BorderLayout());
            //frame.addKeyListener(this);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //panel
            JPanel guideP = new JPanel();
            virtualWorldP = new JPanel();
            JPanel informationP = new JPanel();    //创建一个JPanel对象
            guideP.setBackground(Color.cyan);
            guideP.setFocusable(false);
            virtualWorldP.setBackground(Color.ORANGE);
            //virtualWorldP.setFocusable(true);
            virtualWorldP.addKeyListener(this);
            informationP.setBackground(Color.cyan);
            informationP.setFocusable(false);

            //guide text
            JTextArea guide = new JTextArea("s188026\nYifei Liu\n\n" +
                    "n          new game\n" +
                    "s          save game\n" +
                    "l          load game\n" +
                    "f          fill empty randomly\n" +
                    "ESC        exit\n" +
                    "Enter      next round\n\n" +

                    "Control human:\n" +
                    "h          human ability\n" +
                    "arrow      control human\n\n" +

                    "Tips for information:\n" +
                    " * WOLF        'w'\n" +
                    " * SHEEP       's'\n" +
                    " * FOX         'f'\n" +
                    " * TURTLE      't'\n" +
                    " * ANTELOPE    'a'\n" +
                    " * CYBERSHEEP  'c'\n" +
                    " * GRASS       '!'\n" +
                    " * SOWTHISTLE  '@'\n" +
                    " * GUARANA     '#'\n" +
                    " * BELLADONNA  '$'\n" +
                    " * HOGWEED     '%'\n" +
                    " * HUMAN       'h'\n" +
                    " * nullOrgan   '_'"
                    //"Click Orange Part to get focus\nwhen pressing keyboard is useless."
            );
            guideP.add(guide);
            guide.setEditable(false);
            guide.setForeground(Color.BLACK);
            guide.setBackground(Color.cyan);
            guide.setFont(new Font("Courier New", Font.BOLD, 14));
            guide.setFocusable(false);

            //information text
            informationP.add(information);
            information.setLineWrap(true);
            information.setEditable(false);
            information.setForeground(Color.BLACK);
            information.setBackground(Color.cyan);
            information.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            information.setFocusable(false);

            //scroll
            JScrollPane jsp = new JScrollPane(information);
            Dimension size = information.getPreferredSize();
            jsp.setBounds(110, 90, size.width, size.height);
            informationP.add(jsp);

            //display the frame
            frame.add(guideP, BorderLayout.WEST);
            frame.add(virtualWorldP, BorderLayout.CENTER);
            frame.add(informationP, BorderLayout.EAST);
            frame.setVisible(true);
            virtualWorldP.requestFocus();
        }
    }

    //Grid Buttons
    public void DrawGridButton() {
        int Y = world.getSizeY();
        int X = world.getSizeX();

        virtualWorldP.removeAll();
        virtualWorldP.setLayout(null);

        gridButton = new JButton[X][Y];

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                gridButton[i][j] = new JButton();

                //Dimension preferredSize=new Dimension(23, 23);
                //gridButton[i][j].setPreferredSize(preferredSize);

                gridButton[i][j].setBackground(Color.white);

                gridButton[i][j].setBounds(j * 45, i * 45, 43, 43);

                gridButton[i][j].addActionListener(new MyActionListener(j, i));

                //gridButton[i][j].setVisible(true);
                //gridButton[i][j].setEnabled(true);

                virtualWorldP.add(gridButton[i][j]);
            }
        }

        virtualWorldP.repaint();
        //virtualWorldP.validate();
    }

    public void DrawHexButton() {
        int Y = world.getSizeY();
        int X = world.getSizeX();

        virtualWorldP.removeAll();
        virtualWorldP.setLayout(null);

        hexButton = new HexagonButton[X][Y];

        int offsetX = 0;
        int offsetY = 0;

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                hexButton[j][i] = new HexagonButton();
                /*ImageIcon img = (new ImageIcon(Objects.requireNonNull(HexagonPattern.class.getResource("img/1.png"))));
                img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULST));
                hexButton[i][j].setIcon(img);*/
                hexButton[j][i].addActionListener(new MyActionListener(i, j));
                virtualWorldP.add(hexButton[j][i]);
                hexButton[j][i].setBounds(offsetY, offsetX, 105, 95);
                offsetX += 87;
            }
            if (i % 2 == 0) {
                offsetX = 40;
            } else {
                offsetX = 0;
            }
            offsetY += 76;
        }

        virtualWorldP.repaint();
    }

    //organism chooser
    public class OrganismChooser extends JFrame {
        JList jList;
        String[] organismList;

        public OrganismChooser(int x, int y) {
            super("Choose Organism");
            //setBounds(x, y, 150, 300);
            setSize(150, 300);
            organismList = new String[]{"antelope", "belladonna", "cybersheep", "fox", "grass", "guarana", "hogweed", "sheep", "sowthistle", "turtle", "wolf"};

            jList = new JList(organismList);
            jList.setVisibleRowCount(organismList.length);
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int selected = jList.getSelectedIndex();

                    switch (selected) {
                        case 0:
                            world.organisms[y][x] = new Antelope(x, y, world);
                            break;
                        case 1:
                            world.organisms[y][x] = new Belladonna(x, y, world);
                            break;
                        case 2:
                            world.organisms[y][x] = new CyberSheep(x, y, world);
                            break;
                        case 3:
                            world.organisms[y][x] = new Fox(x, y, world);
                            break;
                        case 4:
                            world.organisms[y][x] = new Plant('!', 0, 0, x, y, world);
                            break;
                        case 5:
                            world.organisms[y][x] = new Guarana(x, y, world);
                            break;
                        case 6:
                            world.organisms[y][x] = new Hogweed(x, y, world);
                            break;
                        case 7:
                            world.organisms[y][x] = new Animal('s', 4, 4, x, y, world);
                            break;
                        case 8:
                            world.organisms[y][x] = new SowThistle(x, y, world);
                            break;
                        case 9:
                            world.organisms[y][x] = new Turtle(x, y, world);
                            break;
                        case 10:
                            world.organisms[y][x] = new Animal('w', 9, 5, x, y, world);
                            break;
                    }

                    if (mode == 1)
                        world.organisms[y][x].Draw(hexButton[y][x]);
                    else
                        world.organisms[y][x].Draw(gridButton[y][x]);

                    virtualWorldP.requestFocus();
                    dispose();
                }
            });
            this.add(jList);
            this.setVisible(true);
        }
    }

    //Key Listener
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //System.out.println(keyCode);

        //control human
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            Human.setArrowKey(keyCode);

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    GUI.getInformation().append("\nHuman will move up next round\n");
                    break;
                case KeyEvent.VK_DOWN:
                    GUI.getInformation().append("\nHuman will move down next round\n");
                    break;
                case KeyEvent.VK_LEFT:
                    GUI.getInformation().append("\nHuman will move left next round\n");
                    break;
                case KeyEvent.VK_RIGHT:
                    GUI.getInformation().append("\nHuman will move right next round\n");
                    break;
            }
        }

        //human ability
        else if (keyCode == KeyEvent.VK_H) {
            if (Human.getAbilityRound() != 0) {

                /**std::cout + "You have started the human ability" + std::endl;*/
                GUI.getInformation().append("\nYou have activated the human ability\n");
                GUI.getInformation().append("remain " + Human.getAbilityRound() + " rounds\n\n");

            } else {
                Human.setAbilityRound(5);
                GUI.getInformation().append("\nActivate the human ability: purication\n");
            }
        }

        //Fill empty grid randomly(include null organism)
        else if (keyCode == KeyEvent.VK_F) {
            Random random = new Random();

            if (world != null)

                for (int i = 0; i < world.getSizeY(); i++) {
                    for (int j = 0; j < world.getSizeX(); j++) {

                        if (world.organisms[i][j].getName() == '_') {
                            int randOrgan = random.nextInt(100);
                            if (randOrgan < 10)
                                world.organisms[i][j] = new Organism('_', -1, -1, j, i, world);

                            else if (randOrgan < 20)
                                world.organisms[i][j] = new Animal('w', 9, 5, j, i, world);

                            else if (randOrgan < 33)
                                world.organisms[i][j] = new Animal('s', 4, 4, j, i, world);

                            else if (randOrgan < 45)
                                world.organisms[i][j] = new Fox(j, i, world);

                            else if (randOrgan < 55)
                                world.organisms[i][j] = new Turtle(j, i, world);

                            else if (randOrgan < 68)
                                world.organisms[i][j] = new Antelope(j, i, world);

                            else if (randOrgan < 78)
                                world.organisms[i][j] = new CyberSheep(j, i, world);

                            else if (randOrgan < 86)
                                world.organisms[i][j] = new Plant('!', 0, 0, j, i, world);

                            else if (randOrgan < 91)
                                world.organisms[i][j] = new SowThistle(j, i, world);

                            else if (randOrgan < 96)
                                world.organisms[i][j] = new Guarana(j, i, world);

                            else if (randOrgan < 98)
                                world.organisms[i][j] = new Belladonna(j, i, world);

                            else world.organisms[i][j] = new Hogweed(j, i, world);
                        }
                    }
                }

            Draw();
        }

        //New game
        else if (keyCode == KeyEvent.VK_N) {
            Human.setAbilityRound(0);

            JTextField xField = new JTextField(5);
            JTextField yField = new JTextField(5);
            JTextField modeField = new JTextField(5);
            xField.setText("10");
            yField.setText("10");
            modeField.setText("0");

            JPanel newGameP = new JPanel();

            newGameP.add(new JLabel("size x:"));
            newGameP.add(xField);
            newGameP.add(Box.createHorizontalStrut(15)); // a spacer
            newGameP.add(new JLabel("size y:"));
            newGameP.add(yField);
            newGameP.add(new JLabel("Mode(0,1)"));
            newGameP.add(modeField);

            int result = JOptionPane.showConfirmDialog(frame, newGameP, "Please Enter X Y Values and Mode(0,1)", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                int X = Integer.parseInt(xField.getText());

                int Y = Integer.parseInt(yField.getText());

                mode = Integer.parseInt(modeField.getText());

                world = new World(X, Y);

                actionSort = new Organism[Y * X];

                if (mode == 0) {
                    DrawGridButton();
                    if (X > 1 && Y > 1) {
                        world.organisms[Y / 2][X / 2] = new Human(X / 2, Y / 2, world);
                        world.organisms[Y / 2][X / 2].Draw(gridButton[Y / 2][X / 2]);
                    }
                } else if (mode == 1) {
                    DrawHexButton();
                    if (X > 1 && Y > 1) {
                        world.organisms[Y / 2][X / 2] = new Human(X / 2, Y / 2, world);
                        world.organisms[Y / 2][X / 2].Draw(hexButton[Y / 2][X / 2]);
                    }
                }
            }

            GUI.getInformation().setText("information:\n");
        }

        //save game
        else if (keyCode == KeyEvent.VK_S) {

            String fileName = JOptionPane.showInputDialog(frame, "Save Game to File:", "test");
            world.Save(fileName, mode);

        }

        //load game
        else if (keyCode == KeyEvent.VK_L) {

            String fileName = JOptionPane.showInputDialog(frame, "Load Game from File:", "test");
            world = new World();
            mode = world.Load(fileName);
            if (mode == 0)
                DrawGridButton();
            else if (mode == 1)
                DrawHexButton();
            actionSort = new Organism[world.getSizeX() * world.getSizeY()];

            Draw();
            GUI.getInformation().setText("information:\n");
        }

        //next round
        else if (keyCode == KeyEvent.VK_ENTER) {
            GUI.getInformation().setText("information:\n");
            if (world != null) {

                int sizeX = world.getSizeX();
                int sizeY = world.getSizeY();
                int actionX, actionY;

                //sort the organisms
                for (int i = 0; i < sizeY; i++) {
                    for (int j = 0; j < sizeX; j++) {
                        world.organisms[i][j].setAge(world.organisms[i][j].getAge() + 1);
                        if (world.organisms[i][j].getName() != '_') {
                            world.organisms[i][j].setActed(false);
                        }
                        actionSort[i * sizeX + j] = new Organism(world.organisms[i][j]);
                    }
                }

                Arrays.sort(actionSort, 0, sizeX * sizeY, cmp);

                //action as sequence
                for (int i = 0; i < sizeY; i++) {
                    for (int j = 0; j < sizeX; j++) {

                        if (actionSort[i * sizeX + j].getName() != '_') {
                            actionX = actionSort[i * sizeX + j].getPosition().getX();
                            actionY = actionSort[i * sizeX + j].getPosition().getY();

                            if (!world.organisms[actionY][actionX].getActed()) {
                                world.organisms[actionY][actionX].setActed(true);
                                world.organisms[actionY][actionX].Action();
                            }

                        }
                    }
                }
                Draw();
            }
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void Draw() {
        //draw grids
        for (int i = 0; i < world.getSizeY(); i++) {
            for (int j = 0; j < world.getSizeX(); j++) {
                if (mode == 1)
                    world.organisms[i][j].Draw(hexButton[i][j]);
                else
                    world.organisms[i][j].Draw(gridButton[i][j]);
            }
        }
    }

    private class MyComparator implements Comparator<Organism> {
        public int compare(Organism a, Organism b) {
            if (a.getInitiative() - b.getInitiative() != 0)
                return a.getInitiative() - b.getInitiative();
            else
                return a.getAge() - b.getAge();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class MyActionListener implements ActionListener {
        private int x;
        private int y;

        public MyActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton tempButton;
            if (mode == 1)
                tempButton = (HexagonButton) e.getSource();
            else
                tempButton = (JButton) e.getSource();
            OrganismChooser chooser = new OrganismChooser(x, y);
            chooser.setBounds(frame.getX() + virtualWorldP.getX() + tempButton.getX(),
                    frame.getY() + virtualWorldP.getY() + tempButton.getY(),
                    chooser.getWidth(), chooser.getHeight());
        }
    }
}

