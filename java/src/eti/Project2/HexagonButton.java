package eti.Project2;

import javax.swing.*;
import java.awt.*;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/6/2 8:52
 * Description: used to create hexagon grid
 */

public class HexagonButton extends JButton {
    //private static final long serialVersionUID = 1L;
    private static final int SIDES = 6;
    private static final int SIDE_LENGTH = 50;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBorderPainted(false);
        setContentAreaFilled(false);
        Polygon hex = new Polygon();
        for (int i = 0; i < SIDES; i++) {
            hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                    (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
        }
        g.drawPolygon(hex);
        //g.setColor(Color.white);
        //g.fillPolygon(hex);
    }
}
