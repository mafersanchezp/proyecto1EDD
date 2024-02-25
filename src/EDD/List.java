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
public class List<T> {
    //Atributos de la clase
    private Nodo head;
    private Nodo tail;
    private int size;

    /**
     * Constructor 1 de la clase List
     * inicia la lista vacia
     */
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Constructor 2 de la clase List
     * inicia la lista con valores insertados
     * @param data arreglo de valores  a ingresar
     */
    public List(T... data){
        for (T element: data){
            append(element);
        }
    }

    /**
     * 
     * @return la cabeza de la lista
     */
    public Nodo getHead() {
        return head;
    }
    
    /**
     * cambia la cabeza de la lista
     * @param head nueva cabeza de la lista
     */
    public void setHead(Nodo head) {
        this.head = head;
    }
    
    /**
     * 
     * @return cola de la lista
     */
    public Nodo getTail() {
        return tail;
    }

    /**
     * cambia la cola de la lista
     * @param tail nueva cola de la lista
     */
    public void setTail(Nodo tail) {
        this.tail = tail;
    }
    
    /**
     * 
     * @return cantidad de elementos en la lista 
     */
    public int getSize() {
        return size;
    }

    /**
     * Cambia la cantidad de elementos de la lista
     * @param size numero de elementos
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Metodo para saber si una lista esta vacia
     * @return true si esta vacia, false si no
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Elimina por completo la lista
     */
    public void delete(){
        this.head = this.tail = null;
        this.size = 0;
    }
    
    /**
     * Agrega un elemento al final de la listaAgrega un elemento al final de la lista
     * @param data dato a ingresar a la lista
     */
    public void append(T data){
        Nodo nd = new Nodo(data);
        if(isEmpty()){
            this.head = this.tail = nd;
        }
        else{
            this.tail.setNext(nd);
            this.tail = nd;
        }
        this.size++;
    }
    
    /**
     * Agrega un elemento al inicio de la lista
     * @param data a insertar en la lista
     */
    public void preappend(T data){
        Nodo nd = new Nodo(data);
        if(isEmpty()){
            this.head = this.tail = nd;
        }
        else{
            nd.setNext(this.head);
            this.head = nd;
        }
        this.size++;
    }
    
    /**
     * Inserta un elemento en la lista por un indice
     * @param data elemento a ingresar
     * @param idx inidice especificado
     */
    public void insert(T data, int idx){
        if(isEmpty()){
            append(data);
        }
        else{
            if(idx >= 0 && idx < this.size){
                if(idx == 0){
                    preappend(data);
                }
                else{
                    Nodo nd = new Nodo(data);
                    Nodo pointer = this.head;
                    for (int i = 0; i < idx-1; i++) {
                        pointer = pointer.getNext();
                    }

                    nd.setNext(pointer.getNext());
                    pointer.setNext(nd);
                    this.size++;
                }                
            }
        }
    }
    
    /**
     * Elimina la cola de la lista
     * @return el valor de la cola
     */
    public T popTail(){
        if(!isEmpty()){
            Nodo pointer = this.head;
            while(pointer.getNext().getNext() != null){
                pointer = pointer.getNext();
            }
            T data = (T) pointer.getNext().getData();
            pointer.setNext(null);
            this.tail = pointer;
            this.size--;
            return data;
        }
        return null;
    }
    
    /**
     * Elimina la cabeza de la cola
     * @return valor de la cabeza
     */
    public T popHead(){
        if(!isEmpty()){
            T data = (T) this.head;
            this.head = this.head.getNext();
            this.size--;
            return data;
        }
        return null;
    }
    
    /**
     * Elimina un elemento de la lista por su indice
     * @param idx indice especificado
     * @return elemento eliminado
     */
    public T pop(int idx){
        if(!isEmpty()){
            if(idx >= 0 && idx < this.size){
                if(idx == 0){
                    return popHead();
                }
                else if(idx == size-1){
                    return popTail();
                }
                else{
                    Nodo pointer = this.head;
                    for (int i = 0; i < idx-1; i++) {
                        pointer = pointer.getNext();
                    }
                    T data = (T) pointer.getNext();
                    pointer.setNext(pointer.getNext().getNext());
                    this.size--;
                    return data;
                }                
            }
        }
        return null;
    }
    
