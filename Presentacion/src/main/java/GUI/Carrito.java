package GUI;

import Control.Control;
import GoOrderDTO.ProductoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Carrito extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_TARJETA = new Color(35, 35, 35);

    private Control control;

    public Carrito(Control control) {
        this.control = control;

        setTitle("GoOrder - Carrito");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegresar.setForeground(COLOR_NEON); }
            public void mouseExited(MouseEvent e) { btnRegresar.setForeground(Color.LIGHT_GRAY); }
        });

        btnRegresar.addActionListener(e -> {
            control.mostrarProductosFORM();
        });

        JLabel lblTitulo = new JLabel("MI CARRITO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_NEON);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblEspacio = new JLabel("    ");
        lblEspacio.setPreferredSize(btnRegresar.getPreferredSize());

        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo, BorderLayout.CENTER);
        headerPanel.add(lblEspacio, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(COLOR_FONDO);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        double subtotalCalculado = 0.0;

        if (this.control != null && this.control.getCarrito() != null && !this.control.getCarrito().isEmpty()) {
            List<ProductoDTO> carrito = this.control.getCarrito();

            for (ProductoDTO producto : carrito) {
                String nombre = producto.getNombre();
                String precioFormateado = String.format("$%.2f", producto.getPrecio());
                subtotalCalculado += producto.getPrecio();

                contentPanel.add(crearPanelProducto(nombre, precioFormateado));
                contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }

            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            JSeparator sep = new JSeparator();
            sep.setForeground(Color.DARK_GRAY);
            sep.setMaximumSize(new Dimension(340, 5));
            contentPanel.add(sep);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            for (ProductoDTO producto : carrito) {
                contentPanel.add(crearFilaResumen(producto.getNombre(), String.format("$%.2f", producto.getPrecio()), false));
                contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }

            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            contentPanel.add(crearFilaResumen("Subtotal", String.format("$%.2f", subtotalCalculado), true));

        } else {
            JLabel lblVacio = new JLabel("Tu carrito está vacío.");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 16));
            lblVacio.setForeground(Color.GRAY);
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
            contentPanel.add(lblVacio);
        }

        contentPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(COLOR_FONDO);
        scrollPane.getViewport().setBackground(COLOR_FONDO);

        add(scrollPane, BorderLayout.CENTER);


        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnEntrega = new BotonNeon("FORMA DE ENTREGA");

        if (subtotalCalculado == 0.0) {
            btnEntrega.setEnabled(false);
        }

        btnEntrega.addActionListener(e -> {
            if (control != null) control.mostrarSeleccionMetodoEntrega();
        });

        footerPanel.add(btnEntrega);
        add(footerPanel, BorderLayout.SOUTH);
    }



    private JPanel crearPanelProducto(String nombre, String precio) {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 10));
        panelPrincipal.setBackground(COLOR_TARJETA);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_TARJETA, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelPrincipal.setMaximumSize(new Dimension(340, 100));

        JLabel lblImagen = new JLabel(" IMG");
        lblImagen.setForeground(Color.GRAY);
        lblImagen.setPreferredSize(new Dimension(70, 70));
        lblImagen.setOpaque(true);
        lblImagen.setBackground(new Color(50, 50, 50));
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(COLOR_TARJETA);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);

        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPrecio.setForeground(COLOR_NEON);

        JLabel lblBasura = new JLabel("🗑");
        lblBasura.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblBasura.setForeground(Color.GRAY);
        lblBasura.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBasura.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { lblBasura.setForeground(new Color(255, 80, 80)); }
            public void mouseExited(MouseEvent e) { lblBasura.setForeground(Color.GRAY); }
        });

        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblPrecio);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblBasura);

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        panelControles.setBackground(COLOR_TARJETA);

        JLabel lblMenos = new JLabel("—");
        lblMenos.setFont(new Font("Arial", Font.BOLD, 16));
        lblMenos.setForeground(Color.WHITE);
        lblMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCantidad = new JLabel("1");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 16));
        lblCantidad.setForeground(Color.WHITE);

        JLabel lblMas = new JLabel("+");
        lblMas.setFont(new Font("Arial", Font.BOLD, 18));
        lblMas.setForeground(COLOR_NEON);
        lblMas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelControles.add(lblMenos);
        panelControles.add(lblCantidad);
        panelControles.add(lblMas);

        panelPrincipal.add(lblImagen, BorderLayout.WEST);
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);
        panelPrincipal.add(panelControles, BorderLayout.EAST);

        return panelPrincipal;
    }

    private JPanel crearFilaResumen(String textoIzquierda, String textoDerecha, boolean esTotal) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setMaximumSize(new Dimension(340, 25));

        Font fuente = esTotal ? new Font("Arial", Font.BOLD, 18) : new Font("Arial", Font.PLAIN, 16);
        Color colorTexto = esTotal ? COLOR_NEON : Color.WHITE;

        JLabel lblIzq = new JLabel(textoIzquierda);
        lblIzq.setFont(fuente);
        lblIzq.setForeground(colorTexto);

        JLabel lblDer = new JLabel(textoDerecha);
        lblDer.setFont(fuente);
        lblDer.setForeground(colorTexto);

        panel.add(lblIzq, BorderLayout.WEST);
        panel.add(lblDer, BorderLayout.EAST);

        return panel;
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
                public void mouseEntered(MouseEvent e) { if(isEnabled()) { over = true; repaint(); } }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (!isEnabled()) {
                g2.setColor(COLOR_TARJETA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
            } else {
                if (over) g2.setColor(COLOR_NEON);
                else g2.setColor(COLOR_TARJETA);
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
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }


}