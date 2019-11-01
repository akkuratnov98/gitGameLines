/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelines;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class ClientReader extends Thread{
    private Socket socket;
    private InputStream in;
    private OutputStream out;    
    oneCell[][] masCell;
    oneCell c = new oneCell();
    JLabel score;
    JOptionPane jOptionPane = null;
    
     public ClientReader(Socket socket, InputStream in,  OutputStream out, oneCell[][] masCell, JLabel score){
         this.socket = socket;
         this.in = in;
         this.out = out;
         this.masCell = masCell;
         this.score = score;
     }
     
      @Override
    public void run() {
        StringBuffer buf;
        StringBuffer buf2;
        StringBuffer buf3;
        StringBuffer buf4;
        StringBuffer buf5;
        int k;

        StringBuffer sb = new StringBuffer();
        while (isInterrupted() == false) {
            try {

                while ((k = in.read()) != -1 && k != ':') {
                    sb.append((char) k);
                }

                switch (sb.toString()) {
//----------------------------------------------------------------------------------------------------------------------------------------- 
                    case "!ENEMYSTRIKE ":          
                        c.fill(3, masCell);
                        sb = new StringBuffer();
                        break;
                        
//                    case "!NewGame ":
//                        c.setNewGame(masCell, score);
//                        sb = new StringBuffer();
//                        break;
                        
                    case "!EnemyNewGame ":
                        c.setNewGame(masCell, score);
                        out.write(("!MyScore :" +score.getText() + "$").getBytes());
//                        sb = new StringBuffer();
                        break;
                        
                    case "!Win ":
                        jOptionPane.showMessageDialog(null, "U WIn");
                        wait(100);
                        jOptionPane = null;                        
                        break;
                        
                    case "!Defeat ":
                        jOptionPane.showMessageDialog(null, "U Defeat");
                        wait(100);
                        jOptionPane = null;
                        break;
                    case "!AutoWin ":
                            in.close();
                            out.close();
                            socket.close();
                            socket = new Socket("127.0.0.1", 2000);
                            in = socket.getInputStream();
                            out = socket.getOutputStream();
                            jOptionPane.showMessageDialog(null, "AutoWIn");
                            wait(100);
                            jOptionPane = null; 
                         
                            break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