    /**
     * Obtiene un elemento de la lista por su indice
     * @param idx indice especificado
     * @return elemento de la lista
     */
    public T get(int idx){
        if(!isEmpty()){
            if(idx >= 0 && idx < size){
                if(idx == 0){
                    return (T) this.head.getData();
                }
                else if(idx == size-1){
                    return (T) this.tail.getData();
                }
                else{
                    Nodo pointer = this.head;
                    for (int i = 0; i < idx; i++) {
                        pointer = pointer.getNext();
                    }
                    return (T) pointer.getData();
                }
            }
        }
        return null;
    }
    
    /**
     * Reemplaza un elemento de la lista por su indice
     * @param idx indice especificado
     * @param data dato a reemplazar
     */
    public void replace(int idx, T data){
        if(!isEmpty()){
            if(idx >= 0 && idx < size){
                if(idx == 0){
                    this.head.setData(data);
                }
                else if(idx == size-1){
                    this.tail.setData(data);
                }
                else{
                    Nodo pointer = this.head;
                    for (int i = 0; i < idx; i++) {
                        pointer = pointer.getNext();
                    }
                    pointer.setData(data);
                }
            }
        }
    }
    
    /**
     * Metodo para saber si un elemento existe en la lista
     * @param data a comprobar si existe
     * @return true si existe, false si no
     */
    public boolean exist(T data){
        if(!isEmpty()){              
            Nodo pointer = this.head;
            while(pointer != null){
                if(pointer.getData().equals(data)){
                    return true;
                }
                pointer = pointer.getNext();
            }
            return false;             
        }
        return false;
    }
    
    /**
     * Metodo para saber si un elemento existe en la lista
     * @param data a comprobar si existe pero en String
     * @return true si existe, false si no
     */
    public boolean exist(String data){
        if(!isEmpty()){              
            Nodo pointer = this.head;
            while(pointer != null){
                if(pointer.getData().equals(data)){
                    return true;
                }
                pointer = pointer.getNext();
            }
            return false;             
        }
        return false;
    }
    
    /**
     * Imprime la lista en la consola
     */
    public void print(){
        if(!isEmpty()){
            Nodo pointer = this.head;
            while(pointer != null){
                System.out.print(pointer.getData() + " ");
                pointer = pointer.getNext();
            }
            System.out.println("");
        }
    }
     /**
     * Metodo para saber si un elemento esta en la lista
     * @param data a comprobar si existe pero en String
     * @return true si esta, false si no
     */
    public boolean contains(T data){
        if(!isEmpty()){
            Nodo pointer = this.head;
            while(pointer != null){
                if(pointer.getData() == data){
                    return true;
                }
                pointer = pointer.getNext();
            }
            return false;
        }
        return false;
    }
    
    /**
     * Obtiene el ultimo de la lista
     * @return dato de la cola
     */
    public T getLast(){
        return (T) this.tail.getData();
    }
    
    /**
     * Suma todos los elementos de la lista
     * @return suma de todos los elementos
     */
    public double sum(){
        if(!isEmpty()){
            Nodo pointer = this.head.getNext();
            double value = (double) this.head.getData();
            while(pointer != null){
                double num = (double) pointer.getData();
                value += num;
                pointer = pointer.getNext();
            }
            return value;
        }
        return 0.0;
    }
    
    /**
     * Crea una copia de la lista
     * @return copia de la lista
     */
    public List copy(){
        if(!isEmpty()){
            List copyList = new List();
            Nodo pointer = this.head;
            while(pointer != null){
                copyList.append(pointer.getData());
                pointer = pointer.getNext();
            }
            return copyList;
        }
        return null;
    }
    
    /**
     * Crea una copia de la lista invertida
     * @return lista invertida
     */
    public List invert(){
        if(!isEmpty()){
            List invertList = new List();
            Nodo pointer = this.head;
            while(pointer != null){
                invertList.preappend(pointer.getData());
                pointer = pointer.getNext();
            }
            return invertList;
        }
        return null;
    }

    
    
    
    
}
