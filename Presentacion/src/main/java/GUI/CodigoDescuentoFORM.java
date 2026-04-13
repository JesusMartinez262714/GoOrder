/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Control.Control;
import GoOrderDTO.ResultadoCanjeoDTO;
import org.example.CanjeoCodigoServicio;
import org.example.VerificarCodigoCasoDeUso;

/**
 *
 * @author daren
 */
public class CodigoDescuentoFORM extends javax.swing.JFrame {

    private Control control;
    private VerificarCodigoCasoDeUso casoDeUso;
    private double descuentoActual = 0;
    
    public CodigoDescuentoFORM(Control control) {
        this.control = control;

        casoDeUso = new VerificarCodigoCasoDeUso(
                new CanjeoCodigoServicio()
        );

        initComponents();
        eventos();
    }

    private void eventos() {

        btnCanjearCodigo.addActionListener(e -> verificarCodigo());

        btnAplicarDescuento.addActionListener(e -> aplicarDescuento());

        btnCancelar.addActionListener(e -> this.dispose());
    }
    
    private void verificarCodigo() {

        String codigo = txtCodigoDescuento.getText();

        ResultadoCanjeoDTO resultado = casoDeUso.ejecutar(codigo);

        if (resultado.isValido()) {

            descuentoActual = resultado.getDescuento();

            lblCantidadDescuento.setText("-" + descuentoActual);

        } else {

            descuentoActual = 0;
            lblCantidadDescuento.setText("0.00");

            javax.swing.JOptionPane.showMessageDialog(this, "Código inválido");
        }
    }
    
    private void aplicarDescuento() {

        control.setDescuento(descuentoActual);

        javax.swing.JOptionPane.showMessageDialog(this,
                "Descuento aplicado correctamente");

        this.dispose();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCodigoDescuento = new javax.swing.JPanel();
        txtCodigoDescuento = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnCanjearCodigo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAplicarDescuento = new javax.swing.JButton();
        lblDescuentoAAplicar = new javax.swing.JLabel();
        lblCantidadDescuento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

        pnCodigoDescuento.setBackground(new java.awt.Color(0, 204, 204));

        txtCodigoDescuento.setText("Ingresa el código de descuento...");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(60, 60, 60));
        lblTitulo.setText("CÓDIGO DE DESCUENTO");

        btnCanjearCodigo.setText("Canjear Código");

        btnCancelar.setText("Cancelar");

        btnAplicarDescuento.setText("Aplicar Descuento");

        lblDescuentoAAplicar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDescuentoAAplicar.setForeground(new java.awt.Color(60, 60, 60));
        lblDescuentoAAplicar.setText("Descuento a aplicar: ");

        lblCantidadDescuento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCantidadDescuento.setForeground(new java.awt.Color(60, 60, 60));
        lblCantidadDescuento.setText("00.00");

        javax.swing.GroupLayout pnCodigoDescuentoLayout = new javax.swing.GroupLayout(pnCodigoDescuento);
        pnCodigoDescuento.setLayout(pnCodigoDescuentoLayout);
        pnCodigoDescuentoLayout.setHorizontalGroup(
            pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCodigoDescuentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCodigoDescuentoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAplicarDescuento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(pnCodigoDescuentoLayout.createSequentialGroup()
                        .addGroup(pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(btnCanjearCodigo)
                            .addGroup(pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCodigoDescuentoLayout.createSequentialGroup()
                                    .addComponent(lblDescuentoAAplicar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCantidadDescuento))
                                .addComponent(txtCodigoDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 278, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnCodigoDescuentoLayout.setVerticalGroup(
            pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCodigoDescuentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCanjearCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addGroup(pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescuentoAAplicar)
                    .addComponent(lblCantidadDescuento))
                .addGap(61, 61, 61)
                .addGroup(pnCodigoDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAplicarDescuento))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCodigoDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCodigoDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicarDescuento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCanjearCodigo;
    private javax.swing.JLabel lblCantidadDescuento;
    private javax.swing.JLabel lblDescuentoAAplicar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnCodigoDescuento;
    private javax.swing.JTextField txtCodigoDescuento;
    // End of variables declaration//GEN-END:variables
}
