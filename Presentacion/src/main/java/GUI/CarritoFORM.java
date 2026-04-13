/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI;

import Control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class CarritoFORM extends JFrame {

    private final Color COLOR_FONDO_VERDE = new Color(81, 240, 153); 
    private final Color COLOR_TEXTO = Color.BLACK;
    private final Color COLOR_BOTON_OSCURO = new Color(40, 40, 40);

    public CarritoFORM(Control control) {
        setTitle("GoOrder - Carrito");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(COLOR_FONDO_VERDE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        
        contentPanel.add(crearPanelProducto("Bagel", "$70.00"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        contentPanel.add(crearPanelProducto("Frappe", "$50.00"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        contentPanel.add(crearFilaResumen("Bagel", "$70.00"));
        contentPanel.add(crearFilaResumen("Frappe", "$50.00"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        contentPanel.add(crearFilaResumen("Subtotal", "$120.00"));

        contentPanel.add(Box.createVerticalGlue());
        add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(COLOR_FONDO_VERDE);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonOscuro btnEntrega = new BotonOscuro("FORMA DE ENTREGA");
        btnEntrega.setPreferredSize(new Dimension(340, 50));
        btnEntrega.setMaximumSize(new Dimension(340, 50));
        
        
        footerPanel.add(btnEntrega);
        add(footerPanel, BorderLayout.SOUTH);
    }

    
    private JPanel crearPanelProducto(String nombre, String precio) {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 10));
        panelPrincipal.setBackground(COLOR_FONDO_VERDE);
        panelPrincipal.setMaximumSize(new Dimension(340, 100));

        JLabel lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(90, 90));
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.WHITE); 
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(COLOR_FONDO_VERDE);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setForeground(COLOR_TEXTO);

        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPrecio.setForeground(COLOR_TEXTO);

        JLabel lblBasura = new JLabel("🗑"); 
        lblBasura.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        lblBasura.setForeground(COLOR_TEXTO);
        lblBasura.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelInfo.add(lblNombre);
        panelInfo.add(lblPrecio);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(lblBasura);

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));
        panelControles.setBackground(COLOR_FONDO_VERDE);

        JLabel lblMenos = new JLabel("—");
        lblMenos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCantidad = new JLabel("1");
        lblCantidad.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblMas = new JLabel("+");
        lblMas.setFont(new Font("Arial", Font.PLAIN, 20));
        lblMas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelControles.add(lblMenos);
        panelControles.add(lblCantidad);
        panelControles.add(lblMas);

        panelPrincipal.add(lblImagen, BorderLayout.WEST);
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);
        panelPrincipal.add(panelControles, BorderLayout.EAST);

        return panelPrincipal;
    }

    private JPanel crearFilaResumen(String textoIzquierda, String textoDerecha) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_VERDE);
        panel.setMaximumSize(new Dimension(340, 25));

        JLabel lblIzq = new JLabel(textoIzquierda);
        lblIzq.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIzq.setForeground(COLOR_TEXTO);

        JLabel lblDer = new JLabel(textoDerecha);
        lblDer.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDer.setForeground(COLOR_TEXTO);

        panel.add(lblIzq, BorderLayout.WEST);
        panel.add(lblDer, BorderLayout.EAST);

        return panel;
    }

    class BotonOscuro extends JButton {
        private boolean over = false;

        public BotonOscuro(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.CENTER_ALIGNMENT);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(new Color(60, 60, 60)); 
            } else {
                g2.setColor(COLOR_BOTON_OSCURO);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); 

            g2.setFont(new Font("Arial", Font.PLAIN, 14));
            g2.setColor(Color.WHITE);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }
}
