/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import EDD.Edge;
import EDD.GraphP;
import EDD.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Mafer
 */
public class Simulation {
    //Atributos de la clase
    private GraphP graph;
    private int cycles;
    private int numAnts;
    private int startVertex;
    private int endVertex;
    private int alpha;
    private int beta;
    private double rho;
    private List<Ant> antsList;
    private int numCycle;

    /**
     * 
     * @param graph grafo de la simulacion
     * @param cycles cantidad de ciclos
     * @param numAnts cantidad de hormigas
     * @param startNode vertice de inicio
     * @param endNode vertice final
     * @param alpha valor de alpha 
     * @param beta valor de beta
     * @param rho valor de rho (entre [0, 1))
     */
    public Simulation(GraphP graph, int cycles, int numAnts, int startNode, int endNode, int alpha, int beta, double rho) {
        this.graph = graph;
        this.cycles = cycles;
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        this.startVertex = startNode;
        this.endVertex = endNode;
        this.antsList = new List();
        this.numCycle = 0;
    }
    
    /**
     * 
     * @return grafo de la simulacion
     */
    public GraphP getGraph() {
        return graph;
    }
    
    /**
     * cambia el grafo de la simulacion
     * @param graph
     */
    public void setGraph(GraphP graph) {
        this.graph = graph;
    }
    
    /**
     * 
     * @return cantidad de ciclos
     */
    public int getCycles() {
        return cycles;
    }
    
    /**
     * Cambia la cantidad de ciclos
     * @param cycles 
     */
    public void setCycles(int cycles) {
        this.cycles = cycles;
    }
    
    /**
     * 
     * @return el numero de hormigas
     */
    public int getNumAnts() {
        return numAnts;
    }
    
    /**
     * Cambia el numero de hormigas
     * @param numAnts 
     */
    public void setNumAnts(int numAnts) {
        this.numAnts = numAnts;
    }
    
    /**
     * 
     * @return vertice de inicio 
     */
    public int getStartVertex() {
        return startVertex;
    }

