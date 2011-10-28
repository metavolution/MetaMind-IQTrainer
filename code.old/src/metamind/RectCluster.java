/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metamind;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math;


class RectCluster {
    
    private int[][] matrix;
    Random rand1 = new Random();
    
    private int xPos;
    private int yPos;
    
    private int size;
    private Color color1, color2;
    
    public static int shapex = 20;
    public static int shapey = 20;
    
    private int width;
    private int height;

    RectCluster(int x, int y, int p_size, Color p_color1, Color p_color2) {
        xPos = x;
        yPos = y;
        size = p_size;
        color1 = p_color1;
        color2 = p_color2;
        width = size*shapex+1;
        height = size*shapey+1;
        generateMatrix(); 
    }

   
    public int[][] getArray() {
        return matrix;
    }
    
    public void morphMatrix(int[][] sourcematrix,int morph) {
        switch(morph) {
            case 1:
                for(int i=0;i<size;i++) {
                    for(int j=0;j<size;j++) {
                        matrix[i][j] = sourcematrix[j][size-1-i];
                    }
                }
            break;
            case 2:
                for(int i=0;i<size;i++) {
                    for(int j=0;j<size;j++) {
                        matrix[i][j] = sourcematrix[size-1-i][size-1-j];
                    }
                }
            break;
            case 3:
                for(int i=0;i<size;i++) {
                    for(int j=0;j<size;j++) {
                        matrix[i][j] = sourcematrix[size-1-j][i];
                    }
                }
            break;
            
        }
    }

    void generateMatrix() {
        
        matrix = new int[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                matrix[i][j] = rand1.nextInt(2);
            }
        }

    }
    
    void mutateMatrix() {
        Random rand1 = new Random(new Date().getTime());
        Random rand2 = new Random(new Date().getTime());
        
        int r1 = rand1.nextInt(size);
        int r2 = rand2.nextInt(size);
        
        matrix[r1][r2] = Math.abs(matrix[r1][r2]-1);
    }
    

    public void paintCluster(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,height);  
        
        //paint matrix
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                switch(matrix[i][j]) {
                    case 0:
                        g.setColor(color1);
                        g.fillRect(xPos+1+shapex*i,yPos+1+shapey*j,shapex,shapey);
                    break;
                    case 1:
                        g.setColor(color2);
                        g.fillRect(xPos+1+shapex*i,yPos+1+shapey*j,shapex,shapey);
                    break;    
                }
            }
        }
        
        
    }
}
