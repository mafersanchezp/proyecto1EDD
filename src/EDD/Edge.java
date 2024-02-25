/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Classes.Ant;

/**
 *
 * @author Mafer
 */
public class Edge {
    //Atributos de la clase
    private int idA;
    private int idB;
    private double distance;
    private double pheromones;
    private List<Ant> antsVisited;
    
    /**
     * Constructor de la clase Edge
     * @param idA
     * @param idB
     * @param size 
     */
    public Edge(int idA, int idB, double size) {
        this.idA = idA;
        this.idB = idB;
        this.distance = size;
        this.pheromones = 0;
        this.antsVisited = new List();
    }

    /**
     * 
     * @return valor del idA 
     */
    public int getIdA() {
        return idA;
    }
    
    /**
     * Cambia el valor del idA
     * @param idA 
     */
    public void setIdA(int idA) {
        this.idA = idA;
    }
    
    /**
     * 
     * @return valor idB 
     */
    public int getIdB() {
        return idB;
    }
    
    /**
     * cambia el valor de idB
     * @param idB 
     */
    public void setIdB(int idB) {
        this.idB = idB;
    }

    /**
     * 
     * @return distancia recorrida de la hormiga 
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * cambia el valor de la distancia
     * @param size 
     */
    public void setDistance(double size) {
        this.distance = size;
    }
    
    /**
     * 
     * @return feromones 
     */
    public double getPheromones() {
        return pheromones;
    }
    
    /**
     * cambiua el valor de las feromonas
     * @param pheromones 
     */
    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }
    
    /**
     * 
     * @return hormigas que visitaron el camino 
     */
    public List<Ant> getAntsVisited() {
        return antsVisited;
    }
    
    /**
     * cambia la lista de las hormigas que visitaron el camino
     * @param antsVisited 
     */
    public void setAntsVisited(List<Ant> antsVisited) {
        this.antsVisited = antsVisited;
    }

    /**
     * 
     * @return string con los atributos y valores de la arista 
     */
    @Override
    public String toString() {
        return "Edge{" + "idA=" + idA + ", idB=" + idB + ", size=" + distance + ", pheromones=" + pheromones + '}';
    }
    
    
    
    
}
