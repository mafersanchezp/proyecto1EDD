/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import EDD.Edge;
import EDD.GraphP;
import EDD.List;
import EDD.Vertex;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;

/**
 *
 * @author Mafer
 */
public class Util {
    
    public static String filePath(){
        JFileChooser jf = new JFileChooser();
        String userHome = System.getProperty("user.home");
        jf.setCurrentDirectory(new File(userHome + "/Desktop"));
        
        int value = jf.showOpenDialog(null);
        
        if(value == JFileChooser.APPROVE_OPTION){
            return jf.getSelectedFile().getAbsolutePath();
        }
        else{
            return "";
        }
    }
    
    
    public static void save(GraphP graph, String filePath){
        try{
            File file = new File(filePath);

            FileWriter fw = new FileWriter(file);
            
            BufferedWriter bw = new BufferedWriter(fw);
            
            List<Vertex> allVertex = graph.getAllVertex();
            List<Edge> adyList = graph.getAdyList();
            
            bw.write("ciudad");
            bw.newLine();
            for (int i = 0; i < allVertex.getSize(); i++) {
                Vertex auxVertex = allVertex.get(i);
                int auxId = auxVertex.getId();
                
                bw.write(Integer.toString(auxId));
                bw.newLine();
            }
            
            bw.write("aristas");
            bw.newLine();
            for (int i = 0; i < adyList.getSize(); i++) {
                Edge auxEdge = adyList.get(i);
                String auxIdA = Integer.toString(auxEdge.getIdA());
                String auxIdB = Integer.toString(auxEdge.getIdB());
                String auxSize = Double.toString(auxEdge.getSize());
                bw.write(auxIdA + "," + auxIdB + "," + auxSize);
                bw.newLine();
            }
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }      
    }
    
    
    public static GraphP load(String filePath){
        try{
            GraphP newGraph = new GraphP();

            File file = new File(filePath);

            FileReader fr = new FileReader(file);
            
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while (!(line = br.readLine()).equals("aristas")) {
                if(!line.equals("ciudad")){
                    newGraph.addVertex(Integer.parseInt(line));                   
                }
            }
            
            while ((line = br.readLine()) != null) {
                if(!line.equals("aristas")){
                    String[] values = line.split(",");
                    int value1 = Integer.parseInt(values[0]);
                    int value2 = Integer.parseInt(values[1]);
                    double value3 = Double.parseDouble(values[2]);
                     
                    newGraph.addEdge(value1, value2, value3);                   
                }
            }
            
            return newGraph;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    
    
    
    
    
}
