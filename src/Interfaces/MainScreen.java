/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Classes.Util;
import EDD.GraphP;
import javax.swing.JOptionPane;

/**
 *
 * @author Mafer
 */
public class MainScreen extends javax.swing.JFrame {
    private static GraphP graph;
    private static String filePath;
    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        this.graph = new GraphP();
        this.filePath = "";
    }
    
    public MainScreen(GraphP graph, String filePath) {
        initComponents();
        this.graph = graph;
        this.filePath = filePath;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        startSimulation = new javax.swing.JButton();
        print = new javax.swing.JButton();
        save = new javax.swing.JButton();
        addCity = new javax.swing.JButton();
        removeCity = new javax.swing.JButton();
        addEdge = new javax.swing.JButton();
        removeEdge = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startSimulation.setText("Iniciar simulacion");
        startSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulationActionPerformed(evt);
            }
        });

        print.setText("Ver grafo");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        save.setText("Guardar grafo");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        addCity.setText("Agregar ciudad");
        addCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCityActionPerformed(evt);
            }
        });

        removeCity.setText("Eliminar ciudad");
        removeCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCityActionPerformed(evt);
            }
        });

        addEdge.setText("Agregar relacion");
        addEdge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEdgeActionPerformed(evt);
            }
        });

        removeEdge.setText("Eliminar relacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(print, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addEdge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeEdge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(startSimulation)
                .addGap(18, 18, 18)
                .addComponent(print)
                .addGap(18, 18, 18)
                .addComponent(save)
                .addGap(18, 18, 18)
                .addComponent(addCity)
                .addGap(18, 18, 18)
                .addComponent(removeCity)
                .addGap(18, 18, 18)
                .addComponent(addEdge)
                .addGap(18, 18, 18)
                .addComponent(removeEdge)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(filePath.isBlank()){
            filePath = Util.filePath();
        }
        Util.save(graph, filePath);
    }//GEN-LAST:event_saveActionPerformed

    private void addCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCityActionPerformed
        if(graph.getSize() >= 20){
            JOptionPane.showMessageDialog(null, "Error: se puede agregar un maximo de 20 ciudades");
        }
        else{
            AddCity ac = new AddCity(graph, filePath);
            ac.setVisible(true);
            this.dispose();            
        }
        
    }//GEN-LAST:event_addCityActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        graph.print();   
    }//GEN-LAST:event_printActionPerformed

    private void removeCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCityActionPerformed
        // TODO add your handling code here:
        if(graph.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error: no hay ciudades en el grafo");
        }
        else{
            RemoveCity rc = new RemoveCity(graph, filePath);
            rc.setVisible(true);
            this.dispose();            
        }
    }//GEN-LAST:event_removeCityActionPerformed

    private void startSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimulationActionPerformed
        // TODO add your handling code here:
        if(graph.getSize() < 4){
            JOptionPane.showMessageDialog(null, "Error: es necesario un minimo de 4 ciudades para iniciar la simulacion");
        }
        else{
            StartSimulation ss = new StartSimulation(graph, graph.copyGraph(), filePath);
            this.dispose();
            ss.setVisible(true);           
        }
    }//GEN-LAST:event_startSimulationActionPerformed

    private void addEdgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEdgeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_addEdgeActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen(graph, filePath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCity;
    private javax.swing.JButton addEdge;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton print;
    private javax.swing.JButton removeCity;
    private javax.swing.JButton removeEdge;
    private javax.swing.JButton save;
    private javax.swing.JButton startSimulation;
    // End of variables declaration//GEN-END:variables
}
