/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverlines;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class ClientThread extends Thread {
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private String nick;
    boolean nickCheck = false;
    private List<ClientThread> threadList;
    private int index;
    private StringBuffer userList;
    //private List<RoomPlayers> playerList;
    //private Game game;
    private ServerLines server;
    int score;
    
    public ClientThread(Socket socket, List threadList, int index, StringBuffer userList, ServerLines server) throws IOException {
        this.socket = socket;
        this.index = index;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.threadList = threadList;
        this.userList = userList;
        //this.playerList = playerList;
        this.server = server;
    }
    
    @Override
    public void run() {
        try {
             while (isInterrupted() == false) {
                int k;
                StringBuffer sb = new StringBuffer();
                StringBuffer buf;
                
                while ((k = in.read()) != -1 && k != ':') {
                    sb.append((char) k);
                }
                
                switch (sb.toString()) {
//-----------------------------------------------------------------------------------------------------------------------------------------                     
                    case "!blowUp ":        
                        SendToEveryoneExceptMe(("!ENEMYSTRIKE :").getBytes());
                        break;   
                        
                    case "!NewGame ": 
                        buf = new StringBuffer();
                        while ((k = in.read()) != -1 && k != '$') 
                        {
                            buf.append((char) k);
                        }
                        score = Integer.parseInt(buf.toString());
                        SendToEveryoneExceptMe(("!EnemyNewGame :").getBytes());
                        break;
                        
                    case "!MyScore ":
                        buf = new StringBuffer();
                        while ((k = in.read()) != -1 && k != '$') 
                        {
                            buf.append((char) k);
                        }
                        if(score > Integer.parseInt(buf.toString()) )
                        {
                            SendToEveryoneExceptMe(("!Win :").getBytes());
                            SendToMe(("!Defeat :").getBytes());                            
                        } else 
                        {
                            SendToEveryoneExceptMe(("!Defeat :").getBytes());
                            SendToMe(("!Win :").getBytes()); 
                        }
                        score = 0;
                        
                        break;    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                SendToEveryoneExceptMe(("!AutoWin :").getBytes());
                in.close();
                out.close();
                socket.close();
                threadList.remove(index);
                this.interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void send(byte[] data) {
        try {
            out.write(data);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void SendToEveryoneExceptMe(byte[] data) {
        for (int i = 0, n = threadList.size(); i < n; i++) {
            if (i != this.index) {
                threadList.get(i).send(data);
            }
        }
    }
    
    public void SendToMe(byte[] data) {
        for (int i = 0, n = threadList.size(); i < n; i++) {
            if (i == this.index) {
                threadList.get(i).send(data);
            }
        }
    }
    
}
