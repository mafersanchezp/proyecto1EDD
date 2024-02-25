/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoedd;

import EDD.GraphP;
import EDD.List;
import Interfaces.Load;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Mafer
 */
public class ProyectoEDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Load load = new Load();
//        load.setVisible(true);
        GraphP graph = new GraphP();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        
        graph.addEdge(1, 2, 20);
        graph.addEdge(1, 2, 10);
        
        graph.print();
        



        

        
    }
    
}
