/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author Mafer
 * @param <T>
 */
public class Nodo<T> {
    //Atributos de la clase
    private T data;
    private Nodo next;
    
    /**
     * Constructor de la clase Nodo
     * @param data 
     */
    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * 
     * @return obtiene el dato del nodo 
     */
    public T getData() {
        return data;
    }
    
    /**
     * Cambia el valor del dato
     * @param data 
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * 
     * @return obtiene el siguiente nodo
     */
    public Nodo getNext() {
        return next;
    }
    
    /**
     * Cambia el valor del siguiente nodo
     * @param next 
     */
    public void setNext(Nodo next) {
        this.next = next;
    }
    
    
            
}
