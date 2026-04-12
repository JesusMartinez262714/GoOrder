package GUI;

import Entitys.Sucursal;
import Entitys.SucursalesDisponibles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SeleccionSucursalesDisponibles extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_TARJETA = new Color(35, 35, 35);

    private Sucursal sucursalSeleccionada = null;
    private List<TarjetaSucursal> listaTarjetas = new ArrayList<>();
    private BotonConfirmar btnConfirmar;

    public SeleccionSucursalesDisponibles(SucursalesDisponibles sucursalesDisponiblesEntity) {
        setTitle("GoOrder - Sucursales");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JLabel lblTitulo = new JLabel("SUCURSALES DISPONIBLES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_NEON);
        headerPanel.add(lblTitulo);
        add(headerPanel, BorderLayout.NORTH);

        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(COLOR_FONDO);
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (Sucursal s : sucursalesDisponiblesEntity.getSucursalesDisponibles()) {
            TarjetaSucursal tarjeta = new TarjetaSucursal(s);
            listaTarjetas.add(tarjeta);
            panelLista.add(tarjeta);
            panelLista.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btnConfirmar = new BotonConfirmar("CONFIRMAR");
        btnConfirmar.setEnabled(false);
        footerPanel.add(btnConfirmar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    class TarjetaSucursal extends JPanel {
        private Sucursal sucursal;
        private boolean seleccionada = false;

        public TarjetaSucursal(Sucursal s) {
            this.sucursal = s;
            setLayout(new BorderLayout(15, 0));
            setBackground(COLOR_TARJETA);
            setBorder(BorderFactory.createLineBorder(COLOR_TARJETA, 2, true));
            setMaximumSize(new Dimension(350, 120));
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            ImageIcon icono = cargarImagenSucursal(s.getNombreImagen());
            JLabel lblIcono = new JLabel(icono);
            lblIcono.setPreferredSize(new Dimension(80, 80));
            add(lblIcono, BorderLayout.WEST);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(COLOR_TARJETA);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

            JLabel lblNombre = new JLabel(s.getNombre());
            lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
            lblNombre.setForeground(Color.WHITE);

            JLabel lblDireccion = new JLabel("Direccion: " + s.getCalle());
            lblDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
            lblDireccion.setForeground(Color.LIGHT_GRAY);

            JLabel lblColonia = new JLabel("Colonia: " + s.getColonia());
            lblColonia.setFont(new Font("Arial", Font.PLAIN, 12));
            lblColonia.setForeground(Color.LIGHT_GRAY);

            infoPanel.add(lblNombre);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(lblDireccion);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 2)));
            infoPanel.add(lblColonia);

            if (s.getDescripcion() != null && !s.getDescripcion().isEmpty()) {
                JLabel lblDescripcion = new JLabel( s.getDescripcion());
                lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
                lblDescripcion.setForeground(COLOR_NEON);
                infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                infoPanel.add(lblDescripcion);
            }

            add(infoPanel, BorderLayout.CENTER);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarEstaTarjeta();
                }
            });
        }

        private ImageIcon cargarImagenSucursal(String nombreImagen) {
            try {
                java.io.File archivoImagen = new java.io.File(".." + java.io.File.separator + "Resources" + java.io.File.separator + nombreImagen);

                if (!archivoImagen.exists()) {
                    archivoImagen = new java.io.File("Resources" + java.io.File.separator + nombreImagen);
                }

                if (!archivoImagen.exists()) {
                    throw new Exception();
                }

                ImageIcon originalIcon = new ImageIcon(archivoImagen.getAbsolutePath());
                Image img = originalIcon.getImage();
                Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                return new ImageIcon(newImg);

            } catch (Exception e) {
                return new ImageIcon();
            }
        }

        private void seleccionarEstaTarjeta() {
            for (TarjetaSucursal t : listaTarjetas) {
                t.setBorder(BorderFactory.createLineBorder(COLOR_TARJETA, 2, true));
                t.seleccionada = false;
            }
            this.setBorder(BorderFactory.createLineBorder(COLOR_NEON, 2, true));
            this.seleccionada = true;
            sucursalSeleccionada = this.sucursal;
            btnConfirmar.setEnabled(true);
            btnConfirmar.repaint();
        }
    }

    class BotonConfirmar extends JButton {
        public BotonConfirmar(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(300, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (isEnabled()) {
                g2.setColor(COLOR_NEON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(COLOR_TARJETA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
            }

            g2.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SucursalesDisponibles mockSucursales = new SucursalesDisponibles();
        mockSucursales.agregarSucursal(new Sucursal(1, "Cafe del compa miguel", "Colonia Diamante", "Casa blanca #456", "Sucursal mas cercana", "sucursal_casa.png"));
        mockSucursales.agregarSucursal(new Sucursal(2, "Cafe del compa miguel", "Colonia Esperanza", "Paris #765", " ", "sucursal_miguel.png"));
        mockSucursales.agregarSucursal(new Sucursal(3, "Cafe del compa miguel", "Colonia Libertad", "Boulevard Lincon", " ", "sucursal_cafesito.png"));

        SwingUtilities.invokeLater(() -> new SeleccionSucursalesDisponibles(mockSucursales).setVisible(true));
    }
}