    /**
     * cambia el vertice de inicio
     * @param startVertex 
     */
    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }    
    
    /**
     * 
     * @return vertice final
     */
    public int getEndVertex() {
        return endVertex;
    }
    
    /**
     * cambia el valor del vertice
     * @param endVertex 
     */
    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }   
        
    /**
     * 
     * @return el valor de alpha
     */
    public int getAlpha() {
        return alpha;
    }
    
    /**
     * cambia el valor de alpha
     * @param alpha 
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    
    /**
     * 
     * @return el valor de beta
     */
    public int getBeta() {
        return beta;
    }

    /**
     * cambia el valor de beta
     * @param beta 
     */
    public void setBeta(int beta) {
        this.beta = beta;
    }
    
    /**
     * 
     * @return valor de rho
     */
    public double getRho() {
        return rho;
    }

    /**
     * cambia el valor de rho
     * @param rho 
     */
    public void setRho(double rho) {
        this.rho = rho;
    }          
    
    /**
     * 
     * @return la lista de hormigas 
     */
    public List<Ant> getAntsList() {
        return antsList;
    }
    
    /**
     * cambia la lista de las hormigas
     * @param antsList 
     */
    public void setAntsList(List<Ant> antsList) {
        this.antsList = antsList;
    }
    
    /**
     * 
     * @return numero de ciclos 
     */
    public int getNumCycle() {
        return numCycle;
    }

    /**
     * Cambia el numero de ciclos
     * @param numCycle 
     */
    public void setNumCycle(int numCycle) {
        this.numCycle = numCycle;
    }
    
    /**
     * Inicializa el valor de las feromonas en todos los caminos del grafo
     * r = 1/m 
     * m = cantidad de vertices en el grafo
     */
    public void setPheromones(){
        List adyList = graph.getAdyList();
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = (Edge) adyList.get(i);
            auxEdge.setPheromones(1.0/graph.getSize());
        }
    }
    
    /**
     * Crea las hormigas basado en la cantidad de hormigas 
     * que el usuario especifico
     */
    public void createAnts(){
        for (int i = 0; i < numAnts; i++) {
            Ant newAnt = new Ant(i, startVertex);
            newAnt.visit(startVertex);
            antsList.append(newAnt);
        }
    }
    
    /**
     * Obtiene los vertices adyacentes a un vertice en el que esta la hormiga
     * @param id del vertice donde esta la hormiga
     * @param ant hormiga
     * @return Lista de los vertices adyacentes
     */
    public List adyVertex(int id, Ant ant){
        List adyList = graph.getAdyList();
        List adyVertex = new List();
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = (Edge) adyList.get(i);
            if(auxEdge.getIdA() == id){
                if(!ant.getVisited().exist(auxEdge.getIdB())){
                    adyVertex.append(auxEdge);                    
                }
            }
            else if(auxEdge.getIdB() == id){
                if(!ant.getVisited().exist(auxEdge.getIdA())){
                    adyVertex.append(auxEdge);                    
                }
            }
        }
        return adyVertex;
    }
    
    /**
     * Calcula la probabilidad que tiene una hormiga de ir por un camino
     * @param edge camino que tomara la hormiga
     * @param ant hormiga 
     * @return probabilidad de la hormiga para cruzar el camino
     */
    public double probability(Edge edge, Ant ant){
        double T_rs = edge.getPheromones();
        double H_rs = 1.0/edge.getDistance();
        
        double sum = 0.0;
        List<Edge> adyVertex = adyVertex(ant.getCurrentVertex(), ant);
        for (int i = 0; i < adyVertex.getSize(); i++) {
            Edge auxEdge = adyVertex.get(i);
            
            double T_ru = auxEdge.getPheromones();
            double H_ru = 1.0/auxEdge.getDistance();
            sum += Math.pow(T_ru, alpha) * Math.pow(H_ru, beta);
        }
        
        return ((Math.pow(T_rs, alpha) * Math.pow(H_rs, beta))/sum) * 100.0;
    }
    
    /**
     * Metodo para actualizar por incremento una arista
     * @param edge arista que sera actualizada
     */
    public void increaseUpdate(Edge edge){
        double deltaT_rs = 0.0;
        for (int i = 0; i < edge.getAntsVisited().getSize(); i++) {
            Ant auxAnt = edge.getAntsVisited().get(i);
            deltaT_rs += 1.0/auxAnt.getDistance();
        }
        
        edge.setPheromones(edge.getPheromones() + deltaT_rs);
    }
    
    /**
     * Actualizacion por evaporacion para todas las aristas del grafo
     */
    public void evaporationUpdate(){
        List<Edge> adyList = graph.getAdyList();
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = adyList.get(i);
            double T_rs = (1 - rho) * auxEdge.getPheromones();
            auxEdge.setPheromones(T_rs);            
        }
    }
    
    /**
     * Selecciona el siguiente camino que tomara la hormiga
     * @param ant hormiga
     */
    public void nextVertex(Ant ant){
        if(ant.canMove()){           
            List<Edge> adyVertex = adyVertex(ant.getCurrentVertex(), ant);
            if(!adyVertex.isEmpty()){
                ant.setMove(true);
                List<Double> probabilities = new List();

                for (int i = 0; i < adyVertex.getSize(); i++) {
                    Edge auxEdge = adyVertex.get(i);
                    double probability = probability(auxEdge, ant);
                    probabilities.append(probability);
                }

                double total = 0.0;
                for (int i = 0; i < probabilities.getSize(); i++) {
                    double num = (double) probabilities.get(i);
                    total += num;
                }

                Random random = new Random();
                double randomNumber = random.nextDouble() * total;

                // Elige al ganador basado en el número aleatorio generado
                double counter = 0.0;
                String ganador = null;
                for (int i = 0; i < probabilities.getSize(); i++) {
                    double auxNum = (double) probabilities.get(i);
                    counter += auxNum;
                    if (randomNumber <= counter) {
                        Edge next = adyVertex.get(i);
                        if(next.getIdA() == ant.getCurrentVertex()){
                            ant.setCurrentVertex(next.getIdB());
                            ant.visit(next.getIdB());
                        }
                        else if(next.getIdB() == ant.getCurrentVertex()){
                            ant.setCurrentVertex(next.getIdA());
                            ant.visit(next.getIdA());
                        }
                        ant.sumDistance(next.getDistance());
                        next.getAntsVisited().append(ant);
                        increaseUpdate(next);
                    }
                }
                if(ant.getCurrentVertex() == endVertex){
                    ant.setMove(false);
                }
            }
            else{
                ant.setMove(false);
            }
        }
    }
    
    /**
     * Reinicia todos los caminos visitados de las hormigas, las distancias 
     * y regresa las hormigas a la ciudad de inicio
     */
    public void restart(){
        for (int i = 0; i < this.antsList.getSize(); i++) {
            Ant auxAnt = antsList.get(i);
            auxAnt.setCurrentVertex(startVertex);
            auxAnt.setDistance(0);
            auxAnt.getVisited().delete();
            auxAnt.getVisited().append(startVertex);
            auxAnt.setMove(true);
        }
        
        List adyList = graph.getAdyList();
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = (Edge) adyList.get(i);
            auxEdge.getAntsVisited().delete();
        }
    }
    
    /**
     * Detecta cuando termina el ciclo de la simulacion
     * @return true si el ciclo termino, false si no termino
     */
    public boolean endCycle(){
        for (int i = 0; i < antsList.getSize(); i++) {
            Ant auxAnt = antsList.get(i);
            if(auxAnt.canMove()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Empieza el ciclo
     */
    public void startCycle(){
        if(numCycle <= cycles){
            restart();
            int count = 1;
            while(!endCycle()){    
                System.out.println("Iteracion " + count);
                for (int i = 0; i < antsList.getSize(); i++) {
                    Ant auxAnt = antsList.get(i);      
                    System.out.print(auxAnt.getCurrentVertex() + " --> ");
                    nextVertex(auxAnt);
                    System.out.println(auxAnt.getCurrentVertex());
                }
                evaporationUpdate();
                count++;
            }
            numCycle++;           
        }
        else{
            JOptionPane.showMessageDialog(null, "Simulacion finalizada");
        }
    }
    
    /**
     * Obtiene el camino mas corto y lo imprime con graphStream
     */
    public void shortRoad(){
        Ant ant = (Ant) antsList.getHead().getData();
        for (int i = 0; i < antsList.getSize(); i++) {
            Ant auxAnt = antsList.get(i);      
            if(auxAnt.getDistance() < ant.getDistance()){
                if(auxAnt.getVisited().getLast() == endVertex){
                    ant = auxAnt;                    
                }
            }
        }
        if(ant.getVisited().getLast() == endVertex){
            graph.printShortRoad(ant.getVisited());
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el camino mas corto");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
