import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.*;

/**
 * Write a description of class Head here.
 *
 * @author Kumalasari Sondjaja
 * @version March 12, 2020
 */
public class Head extends JPanel{
    private boolean mouseIn;    
    
    public Head(){
        this.setPreferredSize(new Dimension(500,500));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));                
        
        class MyMouseListener implements MouseListener{            
            public void mouseEntered(MouseEvent e){
                mouseIn = true;
                //System.out.println("mouse out");
                repaint();
            }
            
            public void mouseExited(MouseEvent e){
                mouseIn = false;
                //System.out.println("mouse out");
                repaint();
            }
        
            public void mouseReleased(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseClicked(MouseEvent e){}
        }
        
        this.addMouseListener(new MyMouseListener());
    }
    
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
              
        g2.setStroke(new BasicStroke(3.0f));
        g2.setPaint(Color.BLACK);
        g2.draw(new Ellipse2D.Double(70,20,360,450));
        
        g2.setPaint(Color.RED);
        g2.fill(new Arc2D.Double(173, 305, 160, 100, 220, 100, Arc2D.OPEN));
        g2.fill(new Arc2D.Double(173, 365, 160, 100, 40, 100, Arc2D.OPEN));
        
        g2.setPaint(Color.BLACK);
        g2.fill(new Ellipse2D.Double(240, 330, 5, 5));
        g2.fill(new Ellipse2D.Double(260, 330, 5, 5));
        
        if(mouseIn){
            g2.setStroke(new BasicStroke(2.0f));
            g2.setPaint(Color.BLACK);
            g2.draw(new Ellipse2D.Double(120, 220, 90, 60));
            g2.draw(new Ellipse2D.Double(290, 220, 90, 60));
        }
        else{
            g2.setStroke(new BasicStroke(2.0f));
            g2.setPaint(Color.BLACK);
            g2.draw(new Arc2D.Double(120, 190, 90, 90, 190, 155, Arc2D.OPEN));
            g2.draw(new Arc2D.Double(290, 190, 90, 90, 195, 155, Arc2D.OPEN));
        }
        
        g2.setStroke(new BasicStroke(2.5f));
        g2.rotate(Math.toRadians(350));
        g2.draw(new Arc2D.Double(0, 250, 70, 120, 102, 163, Arc2D.OPEN));
        
        g2.rotate(Math.toRadians(15));
        g2.draw(new Arc2D.Double(405, 198, 65, 120, 265, 163, Arc2D.OPEN));
        
        g2.rotate(Math.toRadians(355));
        
    }    
    
}
