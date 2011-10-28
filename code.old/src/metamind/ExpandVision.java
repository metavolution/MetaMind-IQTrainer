/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metamind;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.lang.Object.*;
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author rain
 */
public class ExpandVision extends TrainingType{
    
    public ExpandVision(int rounds1,Frame frame1,BufferStrategy buf1) {
        super(frame1,buf1);      
        rounds = rounds1;
        System.out.println("ExpandVision called");
        title = "Expand your Visual Perception Area";
        info = "focus gaze on center and calculate sum of numbers";
            
        try {
//------Draw loop
        int i=0,w=30,h=30,freq=440;
        int range=20,delay=250;
        String numstr;
        String wordlist[] = {"relaxing","breathing","focusing","widening","brain","Gehirn","energy","power"};
        int wordnum = 8;
        Random rand = new Random();
        for(int round=0;round<rounds+20;round++) {
    
            Graphics g = buf.getDrawGraphics();
            paintInfo();
            
            //String imageurl = "pentagram2.gif";
            URL imageurl = this.getClass().getResource("pentagram2.gif");
            
            //Image image = Toolkit.getDefaultToolkit().getImage(imageurl);
            Image image = new ImageIcon(imageurl).getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            ImageObserver obs = null;

            g.drawImage(image, midx-width/2, midy-height/2, obs);
            
            g.setColor(Color.blue);
            g.drawOval(midx-w/2-1, midy-h/2, w, h);
            
            w+=5;
            h+=5;
            
            g.setFont(font1);
            
            FontMetrics fontMetrics = g.getFontMetrics(); Rectangle2D area;
            
            if(round > 20) {
                delay = 6000-(round-21)*500;
                
                g.setColor(Color.red);
                g.fillOval(midx-1, midy-1, 3, 3);
                
                g.setColor(Color.white);
                
                numstr = Integer.toString(rand.nextInt(range)-range/2);
                area = fontMetrics.getStringBounds(numstr, g);
                g.drawString(numstr, (int)(midx-w/2-5-area.getWidth()/2), (int)(midy-h/2-2));
                
                numstr = Integer.toString(rand.nextInt(range)-range/2);
                area = fontMetrics.getStringBounds(numstr, g);
                g.drawString(numstr, (int)(midx+w/2+5-area.getWidth()/2), (int)(midy-h/2-2));
                
                numstr = Integer.toString(rand.nextInt(range)-range/2);
                area = fontMetrics.getStringBounds(numstr, g);
                g.drawString(numstr, (int)(midx-w/2-5-area.getWidth()/2), (int)(midy+h/2+18));
                
                numstr = Integer.toString(rand.nextInt(range)-range/2);
                area = fontMetrics.getStringBounds(numstr, g);
                g.drawString(numstr, (int)(midx+w/2+5-area.getWidth()/2), (int)(midy+h/2+18));
            }


            g.dispose();
            buf.show();
            
            Thread.sleep(delay);
            
        }
        } catch (Throwable e) {
        // Process exception...
        } 
    
    }

}
