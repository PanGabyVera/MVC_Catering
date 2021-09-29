/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;


public class Vista_MenuPrincipal extends javax.swing.JFrame {

   
    public Vista_MenuPrincipal() {
        initComponents();
    }

    public JMenu getBtnmem() {
        return btnmem;
    }

    public void setBtnmem(JMenu btnmem) {
        this.btnmem = btnmem;
    }

    public JMenu getBtnmpe() {
        return btnmpe;
    }

    public void setBtnmpe(JMenu btnmpe) {
        this.btnmpe = btnmpe;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPopupMenu.Separator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JPopupMenu.Separator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JToolBar getjToolBar1() {
        return jToolBar1;
    }

    public void setjToolBar1(JToolBar jToolBar1) {
        this.jToolBar1 = jToolBar1;
    }

    public JLabel getJblMensaje() {
        return jblMensaje;
    }

    public void setJblMensaje(JLabel jblMensaje) {
        this.jblMensaje = jblMensaje;
    }

    public JMenuItem getJmeper() {
        return jmeper;
    }

    public void setJmeper(JMenuItem jmeper) {
        this.jmeper = jmeper;
    }

    public JMenuItem getJmerepe() {
        return jmerepe;
    }

    public void setJmerepe(JMenuItem jmerepe) {
        this.jmerepe = jmerepe;
    }

    public JMenuItem getJmesape() {
        return jmesape;
    }

    public void setJmesape(JMenuItem jmesape) {
        this.jmesape = jmesape;
    }

    public JButton getTbMan() {
        return tbMan;
    }

    public void setTbMan(JButton tbMan) {
        this.tbMan = tbMan;
    }

    public JDesktopPane getJdeskprin() {
        return jdeskprin;
    }

    public void setJdeskprin(JDesktopPane jdeskprin) {
        this.jdeskprin = jdeskprin;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        tbMan = new javax.swing.JButton();
        tbimp = new javax.swing.JButton();
        jdeskprin = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jblMensaje = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnmpe = new javax.swing.JMenu();
        jmeper = new javax.swing.JMenuItem();
        jmerepe = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmesape = new javax.swing.JMenuItem();
        btnmem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        tbMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/png/003-contract.png"))); // NOI18N
        tbMan.setToolTipText("Mantenimiento");
        tbMan.setFocusable(false);
        tbMan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbMan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(tbMan);

        tbimp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/png/015-printer.png"))); // NOI18N
        tbimp.setToolTipText("Imprimir");
        tbimp.setFocusable(false);
        tbimp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbimp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(tbimp);

        javax.swing.GroupLayout jdeskprinLayout = new javax.swing.GroupLayout(jdeskprin);
        jdeskprin.setLayout(jdeskprinLayout);
        jdeskprinLayout.setHorizontalGroup(
            jdeskprinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        jdeskprinLayout.setVerticalGroup(
            jdeskprinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jblMensaje.setText("Version Catering  1.0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jblMensaje))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnmpe.setText("Persona");

        jmeper.setText("Crud Persona");
        btnmpe.add(jmeper);

        jmerepe.setText("Reporte Persona");
        btnmpe.add(jmerepe);
        btnmpe.add(jSeparator1);

        jmesape.setText("Salir");
        btnmpe.add(jmesape);

        jMenuBar1.add(btnmpe);

        btnmem.setText("Empelado");
        jMenuBar1.add(btnmem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jdeskprin)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdeskprin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnmem;
    private javax.swing.JMenu btnmpe;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel jblMensaje;
    private javax.swing.JDesktopPane jdeskprin;
    private javax.swing.JMenuItem jmeper;
    private javax.swing.JMenuItem jmerepe;
    private javax.swing.JMenuItem jmesape;
    private javax.swing.JButton tbMan;
    private javax.swing.JButton tbimp;
    // End of variables declaration//GEN-END:variables
}
