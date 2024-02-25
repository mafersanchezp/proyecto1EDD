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
    private int id;
    private List<Integer> visited;

    public Ant(int id) {
        this.id = id;
        this.visited = new List();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getVisited() {
        return visited;
    }
    
    public void visit(int id){
        this.visited.append(id);
    }

    public void setVisited(List<Integer> visited) {
        this.visited = visited;
    }
    
    
}
