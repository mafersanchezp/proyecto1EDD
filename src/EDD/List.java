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
public class List<T> {
    private Nodo head;
    private Nodo tail;
    private int size;

    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public List(T... data){
        for (T element: data){
            append(element);
        }
    }

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public Nodo getTail() {
        return tail;
    }

    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty(){
        return this.head == null;
    }
    
    public void delete(){
        this.head = this.tail = null;
        this.size = 0;
    }
    
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
    
    public T popHead(){
        if(!isEmpty()){
            T data = (T) this.head;
            this.head = this.head.getNext();
            this.size--;
            return data;
        }
        return null;
    }
    
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
    
    public boolean exist(T data){
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
    
    public T getLast(){
        return (T) this.tail.getData();
    }
    
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
    
    
    
    
}
