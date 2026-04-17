
package GUI;

import Control.Control;
import GoOrderDTO.ProductoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductosFORM extends JFrame {
    private final String rutaImagenes = "Resources/";
    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_TARJETA = new Color(30, 30, 30); 
    private final Color COLOR_BOTON = new Color(35, 35, 35);

    private final Control control;

    public ProductosFORM(Control control) {
        this.control = control;

        setTitle("GoOrder - Menú");
        setSize(400, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);

        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pnlNorte.setBackground(COLOR_FONDO);
        
        JTextField txtBuscar = new JTextField(20);
        txtBuscar.setPreferredSize(new Dimension(300, 35));
        txtBuscar.setBackground(COLOR_TARJETA);
        txtBuscar.setForeground(Color.WHITE);
        txtBuscar.setCaretColor(COLOR_NEON);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_NEON, 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtBuscar.setText("Buscar producto...");
        pnlNorte.add(txtBuscar);

        JPanel pnlGrid = new JPanel(new GridLayout(0, 2, 15, 15));
        pnlGrid.setBackground(COLOR_FONDO);
        pnlGrid.setBorder(new EmptyBorder(10, 15, 10, 15));

        for (ProductoDTO producto : control.obtenerListaProductos()){
            pnlGrid.add(crearTarjetaProducto(producto));
        }

        JScrollPane scrollPane = new JScrollPane(pnlGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(COLOR_FONDO);

        JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pnlSur.setBackground(COLOR_FONDO);
        
        BotonNeon btnCarrito = new BotonNeon("VER CARRITO");
        btnCarrito.setPreferredSize(new Dimension(350, 50));
        btnCarrito.addActionListener(e -> control.mostrarCarrito());
        pnlSur.add(btnCarrito);

        add(pnlNorte, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);
    }

    private JPanel crearTarjetaProducto(ProductoDTO producto) {
        String rutaImagen = rutaImagenes + producto.getImagen();
        System.out.println(rutaImagen);
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(COLOR_TARJETA);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1, true));

        JLabel lblImagen = new JLabel("..", SwingConstants.CENTER);
        lblImagen.setOpaque(true);
        lblImagen.setBackground(new Color(45, 45, 45));
        lblImagen.setForeground(Color.GRAY);
        lblImagen.setPreferredSize(new Dimension(150, 100));

        try{
            ImageIcon iconoProducto = new ImageIcon(rutaImagen);
            if(iconoProducto.getIconWidth() != -1){
                Image img = iconoProducto.getImage();
                Image imgEscalada = img.getScaledInstance(150,100,Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imgEscalada));
                lblImagen.setText("");
            }else{
                lblImagen.setText("SinFoto");
            }
        }catch (Exception e) {
            lblImagen.setText(e.getMessage());
        }
        tarjeta.add(lblImagen, BorderLayout.NORTH);

        JPanel pnlInfo = new JPanel(new GridBagLayout());
        pnlInfo.setBackground(COLOR_TARJETA);
        pnlInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNombre.setForeground(Color.WHITE);
        
        JLabel lblPrecio = new JLabel(String.valueOf(producto.getPrecio()));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(COLOR_NEON);

        BotonAgregar btnAdd = new BotonAgregar(producto);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        pnlInfo.add(lblNombre, gbc);
        
        gbc.gridy = 1; 
        pnlInfo.add(lblPrecio, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 0; gbc.anchor = GridBagConstraints.CENTER;
        pnlInfo.add(btnAdd, gbc);

        tarjeta.add(pnlInfo, BorderLayout.CENTER);

        return tarjeta;
    }

    class BotonAgregar extends JButton {
        private boolean over = false;

        public BotonAgregar(ProductoDTO producto) {
            super("+");
            setFont(new Font("Arial", Font.BOLD, 22));
            setMargin(new Insets(0, 0, 0, 0));
            setForeground(COLOR_NEON);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(35, 35));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
            addActionListener(e -> control.agregarProducto(producto));

        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(COLOR_NEON);
                setForeground(Color.BLACK);
            } else {
                g2.setColor(COLOR_BOTON);
                setForeground(COLOR_NEON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            
            super.paintComponent(g);
            g2.dispose();
        }
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
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

            g2.setFont(getFont());

            if (over) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(COLOR_NEON);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 3;

            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }
}