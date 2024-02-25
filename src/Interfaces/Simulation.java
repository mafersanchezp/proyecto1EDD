/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Classes.Ant;
import EDD.Edge;
import EDD.GraphP;
import EDD.List;
import EDD.Vertex;
import java.util.Random;

/**
 *
 * @author Mafer
 */
public class Simulation extends javax.swing.JFrame {
    private static GraphP graph;
    private static GraphP copyGraph;
    private static String filePath;
    private static int cycles;
    private static int ants;
    private static int start;
    private static int end;
    private static List<Ant> antsList;
    private static int[] array;
    /**
     * Creates new form Simulation
     */
    public Simulation(GraphP graph, GraphP copyGraph, String filePath, int cycles, int ants, int start, int end, int[] array) {
        initComponents();
        this.graph = graph;
        this.copyGraph = copyGraph;
        this.filePath = filePath;
        this.cycles = cycles;
        this.ants = ants;
        this.start = start;
        this.end = end;
        this.antsList = new List();
        this.array = array;
        
        List<Edge> adyList = copyGraph.getAdyList();
        double num1 = 1;
        double num2 = copyGraph.getAllVertex().getSize();
        double pheromones = num1/num2;
        
        for (int i = 0; i < adyList.getSize(); i++) {
            Edge auxEdge = adyList.get(i);
            auxEdge.setPheromones(pheromones);
        }
        
        for (int i = 0; i < ants; i++) {
            Ant auxAnt = new Ant(i);
            auxAnt.visit(start);
            antsList.append(auxAnt);
        }
    }
    
    public double probability(int idA, int idB){
        Edge edge = copyGraph.getEdge(idA, idB);
        double alpha = array[0];
        double beta = array[1];
        
        double r_AB = edge.getPheromones();
        double n_AB = edge.getSize();
        
        double sum = 0;
        for (int i = 0; i < copyGraph.getAdyList().getSize(); i++) {
            Edge auxEdge = (Edge) copyGraph.getAdyList().get(i);
            if(auxEdge.getIdA() == idA || auxEdge.getIdB() == idA){
                double r_sum = auxEdge.getPheromones();
                double n_sum = auxEdge.getSize();
                sum += Math.pow(r_sum, alpha) * Math.pow(n_sum, beta);
            }
        }

        return ((Math.pow(r_AB, alpha) * Math.pow(n_AB, beta))/sum) * 10;
    }
    
    public int nextCity(List probabilityList){
        double total = 0.0;
        for (int i = 0; i < probabilityList.getSize(); i++) {
            double num = (double) probabilityList.get(i);
            total += num;
        }

        // Genera un número aleatorio entre 0 y la suma total de las probabilidades
        Random random = new Random();
        double randomNumber = random.nextDouble() * total;

        // Elige al ganador basado en el número aleatorio generado
        double counter = 0.0;
        String ganador = null;
        for (int i = 0; i < probabilityList.getSize(); i++) {
            double auxNum = (double) probabilityList.get(i);
            counter += auxNum;
            if (randomNumber <= counter) {
                return i;
            }
        }
        return -1;
    }
    
    public void incremento(Edge edge){
        double value = 0;
        for (int i = 0; i < edge.getAntsVisited(); i++) {
            value += 1/edge.getSize();
        }
        edge.setPheromones(value);
    }
    
    public void evaporacion(Edge edge){
        double pheromones = edge.getPheromones();
        double rho = array[2];
        double value = (1 - rho) * pheromones;
        edge.setPheromones(value);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startCycle = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nro de ciclo: 0");

        startCycle.setText("Iniciar Ciclo");
        startCycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCycleActionPerformed(evt);
            }
        });

        jButton2.setText("Ver grafo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");

        jLabel2.setText("Simulacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startCycle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(92, 92, 92))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(startCycle)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startCycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCycleActionPerformed
        List adyList = copyGraph.getAdyList();
        
        
        
        for (int i = 0; i < ants; i++) {
            Ant ant = antsList.get(i);
            ant.getVisited().delete();
            ant.visit(start);
        }
        
        //Recorrido por cada hormiga
        for (int i = 0; i < antsList.getSize(); i++) {
            Ant ant = antsList.get(i);
            
            
            List adyNode = new List();
            List probabilityList = new List();
            int node = ant.getVisited().getLast();
            
            //Mientras una hormiga no entre en calle ciega y no llegue al final
            while(node != end){
                
                //Obtener nodos adyacentes y probabilidades
                for (int j = 0; j < adyList.getSize(); j++) {
                    Edge edge = (Edge) adyList.get(j);
                    System.out.println(j + ") " + edge.getPheromones());
                    int idA = edge.getIdA();
                    int idB = edge.getIdB();

                    if(idA == node || idB == node){
                        if(idA == node){
                            if(!ant.getVisited().exist(idB)){
                                adyNode.append(idB);
                                probabilityList.append(probability(node, idB));                           
                            }
                        }
                        else if(idB == node){
                            if(!ant.getVisited().exist(idA)){
                                adyNode.append(idA);
                                probabilityList.append(probability(node, idA));                            
                            }
                        }
                    }

                }                         
                //Decidir siguiente nodo
                if(adyNode.isEmpty()){
                    break;
                }
                else{
                    int newNode = (int) adyNode.get(nextCity(probabilityList));
                    Edge auxEdge = copyGraph.getEdge(node, newNode);
                    auxEdge.addAnt();
                    
                    incremento(auxEdge);
                    evaporacion(auxEdge);
                    
                    node = newNode;
                    ant.visit(node);
                    
                    adyNode.delete();
                    probabilityList.delete();
                }
            }            
        }
    
            
      
    }//GEN-LAST:event_startCycleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        copyGraph.print();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulation(graph, copyGraph, filePath, cycles, ants, start, end, array).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton startCycle;
    // End of variables declaration//GEN-END:variables
}
