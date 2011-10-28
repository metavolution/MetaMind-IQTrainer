/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metamindiqtrain.trainings.morphmatrix;

import java.util.*;
import java.awt.Color;
import java.lang.Math;

import rainlib.GfxWrapper;



public class RectCluster {
    
    protected int[][] matrix;
    protected Random rand1 = new Random();
    
    protected int xPos;
    protected int yPos;
    
    protected int size;
    protected Color color1;
	protected Color color2;
    
    protected static int shapex = 20;
    protected static int shapey = 20;
    
    protected int width;
    protected int height;

    public RectCluster(int x, int y, int size_p, Color color1_p, Color color2_p) {
        xPos = x;
        yPos = y;
        size = size_p;
        color1 = color1_p;
        color2 = color2_p;
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
    

    public void paintCluster(GfxWrapper rgfx){

        
        //paint matrix
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                switch(matrix[i][j]) {
                    case 0:
                        rgfx.setColor(color1);
                        rgfx.drawRect(xPos+1+shapex*i,yPos+1+shapey*j,shapex,shapey);
                    break;
                    case 1:
                        rgfx.setColor(color2);
                        rgfx.drawRect(xPos+1+shapex*i,yPos+1+shapey*j,shapex,shapey);
                    break;    
                }
            }
        }
        
        
    }
}
