/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Classes.Ant;
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
    //Atributos de la clase
    private List<Edge> adyList;
    private List<Vertex> allVertex;
    private int size;
    
    /**
     * Contructor del grafo
     */
    public GraphP() {
        this.adyList = new List();
        this.allVertex = new List();
        this.size = 0;
    }
    
    /**
     * 
     * @return lista de adyacencia 
     */
    public List getAdyList() {
        return adyList;
    }
    
    /**
     * Cambia la lista de adyacencia
     * @param adyList 
     */
    public void setAdyList(List adyList) {
        this.adyList = adyList;
    }
    
    /**
     * 
     * @return lista de todos los vertices
     */
    public List getAllVertex() {
        return allVertex;
    }
    
    /**
     * Cambia la lista de todos los vertices
     * @param vertex 
     */
    public void setAllVertex(List vertex) {
        this.allVertex = vertex;
    }
    
    /**
     * 
     * @return tamaño del grafo
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Cambia el tamaño del grafo
     * @param size 
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * 
     * @return si el grafo esta vacio (no tiene vertices)
     */
    public boolean isEmpty(){
        return allVertex.isEmpty();
    }
    
    /**
     * Agrega un vetice al grafo
     * @param id vertice
     */
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
    
    /**
     * Metodo para saber si un vertice ya esta en el grafo
     * @param id del vertice
     * @return true si existe, false si no existe
     */
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
    
    /**
     * Agrega una arista al vertice
     * @param idA vertice A
     * @param idB vertice B
     * @param size distancia de la arista
     */
    public void addEdge(int idA, int idB, double size){
        if(isVertex(idA) && isVertex(idB)){
            if(!isEdge(idA, idB, size)){
                getVertex(idA).addRelation();
                getVertex(idB).addRelation();
                Edge newEdge = new Edge(idA, idB, size);
                this.adyList.append(newEdge);
            }
            else{
                Edge auxEdge = getEdge(idA, idB, size);
                int auxSize = (int) auxEdge.getDistance();
                if(auxSize != size){
                    getVertex(idA).addRelation();
                    getVertex(idB).addRelation();
                    Edge newEdge = new Edge(idA, idB, size);
                    this.adyList.append(newEdge);
                }
            }
        }
    }
    
    /**
     * Metodo para saber si una arista existe en el grafo
     * @param idA vertice A
     * @param idB vertice B
     * @param size distancia de la arista
     * @return true si existe, false si no existe
     */
    public boolean isEdge(int idA, int idB, double size){
        if(!isVertex(idA) || !isVertex(idB)){
            return false;
        }
        else{
            for (int i = 0; i < adyList.getSize(); i++) {
                Edge auxEdge = adyList.get(i);
                int auxIdA = auxEdge.getIdA();
                int auxIdB = auxEdge.getIdB();
                double auxSize = auxEdge.getDistance();
                
                if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB && auxIdB == idA)){
                    if(auxSize == size){
                        return true;                        
                    }
                }
            }
            return false;
        }
    }
    

    /**
     * Elimina un vertice del grafo, eliminando tambien sus aristas
     * @param id vertice
     */
    public void removeVertex(int id){
        for (int i = 0; i < this.adyList.getSize();) {
            Edge auxEdge = this.adyList.get(i);
            
            int auxIdA = auxEdge.getIdA();
            int auxIdB = auxEdge.getIdB();
            
            if(auxIdA == id){
                getVertex(auxIdB).removeRelation();
                this.adyList.pop(i);
            }
            else if(auxIdB == id){
                getVertex(auxIdA).removeRelation();
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
    
    /**
     * Elimina una arista del grafo
     * @param idA vertice A
     * @param idB vertice B
     * @param size distancia de la arista
     */
    public void removeEdge(int idA, int idB, double size){
        for (int i = 0; i < this.adyList.getSize(); i++) {
            Edge auxEdge = this.adyList.get(i);
            
            int auxIdA = auxEdge.getIdA();
            int auxIdB = auxEdge.getIdB();
            double auxSize = auxEdge.getDistance();
            
            if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB || auxIdB == idA)){
                if(auxSize == size){
                    getVertex(idA).removeRelation();
                    getVertex(idB).removeRelation();
                    this.adyList.pop(i);
                }
            }
        }
    }
    
    /**
     * 
     * @return la copia del grafo 
     */
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
    
    /**
     * Imprime los vertices y las aristas del grafo
     */
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
            String size = Double.toString(edge.getDistance());

            grafoview.addEdge(Integer.toString(i), idA, idB);
            grafoview.getEdge(Integer.toString(i)).setAttribute("ui.label", size);
        }

        Viewer viewer = grafoview.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    /**
     * Obtiene un vertice apartir de su id
     * @param id vertice
     * @return vertice 
     */
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
    
    /**
     * Obtiene una arista del grafo apartir de los id y la distancia
     * @param idA vertice A
     * @param idB vertice B
     * @param size distancia de la arista
     * @return Arista
     */
    public Edge getEdge(int idA, int idB, double size){
        if(!isEmpty()){
            if(isEdge(idA, idB, size)){
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
    
    /**
     * Obtiene uno o varios vertices apartir de los id
     * @param idA vertice A
     * @param idB vertice B
     * @return Lista de vertices
     */
    public List<Edge> getEdge(int idA, int idB){
        List edgeList = new List();
        if(!isEmpty()){
            for (int i = 0; i < adyList.getSize(); i++) {
                Edge auxEdge = adyList.get(i);
                int auxIdA = auxEdge.getIdA();
                int auxIdB = auxEdge.getIdB();

                if((auxIdA == idA && auxIdB == idB) || (auxIdA == idB && auxIdB == idA)){
                    edgeList.append(auxEdge);
                }
            } 
        }    
        return edgeList;
    }
    
    /**
     * Imprime el grafo junto en donde se encuentras las hormigas
     * @param antList lista de las hormigas
     */
    public void print(List<Ant> antList){
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
            String ants = "";
            Vertex auxVertex = allVertex.get(i);
            grafoview.addNode(Integer.toString(auxVertex.getId()));
            Node node = grafoview.getNode(Integer.toString(auxVertex.getId()));
            
            for (int j = 0; j < antList.getSize(); j++) {
                Ant auxAnt = antList.get(j);
                
                int lastNode = auxAnt.getVisited().getLast();
                if(auxVertex.getId() == lastNode){
                   ants += "Ant" + auxAnt.getId() + " ";
                }
            }           
            node.setAttribute("ui.label", auxVertex.getId() + "\n" + ants);
        }

        for (int i = 0; i < adyList.getSize(); i++) {
            Edge edge = adyList.get(i);
            String idA = Integer.toString(edge.getIdA()), idB = Integer.toString(edge.getIdB());
            String size = Double.toString(edge.getDistance());

            grafoview.addEdge(Integer.toString(i), idA, idB);
            grafoview.getEdge(Integer.toString(i)).setAttribute("ui.label", size);
        }

        Viewer viewer = grafoview.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    /**
     * Imprime el camino mas corto en el grafo
     * @param shortRoad lista del camino mas corto
     */
    public void printShortRoad(List<Integer> shortRoad){
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
            String ants = "";
            Vertex auxVertex = allVertex.get(i);
            grafoview.addNode(Integer.toString(auxVertex.getId()));
            Node node = grafoview.getNode(Integer.toString(auxVertex.getId()));
            
            for (int j = 0; j < shortRoad.getSize(); j++) {
                int id = shortRoad.get(j);
                
                if(auxVertex.getId() == id){
                   node.setAttribute("ui.style", "stroke-mode: plain; stroke-color: red;");
                }
            }           
            node.setAttribute("ui.label", auxVertex.getId() + "\n" + ants);
        }

        for (int i = 0; i < adyList.getSize(); i++) {
            Edge edge = adyList.get(i);
            String idA = Integer.toString(edge.getIdA()), idB = Integer.toString(edge.getIdB());
            String size = Double.toString(edge.getDistance());

            grafoview.addEdge(Integer.toString(i), idA, idB);
            grafoview.getEdge(Integer.toString(i)).setAttribute("ui.label", size);
            
            for (int j = 0,  k = 1; k < shortRoad.getSize(); j++, k++) {
                int auxIdA = Integer.parseInt(idA);
                int auxIdB = Integer.parseInt(idB);
                
                int shortIdA = shortRoad.get(j);
                int shortIdB = shortRoad.get(k);
                
                if((auxIdA == shortIdA && auxIdB == shortIdB) || (auxIdA == shortIdB && auxIdB == shortIdA)){
                    grafoview.getEdge(Integer.toString(i)).setAttribute("ui.style", "fill-color: red;");                    
                }
            }
            
        }

        Viewer viewer = grafoview.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    
     /**
      * Obtiene los vertices adyacentes a un vertice
      * @param id vertice
      * @param visited vertices que ya fueron visitados
      * @return lista de vertices adyacentes
      */  
    public List adyVertex(int id, List<Integer> visited){
        List<Edge> adyVertex = new List();
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = adyList.get(i);
            
            int auxIdA = auxEdge.getIdA();
            int auxIdB = auxEdge.getIdB();
            
            if(auxIdA == id){
                if(!visited.contains(auxIdB)) adyVertex.append(auxEdge);
            }
            else if(auxIdB == id){
                if(!visited.contains(auxIdA)) adyVertex.append(auxEdge);
            }
        }
        return adyVertex;
    }
    
    /**
     * Metodo para saber si no hay ningun vertice sin ninguna coneccion
     * @return true si todos los vertices estan conectados, false si no
     */
    public boolean allConected(){
        for (int i = 0; i < allVertex.getSize(); i++) {
            Vertex auxVertex = allVertex.get(i);
            if(auxVertex.getRelations() == 0){
                return false;
            }
        }
        return true;
    }
    
    
    

    
    
    
    
 
    
}
