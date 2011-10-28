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
/**
 *
 * @author rain
 */
public class TrainingManager implements Runnable, KeyListener
{
   public Frame frame;
   public GraphicsDevice gd;
   public BufferStrategy buf;
   
   public Thread animation;
   
   int x = 0;
   
   public static void main(String[] Args)
   {
      //fullscreentest test = new fullscreentest();
   }

   TrainingManager()
   {
      animation = new Thread(this);
      gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      frame = new Frame(gd.getDefaultConfiguration());
      frame.setUndecorated(true);
      frame.setIgnoreRepaint(true);
      frame.setResizable(false);
      frame.addKeyListener(this);
      gd.setFullScreenWindow(frame);
      
      frame.createBufferStrategy(2);
      buf = frame.getBufferStrategy();
            
      animation.start();
   }
   
   public void run()
   {
      Thread t = Thread.currentThread();
///*
      new ExpandVision(13,frame,buf);
      RainLib.Sleep(2000);

      new MorphMatrix(30,2,frame,buf);
      RainLib.Sleep(2000);
//*/
      new SymbolMemory(8,4,frame,buf);
      RainLib.Sleep(2000);
      
      
      System.exit(0);

   }
   
   public void paintFrame(Graphics2D g)
   {
      g.setPaint(Color.BLACK);
      g.fillRect(0,0,1024,768);
      g.setPaint(Color.WHITE);
      g.drawString("hallo welt",200+x,200);
      
   }
   
   public void keyPressed(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
      {
         animation = null;
         System.out.println("bye");
         System.exit(0);
      }
   }
   
   public void keyReleased(KeyEvent e)
   {
   }
   
   public void keyTyped(KeyEvent e)
   {
   }
}