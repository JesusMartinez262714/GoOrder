package GUI;

import Control.Control;
import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.NegocioException;

public class CodigoDescuentoFORM extends JFrame {

    private Control control;
    private String descuentoActual = "";

    private JTextField txtCodigo;
    private JLabel lblCantidadDescuento;

    public CodigoDescuentoFORM(Control control) {
        this.control = control;

        setTitle("GoOrder - Código de Descuento");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegresar.setForeground(control.COLOR_NEON); }
            public void mouseExited(MouseEvent e) { btnRegresar.setForeground(Color.LIGHT_GRAY); }
        });

        btnRegresar.addActionListener(e -> {
            control.mostrarTotalPrecioProductos();
        });

        JLabel lblTitulo = new JLabel("CÓDIGO DESCUENTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(control.COLOR_NEON);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblEspacio = new JLabel("    ");
        lblEspacio.setPreferredSize(btnRegresar.getPreferredSize());

        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo, BorderLayout.CENTER);
        headerPanel.add(lblEspacio, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(control.COLOR_FONDO);
        contentPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        JPanel panelInput = new JPanel(new BorderLayout(10, 0));
        panelInput.setBackground(control.COLOR_FONDO);
        panelInput.setMaximumSize(new Dimension(340, 45));

        txtCodigo = new JTextField();
        txtCodigo.setBackground(control.COLOR_TARJETA);
        txtCodigo.setForeground(Color.WHITE);
        txtCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        txtCodigo.setCaretColor(control.COLOR_NEON);
        txtCodigo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setBackground(control.COLOR_TARJETA);
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerificar.setFocusPainted(false);
        btnVerificar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelInput.add(txtCodigo, BorderLayout.CENTER);
        panelInput.add(btnVerificar, BorderLayout.EAST);

        contentPanel.add(panelInput);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel panelResumen = new JPanel();
        panelResumen.setLayout(new BoxLayout(panelResumen, BoxLayout.Y_AXIS));
        panelResumen.setBackground(control.COLOR_TARJETA);
        panelResumen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(control.COLOR_BOTON, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        Font fontNormal = new Font("Arial", Font.PLAIN, 15);
        Font fontBold = new Font("Arial", Font.BOLD, 16);

        try {
            CarritoDTO miCarrito = control.getCarrito();

            if (miCarrito != null && miCarrito.getProductos() != null) {
                
                for (ProductoSeleccionadoDTO p : miCarrito.getProductos()) {
                    panelResumen.add(crearFilaTexto(
                            p.getNombre() + " x" + p.getCantidad(), 
                            String.format("$%.2f", p.getImporte()), 
                            fontNormal, 
                            Color.WHITE
                    ));
                }

                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));
                
                panelResumen.add(crearFilaTexto("Subtotal", String.format("$%.2f", miCarrito.getSubTotal()), fontNormal, Color.WHITE));
                
                if (miCarrito.getDescuento() > 0) {
                    panelResumen.add(crearFilaTexto("Descuento", String.format("-$%.2f", miCarrito.getDescuento()), fontNormal, control.COLOR_NEON));
                }

                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));
                JSeparator sep = new JSeparator();
                sep.setForeground(Color.DARK_GRAY);
                panelResumen.add(sep);
                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));

                panelResumen.add(crearFilaTexto("TOTAL", String.format("$%.2f", miCarrito.getTotal()), fontBold, Color.WHITE));
            }
        } catch (Exception ex) {
            Logger.getLogger(CodigoDescuentoFORM.class.getName()).log(Level.SEVERE, "Error al cargar resumen", ex);
        }

        contentPanel.add(panelResumen);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel lblTextoDescuento = new JLabel("DESCUENTO POR APLICAR:");
        lblTextoDescuento.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTextoDescuento.setForeground(Color.LIGHT_GRAY);
        lblTextoDescuento.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblCantidadDescuento = new JLabel("$0.00 MXN");
        lblCantidadDescuento.setFont(new Font("Arial", Font.BOLD, 36));
        lblCantidadDescuento.setForeground(control.COLOR_NEON);
        lblCantidadDescuento.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(lblTextoDescuento);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblCantidadDescuento);

        contentPanel.add(Box.createVerticalGlue());
        add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnAplicar = new BotonNeon("CONFIRMAR");

        btnAplicar.addActionListener(e -> accionAplicarDescuento());

        footerPanel.add(btnAplicar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void accionAplicarDescuento() {
        String textoIngresado = txtCodigo.getText().trim();
        if (textoIngresado.isEmpty()) {
            control.mostrarCodigoDescuentoRechazado();
            return;
        }
        
        try {
            control.AplicarDescuento(textoIngresado); 
            
            JOptionPane.showMessageDialog(this, "Descuento aplicado al ticket.");
            control.mostrarTotalPrecioProductos();
            
        } catch (NegocioException ex) {
            Logger.getLogger(CodigoDescuentoFORM.class.getName()).log(Level.SEVERE, "Error al aplicar descuento", ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Código Inválido", JOptionPane.WARNING_MESSAGE);
            txtCodigo.setText(""); 
        }
    }

    private JPanel crearFilaTexto(String textoIzq, String textoDer, Font fuente, Color colorTexto) {
        JPanel panelFila = new JPanel(new BorderLayout());
        panelFila.setOpaque(false);

        JLabel lblIzq = new JLabel(textoIzq);
        lblIzq.setFont(fuente);
        lblIzq.setForeground(colorTexto);

        JLabel lblDer = new JLabel(textoDer);
        lblDer.setFont(fuente);
        lblDer.setForeground(colorTexto);

        panelFila.add(lblIzq, BorderLayout.WEST);
        panelFila.add(lblDer, BorderLayout.EAST);
        return panelFila;
    }

    class BotonNeon extends JButton {
        private boolean over = false;

        public BotonNeon(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(340, 50));
            setMaximumSize(new Dimension(340, 50));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) g2.setColor(control.COLOR_NEON);
            else g2.setColor(control.COLOR_TARJETA);

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setFont(new Font("Arial", Font.BOLD, 14));
            if (over) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(2, 2, getWidth()-5, getHeight()-5, 15, 15);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }
}