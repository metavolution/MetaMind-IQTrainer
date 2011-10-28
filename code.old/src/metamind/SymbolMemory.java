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
public class SymbolMemory extends TrainingType {
    
        public SymbolMemory(int p_rounds, int p_complex, Frame frame1, BufferStrategy buf1) {
        super(frame1,buf1);            
        System.out.println("SymbolMemory called");            
        title = "Improve your Symbol Memory";
        info = "remember and recognize correct pattern";
        
        rounds = p_rounds;
        complexity = p_complex;
        
            
        Random rand2 = new Random();
        
        
        Color color1 = Color.RED;
        Color color2 = new Color(20,20,20);
        
        
        try {
//------Draw loop
        int delay=4000;int trial = 0;int savetime;int showtime=0;int trials=7;
        int round=0;char[] sarray=null;char[] marray;int slen;int mlen;
        while(round<rounds) {
            slen = (int)(complexity+round);
            
            Graphics g = buf.getDrawGraphics();
            paintInfo();
            buf.show();Thread.sleep(500);
            
            g.setColor(Color.white);
            g.setFont(font3);

            if(trial == 0) {
                savetime=4000;showtime=2000;
                sarray = new char[slen];    
                for(int i=0;i<slen;i++) {
                    sarray[i] = RainLib.getRandomSymbol();
                }

                String original = new String(sarray);
                int w = RainLib.CenterString(original, midx, midy-40, g);
                g.setColor(Color.blue);
                g.drawOval(midx-200, midy-170, 400, 340);
                
                buf.show();
                Thread.sleep(savetime);
                savetime -= 200;
                trial = 1;
                
            } else {
                //possibly mutate array
                mlen = slen;
                marray = new char[mlen];
                System.arraycopy(sarray,0,marray,0,mlen);
                if(rand.nextBoolean()) {
                    //replace one symbol
                    int pos = rand.nextInt(slen);
                    marray[pos] = RainLib.getRandomSymbol();
                }
                
                String modstr = new String(marray);
                g.setColor(Color.lightGray);
                RainLib.CenterString(modstr, midx, midy+40, g);

                buf.show();
                Thread.sleep(showtime);
                showtime -= 100;
                trial++;
                
            }
            
            if(trial > trials) {
                System.out.println("round done");
                round++;
                trial=0;
            }
                
            
            //g.dispose();
            //buf.show();
            
        }
System.out.println("end");
            } catch (Throwable e) {
        // Process exception...
        } 
    
}
}
