/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import controladores.AccesoriosJpaController;
import entidades.Accesorios;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julen García
 */
public class menuAccesorios extends javax.swing.JFrame {

    private AccesoriosJpaController ac = new AccesoriosJpaController();

    /**
     * Creates new form menuAccesorios
     */
    public menuAccesorios() {
        initComponents();
        cargarDatosJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btDelAcce = new javax.swing.JButton();
        btAñadirAcce = new javax.swing.JButton();
        btVolver = new javax.swing.JButton();
        btModAcce = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contAcce = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btDelAcce.setBackground(new java.awt.Color(255, 0, 0));
        btDelAcce.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btDelAcce.setForeground(new java.awt.Color(0, 0, 0));
        btDelAcce.setText("Eliminar Accesorio");
        btDelAcce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelAcce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelAcceActionPerformed(evt);
            }
        });
        getContentPane().add(btDelAcce, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 35));

        btAñadirAcce.setBackground(new java.awt.Color(255, 0, 0));
        btAñadirAcce.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btAñadirAcce.setForeground(new java.awt.Color(0, 0, 0));
        btAñadirAcce.setText("Añadir Accesorio");
        btAñadirAcce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAñadirAcce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAñadirAcceActionPerformed(evt);
            }
        });
        getContentPane().add(btAñadirAcce, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, 35));

        btVolver.setBackground(new java.awt.Color(255, 0, 0));
        btVolver.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btVolver.setForeground(new java.awt.Color(0, 0, 0));
        btVolver.setText("Volver");
        btVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 160, 35));

        btModAcce.setBackground(new java.awt.Color(255, 0, 0));
        btModAcce.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btModAcce.setForeground(new java.awt.Color(0, 0, 0));
        btModAcce.setText("Modificar Accesorio");
        btModAcce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModAcce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModAcceActionPerformed(evt);
            }
        });
        getContentPane().add(btModAcce, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 35));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Accesorios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 220, 70));

        contAcce.setBackground(new java.awt.Color(255, 51, 51));
        contAcce.setForeground(new java.awt.Color(0, 0, 0));
        contAcce.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        contAcce.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jScrollPane1.setViewportView(contAcce);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 430, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menuAcce.PNG"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDelAcceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelAcceActionPerformed
        // TODO add your handling code here:
        String idStr = JOptionPane.showInputDialog("Introduce la id del acceosorio que quieras borrar.");
        try {
            int id = Integer.parseInt(idStr);

            if (id < 1) {
                JOptionPane.showMessageDialog(null, "ID inválida.");
                return;
            }

            ac.delete(id);

            cargarDatosJTable();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");

        }
    }//GEN-LAST:event_btDelAcceActionPerformed

    private void btAñadirAcceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAñadirAcceActionPerformed
        // TODO add your handling code here:
        añadirAcce acce = new añadirAcce(this, true);
        acce.setLocationRelativeTo(null);
        acce.setResizable(false);
        acce.setVisible(true);

        cargarDatosJTable();
    }//GEN-LAST:event_btAñadirAcceActionPerformed

    private void btModAcceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModAcceActionPerformed
        // TODO add your handling code here:
        if (contAcce.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "No has seleccionado ningún registro");
        } else {

            modAccesorio acce = new modAccesorio(this, true);
            acce.setLocationRelativeTo(null);
            acce.setResizable(false);
            acce.setVisible(true);

            cargarDatosJTable();
        }
    }//GEN-LAST:event_btModAcceActionPerformed

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Opciones opciones = new Opciones();
        opciones.setLocationRelativeTo(null);
        opciones.setResizable(false);
        opciones.setVisible(true);

    }//GEN-LAST:event_btVolverActionPerformed

    /**
     * @param args the command line arguments
     */
   // Carga la lista de Accesorios en la tabla de la interfaz
    private void cargarDatosJTable() {

        List<Accesorios> accesorios = ac.findAll();
        String[] columnas = {"IdAccesorio", "Nombre", "Descripcion"};
        DefaultTableModel columnModel = new DefaultTableModel(columnas, 0);

        int numColumnas = columnModel.getColumnCount();

        Object[] fila = new Object[numColumnas];

        for (Accesorios accesorio : accesorios) {
            fila[0] = accesorio.getIdAccesorio();
            fila[1] = accesorio.getNombre();
            fila[2] = accesorio.getDescripcion();

            columnModel.addRow(fila);
        }
        contAcce.setModel(columnModel);

    }

    public JTable getJTable() {
        return this.contAcce;
    }

    public AccesoriosJpaController getAc() {
        return ac;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAñadirAcce;
    private javax.swing.JButton btDelAcce;
    private javax.swing.JButton btModAcce;
    private javax.swing.JButton btVolver;
    private javax.swing.JTable contAcce;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
