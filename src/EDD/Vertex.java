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
public class Vertex {
    //Atributos de la clase
    private int id;
    private int relations;
    
    /**
     * Constructor de la clase Vertex
     * @param id identificador del vertice
     */
    public Vertex(int id) {
        this.id = id;
        this.relations = 0;
    }
    
    /**
     * 
     * @return id del vertice
     */
    public int getId() {
        return id;
    }
    
    /**
     * Cambia el id del vertice
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return la cantidad de relaciones del vertice
     */
    public int getRelations() {
        return relations;
    }
    
    /**
     * Cambia la cantidad de relaciones del vertice
     * @param relations 
     */
    public void setRelations(int relations) {
        this.relations = relations;
    }
    
    /**
     * Agrega 1 a la cantidad de relaciones 
     */
    public void addRelation(){
        this.relations++;
    }
    
    /**
     * Elimina 1 a la cantidad de relaciones
     */
    public void removeRelation(){
        this.relations--;
    }
   
    
    /**
     * 
     * @return String con los atributos de valores de la clase Vertex
     */
    @Override
    public String toString() {
        return "Vertex{" + "id=" + id + '}';
    }

   
    
}
