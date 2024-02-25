/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author Mafer
 */
public class Edge {
    private int idA;
    private int idB;
    private double size;
    private double pheromones;
    private int antsVisited;

    public Edge(int idA, int idB, double size) {
        this.idA = idA;
        this.idB = idB;
        this.size = size;
        this.pheromones = 0;
        this.antsVisited = 0;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPheromones() {
        return pheromones;
    }

    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }

    public int getAntsVisited() {
        return antsVisited;
    }

    public void setAntsVisited(int antsVisited) {
        this.antsVisited = antsVisited;
    }
    
    public void addAnt(){
        this.antsVisited++;
    }
    
    

    @Override
    public String toString() {
        return "Edge{" + "idA=" + idA + ", idB=" + idB + ", size=" + size + ", pheromones=" + pheromones + '}';
    }
    
    
    
    
}
