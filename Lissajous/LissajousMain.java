import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.*;
import java.awt.GridLayout;
/**
 * Write a description of class LissajousMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LissajousMain
{
    public static void main(String[] args){
        JFrame f = new JFrame("Lissajous");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(new Lissajous(500));
        f.pack();
        f.setVisible(true);
    }
}
