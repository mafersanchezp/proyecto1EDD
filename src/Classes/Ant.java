/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import EDD.List;

/**
 *
 * @author Mafer
 */
public class Ant {
    //Atributos de la clase 
    private int id;
    private List<Integer> visited;
    private int currentVertex;
    private double distance;
    private boolean canMove;
    
    /**
     * @param id identificador de la hormiga
     * @param currentVertex vetice en el que se encuentra la hormiga
     */
    public Ant(int id, int currentVertex) {
        this.id = id;
        this.visited = new List();
        this.currentVertex = currentVertex;
        this.distance = 0;
        this.canMove = true;
    }
    
    /**
     * @return id de la hormiga
     */
    public int getId() {
        return id;
    }

    /**
     * @param id cambia el id de la hormiga
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     * 
     * @return lista de los vertice visitados
     */
    public List<Integer> getVisited() {
        return visited;
    }

    /**
     * 
     * @param visited cambia la lista de vertices visitados
     */
    public void setVisited(List<Integer> visited) {
        this.visited = visited;
    }
    
    /**
     * 
     * @return vertice en el que se encuentra la hormiga
     */
    public int getCurrentVertex() {
        return currentVertex;
    }
    
    /**
     * 
     * @param currentVertex cambia el vertice en el que se encuentra la hormiga
     */
    public void setCurrentVertex(int currentVertex) {
        this.currentVertex = currentVertex;
    }
    
    /**
     * 
     * @param id se agrega el vertice a la lista de visitados
     */
    public void visit(int id){
        this.visited.append(id);
    }
    
    /**
     * 
     * @return distancia recorrida por la hormiga
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * 
     * @param distance cambia la distancia recorrida por la hormiga
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    /**
     * 
     * @param distance suma a la distancia recorrida por la hormiga
     */
    public void sumDistance(double distance){
        this.distance += distance;
    }
    
    /**
     * 
     * @return si la hormiga se puede mover o no
     */
    public boolean canMove() {
        return canMove;
    }
    
    /**
     * 
     * @param canMove cambia el valor de si la hormiga puede moverse o no
     */
    public void setMove(boolean canMove) {
        this.canMove = canMove;
    }
    
    
    

    
    
    
}
