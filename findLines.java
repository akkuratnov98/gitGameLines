/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelines;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import java.io.OutputStream;

public class findLines {
    
    public void findLines (oneCell[][] masCell, JLabel labelScore, OutputStream out)
    {
        oneCell cell = new oneCell();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++)
            {
                if(masCell[i][j].numberIMG == masCell[i+1][j].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+2][j].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+3][j].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+4][j].numberIMG 
                        && masCell[i][j].numberIMG !=0)
                {
                    String leter = "!blowUp :";
                    try {
                        out.write(leter.getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(findLines.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int k =0; k < 5; k++)
                    {
                        masCell[i+k][j].numberIMG = 0;
                        cell.setEmpty(masCell[i+k][j]);
                        int score = Integer.parseInt(labelScore.getText()) + 1;
                        labelScore.setText(String.valueOf(score));
                    } 
                }
            }
        
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++)
            {
                if(masCell[i][j].numberIMG == masCell[i][j+1].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i][j+2].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i][j+3].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i][j+4].numberIMG
                        && masCell[i][j].numberIMG !=0)
                {
                    String leter = "!blowUp :";
                    try {
                        out.write(leter.getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(findLines.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int k =0; k < 5; k++)
                    {
                        masCell[i][j+k].numberIMG = 0;
                        cell.setEmpty(masCell[i][j+k]);
                        int score = Integer.parseInt(labelScore.getText()) + 1;
                        labelScore.setText(String.valueOf(score));
                    } 
                }
            }
        for (int i = 0; i < 5; i++)
            for (int j = 0; j<5; j++)
            {
                if(masCell[i][j].numberIMG == masCell[i+1][j+1].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+2][j+2].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+3][j+3].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+4][j+4].numberIMG
                        && masCell[i][j].numberIMG !=0)
                {
                    String leter = "!blowUp :";
                    try {
                        out.write(leter.getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(findLines.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int k =0; k < 5; k++)
                    {
                        masCell[i+k][j+k].numberIMG = 0;
                        cell.setEmpty(masCell[i+k][j+k]);
                        int score = Integer.parseInt(labelScore.getText()) + 1;
                        labelScore.setText(String.valueOf(score));
                    } 
                }
            }
            for (int i = 0; i < 5; i++)
                for (int j = 5; j<9; j++)
                {
                    if(masCell[i][j].numberIMG == masCell[i+1][j-1].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+2][j-2].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+3][j-3].numberIMG 
                        && masCell[i][j].numberIMG == masCell[i+4][j-4].numberIMG
                        && masCell[i][j].numberIMG !=0)
                    {
                    String leter = "!blowUp :";
                        try {
                            out.write(leter.getBytes());
                        } catch (IOException ex) {
                            Logger.getLogger(findLines.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int k =0; k < 5; k++)
                        {
                            masCell[i+k][j-k].numberIMG = 0;
                            cell.setEmpty(masCell[i+k][j-k]);
                            int score = Integer.parseInt(labelScore.getText()) + 1;
                            labelScore.setText(String.valueOf(score));
                        } 
                }
            }
    }
    
    public boolean endgame(oneCell[][] masCell)
    {
        for (oneCell[] cells : masCell)
            for (oneCell cell : cells)
            {
                if (cell.numberIMG == 0)
                    return false;
            }
        return true;    
    }
}
