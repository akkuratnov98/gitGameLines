/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverlines;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerLines {
    
    
    
    //private List<RoomPlayers> playersList = new ArrayList();
 
    
    private void RunSerwer() throws IOException {
        
        ServerSocket server = new ServerSocket(2000);
        System.out.println("Server Run, PORT:2000");
        while (true) { 
            int index = 0;
            List<ClientThread> clients =new ArrayList<ClientThread>();
            StringBuffer userList  = new StringBuffer();
            Socket socket1 = server.accept();
            System.out.println("Client1 accepted");
            Socket socket2 = server.accept();
            System.out.println("Client2 accepted");
            ClientThread clientThread1 = new ClientThread(socket1, clients, index, userList, this);
            index++;
            ClientThread clientThread2 = new ClientThread(socket2, clients, index, userList, this);
            clientThread1.start();
            clientThread2.start();
            clients.add(clientThread1);
            clients.add(clientThread2);
        }

    }
    public static void main(String[] args) {
        try {
            new ServerLines().RunSerwer();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
    
}
