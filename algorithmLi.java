/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelines;

import java.awt.List;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Struct;
import java.util.ArrayList;
import static java.util.Collections.list;
//import gamelines.oneCell;


public class algorithmLi {
    
        public class dot
        {
            public int x;
            public int y;

        }        
        
        ArrayList<ArrayList> ListList= new ArrayList<ArrayList>();
        
        public boolean findWay(int i, int j, int oldI, int oldJ, oneCell[][] masCell)
        {
            masCell[oldI][oldJ].pathLength = 0;
            int d = 1;
            int i1 = oldI;
            int j1 = oldJ;
            int count = 1;
            ArrayList<oneCell> dotList1 = new ArrayList<oneCell>();
            dotList1.add(masCell[oldI][oldJ]);
            ListList.add(dotList1);                      
            while (!ListList.get(count-1).isEmpty())
            {                
                ArrayList<oneCell> dotList = new ArrayList<oneCell>();
                for (int k = 0; k < ListList.get(count-1).size(); k++)
                {
                    oneCell c = (oneCell) ListList.get(count-1).get(k);                    
                    addPathLength(masCell, c.col, c.row, d, dotList);                    
                }
                
                for (int k = 0; k < ListList.get(count-1).size(); k++)
                {
                    oneCell c = (oneCell) ListList.get(count-1).get(k);
                    if (c.col == i && c.row == j)
                    {
                        for(oneCell[] c1 : masCell)
                        {
                            for(oneCell c2: c1)
                            {
                                c2.pathLength = -1;
                            }
                        }
                        return true;
                    }
                }                
                d++;
                ListList.add(dotList);
                count++;
            }                     
            for(oneCell[] c : masCell)
            {
                for(oneCell c1: c)
                {
                    c1.pathLength = -1;
                }
            }
            return false;
        }
        
        public void addPathLength(oneCell[][] masCell,int i1,int j1, int d, ArrayList<oneCell> dotList)
        {
            //Вниз
            if(i1+1>-1 && i1+1<9 && j1>-1 && j1<9)
                if(masCell[i1+1][j1].pathLength == -1 && masCell[i1+1][j1].numberIMG == 0)
                {
                    masCell[i1+1][j1].pathLength = d;
                    dotList.add(masCell[i1+1][j1]);
                }
            //Вверх
            if(i1-1>-1 && i1-1<9 && j1>-1 && j1<9) 
                if (masCell[i1-1][j1].pathLength == -1 && masCell[i1-1][j1].numberIMG == 0)
                {
                        masCell[i1-1][j1].pathLength = d;
                        dotList.add(masCell[i1-1][j1]);
                }
            //Вправо
            if(i1>-1 && i1<9 && j1+1>-1 && j1+1<9 ) 
                if (masCell[i1][j1+1].pathLength == -1 && masCell[i1][j1+1].numberIMG == 0)
                {
                    masCell[i1][j1+1].pathLength = d;
                    dotList.add(masCell[i1][j1+1]);
                }
            //Влево
            if(i1>-1 && i1<9 && j1-1>-1 && j1-1<9) 
                if (masCell[i1][j1-1].pathLength == -1 && masCell[i1][j1-1].numberIMG == 0)
                {
                    masCell[i1][j1-1].pathLength = d;
                    dotList.add(masCell[i1][j1-1]);
                }           
        }
}
