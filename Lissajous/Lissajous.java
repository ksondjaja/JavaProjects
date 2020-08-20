import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.*;

/**
 * Write a description of class Lissajous here.
 *
 * @author Kumalasari Sondjaja
 * @version April 2, 2020
 */
public class Lissajous extends JPanel
{
    private static double a = 2;
    private static double b = 3;
    private static double delta = 0.5;
    private static double x;
    private static double y;
    private double size;
    
    public Lissajous(int size){
        this.size = size;
        
        this.setPreferredSize(new Dimension(size,size));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        JLabel alab = new JLabel("a:");
        JTextField af = new JTextField(Integer.toString((int)a));
        af.setColumns(3);
        
        JLabel blab = new JLabel("b:");
        JTextField bf = new JTextField(Integer.toString((int)b));
        bf.setColumns(3);
        
        JLabel dlab = new JLabel("delta:");
        JTextField df = new JTextField(Double.toString(delta));
        df.setColumns(3);
        
        class ValueActionListener implements ActionListener{
            public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==af){
                    String aval = af.getText();  
                    Lissajous.a = Double.parseDouble(aval);
                }
                if(ae.getSource()==bf){
                    String bval = bf.getText();
                    Lissajous.b = Double.parseDouble(bval);
                }
                if(ae.getSource()==df){
                    String dval = df.getText();
                    Lissajous.delta = Double.parseDouble(dval);
                }
                repaint();
            }
        }
                
        this.add(alab);
        af.addActionListener(new ValueActionListener());
        this.add(af);
                
        this.add(blab);
        bf.addActionListener(new ValueActionListener());
        this.add(bf);
        
        this.add(dlab);
        df.addActionListener(new ValueActionListener());
        this.add(df);
        
    }
    
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Path2D.Double path = new Path2D.Double();
        for(double t=0; t<(a+b)*Math.PI; t=t+0.005){
            x = size/2 + (2*size/5) * Math.sin(a*t+delta);
            y = size/2 + (2*size/5) * Math.cos(b*t);
                        
            if(t==0){path.moveTo(x,y);}
            else{path.lineTo(x,y);}
            //System.out.println("("+x+","+y+")");
        }
        Area area = new Area(path);
        g2.setStroke(new BasicStroke(2.7f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2.setPaint(Color.BLACK);
        g2.draw(path);
    }
}