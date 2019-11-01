/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelines;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public  class GameLines {
    
    static oldAndNewJLabel oldAndNew = new oldAndNewJLabel();
    static oneCell[][] masCell;
    
    public static void main(String[] args) {
        
            
        Socket socket;
        InputStream in;
        OutputStream out;
        try {
        socket = new Socket("127.0.0.1", 2000);
        in = socket.getInputStream();
        out = socket.getOutputStream();
        gameBoard board = new gameBoard(out);
        System.out.println("Connect");        

        
        JButton buttonNewGame =  board.getNewGame();
        buttonNewGame.addActionListener((ActionEvent ae) -> {            
                oneCell onecellClass = new oneCell();
                
                String message = "!NewGame :" + board.getScore().getText() + "$";
                try {
                    out.write(message.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(oneCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                onecellClass.setNewGame(masCell, board.getScore());  
             });
        
        masCell = (oneCell[][]) board.createCell( (int i, int j) -> {
            if(oldAndNew.first && masCell[i][j].numberIMG == 0)
            {
                return;
            }
                
            if (!oldAndNew.first && i == oldAndNew.oldI && j == oldAndNew.oldJ)
            {
                oldAndNew.first = true;
                return;
            }
            if (!oldAndNew.first && masCell[i][j].numberIMG != 0)
            {
                oldAndNew.first = true;  
                oldAndNew.oldI = -1;
                oldAndNew.oldJ = -1;
                return;
            }          
            if (oldAndNew.first)
            {
                oldAndNew.oldJLabel = masCell[i][j].jLabel;
                oldAndNew.oldI = i;
                oldAndNew.oldJ = j;
                oldAndNew.first = false;
            } else             
            {
                algorithmLi li = new algorithmLi();
                if (li.findWay(i, j, oldAndNew.oldI, oldAndNew.oldJ, masCell))
                {                    
                    oneCell cell = new oneCell();
                    cell.setColor(masCell[i][j], masCell[oldAndNew.oldI][oldAndNew.oldJ].numberIMG);
                    cell.setEmpty(masCell[oldAndNew.oldI][oldAndNew.oldJ]);
                    oldAndNew.first = true;
                    oldAndNew.oldI = -1;
                    oldAndNew.oldJ = -1;
                    cell.fill(3, masCell);
                    findLines lines = new findLines();
                    JLabel labelScore = board.getScore();
                    lines.findLines(masCell, labelScore, out);
                    JLabel score = board.getScore();
                    if (lines.endgame(masCell)) 
                    {
                        
                        String message = "!NewGame :" + board.getScore().getText() + "$";
                        try {
                            out.write(message.getBytes());
                        } catch (IOException ex) {
                            Logger.getLogger(oneCell.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        cell.setNewGame(masCell, score);
                    }                    
                }
                else
                {
                    oldAndNew.first = true;
                    oldAndNew.oldI = -1;
                    oldAndNew.oldJ = -1;
                    return;
                }
            }               
        });
        oneCell cell = new oneCell();
        cell.fill(5, masCell);         
        board.setSize(board.getWidth(), board.getHeight() + 1);
        board.setVisible(true);
        ClientReader clientReader = new ClientReader(socket, in, out, masCell, board.getScore());
        clientReader.start();
        JPanel jPanel = board.getJpanel();   
            } catch (IOException ex) {
            Logger.getLogger(GameLines.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
