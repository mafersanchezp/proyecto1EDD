/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author Mafer
 */
public class GraphP {
    private List<Edge> adyList;
    private List<Vertex> allVertex;
    private int size;

    public GraphP() {
        this.adyList = new List();
        this.allVertex = new List();
        this.size = 0;
    }

    public List getAdyList() {
        return adyList;
    }

    public void setAdyList(List adyList) {
        this.adyList = adyList;
    }

    public List getAllVertex() {
        return allVertex;
    }

    public void setAllVertex(List vertex) {
        this.allVertex = vertex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    public boolean isEmpty(){
        return allVertex.isEmpty();
    }
    
    
    public void addVertex(int id){
        Vertex newVertex = new Vertex(id);
        if(isEmpty()){
            allVertex.append(newVertex);
            this.size++;
        }
        else{
            if(!isVertex(id)){
                allVertex.append(newVertex);
                this.size++;
            }
        }
    }
    
    public boolean isVertex(int id){
        if(isEmpty()){
            return false;
        }
        else{
            for (int i = 0; i < this.allVertex.getSize(); i++) {
                Vertex auxVertex = this.allVertex.get(i);
                if(id == auxVertex.getId()){
                    return true;
                }
            }
            return false;
        }
    }
    
    public void addEdge(int idA, int idB, double size){
        if(isVertex(idA) && isVertex(idB)){
            if(!isEdge(idA, idB)){
                Edge newEdge = new Edge(idA, idB, size);
                this.adyList.append(newEdge);
            }
            else{
                Edge auxEdge = getEdge(idA, idB);
                int auxSize = (int) auxEdge.getSize();
                if(auxSize != size){
                    Edge newEdge = new Edge(idA, idB, size);
                    this.adyList.append(newEdge);
                }
            }
        }
    }
    
    public boolean isEdge(int idA, int idB){
        if(!isVertex(idA) || !isVertex(idB)){
            return false;
        }
        else{
            for (int i = 0; i < adyList.getSize(); i++) {
                Edge auxEdge = adyList.get(i);
                int auxIdA = auxEdge.getIdA();
                int auxIdB = auxEdge.getIdB();
                
                if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB && auxIdB == idA)){
                    return true;
                }
            }
            return false;
        }
    }
    
    public void removeVertex(int id){
        for (int i = 0; i < this.adyList.getSize();) {
            Edge auxEdge = this.adyList.get(i);
            
            int auxIdA = auxEdge.getIdA();
            int auxIdB = auxEdge.getIdB();
            
            if(auxIdA == id){
                this.adyList.pop(i);
            }
            else if(auxIdB == id){
                this.adyList.pop(i);              
            }
            else{
                i++;
            }
        }
        
        for (int i = 0; i < this.allVertex.getSize(); i++) {
            Vertex auxVertex = this.allVertex.get(i);
            
            int auxId = auxVertex.getId();
            
            if(auxId == id){
                this.allVertex.pop(i);
                this.size--;
            }
        }
    }
    
    public void removeEdge(int idA, int idB){
        for (int i = 0; i < this.adyList.getSize(); i++) {
            Edge auxEdge = this.adyList.get(i);
            
            int auxIdA = auxEdge.getIdA();
            int auxIdB = auxEdge.getIdB();
            
            if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB || auxIdB == idA)){
                this.adyList.pop(i);
            }
        }
    }
    
    public GraphP copyGraph(){
        GraphP copyGraph = new GraphP();
        if(!isEmpty()){
            for (int i = 0; i < adyList.getSize(); i++) {
                copyGraph.getAdyList().append(this.adyList.get(i));
            }
            for (int i = 0; i < allVertex.getSize(); i++) {
                copyGraph.getAllVertex().append(this.allVertex.get(i));
            }
        }
        return copyGraph;
    }
    
    public void print(){
        System.setProperty("org.graphstream.ui", "swing");

        Graph grafoview = new MultiGraph("graph");
        
        String styleSheet = 
        "node {" +
        "   size: 20px;" +
        "   shape: circle;" +
        "   fill-color: white;" +
        "   stroke-mode: plain;" +
        "   stroke-color: black;" +
        "   stroke-width: 2px;" +
        "}" +
        "edge {" +
        "   shape: line;" +
        "   fill-color: black;" +
        "   size: 2px;" +
        "}";
        
        grafoview.setAttribute("ui.stylesheet", styleSheet);

        for (int i = 0; i < allVertex.getSize(); i++) {
            Vertex auxVertex = allVertex.get(i);
            grafoview.addNode(Integer.toString(auxVertex.getId()));
            Node node = grafoview.getNode(Integer.toString(auxVertex.getId()));
            node.setAttribute("ui.label", auxVertex.getId());
        }

        for (int i = 0; i < adyList.getSize(); i++) {
            Edge edge = adyList.get(i);
            String idA = Integer.toString(edge.getIdA()), idB = Integer.toString(edge.getIdB());
            String size = Double.toString(edge.getSize());

            grafoview.addEdge(Integer.toString(i), idA, idB);
            grafoview.getEdge(Integer.toString(i)).setAttribute("ui.label", size);
        }

        Viewer viewer = grafoview.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    public Vertex getVertex(int id){
        if(!isEmpty()){
            if(isVertex(id)){
                for (int i = 0; i < allVertex.getSize(); i++) {
                    Vertex auxVertex = allVertex.get(i);
                    if(auxVertex.getId() == id){
                        return auxVertex;
                    }
                }
            }
        }
        return null;
    }
    
    public Edge getEdge(int idA, int idB){
        if(!isEmpty()){
            if(isEdge(idA, idB)){
                for (int i = 0; i < adyList.getSize(); i++) {
                    Edge auxEdge = adyList.get(i);
                    int auxIdA = auxEdge.getIdA();
                    int auxIdB = auxEdge.getIdB();
                    
                    if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB && auxIdB == idA)){
                        return auxEdge;
                    }
                }
            }
        }
        return null;
    }
    
}
