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
public class MorphMatrix extends TrainingType {
    
        public MorphMatrix(int p_rounds, int p_complex, Frame frame1, BufferStrategy buf1) {
        super(frame1,buf1);            
        System.out.println("MorphMatrix called");            
        title = "Morph and compare the Matrix in your Mind";
        info = "mentally rotate left pattern and compare with right image";
        
        rounds = p_rounds;
        complexity = p_complex;
        
            
        Random rand2 = new Random();
        int csize = 3;
        Color color1 = Color.RED;
        Color color2 = new Color(20,20,20);
        
        try {
//------Draw loop
        int delay=4000;
        for(int round=0;round<rounds;round++) {
    
            if(round >= 3) csize = 4;
            if(round >= 20) csize = 5;
                
            int width = csize*RectCluster.shapex+1;
            int height = csize*RectCluster.shapey+1;
            int yPos = midy-height/2;        

            Graphics g = buf.getDrawGraphics();
            paintInfo();
            
            RectCluster matrix1 = new RectCluster(midx-(int)(width*1.2),yPos,csize,color1,color2);
            RectCluster matrix2 = new RectCluster(midx+(int)(width*1.2/2),yPos,csize,color1,color2);                
            
            matrix1.generateMatrix();
            matrix2.morphMatrix(matrix1.getArray(), rand2.nextInt(3)+1);  
            
            if(rand.nextBoolean())
                matrix2.mutateMatrix();
            
            matrix1.paintCluster(g);
            matrix2.paintCluster(g);
            
            g.dispose();
            buf.show();
            
            Thread.sleep(delay);
            delay -= 100;
        }

            } catch (Throwable e) {
        // Process exception...
        } 
    
}
}
