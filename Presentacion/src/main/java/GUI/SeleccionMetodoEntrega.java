package GUI;

import javax.swing.*;
import java.awt.*;
import Control.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeleccionMetodoEntrega extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_BOTON = new Color(35, 35, 35);

    public SeleccionMetodoEntrega(Control control) {
        setTitle("Seleccionar Metodo de Entrega");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

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
            try {
                control.mostrarCarrito();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al regresar: " + ex.getMessage());
            }
        });
        headerPanel.add(btnRegresar, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(COLOR_FONDO);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 40, 30));

        JLabel lblTitulo = new JLabel("GO-ORDER");
        lblTitulo.setFont(new Font("Arial", Font.ITALIC, 32));
        lblTitulo.setForeground(COLOR_NEON);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("ELIGE TU METODO DE ENTREGA");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(lblTitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(lblSubtitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        BotonNeon btnSucursal = new BotonNeon("PICK UP");
        BotonNeon btnDomicilio = new BotonNeon("A DOMICILIO");

        btnSucursal.addActionListener(e -> control.mostrarSeleccionSucursalesDisponibles());
        btnDomicilio.addActionListener(e -> control.mostrarDomicilioFORM());

        contentPanel.add(btnSucursal);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(btnDomicilio);

        contentPanel.add(Box.createVerticalGlue());

        ImageIcon icono = control.obtenerImagen("ImagenMetodoEntrega.png");
        Image newImg = icono.getImage().getScaledInstance(340, 160, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(newImg));
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblImagen);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 90)));
        add(contentPanel);
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
            setMaximumSize(new Dimension(340, 100));
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
                g2.setColor(COLOR_NEON);
            } else {
                g2.setColor(COLOR_BOTON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

            g2.setFont(new Font("Arial", Font.BOLD, 18));
            if (over) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(3, 3, getWidth()-7, getHeight()-7, 20, 20);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }
}