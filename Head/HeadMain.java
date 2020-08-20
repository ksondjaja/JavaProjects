import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.*;
import java.awt.GridLayout;
/**
 * Write a description of class HeadMain here.
 *
 * @author Kumalasari Sondjaja
 * @version March 13, 2020
 */
public class HeadMain
{
    public static void main(String[] args){
        JFrame f = new JFrame("Head");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.add(new Head());
        f.add(new Head());
        f.add(new Head());
        f.add(new Head());
        f.setLayout(new GridLayout(2,2));
        f.setSize(1000,1000);
        f.setVisible(true);
    }
}
