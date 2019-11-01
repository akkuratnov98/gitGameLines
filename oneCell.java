/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelines;

import java.io.IOException;
import java.util.Random;
import javax.swing.JLabel;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class oneCell {
        JLabel jLabel = new JLabel();
        int numberIMG = 0;
        int pathLength = -1;
        int col, row;
        String[] s = {"/gamelines/empty.png","/gamelines/red.png","/gamelines/green.png","/gamelines/yellow.png", "/gamelines/blue.png"};
        
        public void fill (int count,  oneCell[][] masCell)
        {
            int k = 0;
            Random rand = new Random();
            int i, j;
            while ( k != count)
            {
                i = rand.nextInt(9);
                j = rand.nextInt(9);
                if (masCell[i][j].numberIMG == 0)
                {
                    k++;
                    masCell[i][j].numberIMG = rand.nextInt(4) + 1;
                    masCell[i][j].jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(s[masCell[i][j].numberIMG])));
                }               
            }
        }
        
        public void setEmpty(oneCell cell)
        {
            cell.jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(s[0])));
            cell.numberIMG = 0;            
        }
        public void setColor(oneCell cell, int color)
        {
            cell.jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(s[color])));
            cell.numberIMG = color;
        }      
        
        public void setNewGame(oneCell[][] masCell, JLabel score)
        {
            for (oneCell[] cells : masCell)                    
                {
                    oneCell c = new oneCell();
                    for (oneCell cell : cells) 
                    {                        
                        setEmpty(cell);                        
                    }
                }
            score.setText("0");                
            fill(5, masCell);

        }
        
}
